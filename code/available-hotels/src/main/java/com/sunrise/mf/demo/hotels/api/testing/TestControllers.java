/**
 * TestControllers.java
 * @author Shrouq Sabah 
 * Created on May 21, 2019
 */
package com.sunrise.mf.demo.hotels.api.testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */

@RestController( )
public class TestControllers {
	
	@GetMapping(value="/BestHotels",   produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> testBestHotesl(@QueryParam("city") String city,
			@QueryParam("fromDate") String fromDate, @QueryParam("toDate") String toDate,
			@QueryParam("numberOfAdults") Integer numberOfAdults) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i=0;i<10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("hotel", "hotel x "+i);
			map.put("hotelRate", String.valueOf(i%5+1));
			map.put("hotelFare", String.valueOf((i+1)*100.2));
			map.put("roomAmenities", "music,view,ac");
			
			list.add(map);
		}
		
		
		return list;

	}
	
	
	@GetMapping(value="/CrazyHotel",   produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> testCrazyHotel(@QueryParam("city") String city,
			@QueryParam("from") String fromDate, @QueryParam("to") String toDate,
			@QueryParam("adultsCount") Integer adultsCount) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		for(int i=0;i<10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("hotelName", "hotel y "+i);
			map.put("rate",String.join("", Collections.nCopies(i%5+1, "*")));
			map.put("price", String.valueOf((i+1)*2.2));
			map.put("discount", String.valueOf(10.3));
			map.put("amenities", new String[] {"view","ac","cool","tv"});
			
			list.add(map);
		}
		
		
		return list;

	}
}
