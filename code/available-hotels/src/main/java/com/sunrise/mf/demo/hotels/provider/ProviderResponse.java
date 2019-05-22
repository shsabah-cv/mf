/**
 * ProviderResponse.java
 * @author Shrouq Sabah 
 * Created on May 21, 2019
 */
package com.sunrise.mf.demo.hotels.provider;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class ProviderResponse {
	
	private IProvider provider;
	private List<Map<String,Object>> results;
	/**
	 * 
	 */
	public ProviderResponse() {
		super();

	}
	/**
	 * @param provider
	 * @param results
	 */
	public ProviderResponse(IProvider provider, List<Map<String, Object>> results) {
		super();
		this.provider = provider;
		this.results = results;
	}
	/**
	 * @return the provider
	 */
	public IProvider getProvider() {
		return provider;
	}
	/**
	 * @param provider the provider to set
	 */
	public void setProvider(IProvider provider) {
		this.provider = provider;
	}
	/**
	 * @return the results
	 */
	public List<Map<String, Object>> getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(List<Map<String, Object>> results) {
		this.results = results;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		result = prime * result + ((results == null) ? 0 : results.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProviderResponse other = (ProviderResponse) obj;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		if (results == null) {
			if (other.results != null)
				return false;
		} else if (!results.equals(other.results))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProviderResponse [");
		if (provider != null) {
			builder.append("provider=");
			builder.append(provider);
			builder.append(", ");
		}
		if (results != null) {
			builder.append("results=");
			builder.append(results);
		}
		builder.append("]");
		return builder.toString();
	}
	
	

}
