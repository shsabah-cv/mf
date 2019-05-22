/**
 * CrazyHotelProvider.java
 * @author Shrouq Sabah 
 * Created on May 21, 2019
 */
package com.sunrise.mf.demo.hotels.provider.crazy_hotel;

import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sunrise.mf.demo.hotels.dto.models.AvailableHotel;
import com.sunrise.mf.demo.hotels.dto.models.AvailableHotelSearchCriteria;
import com.sunrise.mf.demo.hotels.provider.IAvailableHotelRequestMapper;
import com.sunrise.mf.demo.hotels.provider.IAvailableHotelResponseMapper;
import com.sunrise.mf.demo.hotels.provider.IProvider;
import com.sunrise.mf.demo.hotels.utils.Utils;

/**
 *
 */
@Component
public class CrazyHotelProvider implements IProvider {
	
	 

	private final IAvailableHotelRequestMapper availableHotelRequestMapper = new IAvailableHotelRequestMapper() {

		@Override
		public Map<String, Object> map(AvailableHotelSearchCriteria searchCriteria) {
			Map<String, Object> request = new HashMap<String, Object>();
			request.put("city", searchCriteria.getCity());
			request.put("from",
					Utils.formatDateToString(searchCriteria.getFromDate(), DateTimeFormatter.ISO_INSTANT,searchCriteria.getCity()));
			request.put("to", Utils.formatDateToString(searchCriteria.getToDate(), DateTimeFormatter.ISO_INSTANT,searchCriteria.getCity()));
			request.put("adultsCount", String.valueOf(searchCriteria.getNumberOfAdults()));
			
			

			return request;
		}
	};

	private final IAvailableHotelResponseMapper availableHotelResponseMapper = new IAvailableHotelResponseMapper() {

		@Override
		public AvailableHotel map(Map<String, Object> response, AvailableHotelSearchCriteria searchCriteria) {
			AvailableHotel availableHotel = new AvailableHotel();

			availableHotel.setAmenities(Utils.convertToListOfStringFromArray(response.get("amenities")));			
			availableHotel.setHotelName(Utils.convertToString(response.get("hotelName")));
			availableHotel.setProvider(getProviderName());
			availableHotel.setRate((short) StringUtils.countOccurrencesOf(Utils.convertToString(response.get("rate")), "*"));
			
			double pricePerNight= Utils.convertToDouble(response.get("price"));
			double discount = Utils.convertToDouble(response.get("discount"));
			
			Period period = Period.between(searchCriteria.getFromDate().toLocalDate(), searchCriteria.getToDate().toLocalDate());
		    int numOfNights = period.getDays();
		    
		    availableHotel.setFare(Math.round(100*(pricePerNight*numOfNights-discount)/numOfNights)/100.0) ;

			return availableHotel;
		}
	};

	@Override
	public String getProviderName() {
		return "CrazyHotel";
	}

	@Override
	public String getProviderAvailableHotelsApiUrl() {
		return "http://localhost:8080/CrazyHotel";
	}

	@Override
	public IAvailableHotelRequestMapper getAvailableHotelApiRequestMapper() {
		return availableHotelRequestMapper;
	}

	@Override
	public IAvailableHotelResponseMapper getIAvailableHotelApiResponseMapper() {
		return availableHotelResponseMapper;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CrazyHotelProvider [");
		if (getProviderName() != null) {
			builder.append("getProviderName()=");
			builder.append(getProviderName());
			builder.append(", ");
		}
		if (getProviderAvailableHotelsApiUrl() != null) {
			builder.append("getProviderAvailableHotelsApiUrl()=");
			builder.append(getProviderAvailableHotelsApiUrl());
		}
		builder.append("]");
		return builder.toString();
	}
	
	

}
