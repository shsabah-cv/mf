/**
 * HotelsProviders.java
 * @author Shrouq Sabah 
 * Created on May 21, 2019
 */
package com.sunrise.mf.demo.hotels.provider;

import java.util.ArrayList;
import java.util.List;

/**
 *this class contains list of active providers 
 */

public class HotelsProviders {
	
	private final static  List<IProvider> providersList;
	static {
		providersList= new  ArrayList<IProvider>();
	}

	
	
	public static void addProvider(IProvider provider) {
		providersList.add(provider);
	}
	
	public static List<IProvider> getProviders(){
		return providersList;
	}
}
