/**
 * IAvailableHotelResponseMapper.java
 * @author Shrouq Sabah 
 * Created on May 21, 2019
 */
package com.sunrise.mf.demo.hotels.provider;

import java.util.Map;

import com.sunrise.mf.demo.hotels.dto.models.AvailableHotel;
import com.sunrise.mf.demo.hotels.dto.models.AvailableHotelSearchCriteria;

/**
 *this class to convert API provider response to our model also utilize request data for business logic
 */
public interface IAvailableHotelResponseMapper {

	public AvailableHotel map(Map<String,Object>  response,AvailableHotelSearchCriteria searchCriteria);
}
