package com.milestone1.Dao;

import java.io.IOException;

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
public class StormDetectorInit {
	@GET
	@Path("/init")
	@Produces("text/plain")
	public String initZookeeper() throws IOException{
		
		//ZookeeperServiceRegistry registry=new ZookeeperServiceRegistry();
		String endpointURI = "http://localhost:8080/SG_MICROSERVICE_STORMDETECTOR/gateway/StormDetection/get";
	    //private final String endpointURI = "http://" + serverName + ":" + serverPort + "/catalog/resources/catalog";
	    //private final String endpointURI = "http://" + WildFlyUtil.getHostName() + ":" + WildFlyUtil.getHostPort() + "/catalog/resources/catalog";
	    String serviceName = "stormDetection";
		
	    int port = 8080;

		CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("localhost:2181",
				new RetryNTimes(5, 1000));
		curatorFramework.start();
		try {
			
			ServiceInstance serviceInstance = ServiceInstance.builder().uriSpec(new UriSpec(endpointURI)).address("localhost").port(port).name(serviceName).build();
			ServiceDiscoveryBuilder.builder(Void.class).basePath("load-balancing-example").client(curatorFramework)
			.thisInstance(serviceInstance).build().start();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    //registry.unregisterService(serviceName, endpointURI);
	    
	    //registry.discoverServiceURI(serviceName);
		return "done";
	}

}
