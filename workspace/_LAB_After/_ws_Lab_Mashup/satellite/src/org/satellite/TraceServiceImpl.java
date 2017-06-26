package org.satellite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("TraceService")

public class TraceServiceImpl implements TraceService {
	
	double[][] loc = new double[10][2];
	
	public TraceServiceImpl(){
		
		loc[0] = new double[]{37.500959,127.036393};
		loc[1] = new double[]{37.497810,127.027659};
		loc[2] = new double[]{37.476561,126.981740};
		loc[3] = new double[]{37.484224,126.929727};
		loc[4] = new double[]{37.508534,126.891618};
		loc[5] = new double[]{37.530659,126.883078};
		loc[6] = new double[]{37.575093,126.967750};
		loc[7] = new double[]{37.561589,126.994786};
		loc[8] = new double[]{37.544986,127.024741};
		loc[9] = new double[]{37.513675,127.030964};
	}
	

	@Override
	public TerminatorGeo getGeoPoint(String tcode) throws RuntimeException {
		
		TerminatorGeo result = new TerminatorGeo();
		result.setTcode(tcode);
		result.setLongitude(127.143402);
		result.setLatitude(37.479762);
		
		return result;
	}

	@Override
	public List<TerminatorGeo> getAllGeoPoint() throws RuntimeException {

		List<TerminatorGeo> list = new ArrayList<TerminatorGeo>();
		
		moveTerminators();
		
		for(int i = 0; i < 10; i++){
			TerminatorGeo result = new TerminatorGeo();
			if (i % 4 == 0)
				result.setTcode("T-800");
			else if (i % 4 == 1)
				result.setTcode("T-1000");
			else if (i % 4 == 2)
				result.setTcode("T-X New Terminator");
			else 
				result.setTcode("T-850");
			
			result.setLongitude(loc[i][1]);
			result.setLatitude(loc[i][0]);
			
			if(i == 0){
				System.out.println(result.getTcode()+ " : " +loc[i][1]+":"+loc[i][0]);
			}
			
			list.add(result);
			
		}
		
		return list;
	}


	
	
	private synchronized void moveTerminators() {

		int directionX = -1;
		int directionY = -1;
		
		for(int i = 0; i < loc.length ; i++){
			
			double[] target = loc[i];
			
			int temp1 =  (int)(Math.random()*100);
			int temp2 =  (int)(Math.random()*100);
			
			if(temp1 > 50 ){
				directionX = temp1 ;
			}else{
				directionX = temp1 * -1;
			}
			
			if(temp2 > 50 ){
				directionY = temp2 ;
			}else{
				directionY = temp2 * -1;
			}
			target[0] =    ( (target[0]*100000) + directionX ) / 100000;
			target[1] =    ( (target[1]*100000) + directionY ) / 100000;
			

		}
		
		
	}

}
