/**
 * Utils.java
 * @author Shrouq Sabah 
 * Created on May 21, 2019
 */
package com.sunrise.mf.demo.hotels.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunrise.mf.demo.hotels.controllers.HotelsController;
import com.sunrise.mf.demo.hotels.dto.models.AvailableHotel;
import com.sunrise.mf.demo.hotels.dto.models.AvailableHotelSearchCriteria;
import com.sunrise.mf.demo.hotels.dto.req.AvailableHotelsRequestDto;
import com.sunrise.mf.demo.hotels.dto.rsp.AvailableHotelsResponseDto;

/**
 *
 */
public class Utils {
	
	static Logger logger = LoggerFactory.getLogger(Utils.class);

	public static AvailableHotelSearchCriteria convertToAvailableHotelSearchCriteria(AvailableHotelsRequestDto from) {
		if (from == null) {
			return null;
		}
		AvailableHotelSearchCriteria to = new AvailableHotelSearchCriteria();
		to.setCity(from.getCity());
		to.setFromDate(convertToLocalDate(from.getFromDate(), DateTimeFormatter.ISO_LOCAL_DATE,from.getCity()));
		to.setToDate(convertToLocalDate(from.getToDate() , DateTimeFormatter.ISO_LOCAL_DATE,from.getCity()));
		to.setNumberOfAdults(from.getNumberOfAdults());

		return to;

	}

	public static LocalDateTime convertToLocalDate(String from, DateTimeFormatter formatter,String cityCode) {

		if (from == null || formatter == null) {
			return null;
		}
		try {
			LocalDate localDate = LocalDate.parse(from, formatter);
		    LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.now(TimeZone.getTimeZone(cityCode).toZoneId()) );
		    
			return localDateTime;
		} catch (Exception ex) {
			logger.warn("convertToLocalDate",ex);
		}
		return null;

	}

	public static String formatDateToString(LocalDateTime from, DateTimeFormatter formatter, String cityCode) {
 
		if (from == null || formatter == null) {
			return null;
		}
		try {
 
			ZonedDateTime zonedDateTime=from.atZone(TimeZone.getTimeZone(cityCode).toZoneId());
			return formatter.format(zonedDateTime);
		} catch (Exception ex) {
			logger.warn("convertToLocalDate",ex);
		}
		return null;

	}
	
	
	 

	public static AvailableHotelsResponseDto convertToAvailableHotelsResponseDto(AvailableHotel from) {
		if (from == null) {
			return null;
		}
		AvailableHotelsResponseDto to = new AvailableHotelsResponseDto();
		to.setAmenities(from.getAmenities());
		to.setFare(from.getFare());
		to.setHotelName(from.getHotelName());
		to.setProvider(from.getProvider());

		return to;

	}

	public static void convertAndFillToAvailableHotelsResponseDto(List<AvailableHotel> fromList,
			List<AvailableHotelsResponseDto> toList) {
		if (fromList == null) {
			return;
		}

		for (AvailableHotel from : fromList) {
			toList.add(convertToAvailableHotelsResponseDto(from));
		}

	}

	public static double convertToDouble(Object from) {
		if (from == null) {
			return 0.0;
		}

		try {
			return Double.parseDouble(from.toString());
		} catch (Exception ex) {

		}
		return 0.0;
	}

	public static String convertToString(Object from) {
		if (from == null) {
			return "";
		}

		try {
			return from.toString();
		} catch (Exception ex) {

		}
		return "";
	}

	public static short convertToShort(Object from) {
		if (from == null) {
			return (short) 0.0;
		}

		try {
			return Short.parseShort(from.toString());
		} catch (Exception ex) {

		}
		return (short) 0.0;
	}

	public static List<String> convertToListOfStringFromString(Object from) {
		if (from == null) {
			return new ArrayList<String>();
		}

		try {
			return Arrays.asList((from.toString().split(",")));
		} catch (Exception ex) {

		}
		return new ArrayList<String>();
	}
	
	public static List<String> convertToListOfStringFromArray(Object from) {
		if (from == null) {
			return new ArrayList<String>();
		}

		try {
			return Arrays.asList((String[])(from));
		} catch (Exception ex) {

		}
		return new ArrayList<String>();
	}

}
