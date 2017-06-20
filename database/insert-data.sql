-- test data record for checking wf mam runs ok when running integration test
insert into WF_MAM_OP_HDR_TRLR values( 2006 , '2017-6-11' , 1 , 'WF_BOA_MAM_20170611','','');


INSERT INTO WF_MAM_SRC_FILE (CYCLE_NUMBER ,SEQUENCE_NUMBER ,REFERENCE_NUMBER ,SECONDARY_AUTH ,CONSOLIDATED_NAME ,DUE_DATE, AMOUNT_DUE ,INVOICE_NUMBER ,INVOICE_DATE ,EMAIL ,ADDRESS ,ADDRESS_2 ,CITY ,STATE ,ZIP ,PHONE) VALUES (2006 ,1 ,'p89724' ,'sec' ,'arg2' ,'2017-2-2' ,100 ,'inv2903' ,'2017-1-1' ,'test@pei.com' ,'mich 20 ln st' ,'2373' ,'det' ,'mi' ,'55555' ,'555-555555')