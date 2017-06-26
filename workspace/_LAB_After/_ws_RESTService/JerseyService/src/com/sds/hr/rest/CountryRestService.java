package com.sds.hr.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import com.sds.hr.service.CountryService;
import com.sds.hr.service.CountryServiceImpl;
import com.sds.hr.vo.Country;
import com.sds.hr.vo.RestResult;

@Path("/service")
public class CountryRestService {
	
	private CountryService countryService = new CountryServiceImpl();
	
	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("country/{country_id}")
	public RestResult updateCountry(
			JAXBElement<Country> data,
			@PathParam("country_id") final String country_id) throws Exception {
		Country c = data.getValue();
		RestResult result = null;
		if (c.getCountry_id().equals(country_id)) {
			countryService.updateCountry(c);
			result = new RestResult(true);
		} else {
			result = new RestResult(false);
		}
	
		return result;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("countries")
	public List<Country> getCountryAll() throws Exception {
		return countryService.getAllCountries();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("country/{country_id}")
	public Country getCountry(
			@PathParam("country_id") final String country_id) throws Exception {
		return countryService.getCountry(country_id);
	}
}
