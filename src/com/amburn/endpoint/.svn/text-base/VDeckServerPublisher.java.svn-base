package com.amburn.endpoint;
import java.net.InetAddress; 
import javax.xml.ws.Endpoint;
import com.amburn.ws.VDeckServerImpl;
 
//Endpoint publisher
public class VDeckServerPublisher{
 
	public static void main(String[] args) {
		
	   try {
	 	InetAddress IP=InetAddress.getLocalHost();
		String Host = InetAddress.getLocalHost().getHostName();
		String IP2;
		System.out.println(Host.toString());
		if (Host.toString().equals("techyon")) {
			IP2 = "130.157.166.108";
		}
		else {
			String[] IpParts = IP.toString().split("/");
			IP2 = IpParts[1];
		}
		Endpoint.publish("http://" + IP2 + ":8080/ws/game", new
			VDeckServerImpl());
	   }
	   catch (Exception e) {
		System.out.println("Failed to get IP");
	   	System.out.println(e.getMessage());
	   }
    }
 
}
