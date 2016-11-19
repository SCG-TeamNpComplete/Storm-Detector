package com.milestone1.Dao;

import java.net.URI;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceProvider;
import org.apache.curator.x.discovery.UriSpec;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.glassfish.jersey.client.ClientConfig;

import com.milestone1.Service.StormDetectionService;


@Path("/StormDetectionManager")
public class StormDetectorManager {
	
	private StormDetectionService stormDetectionService;
	
	
	@GET
	@Path("/delegate")
	@Produces("text/plain")
	public String delegate() {
		
		StromDetection sd=new StromDetection();
		
		/*CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("localhost:2181", new RetryNTimes(5, 1000));
		curatorFramework.start();*/
		
		CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("localhost:2181", new RetryNTimes(5, 1000));
		curatorFramework.start();

		ServiceDiscovery<Void> serviceDiscovery = ServiceDiscoveryBuilder.builder(Void.class).basePath("load-balancing-example").client(curatorFramework).build();
		try {
			serviceDiscovery.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ServiceProvider<Void> serviceProvider = serviceDiscovery.serviceProviderBuilder().serviceName("stormDetection")
				.build();
		try {
			serviceProvider.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ServiceProvider serviceProvider;
		ServiceInstance instance;
		//InstanceProvider instanceProvider;
		
		
		try {
			List<ServiceInstance<Void>> instances =(List<ServiceInstance<Void>>) serviceProvider.getAllInstances();
			if ( instances.size() == 0 )
	        {
	            return null;
	        }
			
	        int thisIndex = stormDetectionService.getIndex();
	        stormDetectionService.setIndex(thisIndex+1);
	        System.out.println("thisIndex"+thisIndex);
	        System.out.println(instances.get(thisIndex % instances.size())); 
	        
	        
	       
			String address = instances.get(thisIndex % instances.size()).getId();
			UriSpec address1 = instances.get(thisIndex % instances.size()).getUriSpec();
			String url=address1.build();
			
			System.out.println("response from storm detector manager");
			System.out.println(url);
			
			
			//URIBuilder builder = new URIBuilder();
			//builder.setScheme("http").setHost("localhost:8080").setPath(url);
			//URI uri = builder.build();
			//HttpGet httpget = new HttpGet(uri);
			
			
			ClientConfig clientConfig = new ClientConfig();
			Client client = ClientBuilder.newClient(clientConfig);
			String response =client.target(url).request().get(String.class);
			System.out.println("response from storm clustering");
			System.out.println(response);
			
			
			
			//sd.generateKML(url);
			
			
			return response;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return "nothing";
	    
	}

}
