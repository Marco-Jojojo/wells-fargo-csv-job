package com.peiwc.billing.process;

import com.peiwc.billing.dao.WFMamSrcFileDAO;
import com.peiwc.billing.domain.WFMamSrcFile;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * this process generates a CSV file from data retrieved from current cycle.
 */
@Component("writeWFMAMSrcFileCSV")
public class WriteWFMAMSrcFileCSV {

    @Autowired
    private WFMamSrcFileDAO wfMamSrcFileDAO;

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
     * new line
     */
    public static final String NEW_LINE = "\r\n";

    /**
     * comma
     */
    public static final String COMMA = ",";

    /**
     * writes Data Retrieved from cycle number to database.
     *
     * @param cycleNumber number of cycle where wfdata is retrieved
     * @param fileName    file where data is being stored.
     * @return a record count of the number of records processed.
     * @throws IOException           if file could not be written
     * @throws FileNotFoundException
     */
    public int writeDataToCSV(final int cycleNumber, final String fileName) throws IOException {
        int recordCount = 0;
        final List<WFMamSrcFile> wfList = this.wfMamSrcFileDAO.findByCycleNumber(cycleNumber);
        OutputStream out = null;
        try {
            out = new FileOutputStream(fileName, true);
            final byte[] headers = (csvHeaders + NEW_LINE).getBytes();
            out.write(headers);
            for (final WFMamSrcFile wfMamSrcFile : wfList) {
                final String csvLine = getCSVFromSrcFile(wfMamSrcFile);
                out.write(csvLine.getBytes());
                recordCount++;
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
        builder.append(wfMamSrcFile.getConsolidatedName());
        builder.append(WriteWFMAMSrcFileCSV.COMMA);
        builder.append(dateFormat.format(wfMamSrcFile.getDueDate()));
        builder.append(WriteWFMAMSrcFileCSV.COMMA);
        builder.append(numberFormat.format(wfMamSrcFile.getAmountDue()));
        builder.append(WriteWFMAMSrcFileCSV.COMMA);
        builder.append(wfMamSrcFile.getInvoiceNumber());
        builder.append(WriteWFMAMSrcFileCSV.COMMA);
        builder.append(dateFormat.format(wfMamSrcFile.getInvoiceDate()));
        builder.append(WriteWFMAMSrcFileCSV.COMMA);
        builder.append(wfMamSrcFile.getEmail());
        builder.append(WriteWFMAMSrcFileCSV.COMMA);
        builder.append(wfMamSrcFile.getAddress());
        builder.append(WriteWFMAMSrcFileCSV.COMMA);
        builder.append(wfMamSrcFile.getAddress2());
        builder.append(WriteWFMAMSrcFileCSV.COMMA);
        builder.append(wfMamSrcFile.getCity());
        builder.append(WriteWFMAMSrcFileCSV.COMMA);
        builder.append(wfMamSrcFile.getState());
        builder.append(WriteWFMAMSrcFileCSV.COMMA);
        builder.append(wfMamSrcFile.getZip());
        builder.append(WriteWFMAMSrcFileCSV.COMMA);
        builder.append(wfMamSrcFile.getPhone());
        builder.append(WriteWFMAMSrcFileCSV.NEW_LINE);
        return builder.toString();
    }


}
