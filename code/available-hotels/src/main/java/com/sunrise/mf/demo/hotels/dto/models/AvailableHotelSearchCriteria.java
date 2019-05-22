/**
 * AvailableHotelSearchCriteria.java
 * @author Shrouq Sabah 
 * Created on May 21, 2019
 */
package com.sunrise.mf.demo.hotels.dto.models;

import java.time.LocalDateTime;

/**
 *
 */
public class AvailableHotelSearchCriteria {

	private LocalDateTime fromDate;//ISO_LOCAL_DATE
	private LocalDateTime toDate;//ISO_LOCAL_DATE
	private String city; //IATA code (AUH)
	private int numberOfAdults;
	/**
	 * 
	 */
	public AvailableHotelSearchCriteria() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param fromDate
	 * @param toDate
	 * @param city
	 * @param numberOfAdults
	 */
	public AvailableHotelSearchCriteria(LocalDateTime fromDate, LocalDateTime toDate, String city, int numberOfAdults) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.city = city;
		this.numberOfAdults = numberOfAdults;
	}
	/**
	 * @return the fromDate
	 */
	public LocalDateTime getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public LocalDateTime getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the numberOfAdults
	 */
	public int getNumberOfAdults() {
		return numberOfAdults;
	}
	/**
	 * @param numberOfAdults the numberOfAdults to set
	 */
	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + numberOfAdults;
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
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
		AvailableHotelSearchCriteria other = (AvailableHotelSearchCriteria) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (numberOfAdults != other.numberOfAdults)
			return false;
		if (toDate == null) {
			if (other.toDate != null)
				return false;
		} else if (!toDate.equals(other.toDate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AvailableHotelSearchCriteria [");
		if (fromDate != null) {
			builder.append("fromDate=");
			builder.append(fromDate);
			builder.append(", ");
		}
		if (toDate != null) {
			builder.append("toDate=");
			builder.append(toDate);
			builder.append(", ");
		}
		if (city != null) {
			builder.append("city=");
			builder.append(city);
			builder.append(", ");
		}
		builder.append("numberOfAdults=");
		builder.append(numberOfAdults);
		builder.append("]");
		return builder.toString();
	}
	
	
}
