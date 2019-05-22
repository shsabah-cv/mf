/**
 * TestHotelsController.java
 * @author Shrouq Sabah 
 * Created on May 22, 2019
 */
package com.sunrise.mf.demo.hotels.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sunrise.mf.demo.hotels.controllers.HotelsController;
import com.sunrise.mf.demo.hotels.dto.req.AvailableHotelsRequestDto;
import com.sunrise.mf.demo.hotels.dto.rsp.AvailableHotelsResponseDto;

/**
 *
 */
 
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestHotelsController {
	
	@Autowired
	private HotelsController hotelsController;

	@Test
	public void testAvilableHotelsWithAllEmptyParams() {
		AvailableHotelsRequestDto request=new AvailableHotelsRequestDto();
		List<AvailableHotelsResponseDto> actuals = hotelsController.findAvailableHotels(request);
		assertEquals(0, actuals.size());
	}
	
	@Test
	public void testAvilableHotelsWithEmptyfromDate() {
		AvailableHotelsRequestDto request=new AvailableHotelsRequestDto();
		request.setCity("USA");
		request.setFromDate(null);
		request.setToDate("2019-10-6");
		request.setNumberOfAdults(10);
		List<AvailableHotelsResponseDto> actuals = hotelsController.findAvailableHotels(request);
		assertEquals(0, actuals.size());
	}
	
	@Test
	public void testAvilableHotelsWithEmptyToDate() {
		AvailableHotelsRequestDto request=new AvailableHotelsRequestDto();
		request.setCity("USA");
		request.setFromDate("2016-05-02");
		request.setToDate(null);
		request.setNumberOfAdults(10);
		List<AvailableHotelsResponseDto> actuals = hotelsController.findAvailableHotels(request);
		assertEquals(0, actuals.size());
	}
	
	
	@Test
	public void testAvilableHotelsWithParams() {
		AvailableHotelsRequestDto request=new AvailableHotelsRequestDto();
		request.setCity("USA");
		request.setFromDate("2016-05-02");
		request.setToDate("2016-05-20");
		request.setNumberOfAdults(10);
		List<AvailableHotelsResponseDto> actuals = hotelsController.findAvailableHotels(request);
		assertEquals(20, actuals.size());
	}
}
