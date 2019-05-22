/**
 * IAvailableHotelRequestMapper.java
 * @author Shrouq Sabah 
 * Created on May 21, 2019
 */
package com.sunrise.mf.demo.hotels.provider;

import java.util.Map;

import com.sunrise.mf.demo.hotels.dto.models.AvailableHotelSearchCriteria;

/**
 *this class to convert search criteria to the request of API provider 
 *
 */
public interface IAvailableHotelRequestMapper {

	public Map<String,Object> map(AvailableHotelSearchCriteria  searchCriteria);
}
