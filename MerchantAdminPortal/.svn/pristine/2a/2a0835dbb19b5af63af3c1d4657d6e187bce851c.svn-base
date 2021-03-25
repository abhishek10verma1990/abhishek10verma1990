package com.npst.upi.portal.merchant.utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public final class UtilityFunctions {
	/**
	 * This method returns true if the collection is null or is empty.
	 * 
	 * @param collection
	 * @return true | false
	 */
	public static boolean isNotNullOrEmpty(final Collection<?> collection) {
		if (collection != null && !collection.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true of the map is null or is empty.
	 * 
	 * @param map
	 * @return true | false
	 */
	public static boolean isNotNullOrEmpty(final Map<?, ?> map) {
		if (map != null && !map.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the objet is null.
	 * 
	 * @param object
	 * @return true | false
	 */
	public static boolean isNotNullOrEmpty(final Object object) {
		if (object != null) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the input array is null or its length is
	 * zero.
	 * 
	 * @param array
	 * @return true | false
	 */
	public static boolean isNotNullOrEmpty(final Object[] array) {
		if (array != null && array.length > 0) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the input string is null or its length is
	 * zero.
	 * 
	 * @param string
	 * @return true | false
	 */
	public static boolean isNotNullOrEmpty(final String string) {
		if (string != null && !"null".equalsIgnoreCase(string.trim()) && string.trim().length() > 0) {
			return true;
		}
		return false;
	}
	
	public static String dateFormat(Timestamp date){
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String finalDate=format.format(new Date(date.getTime()));
		return finalDate;
	}
}
