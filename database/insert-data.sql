-- test data record for checking wf mam runs ok when running integration test
insert into WF_MAM_OP_HDR_TRLR values( 2006 , '2017-6-11' , 1 , 'WF_BOA_MAM_20170611','','');



INSERT INTO WF_MAM_SRC_FILE (CYCLE_NUMBER ,SEQUENCE_NUMBER ,REFERENCE_NUMBER ,SECONDARY_AUTH ,CONSOLIDATED_NAME ,DUE_DATE, AMOUNT_DUE ,INVOICE_NUMBER ,INVOICE_DATE ,EMAIL ,ADDRESS ,ADDRESS_2 ,CITY ,STATE ,ZIP ,PHONE) 
VALUES  (2007 ,1 ,'89724' ,'1' ,'arg2' ,'2017-2-2' ,-100.00 ,'0' ,'2017-1-1' ,'test@pei.com' ,'mich 20 ln st' ,'2373' ,'det' ,'mi' ,'55555' ,'555-555555');

INSERT INTO SPR_LOCATION (SUBMISSION_NUMBER, ENTITY_NUMBER, LOCATION, ADDR_1, ADDR_2, CITY, STATE, ZIP1) VALUES	(2007, 1, 'mx','1020 MVN ST', 'APT 1', 'SAN DIEGO','CA','92154' );

INSERT INTO SPR_INSURED_CONTACT_ (CONTACT_CODE_ID, SUBMISSION_NUMBER, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, PHONE_AREA_CODE, PHONE_PREFIX, PHONE_SUFFIX) VALUES (22, 2007, 'John', 'Doe', 'john@doe.com', 55, 619, 2230 );

INSERT INTO BILLING_STATEMENT_CO (POLICY_PREFIX_1 ,POLICY_PREFIX_2 ,POLICY_NUMBER ,POLICY_SUFFIX ,STATEMENT_DATE ,BILLING_INVOICE_NUMB ,COMMENT_LINE_1 ,COMMENT_LINE_2 ,COMMENT_LINE_STAR)
VALUES ('WK' ,'N' ,102009 ,'10' ,'2008-04-01 00:00:00.000' ,0 ,'' ,'' ,'');

INSERT INTO SP_BILL_STMT_CTRL (POLICY_PREFIX_1 ,POLICY_PREFIX_2 ,POLICY_NUMBER ,POLICY_SUFFIX ,STMT_DATE ,DUE_DATE ,ENTRY_DATE ,DATE_PRINTED ,INVOICE_NUMBER ,AMOUNT_DUE ,PENDING_CANCEL_DATE ,REVIEWED_FOR_CANCEL ,STATEMENT_TYPE ,AGENCY_CODE ,AUDIT_BILL ,AUDIT_PRINTED ,PEND_CNCL_RPT_PRINTE ,DEPOSITTRANSEXISTS)
VALUES ('WK' ,'N' ,102009 ,'10' ,'2008-06-01 00:00:00.000' ,'2008-06-21 00:00:00.000' ,'2008-06-01 00:00:00.000' ,'2008-05-30 00:00:00.000' ,1 ,0.00 ,'2008-06-30 00:00:00.000' ,'Y' ,'B' ,'20000   ' ,'N' ,0 ,'N' ,0);

INSERT INTO SPR_DBA(SUBMISSION_NUMBER, ENTITY_NUMBER, DBA_NUMBER, DBA_NAME)VALUES(2007, 2, 1, 'PEI TEST');

INSERT INTO SPR_ENTITY_FILE(SUBMISSION_NUMBER, NEXT_DBA_NUMBER, ENTITY_NUMBER, ENTITY_NAME)VALUES(2007, 2, 1, 'PEI TEST2');


