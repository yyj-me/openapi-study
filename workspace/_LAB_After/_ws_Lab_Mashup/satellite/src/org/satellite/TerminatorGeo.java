package org.satellite;

public class TerminatorGeo {
	
	private String tcode;
	private double latitude;
	private double longitude;
	

	
	public String getTcode() {
		return tcode;
	}



	public void setTcode(String tcode) {
		this.tcode = tcode;
	}



	public double getLatitude() {
		return latitude;
	}



	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}



	public double getLongitude() {
		return longitude;
	}



	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}



	@Override
	public String toString() {
		return "TerminatorGeo [tcode=" + tcode + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
	
	

}
