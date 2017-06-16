package com.peiwc.billing.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * this is the entity bean that contains the data related to master export of
 * csv file to database.
 */
@Entity
@Table(name = "WF_MAM_OP_HDR_TRLR")
public class WFMamOpHDRTRLR {

    @Id
    @Column(name = "CYCLE_NUMBER")
    private Integer cycleNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "TOTAL_RECORD_COUNT")
    private int totalRecordCount;

    @Column(name = "FILENAME", length = 250)
    private String fileName;

    @Column(name = "ERROR_MSG", length = 100)
    private String errorMessage;


    @Column(name = "STATUS", length = 20)
    private String status;

    /**
     * @return the cycleNumber
     */
    public Integer getCycleNumber() {
        return cycleNumber;
    }

    /**
     * @param cycleNumber the cycleNumber to set
     */
    public void setCycleNumber(final Integer cycleNumber) {
        this.cycleNumber = cycleNumber;
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the totalRecordCount
     */
    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    /**
     * @param totalRecordCount the totalRecordCount to set
     */
    public void setTotalRecordCount(final int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    /**
     * gets the error message
     *
     * @return error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * sets the error message
     *
     * @param errorMessage sets the error message
     */
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * gets the status
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * sets the status
     *
     * @param status parameter
     */
    public void setStatus(final String status) {
        this.status = status;
    }
}
