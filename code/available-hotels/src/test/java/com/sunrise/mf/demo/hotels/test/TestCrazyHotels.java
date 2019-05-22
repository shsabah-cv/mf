/**
 * TestCrazyHotels.java
 * @author Shrouq Sabah 
 * Created on May 22, 2019
 */
package com.sunrise.mf.demo.hotels.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sunrise.mf.demo.hotels.dto.models.AvailableHotel;
import com.sunrise.mf.demo.hotels.dto.models.AvailableHotelSearchCriteria;
import com.sunrise.mf.demo.hotels.dto.req.AvailableHotelsRequestDto;
import com.sunrise.mf.demo.hotels.provider.crazy_hotel.CrazyHotelProvider;
import com.sunrise.mf.demo.hotels.utils.Utils;

/**
 *Test  CrazyHotels provider
 */
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCrazyHotels {

	
	@Autowired
	private CrazyHotelProvider provider;
	AvailableHotelSearchCriteria searchCriteria=null;
	
	@Before
	public void prepareData() {
		AvailableHotelsRequestDto request=new AvailableHotelsRequestDto();
		request.setCity("USA");
		request.setFromDate("2016-05-02");
		request.setToDate("2016-05-20");
		request.setNumberOfAdults(10);
		
	    searchCriteria=Utils.convertToAvailableHotelSearchCriteria(request);
	}
	
	
	@Test
	public void testName() {
		assertNotNull("name of provider is empty", provider.getProviderName());
		assertNotEquals("", provider.getProviderName()); 
	}
	
	
	@Test
	public void testUrl() {
		assertNotNull("name of provider is empty", provider.getProviderAvailableHotelsApiUrl());
		assertNotEquals("", provider.getProviderAvailableHotelsApiUrl()); 
	}
	
	
	@Test
	public void requestMapperNotNull() {
		assertNotNull(  provider.getAvailableHotelApiRequestMapper ());
		 
	}
	
	@Test
	public void responseMapperNotNull() {
		assertNotNull(  provider.getIAvailableHotelApiResponseMapper());
		 
	}
	
	@Test
	public void requestMapper() {
		Map<String, Object> expected = new HashMap<String, Object>();
		LocalDate localDateFrom = LocalDate.parse("2016-05-02", DateTimeFormatter.ISO_LOCAL_DATE);
	    LocalDateTime localDateTimeFrom = LocalDateTime.of(localDateFrom, LocalTime.now(TimeZone.getTimeZone("USA").toZoneId()) );
	    ZonedDateTime zonedDateTimeFrom=localDateTimeFrom.atZone(TimeZone.getTimeZone("USA").toZoneId());
	    
	    LocalDate localDateTo = LocalDate.parse("2016-05-20", DateTimeFormatter.ISO_LOCAL_DATE);
	    LocalDateTime localDateTimeTo = LocalDateTime.of(localDateTo, LocalTime.now(TimeZone.getTimeZone("USA").toZoneId()) );
	    ZonedDateTime zonedDateTimeTo=localDateTimeTo.atZone(TimeZone.getTimeZone("USA").toZoneId());
	    
	    
		expected.put("city", "USA");
		expected.put("from", DateTimeFormatter.ISO_INSTANT.format(zonedDateTimeFrom));
		expected.put("to", DateTimeFormatter.ISO_INSTANT.format(zonedDateTimeTo));
		expected.put("adultsCount", "10");
		
		Map<String, Object> actual = provider.getAvailableHotelApiRequestMapper ().map(searchCriteria);
		assertEquals(expected, actual);
		 
	}
	
	
	@Test
	public void responseMapper() {
		AvailableHotel expected = new AvailableHotel();
		expected.setAmenities(Arrays.asList(new String[] {"a1","a2","a3"}));
		expected.setFare(20.0);
		expected.setHotelName("XX");
		expected.setProvider("CrazyHotels");
		expected.setRate((short) 5);
		
		Map<String, Object> response= new HashMap<String, Object>();
		response.put("hotelName", "XX");
		response.put("rate", "*****");
		response.put("price", 20.0);
		response.put("amenities", new String[] {"a1","a2","a3"});
		 
		
		AvailableHotel actual = provider.getIAvailableHotelApiResponseMapper().map(response, searchCriteria);
		assertEquals(expected, actual);
		 
	}
	
}
