package org.satellite;

import java.util.List;



public interface TraceService {


	public TerminatorGeo getGeoPoint( String tcode)throws RuntimeException;
	
	
	
	public List<TerminatorGeo> getAllGeoPoint()throws RuntimeException;
	
}
