/*package test.java;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StormDetectorTest {
	private ClientConfig clientConfig;
	private Client client;
	private WebTarget target;
	private String result;
	private String dummy;
	private String url;
	private String KML;

	@Before
	public void returnRequiredObjects() {

		result = "Generated in StormDetection.java";
		dummy = "";
		url = "https://noaa-nexrad-level2.s3.amazonaws.com/1996/06/06/hello/hello19960606_001958.gz";
		clientConfig = new ClientConfig();
		client = ClientBuilder.newClient(clientConfig);
		target = client.target("http://ec2-35-161-48-143.us-west-2.compute.amazonaws.com:9999/SG_MICROSERVICE_STORMDETECTOR/gateway/StormDetection")
				.path("send");
		KML = new String("<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" + "  <Placemark>\n"
				+ "    <name>Simple placemark</name>\n"
				+ "    <description>Attached to the ground. Intelligently places itself \n"
				+ "       at the height of the underlying terrain.</description>\n" + "    <Point>\n"
				+ "      <coordinates>-122.0822035425683,37.42228990140251,0</coordinates>\n" + "    </Point>\n"
				+ "  </Placemark>\n" + "</kml>");
	}

	@Test
	public void testJSON() {

		String responsefrom = target.request().post(Entity.entity(url, "application/xml"), String.class);
		System.out.println(responsefrom);
		Assert.assertEquals("https://noaa-nexrad-level2.s3.amazonaws.com/1996/06/06/hello/hello19960606_001958.gz", responsefrom);

	}
}
*/