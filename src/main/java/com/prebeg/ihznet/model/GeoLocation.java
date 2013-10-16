package com.prebeg.ihznet.model;

public class GeoLocation {
	/* based on World Geodetic System 1984 (WGS84)  */
	Double altitude;
	Double longitude;
	Double latitude;
	
	public Double getAltitude() {
		return altitude;
	}
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	
}
