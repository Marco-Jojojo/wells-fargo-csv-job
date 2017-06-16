package com.peiwc.billing.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.WFMamOpHDRTRLRRepository;
import com.peiwc.billing.domain.WFMamOpHDRTRLR;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

/**
 * manages master table process for creating next run of wells fargo csv
 * details.
 */
@Component("wfMamOpHDRTRLRProcess")
public class WFMamOpHDRTRLRProcess {

	@Autowired
	private WFMamOpHDRTRLRRepository wfMamOpHDRTRLRRepository;

	@Autowired
	private ProcessManagerCheck processManagerCheck;

	private static final Logger LOGGER = Logger.getLogger(WFMamOpHDRTRLRProcess.class);

	@Value("${cifs.user.pass}")
	private String user;

	@Value("${cifs.process.enabled}")
	private boolean processEnabled;

	/**
	 * saves next cycle and return generated object
	 *
	 * @param nextCycle
	 *            next cycle that is being run.
	 * @param currentDate
	 *            today's date.
	 * @return WFMamOpHDRTRLR saved instance
	 */
	@Transactional
	public WFMamOpHDRTRLR saveNextCycle(final int nextCycle, final Date currentDate) {
		final WFMamOpHDRTRLR wfMamOpHDRTRLR = new WFMamOpHDRTRLR();
		wfMamOpHDRTRLR.setCreationDate(currentDate);
		wfMamOpHDRTRLR.setCycleNumber(nextCycle);
		return this.wfMamOpHDRTRLRRepository.saveAndFlush(wfMamOpHDRTRLR);
	}

	/**
	 * saves the total of records processed in this run.
	 *
	 * @param nextCycle
	 *            cycle that is currently run for getting the CSV records and
	 *            saving them into the file.
	 * @param totalRecordCount
	 *            records that have been processed currently.
	 */
	public void saveTotalRecordsProcessed(final int nextCycle, final int totalRecordCount) {
		final WFMamOpHDRTRLR wfMamOpHDRTRLR = wfMamOpHDRTRLRRepository.findOne(nextCycle);
		wfMamOpHDRTRLR.setTotalRecordCount(totalRecordCount);
		this.wfMamOpHDRTRLRRepository.saveAndFlush(wfMamOpHDRTRLR);
	}

	/**
	 * saves current Error Message to master table for current cycle.
	 *
	 * @param cycleNumber
	 *            current cycle number run by the application.
	 * @param errorMessage
	 *            critical error caught during execution.
	 */
	public void saveErrorMessage(final int cycleNumber, final String errorMessage) {
		final WFMamOpHDRTRLR wfMamOpHDRTRLR = wfMamOpHDRTRLRRepository.findOne(cycleNumber);
		wfMamOpHDRTRLR.setErrorMessage(errorMessage);
		wfMamOpHDRTRLRRepository.saveAndFlush(wfMamOpHDRTRLR);
	}

	/**
	 * saves current state of project to master table.
	 *
	 * @param processState
	 *            indicates the current process of project.
	 * @param cycleNumber
	 *            current cycle number run by the application.
	 */
	public void setCurrentState(final ProcessState processState, final int cycleNumber) {
		final WFMamOpHDRTRLR wfMamOpHDRTRLR = wfMamOpHDRTRLRRepository.findOne(cycleNumber);
		WFMamOpHDRTRLRProcess.LOGGER.info("Process State is: " + processState.getName());
		wfMamOpHDRTRLR.setStatus(processState.getName());
		wfMamOpHDRTRLRRepository.saveAndFlush(wfMamOpHDRTRLR);
	}

	/**
	 * if process has already run for today, sets error as already run.
	 * 
	 * @return cycle number.
	 */
	public int setProcessAsAlreadyRunForToday() {
		final int lastCycleNumber = processManagerCheck.getLastCycleNumber();
		this.setCurrentState(ProcessState.ALREADY_RUN, lastCycleNumber);
		return lastCycleNumber;
	}

	/**
	 * moves csv generated file to an external location.
	 * 
	 * @param fileName
	 *            csv generated file
	 * @param fileNameLocation
	 *            external folder.
	 * @throws IOException
	 *             when file could not be written.
	 */
	public void moveGeneratedFileToExternalLocation(final String fileName, final String fileNameLocation)
			throws IOException {
		if (processEnabled) {
			final NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user);
			final String path = "smb://" + fileNameLocation + "/" + fileName;
			final SmbFile sFile = new SmbFile(path, auth);
			final SmbFileOutputStream sfos = new SmbFileOutputStream(sFile);
			final byte[] data = readAllBytes(fileName);
			sfos.write(data);
			sfos.close();
		}
	}

	private byte[] readAllBytes(final String fileName) {
		final File file = new File(fileName);
		final byte[] bytesArray = new byte[(int) file.length()];
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			fis.read(bytesArray);
		} catch (final IOException ex) {
			WFMamOpHDRTRLRProcess.LOGGER.error(ex, ex);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (final Exception ex) {
					WFMamOpHDRTRLRProcess.LOGGER.error(ex, ex);
				}
			}
		}
		return bytesArray;
	}

}
