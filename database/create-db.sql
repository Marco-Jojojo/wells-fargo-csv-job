
-- creates master table wf mam, this table contains data of records 
-- created in cycle number and error messages during the processing of data generated.

CREATE CACHED TABLE PUBLIC.WF_MAM_OP_HDR_TRLR(
    CYCLE_NUMBER DECIMAL(10, 0) NOT NULL,
    CREATION_DATE DATE NOT NULL,
    TOTAL_RECORD_COUNT DECIMAL(10, 0),
    FILENAME VARCHAR(250),
    ERROR_MSG VARCHAR(100),
    STATUS VARCHAR(20)
);
	
--creates detail table for wf mam, this table contains all the records that are written to csv.	
CREATE TABLE WF_MAM_SRC_FILE(
	CYCLE_NUMBER decimal(10, 0) NOT NULL,
	SEQUENCE_NUMBER decimal(10, 0) NOT NULL,
	REFERENCE_NUMBER varchar(255) NOT NULL,
	SECONDARY_AUTH varchar(255) NOT NULL,
	CONSOLIDATED_NAME varchar(128) NOT NULL,
	DUE_DATE date NULL,
	AMOUNT_DUE decimal(20, 2) NOT NULL,
	INVOICE_NUMBER varchar(20) NOT NULL,
	INVOICE_DATE date NOT NULL,
	EMAIL varchar(128) NULL,
	ADDRESS varchar(100) NULL,
	ADDRESS_2 varchar(100) NULL,
	CITY varchar(50) NULL,
	STATE char(2) NULL,
	ZIP char(10) NULL,
	PHONE varchar(20) NULL,
	STATUS varchar(20) NULL,
	STATUS_INVOICE varchar(50) NULL,
	primary key(CYCLE_NUMBER, SEQUENCE_NUMBER )
	);
	
CREATE TABLE SPR_LOCATION(
	SUBMISSION_NUMBER int NOT NULL,
	ENTITY_NUMBER int NOT NULL,
	LOCATION char(4) NOT NULL,
	ADDR_1 char(40) NOT NULL,
	ADDR_2 char(40) NOT NULL,
	CITY char(40) NOT NULL,
	STATE char(2) NOT NULL,
	ZIP1 char(5) NOT NULL
	);
	
