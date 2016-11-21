package com.milestone1.Dao;


import javax.servlet.http.HttpServlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;

@Path("/StormDetection")
public class StormDetectorInit extends HttpServlet {

	@Override
	public void init() throws ServletException {
		String ip=null;
    	try
    	{
	    	URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(
							whatismyip.openStream()));
	
			ip = in.readLine(); //you get the IP as a String
			System.out.println(ip);
    	}
    	catch (Exception exception)
    	{
    		exception.printStackTrace();
    	}
    	System.out.println("Registering data detector");
		// ZookeeperServiceRegistry registry=new ZookeeperServiceRegistry();
		String endpointURI = "http://"+ip+":8080/SG_MICROSERVICE_STORMDETECTOR/gateway/StormDetection/get";
		// private final String endpointURI = "http://" + serverName + ":" +
		// serverPort + "/catalog/resources/catalog";
		// private final String endpointURI = "http://" +
		// WildFlyUtil.getHostName() + ":" + WildFlyUtil.getHostPort() +
		// "/catalog/resources/catalog";
		String serviceName = "stormDetection";

		int port = 8080;

		
		 /*CuratorFramework curatorFramework =
		 CuratorFrameworkFactory.newClient("localhost:2181", new
		 RetryNTimes(5, 1000)); curatorFramework.start();*/
		 
		 CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("ec2-35-161-48-143.us-west-2.compute.amazonaws.com:2181", new RetryNTimes(5, 1000));
		  curatorFramework.start();

		/*CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("10.0.0.39:2888:2181",
				new RetryNTimes(5, 1000));
		curatorFramework.start();
*/
		try {

			ServiceInstance serviceInstance = ServiceInstance.builder().uriSpec(new UriSpec(endpointURI))
					.address("localhost").port(port).name(serviceName).build();
			ServiceDiscoveryBuilder.builder(Void.class).basePath("load-balancing-example").client(curatorFramework)
					.thisInstance(serviceInstance).build().start();
			System.out.println("Registered data detector");
			// return "SD register done";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// registry.unregisterService(serviceName, endpointURI);

		// registry.discoverServiceURI(serviceName);
		// return "cancel";

	}

	/*@GET
	@Path("/init")
	@Produces("text/plain")
	public String initZookeeper() throws IOException {

		// ZookeeperServiceRegistry registry=new ZookeeperServiceRegistry();
		String endpointURI = "http://localhost:8080/SG_MICROSERVICE_STORMDETECTOR/gateway/StormDetection/get";
		// private final String endpointURI = "http://" + serverName + ":" +
		// serverPort + "/catalog/resources/catalog";
		// private final String endpointURI = "http://" +
		// WildFlyUtil.getHostName() + ":" + WildFlyUtil.getHostPort() +
		// "/catalog/resources/catalog";
		String serviceName = "stormDetection";

		int port = 8080;

		
		 * CuratorFramework curatorFramework =
		 * CuratorFrameworkFactory.newClient("localhost:2181", new
		 * RetryNTimes(5, 1000)); curatorFramework.start();
		 

		CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("149.160.232.34:2181",
				new RetryNTimes(5, 1000));
		curatorFramework.start();

		try {

			ServiceInstance serviceInstance = ServiceInstance.builder().uriSpec(new UriSpec(endpointURI))
					.address("localhost").port(port).name(serviceName).build();
			ServiceDiscoveryBuilder.builder(Void.class).basePath("load-balancing-example").client(curatorFramework)
					.thisInstance(serviceInstance).build().start();
			return "SD register done";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// registry.unregisterService(serviceName, endpointURI);

		// registry.discoverServiceURI(serviceName);
		return "cancel";
	}*/

}
