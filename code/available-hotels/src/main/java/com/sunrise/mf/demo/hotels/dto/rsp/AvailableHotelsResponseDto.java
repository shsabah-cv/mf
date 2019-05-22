/**
 * AvailableHotelsResponseDto.java
 * @author Shrouq Sabah 
 * Created on May 21, 2019
 */
package com.sunrise.mf.demo.hotels.dto.rsp;

import java.util.List;

/**
 *
 */

public class AvailableHotelsResponseDto {
	
	private String provider;//name of the provider (“BestHotels” or “CrazyHotels”)
	private String hotelName;//Name of the hotel
	private double fare;//fare per night
	private List<String> amenities;//a desirable or useful feature or facility of hotel
	/**
	 * 
	 */
	public AvailableHotelsResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param provider
	 * @param hotelName
	 * @param fare
	 * @param amenities
	 */
	public AvailableHotelsResponseDto(String provider, String hotelName, double fare, List<String> amenities) {
		super();
		this.provider = provider;
		this.hotelName = hotelName;
		this.fare = fare;
		this.amenities = amenities;
	}
	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}
	/**
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}
	/**
	 * @return the hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}
	/**
	 * @param hotelName the hotelName to set
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	/**
	 * @return the fare
	 */
	 
	public double getFare() {
		return fare;
	}
	/**
	 * @param fare the fare to set
	 */
	public void setFare(double fare) {
		this.fare = fare;
	}
	/**
	 * @return the amenities
	 */
	public List<String> getAmenities() {
		return amenities;
	}
	/**
	 * @param amenities the amenities to set
	 */
	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amenities == null) ? 0 : amenities.hashCode());
		long temp;
		temp = Double.doubleToLongBits(fare);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((hotelName == null) ? 0 : hotelName.hashCode());
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
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
		AvailableHotelsResponseDto other = (AvailableHotelsResponseDto) obj;
		if (amenities == null) {
			if (other.amenities != null)
				return false;
		} else if (!amenities.equals(other.amenities))
			return false;
		if (Double.doubleToLongBits(fare) != Double.doubleToLongBits(other.fare))
			return false;
		if (hotelName == null) {
			if (other.hotelName != null)
				return false;
		} else if (!hotelName.equals(other.hotelName))
			return false;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AvailableHotelsResponseDto [");
		if (provider != null) {
			builder.append("provider=");
			builder.append(provider);
			builder.append(", ");
		}
		if (hotelName != null) {
			builder.append("hotelName=");
			builder.append(hotelName);
			builder.append(", ");
		}
		builder.append("fare=");
		builder.append(fare);
		builder.append(", ");
		if (amenities != null) {
			builder.append("amenities=");
			builder.append(amenities);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
