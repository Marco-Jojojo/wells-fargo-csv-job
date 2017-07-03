package com.peiwc.billing.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class DateFormatUtil {
	private static SimpleDateFormat format;
	private static SimpleDateFormat dateFormatParameter;
	private static final Logger LOGGER = Logger.getLogger(DateFormatUtil.class);
	static {
		DateFormatUtil.format = new SimpleDateFormat("yyyy-MM-dd");
		DateFormatUtil.dateFormatParameter = new SimpleDateFormat("dd/MM/yyyy");
	}

	public static String formatDate(final Date date) {
		return DateFormatUtil.format.format(date);
	}

	public static Date currentDateByParameter() {
		Date date = new Date();
		final String dateFromParameter = System.getProperty("start.date");
		if (StringUtils.isNotEmpty(dateFromParameter)) {
			try {
				date = DateFormatUtil.dateFormatParameter.parse(dateFromParameter);
			} catch (final ParseException e) {
				DateFormatUtil.LOGGER.error(e, e);
			}
		}
		return date;
	}
}