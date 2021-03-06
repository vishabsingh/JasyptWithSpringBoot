package com.vs.jasypt.utils;

import java.util.Calendar;
import java.util.TimeZone;

public final class DateUtils {

	/**
	 * The default application {@link TimeZone}
	 */
	private static TimeZone applicationTimeZone = TimeZone.getDefault();

	/**
	 * The original {@link TimeZone} before any change.
	 */
	public static final TimeZone ORIGINAL_DEFAULT_TIMEZONE = TimeZone.getDefault();

	private DateUtils() {
		// Utility class
	}

	/**
	 * Replace the current application {@link TimeZone} with the given one from its identifier.
	 *
	 * @param timeZone
	 *            {@link TimeZone}.
	 */
	public static void setApplicationTimeZone(final TimeZone timeZone) {
		applicationTimeZone = timeZone;
		TimeZone.setDefault(timeZone);
	}

	/**
	 * Return the application {@link TimeZone}
	 *
	 * @return the application {@link TimeZone}
	 */
	public static TimeZone getApplicationTimeZone() {
		return applicationTimeZone;
	}

	/**
	 * Return a new calendar based on default time-zone.
	 *
	 * @return The new calendar instance.
	 */
	public static Calendar newCalendar() {
		return Calendar.getInstance(applicationTimeZone);
	}
}


