package com.peiwc.billing.dao;

import com.peiwc.billing.domain.WFMamSrcFile;
import java.util.List;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Aug 30, 2017
 */
public interface CalcClearedReceivableRecordsDAO
{
    /**
     * Getting all cleared receivable records
     * @return A list of them
     */
    List<WFMamSrcFile> findAllClearedReceivables();
    
    /**
     * Inserting the corresponding record into WF_MAM_SRC_FILE
     * @param wfMamSrcFile 
     */
    void create(WFMamSrcFile wfMamSrcFile);
}
