package com.milestone1.Dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;
import org.json.JSONObject;

import com.milestone1.Service.StormDetectionService;

@Path("/StormDetection")
public class StromDetection {
	private StormDetectionService stormDetectionService;
	JsonCreation json = new JsonCreation();

	public String KML = new String("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"
					+ "  <Placemark>\n" + "    <name>Simple placemark</name>\n"
					+ "    <description>Attached to the ground. Intelligently places itself \n"
					+ "       at the height of the underlying terrain.</description>\n" + "    <Point>\n"
					+ "      <coordinates>-122.0822035425683,37.42228990140251,0</coordinates>\n" + "    </Point>\n"
					+ "  </Placemark>\n" + "</kml>");

	@GET
	// @POST
	@Path("/get")
	@Produces("application/xml")
	public String generateKML(String newUrl) throws ParseException {
		StromDetection sd = new StromDetection();

		sd.sendURL(newUrl);

		// Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

		// String filevalue =
		// "http://noaa-nexrad-level2.s3.amazonaws.com/2015/03/03/KABX/KABX20150303_001050_V06.gz";

		//String result = "@Produces(\"application/xml\")" + "\n\n" + "Output:\n\n Example KML, we can add tags here "
		//		+ "\n\n" + "\n\n URL for file is " + newUrl + "\n\n" + " use this for download \n\n";
		
		String result="Generated in StormDetection.java";
		
		
		return "<KMLResponseFromStormDetector>" + "<newUrl>" + newUrl + "</newUrl>" + "<dummyKml>" + KML + "</dummyKml>"
				+ "<filename>" + result + "</filename>" + "</KMLResponseFromStormDetector>";
	}

	@POST
	@Path("/send")
	@Consumes("application/xml")
	public Response json(String url) throws ParseException, JSONException {
		StromDetection sd = new StromDetection();
		String newUrl = url;
		System.out.println("data in detector  " + newUrl);
		String KML = sd.generateKML(newUrl);
		sd.sendURL(KML);
		return Response.status(200).entity(newUrl).build();
	}

	public String sendURL(String url) {
		ClientConfig config1 = new ClientConfig();
		// System.out.println("ClientConfig config1 ");
		Client client1 = ClientBuilder.newClient(config1);
		// System.out.println("Client client1 ");

		WebTarget target1 = client1
				.target("http://localhost:8080/SG_MICROSERVICE_STROMCLUSTERING/gateway/StormClustering").path("send");
		System.out.println("WebTarget in detector");
		// target1.queryParam("name1", "value1");

		// Response response = target1.request().post(Entity.entity(url,
		// "application/xml"), Response.class);
		String responsefrom;
		responsefrom = target1.request().post(Entity.entity(url, "application/xml"), String.class);
		// System.out.println(response.toString());
		System.out.println();
		System.out.println(responsefrom);
		return url;

	}

}
