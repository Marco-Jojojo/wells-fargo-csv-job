package com.peiwc.billing.process;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.WFMamErrLogRepository;
import com.peiwc.billing.dao.WFMamSrcFileDAO;
import com.peiwc.billing.domain.WFMamErrLog;
import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;

/**
 * this process generates a CSV file from data retrieved from current cycle.
 */
@Component("writeWFMAMSrcFileCSV")
public class WriteWFMAMSrcFileCSV {

	/**
	 * new line
	 */
	public static final String NEW_LINE = "\r\n";

	/**
	 * comma
	 */
	public static final String COMMA = ",";

	public static final String QUOT = "\"";

	@Autowired
	private WFMamSrcFileDAO wfMamSrcFileDAO;

	@Autowired
	private WFMamErrLogRepository wfMamErrLogRepository;

	private final static Logger LOGGER = Logger.getLogger(WriteWFMAMSrcFileCSV.class);

	private SimpleDateFormat dateFormat;

	private DecimalFormat numberFormat;

	@Value("${csv.date.format}")
	private String currentDateFormat;

	@Value("${numeric.decimal.places}")
	private int minFractionDigits;

	@Value("${csv.headers}")
	private String csvHeaders;

	/**
	 * Add code initialize
	 */
	@PostConstruct
	public void init() {
		dateFormat = new SimpleDateFormat(currentDateFormat);
		numberFormat = new DecimalFormat();
		numberFormat.setMinimumFractionDigits(minFractionDigits);
	}

	/**
	 * writes Data Retrieved from cycle number to database.
	 *
	 * @param cycleNumber
	 *            number of cycle where wfdata is retrieved
	 * @param fileName
	 *            file where data is being stored.
	 * @return a record count of the number of records processed.
	 * @throws IOException
	 *             if file could not be written
	 * @throws FileNotFoundException
	 */
	public int writeDataToCSV(final int cycleNumber, final String fileName) throws IOException {
		int recordCount = 0;
		final List<WFMamSrcFile> wfList = this.wfMamSrcFileDAO.findByCycleNumber(cycleNumber);
		OutputStream out = null;
		final Set<WFMamSrcFilePK> errorList = this.getErrorsFromCycle(cycleNumber);
		try {
			out = new FileOutputStream(fileName);
			final byte[] headers = (csvHeaders + WriteWFMAMSrcFileCSV.NEW_LINE).getBytes();
			out.write(headers);
			for (final WFMamSrcFile wfMamSrcFile : wfList) {
				if (!errorList.contains(wfMamSrcFile.getId())) {
					final String csvLine = getCSVFromSrcFile(wfMamSrcFile);
					out.write(csvLine.getBytes());
					recordCount++;
				}
			}
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (final IOException e) {
					WriteWFMAMSrcFileCSV.LOGGER.error(e, e);
				}
			}
		}
		return recordCount;
	}

	private String getCSVFromSrcFile(final WFMamSrcFile wfMamSrcFile) {
		final StringBuilder builder = new StringBuilder();
		builder.append(wfMamSrcFile.getReferenceNumber());
		builder.append(WriteWFMAMSrcFileCSV.COMMA);
		builder.append(wfMamSrcFile.getSecondaryAuth());
		builder.append(WriteWFMAMSrcFileCSV.COMMA);
		builder.append(formatString(wfMamSrcFile.getConsolidatedName()));
		builder.append(WriteWFMAMSrcFileCSV.COMMA);
		builder.append(formatDate(wfMamSrcFile.getDueDate()));
		builder.append(WriteWFMAMSrcFileCSV.COMMA);
		builder.append(formatNumber(wfMamSrcFile.getAmountDue()));
		builder.append(WriteWFMAMSrcFileCSV.COMMA);
		builder.append(wfMamSrcFile.getInvoiceNumber());
		builder.append(WriteWFMAMSrcFileCSV.COMMA);
		builder.append(formatDate(wfMamSrcFile.getInvoiceDate()));
		builder.append(WriteWFMAMSrcFileCSV.COMMA);
		builder.append(formatString(wfMamSrcFile.getEmail()));
		builder.append(WriteWFMAMSrcFileCSV.COMMA);
		builder.append(formatString(wfMamSrcFile.getAddress()));
		builder.append(WriteWFMAMSrcFileCSV.COMMA);
		builder.append(formatString(wfMamSrcFile.getAddress2()));
		builder.append(WriteWFMAMSrcFileCSV.COMMA);
		builder.append(formatString(wfMamSrcFile.getCity()));
		builder.append(WriteWFMAMSrcFileCSV.COMMA);
		builder.append(formatString(wfMamSrcFile.getState()));
		builder.append(WriteWFMAMSrcFileCSV.COMMA);
		builder.append(formatString(wfMamSrcFile.getZip()));
		builder.append(WriteWFMAMSrcFileCSV.COMMA);
		builder.append(formatString(wfMamSrcFile.getPhone()));
		builder.append(WriteWFMAMSrcFileCSV.COMMA);
		builder.append(formatString(wfMamSrcFile.getStatus()));
		builder.append(WriteWFMAMSrcFileCSV.NEW_LINE);
		return builder.toString();
	}

	private Set<WFMamSrcFilePK> getErrorsFromCycle(final int cycleNumber) {
		final List<WFMamErrLog> errors = wfMamErrLogRepository.getErrorsFromCycleNumber(cycleNumber);
		final Set<WFMamSrcFilePK> errorList = new HashSet<WFMamSrcFilePK>();
		for (final WFMamErrLog error : errors) {
			final int cycleNumberError = error.getCycleNumber();
			final int sequenceNumber = error.getSequenceNumber();
			final WFMamSrcFilePK pk = new WFMamSrcFilePK();
			pk.setCycleNumber(cycleNumberError);
			pk.setSequenceNumber(sequenceNumber);
			errorList.add(pk);
		}
		return errorList;
	}

	private String formatDate(final Date dateValue) {
		String resultFormat = "";
		if (dateValue != null) {
			resultFormat = dateFormat.format(dateValue);
		}
		return resultFormat;
	}

	private String formatString(final String stringValue) {
		String resultFormat = "";
		if (stringValue != null) {
			resultFormat = stringValue.replaceAll(WriteWFMAMSrcFileCSV.QUOT,
					WriteWFMAMSrcFileCSV.QUOT + WriteWFMAMSrcFileCSV.QUOT);
		}
		return WriteWFMAMSrcFileCSV.QUOT + resultFormat + WriteWFMAMSrcFileCSV.QUOT;
	}

	private String formatNumber(final Number numberValue) {
		final String finalFormat = numberFormat.format(numberValue).replaceAll(",", "");
		return WriteWFMAMSrcFileCSV.QUOT + finalFormat + WriteWFMAMSrcFileCSV.QUOT;
	}

}
