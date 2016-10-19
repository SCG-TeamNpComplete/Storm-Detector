package com.milestone1.Dao;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class JsonCreation {
	public JSONObject getObj() {
		return obj;
	}
	public void setObj(JSONObject obj) {
		this.obj = obj;
	}
	JSONObject obj = new JSONObject();
	Message msg=new Message();
	public JsonCreation(){
		  	

	      try {
	    	msg.setReqId(10);
	    	msg.setUserId("soumya");
	    	msg.setServiceId("StormDetection");
	    	msg.setText("test text");
			/*obj.put("userId", "soumya");
			obj.put("reqId",10);
		    obj.put("serviceId", "StormDetection");
		    obj.put("text", "test text, we can put some data here. May be KML output");*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      System.out.print(msg);
	      //setObj(obj);
	      
	      
		ClientConfig config1 = new ClientConfig();
		System.out.println("ClientConfig config1 ");
		Client client1 = ClientBuilder.newClient(config1);
		 System.out.println("Client client1 ");
		// http://ec2-54-69-92-137.us-west-2.compute.amazonaws.com:65000/forecast_decision/json
		//WebTarget target1 =
		//client1.target("http://localhost:8080/SG_SC").path("gateway").path("StormDetection").path("send");
		//WebTarget target1 = client1.target("http://ec2-54-69-92-137.us-west-2.compute.amazonaws.com:65000/forecast_decision").path("json");
		WebTarget target1 = client1.target("http://ec2-54-69-92-137.us-west-2.compute.amazonaws.com:8080/SG_MICROSERVICE_REGISTRY/gateway").path("message").path("saveData");
		//WebTarget target1 = client1.target("http://localhost:8080/SG_MICROSERVICE_STORMDETECTOR").path("gateway").path("StormDetection").path("sendjson");
		System.out.println("WebTarget");
		//// target1.queryParam("name1", "value1");

		//Response response1 = target1.request().post(Entity.entity("hii","application/xml"),Response.class);
		Response response1 = target1.request().post(Entity.entity(msg,"application/json"), Response.class);
	
		String responsefrom;
		responsefrom = target1.request().post(Entity.entity(msg,"application/json"), String.class);
		System.out.println(response1.toString());
		System.out.println();
		System.out.println("response is"+responsefrom);
		
	}
	
}
