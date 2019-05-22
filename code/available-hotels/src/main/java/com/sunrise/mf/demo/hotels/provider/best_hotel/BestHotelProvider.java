/**
 * BestHotelProvider.java
 * @author Shrouq Sabah 
 * Created on May 21, 2019
 */
package com.sunrise.mf.demo.hotels.provider.best_hotel;

import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

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
public class BestHotelProvider implements IProvider {

	private final IAvailableHotelRequestMapper availableHotelRequestMapper = new IAvailableHotelRequestMapper() {

		@Override
		public Map<String, Object> map(AvailableHotelSearchCriteria searchCriteria) {
			Map<String, Object> request = new HashMap<String, Object>();
			request.put("city", searchCriteria.getCity());
			request.put("fromDate",
					Utils.formatDateToString(searchCriteria.getFromDate(), DateTimeFormatter.ISO_LOCAL_DATE,searchCriteria.getCity()));
			request.put("toDate",
					Utils.formatDateToString(searchCriteria.getToDate(), DateTimeFormatter.ISO_LOCAL_DATE,searchCriteria.getCity()));
			request.put("numberOfAdults", String.valueOf(searchCriteria.getNumberOfAdults()));

			return request;
		}
	};

	private final IAvailableHotelResponseMapper availableHotelResponseMapper = new IAvailableHotelResponseMapper() {

		@Override
		public AvailableHotel map(Map<String, Object> response, AvailableHotelSearchCriteria searchCriteria) {
			AvailableHotel availableHotel = new AvailableHotel();

			availableHotel.setAmenities(Utils.convertToListOfStringFromString(response.get("roomAmenities")));
			availableHotel.setHotelName(Utils.convertToString(response.get("hotel")));
			availableHotel.setProvider(getProviderName());
			availableHotel.setRate(Utils.convertToShort(response.get("hotelRate")));

			Period period = Period.between(searchCriteria.getFromDate().toLocalDate(), searchCriteria.getToDate().toLocalDate());
			int numOfNights = period.getDays();
			double totalPrice = Utils.convertToDouble(response.get("hotelFare"));

			availableHotel.setFare(Math.round(100.0*totalPrice / numOfNights)/100.0);

			return availableHotel;
		}
	};

	@Override
	public String getProviderName() {
		return "BestHotels";
	}

	@Override
	public String getProviderAvailableHotelsApiUrl() {
		return "http://localhost:8080/BestHotels";
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
		builder.append("BestHotelProvider [");
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
