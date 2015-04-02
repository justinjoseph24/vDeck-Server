package com.amburn.ws;
import com.amburn.game.*;
 
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
 
//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface VDeckServer{
 
	@WebMethod String getVDeckServerAsString(String name);
	@WebMethod String[] action(String[] name);
}
