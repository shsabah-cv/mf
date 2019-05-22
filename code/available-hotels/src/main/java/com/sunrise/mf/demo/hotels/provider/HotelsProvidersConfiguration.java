/**
 * HotelsProvidersConfiguration.java
 * @author Shrouq Sabah 
 * Created on May 22, 2019
 */
package com.sunrise.mf.demo.hotels.provider;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.sunrise.mf.demo.hotels.provider.best_hotel.BestHotelProvider;
import com.sunrise.mf.demo.hotels.provider.crazy_hotel.CrazyHotelProvider;

/**
 *this class to add active providers when the service started 
 */

@Configuration
public class HotelsProvidersConfiguration {

	@Autowired
	private BestHotelProvider bestHotelProvider;

	@Autowired
	private CrazyHotelProvider crazyHotelProvider;

	@PostConstruct
	public void init() {

		HotelsProviders.addProvider(bestHotelProvider);
		HotelsProviders.addProvider(crazyHotelProvider);

	}
}
