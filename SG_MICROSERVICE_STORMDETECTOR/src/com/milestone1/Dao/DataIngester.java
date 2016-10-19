package com.milestone1.Dao;



import javax.ws.rs.BeanParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;

import com.milestone1.beanParam.*;
import com.milestone1.Service.*;


@Path("/dataingester")
//@Produces(MediaType.TEXT_XML)
@Produces("application/xml")
public class DataIngester {
	private DataIngesterService DIservice;

	@GET
	//@POST
	public String returnURL(@BeanParam DataIngesterParamHolder dataingesterparam) {
		DIservice = new DataIngesterService();
		return DIservice.returnBuiltURL(dataingesterparam.getDate(), dataingesterparam.getTime(),
				dataingesterparam.getNEXRADstation());
		
		
	}
	
	/*@GET
	public String returnURL1(@DefaultValue("00") @QueryParam("date") String date,
			@DefaultValue("00") @QueryParam("time") int time,
			@DefaultValue("giveMeSomeName") @QueryParam("NEXRADstation") String nexrad) {
		DIservice = new DataIngesterService();
		return DIservice.returnBuiltURL(date,time,nexrad);
	}*/

	
	
	
	
	
}
	
	