CREATE TABLE SPR_INSURED_CONTACT_(
	CONTACT_CODE_ID int NOT NULL,
	SUBMISSION_NUMBER int NOT NULL,
	FIRST_NAME char(20) NOT NULL,
	LAST_NAME char(35) NOT NULL,
	EMAIL_ADDRESS char(80) NOT NULL,
	PHONE_AREA_CODE int NOT NULL,
	PHONE_PREFIX int NOT NULL,
	PHONE_SUFFIX int NOT NULL	
	);



	--create COLLECTION_MASTER table
     CREATE TABLE COLLECTION_MASTER(
             COLLECTION_REC_ID   int  NOT NULL,
             POLICY_PREFIX_1   char(2) NOT NULL,
             POLICY_PREFIX_2   char(1) NOT NULL,
             POLICY_NUMBER   decimal(11, 0) NOT NULL,
             POLICY_SUFFIX   char(2) NOT NULL,
             SUBMISSION_NUMBER   int  NOT NULL,
             AGENCYDIR_COLLECTOR   char(8) NOT NULL,
             AGENCYDIR_INDICATOR   char(1) NOT NULL,
             ORIG_AGENCY_CODE   char(8) NOT NULL,
             DIRECT_COLLECTOR   char(8) NOT NULL,
             BILLING_PD_FROM   datetime  NULL,
             BILLING_PD_TO   datetime  NULL,
             DATE_CLEARED   datetime  NULL,
             DATE_PRINTED   datetime  NULL,
             AUDIT_INVOICE_NUMBER   int  NOT NULL,
             TRANSACTION_CODE   int  NOT NULL,
             GROSS_PREMIUM_AMOUNT   decimal(10, 2) NOT NULL,
             COMMISSION_AMOUNT   decimal(10, 2) NOT NULL,
             NET_PREMIUM_AMOUNT   decimal(10, 2) NOT NULL,
             GROSS_PAYMENT_APPLIE   decimal(10, 2) NOT NULL,
             ENTRY_DATE   datetime  NULL,
             CASH_APPL_TYPE   char(2) NOT NULL,
             NET_PAYMENT_APPLIED   decimal(10, 2) NOT NULL,
             RECORD_TYPE   int  NOT NULL,
             CLEARED_RECEIVABLE   char(1) NOT NULL,
             CASH_RECORD_ID   int  NOT NULL,
             COLLECT_REASON_CODE   int  NOT NULL,
             TRANSFER_FLAG   char(1) NOT NULL,
             AGENCYDIRECT_BILL   char(1) NOT NULL,
             ORIGINAL_ENTRY_DATE   datetime  NULL,
             O_COMMENT   char(60) NOT NULL,
             DATE_OF_COMMENT   datetime  NULL,
             ORIGINAL_TRANS_CODE   int  NOT NULL,
             COMMISSION_PAID_DATE   datetime  NULL,
             TIME_CREATED   char(8) NOT NULL,
             DIRECT_BILL_INVOICE   int  NOT NULL,
             COMMISSION   decimal(10, 9) NOT NULL,
             USERID   char(10) NOT NULL,
             COMMISSION_CLEARED   char(1) NOT NULL,
             COMMISSION_BASE_AMT   decimal(8, 0) NOT NULL,
             GL_COMPANY_CODE   int  NOT NULL,
             RECORD_CLEARED_ID   int  NOT NULL,
             ACCOUNT_CURRENT   char(1) NOT NULL,
             ACCOUNT_CURRENT_DATE   datetime  NULL,
             AMOUNT_XFERRED   decimal(10, 2) NOT NULL,
             XFER_POLICY_PREFIX_1   char(2) NOT NULL,
             XFER_POLICY_PREFIX_2   char(1) NOT NULL,
             XFER_POLICY_NUMBER   decimal(11, 0) NOT NULL,
             XFER_POLICY_SUFFIX   char(2) NOT NULL,
             XFER_ID   int  NOT NULL,
             POLICY_STATE_IDC   int  NOT NULL,
             DEFERRAL_PERCENT   decimal(9,0) NOT NULL,
             DEFERRAL_GROSS_AMOUN   decimal(10, 2) NOT NULL,
             DEFERRAL_COMM_AMOUNT   decimal(10, 2) NOT NULL,
             DEFERRAL_NET_AMOUNT   decimal(10, 2) NOT NULL,
             SEQUENCENUMBER  decimal(12, 0) NOT NULL
             );

    --create SP_BILL_STMT_CTRL table
    CREATE TABLE SP_BILL_STMT_CTRL(
        POLICY_PREFIX_1 char(2) NOT NULL,
        POLICY_PREFIX_2 char(1) NOT NULL,
        POLICY_NUMBER decimal(11, 0) NOT NULL,
        POLICY_SUFFIX char(2) NOT NULL,
        STMT_DATE datetime NULL,
        DUE_DATE datetime NULL,
        ENTRY_DATE datetime NULL,
        DATE_PRINTED datetime NULL,
        INVOICE_NUMBER int NOT NULL,
        AMOUNT_DUE decimal(10, 2) NOT NULL,
        PENDING_CANCEL_DATE datetime NULL,
        REVIEWED_FOR_CANCEL char(1) NOT NULL,
        STATEMENT_TYPE char(1) NOT NULL,
        AGENCY_CODE char(8) NOT NULL,
        AUDIT_BILL char(1) NOT NULL,
        AUDIT_PRINTED bit NOT NULL,
        PEND_CNCL_RPT_PRINTE char(1) NOT NULL,
        DEPOSITTRANSEXISTS bit NOT NULL
        );

    --create a BILLING_STATEMENT_CO table
    CREATE TABLE BILLING_STATEMENT_CO(
        POLICY_PREFIX_1 char(2) NOT NULL,
        POLICY_PREFIX_2 char(1) NOT NULL,
        POLICY_NUMBER decimal(11, 0) NOT NULL,
        POLICY_SUFFIX char(2) NOT NULL,
        STATEMENT_DATE datetime NULL,
        BILLING_INVOICE_NUMB int NOT NULL,
        COMMENT_LINE_1 char(80) NOT NULL,
        COMMENT_LINE_2 char(80) NOT NULL,
        COMMENT_LINE_STAR char(80) NOT NULL
    );
    
    
    CREATE TABLE WF_MAM_ERR_LOG(
		ID_ERR_LOG decimal(18, 0) NOT NULL PRIMARY KEY AUTO_INCREMENT,
		CYCLE_NUMBER decimal(10, 0) NOT NULL,
		SEQUENCE_NUMBER decimal(10, 0) NOT NULL,
		DESCRIPTION varchar(255) NOT NULL,
		STATUS varchar(20) NOT NULL
	);
    
    

