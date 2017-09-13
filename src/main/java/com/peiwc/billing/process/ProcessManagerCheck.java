package com.peiwc.billing.process;

import com.peiwc.billing.dao.ProcessDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * checks if the process has been run for today and gets the next cycle number.
 */
@Component
public class ProcessManagerCheck {

    @Autowired
    private ProcessDAO processDAO;

    /**
     * @param currentDate today's date.
     * @return an indicator that checks if the process has already been run for
     * today.
     */
    public boolean checkIfProcessHasAlreadyRun(final Date currentDate) {
        return processDAO.checkProcessDate(currentDate);
    }

    /**
     * @return the new cycle number that must run in this process.
     */
    public int getNextCycleNumber() {
        return this.processDAO.getLastCycleNumber() + 1;
    }

    /**
     * gets last cycle number.
     *
     * @return last cycle number.
     */
    public int getLastCycleNumber() {
        return this.processDAO.getLastCycleNumber();
    }

}