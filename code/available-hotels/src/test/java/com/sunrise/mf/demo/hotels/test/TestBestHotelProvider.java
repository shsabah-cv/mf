/**
 * TestBestHotelProvider.java
 * @author Shrouq Sabah 
 * Created on May 22, 2019
 */
package com.sunrise.mf.demo.hotels.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
import com.sunrise.mf.demo.hotels.provider.best_hotel.BestHotelProvider;
import com.sunrise.mf.demo.hotels.utils.Utils;

/**
 * Testing BestHotel provider
 */

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBestHotelProvider {
	
	@Autowired
	private BestHotelProvider provider;
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
		expected.put("city", "USA");
		expected.put("fromDate", "2016-05-02");
		expected.put("toDate", "2016-05-20");
		expected.put("numberOfAdults", "10");
		
		Map<String, Object> actual = provider.getAvailableHotelApiRequestMapper ().map(searchCriteria);
		assertEquals(expected, actual);
		 
	}
	
	
	@Test
	public void responseMapper() {
		AvailableHotel expected = new AvailableHotel();
		expected.setAmenities(Arrays.asList(new String[] {"a1","a2","a3"}));
		expected.setFare(55.56);
		expected.setHotelName("XX");
		expected.setProvider("BestHotels");
		expected.setRate((short) 5);
		
		Map<String, Object> response= new HashMap<String, Object>();
		response.put("hotel", "XX");
		response.put("hotelRate", 5);
		response.put("hotelFare", 1000);
		response.put("roomAmenities", "a1,a2,a3");
		 
		
		AvailableHotel actual = provider.getIAvailableHotelApiResponseMapper().map(response, searchCriteria);
		assertEquals(expected, actual);
		 
	}
	

}