INSERT INTO COLLECTION_MASTER
           (COLLECTION_REC_ID ,POLICY_PREFIX_1 ,POLICY_PREFIX_2 ,POLICY_NUMBER ,POLICY_SUFFIX ,SUBMISSION_NUMBER ,AGENCYDIR_COLLECTOR ,AGENCYDIR_INDICATOR ,ORIG_AGENCY_CODE
           ,DIRECT_COLLECTOR ,BILLING_PD_FROM ,BILLING_PD_TO ,DATE_CLEARED ,DATE_PRINTED ,AUDIT_INVOICE_NUMBER ,TRANSACTION_CODE ,GROSS_PREMIUM_AMOUNT ,COMMISSION_AMOUNT ,NET_PREMIUM_AMOUNT
           ,GROSS_PAYMENT_APPLIE ,ENTRY_DATE ,CASH_APPL_TYPE ,NET_PAYMENT_APPLIED ,RECORD_TYPE ,CLEARED_RECEIVABLE ,CASH_RECORD_ID ,COLLECT_REASON_CODE ,TRANSFER_FLAG ,AGENCYDIRECT_BILL
           ,ORIGINAL_ENTRY_DATE ,O_COMMENT ,DATE_OF_COMMENT ,ORIGINAL_TRANS_CODE ,COMMISSION_PAID_DATE ,TIME_CREATED ,DIRECT_BILL_INVOICE ,COMMISSION ,USERID ,COMMISSION_CLEARED ,COMMISSION_BASE_AMT
           ,GL_COMPANY_CODE ,RECORD_CLEARED_ID ,ACCOUNT_CURRENT ,ACCOUNT_CURRENT_DATE ,AMOUNT_XFERRED ,XFER_POLICY_PREFIX_1 ,XFER_POLICY_PREFIX_2 ,XFER_POLICY_NUMBER ,XFER_POLICY_SUFFIX ,XFER_ID
           ,POLICY_STATE_IDC ,DEFERRAL_PERCENT ,DEFERRAL_GROSS_AMOUN ,DEFERRAL_COMM_AMOUNT ,DEFERRAL_NET_AMOUNT ,SEQUENCENUMBER)
VALUES (4012525,'WK' ,'N' ,102009 ,'10' ,1 ,'86080   ' ,'A' ,'86080   '
		 ,'' ,'2016-01-05 00:00:00.000' ,'2017-01-05 00:00:00.000' ,NULL ,NULL ,0 ,216 ,2.00 ,0.00 ,2.00 
		 ,0.00 ,'2016-01-05 00:00:00.000' ,'' ,0.00 ,1 ,'N' ,0 ,0 ,'' ,'D' 
		 ,'2016-01-05 00:00:00.000' ,'' ,NULL ,801 ,NULL ,'184010  ' ,0 ,0.120000000 ,'WPELETI   ' ,'N' ,0 
		 ,1 ,0 ,'N' ,NULL ,0.00 ,'' ,'' ,0 ,'' ,0 
		 ,4 ,0.00000000 ,0.00 ,0.00 ,0.00 ,13694213);
		 
