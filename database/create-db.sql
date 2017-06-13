
-- creates master table wf mam, this table contains data of records 
-- created in cycle number and error messages during the processing of data generated.

CREATE TABLE WF_MAM_OP_HDR_TRLR(
	CYCLE_NUMBER decimal(10, 0) NOT NULL primary key,
	CREATION_DATE date NOT NULL,
	TOTAL_RECORD_COUNT decimal(10, 0) NULL,
	FILENAME varchar(250) NULL,
	ERROR_MSG varchar(100) NULL);