package test;
import java.util.List;

import com.sds.hr.service.CountryService;
import com.sds.hr.service.CountryServiceImpl;
import com.sds.hr.vo.Country;


public class Test {

	public static void main(String[] args) throws Exception {
		CountryService service = new CountryServiceImpl();
//		List<Country> list = service.getAllCountries();
//		for(int i=0; i < list.size(); i++) {
//			System.out.println(
//				list.get(i).getCountry_id() + " : " + list.get(i).getCountry_name()
//			);
//		}
		
		Country c  = new Country();
		c.setCountry_id("AR");
		c.setCountry_name("Argentina");
		service.updateCountry(c);
		
		Country c2 = service.getCountry("AR");
		System.out.println(c2.getCountry_id() + " : " + c2.getCountry_name());
	}

}