INSERT INTO SPR_FINANCIAL_FILE
           (SUBMISSION_NUMBER ,QUOTE_NUMBER ,QUOTE_DESCRIPTION ,QUOTE_STATUS ,POLICY_PREFIX_ID ,POLICY_PREFIX_1 ,POLICY_PREFIX_2 ,GL_COMPANY_CODE ,SAFETY_GROUP_CODE ,SAFETY_GROUP_DATE
           ,GOVERNING_CLASS_ID ,GOVERNING_CLASS_CODE ,GOVERNING_CLASS_SUFF ,QUALITY_GRADE ,BILLING_TIMING_CODE ,RETRO_YN ,RETRO_NUMBER_OF_YEAR ,PARTICIPATINGNON ,PARTICIPATING_NUMBER
           ,MISC_EXPENSE_FACTOR ,MINIMUM_PREMIUM ,MINIMUM_PREMIUM_STAT ,MINIMUM_PREMIUM_USLH ,COMMISSION ,COMMISSION_PREMIUM ,MANUAL_EAP ,STANDARD_PREMIUM ,FINANCIAL_EAP ,BILLABLE_EAP
           ,DEPOSIT ,DEPOSIT_AMOUNT ,NUMBER_OF_INSTALLMEN ,NUMBER_OF_CHECKING_A ,FREQUENCY_CODE ,AUDIT_CHECKING_FREQU ,DELAY_CODE ,AGYDIRECT_BILL_CODE ,DATE_PRINTED ,AUDIT_INVOICE_METHOD
           ,AUDITINSTALLMENT ,COLLATERALIZED_DEPOS ,CHECK_WITH_PAYROLL ,AUDIT_SOLICITATION_M ,POLICY_MINIMUM_PREMIUM ,ASSESMENT_EAP ,GOVERNING_CLASS_OVER ,COMM_PERCENT_OVERRID ,DEFERRAL_PERCENTAGE
           ,DEFERRAL_AMOUNT ,DEFERRAL_PERCENT_OVE ,RECALC_NEEDED ,ORIG_SUBM_EFF_DATE ,ORIG_SUBM_EXP_DATE ,ORIG_SUBM_NAD ,EXPENSE_CONST_AMT ,EXPENSE_CONST_STATE ,EXPENSE_CONST_USLH)
     VALUES
           (1 ,1 ,'D/B QUARTERLY' ,4 ,1 ,'WK' ,'N' ,1 ,0 ,NULL
           ,127 ,2883 ,'' ,0.00 ,13 ,'N' ,0 ,'N' ,0
           ,0.000 ,750 ,'CA' ,'' ,0.150000000 ,1310 ,1092 ,1310 ,1310 ,1324
           ,0.30000000 ,739 ,1 ,3 ,'Q' ,'Q' ,'N' ,'D' ,'1998-06-12 00:00:00.000' ,'C'
           ,'I' ,'N' ,'N' ,'C' ,750 ,13.00 ,0 ,0 ,0.00000000
           ,0 ,0 ,0 ,'1998-06-12 00:00:00.000' ,'1998-06-12 00:00:00.000' ,NULL ,0 ,'' ,'');
           
INSERT INTO POLICY_MASTER
           (SUBMISSION_NUMBER ,POLICY_PREFIX_1 ,POLICY_PREFIX_2 ,POLICY_NUMBER ,POLICY_SUFFIX ,SUBMISSION_DATE ,RECEIVED_DATE ,ISSUED_DATE ,EFFECTIVE_DATE ,EXPIRATION_DATE
           ,NORMAL_ANNIV_DATE ,STATUS_CODE ,STATUS_DATE ,AGENCY_CODE ,AGENCY_GROUP_CODE ,TERM ,CANCEL_DATE ,CANCEL_TYPE ,REINSURANCE_TYPE_COD ,REINSTATEMENT_DATE
           ,REINSTATE_W_LAPS_EFF ,REINSTATE_W_LAPS_EXP ,RENEWABLE_FLAG ,NONREN_DATE ,NONREN_CODE ,ADMIN_UNIT_CODE ,FINAL_AUDIT_FLAG ,CLAIM_EXAMINER ,CSO ,DIRECT_COLLECTOR_COD
           ,ASSIGNED_RISK ,DATE_OF_NOTICE ,FINANCE_COMPANY_CODE ,STANDARD_INDUSTRY_CO ,REASSIGNED ,ASSUMED_FROM ,ASSUMED_POLICY_NO ,AGENCY_ISSUED ,BINDER_NUMBER ,WINNING_QUOTE_NUMBER
           ,NEXT_ENTITY_NUMBER ,FOR_CCURNCYBINDER ,COMBINED_ID ,LOCKED ,XUE_ENTERED)
     VALUES
           (1 ,'WK','N' ,102009 ,'1' ,'1998-06-12 00:00:00.000' ,'1998-06-12 00:00:00.000' ,'1998-06-12 00:00:00.000' ,'2016-06-12 00:00:00.000' ,'1999-06-12 00:00:00.000'
           ,NULL ,6 ,'1999-05-20 00:00:00.000' ,'30000   ' ,'30000' ,12 ,NULL ,0 ,0 ,NULL
           ,NULL ,NULL ,'Y' ,NULL ,0 ,0 ,'Y' ,0 ,1 ,''
           ,'N' ,NULL ,0 ,99999 ,'' ,0 ,'' ,'N' ,0 ,1
           ,2 ,0 ,0 ,0 ,0);
