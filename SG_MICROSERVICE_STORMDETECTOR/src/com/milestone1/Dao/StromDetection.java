package com.milestone1.Dao;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;

import com.milestone1.Service.StormDetectionService;

@Path("/StormDetection")
public class StromDetection {
	
	private static Logger log=Logger.getLogger(StromDetection.class);
	private StormDetectionService stormDetectionService;
	
	
	public String KML = new String("<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"
					+ "  <Placemark>\n" + "    <name>Simple placemark</name>\n"
					+ "    <description>Attached to the ground. Intelligently places itself \n"
					+ "       at the height of the underlying terrain.</description>\n" + "    <Point>\n"
					+ "      <coordinates>-122.0822035425683,37.42228990140251,0</coordinates>\n" + "    </Point>\n"
					+ "  </Placemark>\n" + "</kml>");

	@GET
	@Path("/get")
	@Produces("application/xml")
	public String generateKML(String newUrl) throws ParseException {
		StromDetection sd = new StromDetection();
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sd.sendURL(newUrl);

		// Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

		// String filevalue =
		// "http://noaa-nexrad-level2.s3.amazonaws.com/2015/03/03/KABX/KABX20150303_001050_V06.gz";

		//String result = "@Produces(\"application/xml\")" + "\n\n" + "Output:\n\n Example KML, we can add tags here "
		//		+ "\n\n" + "\n\n URL for file is " + newUrl + "\n\n" + " use this for download \n\n";
		
		String url=newUrl;
		String result="Generated in StormDetection.java";
		String dummy="";
		
		
		return "<KMLResponseFromStormDetector>" + "<tags>"+ dummy +"</tags>"+ "<newUrl>" + url + "</newUrl>" + "<dummyKml>" + KML + "</dummyKml>"
				+ "<filename>" + result + "</filename>" +"</KMLResponseFromStormDetector>";
	}

	@POST
	@Path("/send")
	@Consumes("application/xml")
	public Response json(String url) throws ParseException, JSONException {
		
		log.info("contacted post method of storm detector");
		
		System.out.println("in send method of detector - successfully posted to detector");
		
		log.info("contacted JsonCreation");
		
		JsonCreation json = new JsonCreation();
		
		
		
		System.out.println("Done sending to DB");
		StromDetection sd = new StromDetection();
		String newUrl = url;
		System.out.println();
		System.out.println("data in detector " + newUrl);
		String KML = sd.generateKML(newUrl);
		sd.sendURL(KML);
		
		log.info("called sendURL() of storm detector");
		
		return Response.status(200).entity(newUrl).build();
	}

	public String sendURL(String url) {
		ClientConfig config1 = new ClientConfig();
		// System.out.println("ClientConfig config1 ");
		Client client1 = ClientBuilder.newClient(config1);
		// System.out.println("Client client1 ");

		WebTarget target1 = client1
				.target("http://ec2-35-161-48-143.us-west-2.compute.amazonaws.com:8888/SG_MICROSERVICE_STROMCLUSTERING/gateway/StormClustering").path("send");
		System.out.println("WebTarget in detector");
		// target1.queryParam("name1", "value1");

		// Response response = target1.request().post(Entity.entity(url,
		// "application/xml"), Response.class);
		String responsefrom;
		System.out.println("posting to StormClustering");
		responsefrom = target1.request().post(Entity.entity(url, "application/xml"), String.class);
		
		log.info("notified storm clustering");
		// System.out.println(response.toString());
		System.out.println();
		System.out.println(responsefrom);
		return url;

	}

}
