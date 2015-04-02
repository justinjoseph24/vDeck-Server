/* Copyright (c) 2014 Justin Amburn. All rights reserved.*/
package com.amburn.client;
 
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import com.amburn.ws.VDeckServer;
import java.util.Arrays;

/* This is an XML client that tests basic game functionality by issuing all 
 *  possible client commands. Below it is being used to run a game from start to
 *  finish. 
 */
 
public class VDeckServerClient{
 
	public static void main(String[] args) throws Exception {
 
	URL url = new URL("http://192.168.7.17:8080/ws/game?wsdl");
	//URL url = new URL("http://10.20.3.141:8080/ws/game?wsdl");
 
        //1st argument service URI, refer to wsdl document above
	//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.amburn.com/", "VDeckServerImplService");
 
        Service service = Service.create(url, qname);
 
        VDeckServer game = service.getPort(VDeckServer.class);
 
        System.out.println(Arrays.toString(game.action(new String[] { "ShowGames" })));
        System.out.println(Arrays.toString(game.action(new String[] { "CreateGame", "War" })));
        System.out.println(Arrays.toString(game.action(new String[] { "ShowGames" })));   
        System.out.println(Arrays.toString(game.action(new String[] { "JoinGame", "0", "Han"})));   
        System.out.println(Arrays.toString(game.action(new String[] { "JoinGame", "0", "Ash"})));
        System.out.println(Arrays.toString(game.action(new String[] { "JoinGame", "0", "Brad"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "HostDeal", "52","0", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "GetPlayerCardCnt","1","0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "GetPlayerCardCnt","2","0"})));  
        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   

        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   
        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   
        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   
         
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   
        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   
        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   
        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   
        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   
         
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   
        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   
        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   
        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   
        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   
        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   
        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "1", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));     
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "2", "0"})));
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));        
        System.out.println(Arrays.toString(game.action(new String[] { "PlayerMove", "3", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"}))); 
        System.out.println(Arrays.toString(game.action(new String[] { "PollForAction", "0", "0"})));   

        System.out.println(Arrays.toString(game.action(new String[] { "Restart"})));   
        
    }
 
}
