/**
 * IProvider.java
 * @author Shrouq Sabah 
 * Created on May 21, 2019
 */
package com.sunrise.mf.demo.hotels.provider;

/**
 * template of provider.
 * for each provider we ned only to implement this interface.
 */
public interface IProvider {
	
	//provider name
	public String getProviderName();
	//URL of provider API to retrieve available hotels
	public String getProviderAvailableHotelsApiUrl();
	
	public IAvailableHotelRequestMapper getAvailableHotelApiRequestMapper();
	public IAvailableHotelResponseMapper getIAvailableHotelApiResponseMapper();
	
	 

}
