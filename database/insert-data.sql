-- test data record for checking wf mam runs ok when running integration test
insert into WF_MAM_OP_HDR_TRLR values( 2006 , '2017-6-11' , 1 , 'WF_BOA_MAM_20170611','','');



INSERT INTO WF_MAM_SRC_FILE (CYCLE_NUMBER ,SEQUENCE_NUMBER ,REFERENCE_NUMBER ,SECONDARY_AUTH ,CONSOLIDATED_NAME ,DUE_DATE, AMOUNT_DUE ,INVOICE_NUMBER ,INVOICE_DATE ,EMAIL ,ADDRESS ,ADDRESS_2 ,CITY ,STATE ,ZIP ,PHONE) VALUES (2006 ,1 ,'p89724' ,'20' ,'arg2' ,'2017-2-2' ,100 ,'inv2903' ,'2017-1-1' ,'test@pei.com' ,'mich 20 ln st' ,'2373' ,'det' ,'mi' ,'55555' ,'555-555555');

INSERT INTO SPR_LOCATION (SUBMISSION_NUMBER, ENTITY_NUMBER, LOCATION, ADDR_1, ADDR_2, CITY, STATE, ZIP1) VALUES	(2007, 1, 'mx','1020 MVN ST', 'APT 1', 'SAN DIEGO','CA','92154' );

INSERT INTO SPR_INSURED_CONTACT_ (CONTACT_CODE_ID, SUBMISSION_NUMBER, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, PHONE_AREA_CODE, PHONE_PREFIX, PHONE_SUFFIX) VALUES (22, 2007, 'John', 'Doe', 'john@doe.com', 55, 619, 2230 );

INSERT INTO POLICY_MASTER (POLICY_NUMBER, POLICY_SUFFIX, SUBMISSION_NUMBER,	STATUS_CODE) VALUES (3010, 1, 2007, 2);



INSERT INTO BILLING_STATEMENT_CO (POLICY_PREFIX_1 ,POLICY_PREFIX_2 ,POLICY_NUMBER ,POLICY_SUFFIX ,STATEMENT_DATE ,BILLING_INVOICE_NUMB ,COMMENT_LINE_1 ,COMMENT_LINE_2 ,COMMENT_LINE_STAR)
VALUES ('AA' ,'N' ,102009 ,'10' ,'2008-04-01 00:00:00.000' ,749678 ,'' ,'' ,'');

INSERT INTO SP_BILL_STMT_CTRL (POLICY_PREFIX_1 ,POLICY_PREFIX_2 ,POLICY_NUMBER ,POLICY_SUFFIX ,STMT_DATE ,DUE_DATE ,ENTRY_DATE ,DATE_PRINTED ,INVOICE_NUMBER ,AMOUNT_DUE ,PENDING_CANCEL_DATE ,REVIEWED_FOR_CANCEL ,STATEMENT_TYPE ,AGENCY_CODE ,AUDIT_BILL ,AUDIT_PRINTED ,PEND_CNCL_RPT_PRINTE ,DEPOSITTRANSEXISTS)
VALUES ('AA' ,'N' ,102009 ,'10' ,'2008-06-01 00:00:00.000' ,'2008-06-21 00:00:00.000' ,'2008-06-01 00:00:00.000' ,'2008-05-30 00:00:00.000' ,749678 ,0.00 ,'2008-06-30 00:00:00.000' ,'Y' ,'B' ,'20000   ' ,'N' ,0 ,'N' ,0);

INSERT INTO COLLECTION_MASTER
           (COLLECTION_REC_ID ,POLICY_PREFIX_1 ,POLICY_PREFIX_2 ,POLICY_NUMBER ,POLICY_SUFFIX ,SUBMISSION_NUMBER ,AGENCYDIR_COLLECTOR ,AGENCYDIR_INDICATOR ,ORIG_AGENCY_CODE
           ,DIRECT_COLLECTOR ,BILLING_PD_FROM ,BILLING_PD_TO ,DATE_CLEARED ,DATE_PRINTED ,AUDIT_INVOICE_NUMBER ,TRANSACTION_CODE ,GROSS_PREMIUM_AMOUNT ,COMMISSION_AMOUNT ,NET_PREMIUM_AMOUNT
           ,GROSS_PAYMENT_APPLIE ,ENTRY_DATE ,CASH_APPL_TYPE ,NET_PAYMENT_APPLIED ,RECORD_TYPE ,CLEARED_RECEIVABLE ,CASH_RECORD_ID ,COLLECT_REASON_CODE ,TRANSFER_FLAG ,AGENCYDIRECT_BILL
           ,ORIGINAL_ENTRY_DATE ,O_COMMENT ,DATE_OF_COMMENT ,ORIGINAL_TRANS_CODE ,COMMISSION_PAID_DATE ,TIME_CREATED ,DIRECT_BILL_INVOICE ,COMMISSION ,USERID ,COMMISSION_CLEARED ,COMMISSION_BASE_AMT
           ,GL_COMPANY_CODE ,RECORD_CLEARED_ID ,ACCOUNT_CURRENT ,ACCOUNT_CURRENT_DATE ,AMOUNT_XFERRED ,XFER_POLICY_PREFIX_1 ,XFER_POLICY_PREFIX_2 ,XFER_POLICY_NUMBER ,XFER_POLICY_SUFFIX ,XFER_ID
           ,POLICY_STATE_IDC ,DEFERRAL_PERCENT ,DEFERRAL_GROSS_AMOUN ,DEFERRAL_COMM_AMOUNT ,DEFERRAL_NET_AMOUNT ,SEQUENCENUMBER)
VALUES (7295570 ,'AA' ,'N' ,102009 ,'10' ,454950 ,'86080   ' ,'A' ,'86080   ' ,'' ,'2016-01-05 00:00:00.000' ,'2017-01-05 00:00:00.000' ,NULL ,NULL ,0 ,801 ,2.00 ,0.00 ,2.00 ,0.00
           ,'2016-01-05 00:00:00.000' ,'' ,0.00 ,1 ,'N' ,0 ,0 ,'' ,'D' ,'2016-01-05 00:00:00.000' ,'' ,NULL ,801 ,NULL ,'184010  ' ,749678 ,0.120000000 ,'WPELETI   ' ,'N' ,0 ,1 ,0 ,'N' ,NULL
           ,0.00 ,'' ,'' ,0 ,'' ,0 ,4 ,0.00000000 ,0.00 ,0.00 ,0.00 ,13694213);
