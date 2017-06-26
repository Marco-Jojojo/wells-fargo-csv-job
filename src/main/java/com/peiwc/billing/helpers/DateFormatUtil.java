package com.peiwc.billing.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

	private static SimpleDateFormat format;

	static {
		DateFormatUtil.format = new SimpleDateFormat("yyyy-MM-dd");
	}

	public static String formatDate(final Date date) {
		return DateFormatUtil.format.format(date);
	}

}
