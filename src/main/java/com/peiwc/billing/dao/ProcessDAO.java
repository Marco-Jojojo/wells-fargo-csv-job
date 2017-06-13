package com.peiwc.billing.dao;

import java.util.Date;

public interface ProcessDAO {

	boolean checkProcessDate(Date creationDate);

	int getLastCycleNumber();

}
