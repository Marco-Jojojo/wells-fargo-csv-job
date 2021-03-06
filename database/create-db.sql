
-- creates master table wf mam, this table contains data of records 
-- created in cycle number and error messages during the processing of data generated.

CREATE TABLE WF_MAM_OP_HDR_TRLR(
	CYCLE_NUMBER decimal(10, 0) NOT NULL primary key,
	CREATION_DATE date NOT NULL,
	TOTAL_RECORD_COUNT decimal(10, 0) NULL,
	FILENAME varchar(250) NULL,
	ERROR_MSG varchar(100) NULL);
	
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
	primary key(CYCLE_NUMBER, SEQUENCE_NUMBER )
	);