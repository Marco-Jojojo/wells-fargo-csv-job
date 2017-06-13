package com.peiwc.billing.process;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.ProcessDAO;

@Component
public class ProcessManagerCheck {

	@Autowired
	private ProcessDAO processDAO;

	public boolean checkIfProcessHasAlreadyRun(final Date currentDate) {
		return this.processDAO.checkProcessDate(currentDate);
	}

	public int getNextCycleNumber() {
		return this.processDAO.getLastCycleNumber() + 1;
	}

}