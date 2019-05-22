/**
 * AvailableHotelsService.java
 * @author Shrouq Sabah 
 * Created on May 21, 2019
 */
package com.sunrise.mf.demo.hotels.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sunrise.mf.demo.hotels.dto.models.AvailableHotel;
import com.sunrise.mf.demo.hotels.dto.models.AvailableHotelSearchCriteria;
import com.sunrise.mf.demo.hotels.provider.HotelsProviders;
import com.sunrise.mf.demo.hotels.provider.IProvider;
import com.sunrise.mf.demo.hotels.provider.ProviderResponse;

/**
 *
 */
@Service
public class AvailableHotelsService {

	private Logger logger = LoggerFactory.getLogger(AvailableHotelsService.class);
	
	

	//Sorting policy of available hotels service
	private final Comparator<AvailableHotel> availableHotelsComparatorDesc = new Comparator<AvailableHotel>() {

		@Override
		public int compare(AvailableHotel o1, AvailableHotel o2) {
			if (o1 == null && o2 == null) {
				return 0;
			} else if (o1 == null) {
				return 1;
			} else if (o2 == null) {
				return -1;
			}

			return o2.getRate() - o1.getRate();
		}
	};

	private RestTemplate restTemplate = new RestTemplate();

	//Retrieve available hotels from all providers in parallel execution then gathering data 
	public List<AvailableHotel> retrieveAvailableHotels(AvailableHotelSearchCriteria searchCriteria) {

		
		List<AvailableHotel> listOfAvailableHotel = new ArrayList<AvailableHotel>();

		List<CompletableFuture<ProviderResponse>> providersResult = new ArrayList<CompletableFuture<ProviderResponse>>();

		//prepare threads from list of providers
		List<IProvider> providersList = HotelsProviders.getProviders();
		CompletableFuture<ProviderResponse> providerData = null;
		for (IProvider provider : providersList) {

			try {
				providerData = findAvailableHotels(provider.getAvailableHotelApiRequestMapper().map(searchCriteria),
						provider);

				providersResult.add(providerData);

			} catch (Exception ex) {
				logger.error("the provider has error during finding avilable hotels ", ex);
			}

		}

		//join execution of all threads 
		CompletableFuture<?>[] arrProviders = new CompletableFuture<?>[providersResult.size()];
		CompletableFuture.allOf(providersResult.toArray(arrProviders));

		//Gathering data
		for (CompletableFuture<ProviderResponse> pr : providersResult) {
			try {
				List<Map<String, Object>> results = pr.get().getResults();
				if(results!=null) {
					for (Map<String, Object> r : results) {

						listOfAvailableHotel
								.add(pr.get().getProvider().getIAvailableHotelApiResponseMapper().map(r, searchCriteria));

					}
				}
				
			} catch (InterruptedException | ExecutionException ex) {
				logger.error("error when retrive result from provider", ex);
			}

		}
		return listOfAvailableHotel;

	}

	
	//Sorting list of hotels according sorting policy 
	public void sortAvailableHotels(List<AvailableHotel> hotels) {

		hotels.sort(availableHotelsComparatorDesc);

	}

	//thread code to retrieve data form provider by calling API
	@Async
	private CompletableFuture<ProviderResponse> findAvailableHotels(Map<String, Object> request, IProvider provider) {

		ProviderResponse providerResponse = new ProviderResponse();
		providerResponse.setProvider(provider);
		
		try {
			

			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", "application/json");

			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(provider.getProviderAvailableHotelsApiUrl())
				     ;
			for(  Entry<String, Object> qp:request.entrySet()) {
				builder.queryParam(qp.getKey(), qp.getValue());
			}
			
			HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<Map<String, Object>>(headers);
			ResponseEntity<List<Map<String, Object>>> httpResponse = restTemplate.exchange(
					builder.toUriString(), HttpMethod.GET, httpEntity,
					new ParameterizedTypeReference<List<Map<String, Object>>>() {
					});

			providerResponse.setResults(httpResponse.getBody());
			
		}catch(Exception ex) {
			logger.error("can not call API of provider : "+provider.toString(),ex);
		}
		
		return CompletableFuture.completedFuture(providerResponse); 
	}

}
