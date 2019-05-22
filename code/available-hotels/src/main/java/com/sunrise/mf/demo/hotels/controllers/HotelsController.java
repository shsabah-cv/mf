/**
 * HotelsController.java
 * @author Shrouq Sabah 
 * Created on May 21, 2019
 */
package com.sunrise.mf.demo.hotels.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunrise.mf.demo.hotels.dto.models.AvailableHotel;
import com.sunrise.mf.demo.hotels.dto.req.AvailableHotelsRequestDto;
import com.sunrise.mf.demo.hotels.dto.rsp.AvailableHotelsResponseDto;
import com.sunrise.mf.demo.hotels.services.AvailableHotelsService;
import com.sunrise.mf.demo.hotels.utils.Utils;

/**
 *
 */

@RestController( )
public class HotelsController {

	Logger logger = LoggerFactory.getLogger(HotelsController.class);

	@Autowired
	private AvailableHotelsService availableHotelsService;

	
	//http://localhost:8080/available-hotels?fromDate=2011-12-03&toDate=2011-12-04&city=USA&numberOfAdults=2
	
	@GetMapping(value="/available-hotels",      produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AvailableHotelsResponseDto> findAvailableHotels(AvailableHotelsRequestDto request) {
		logger.trace("available-hotels API has been received request : \n" + request.toString());
		
		
		

		List<AvailableHotelsResponseDto> response = new ArrayList<AvailableHotelsResponseDto>();
		
		
		
		try {
			
			if(!validateRequest(request)) {
				logger.info("available-hotels API ,the request has missing params : \n" + request.toString());
				return response;
			}
			List<AvailableHotel> availableHotels = availableHotelsService
					.retrieveAvailableHotels(Utils.convertToAvailableHotelSearchCriteria(request));			
			
			if(availableHotels!= null) {
				availableHotelsService.sortAvailableHotels(availableHotels);
				Utils.convertAndFillToAvailableHotelsResponseDto(availableHotels,response);
			}
		} catch (Exception ex) {
			logger.error("available-hotels API has been received request "+request.toString()+" : \n but has an error. ", ex);
		}

		logger.trace("available-hotels API will return response : \n" + response.toString());
		return response;
	}


	/**
	 * @param request
	 * @return
	 */
	private boolean validateRequest(AvailableHotelsRequestDto request) {
		 
		return !(request.getCity()==null || request.getFromDate()==null || request.getNumberOfAdults()==0 || request.getToDate()==null) ;
	}
}
