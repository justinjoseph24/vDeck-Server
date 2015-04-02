package com.amburn.ws;
 
import java.util.*;
import javax.jws.WebService;
import com.amburn.game.*;

/*
* Service Implementation which hanldes all XML Client and Client-Host requests. 
* The below class routes XML requests to the correct game instances based on 
* sessionId and gameId.
*/

@WebService(endpointInterface = "com.amburn.ws.VDeckServer")
public class VDeckServerImpl implements VDeckServer{
 
	@Override
			
		
		/* Shows all running game instances. */
		
		if (query[0].equals("ShowGames")) {
			int theSize;
			theSize = gamesRn.size() > 0 ? gamesRn.size() * 2 : 1;
			String[] actGames = new String[theSize];
			if(gamesRn.size() == 0) { 
				actGames[0] = "None";
			}
			int i = 0;
			for(Game aGame : gamesRn) {
				actGames[i] = aGame.getGameName();
				actGames[i + 1] = " " + aGame.getGameId();
				i++; i++;
			}
			return actGames;	
		}
		
		
		/* 
		* Create a game. The client that creates the game will be 
		* known as the Host-Client. 
		*/
		
		if (query[0].equals("CreateGame")) {
			if (query.length < 2) {
				return new String[] {"Required: [CreateGame,
					gameName]."};
			}
			if (sessionIds.size() > maxSessions) {
				return new String[] {"Too many sessions."};
			}
			if (gamesRn.size() > maxGames) {
				return new String[] {"Too many games."};
			}
			if (! query[1].equals("War")) {
					return new String[] {"Required: " +
						"[CreateGame,War||52Pickup]. "
						+ "Got: " + query[0] + " " +
					query[1]};
			}
			// Allocate sessionId and gameId. 
			int thisSessionId = allocateSessionId();
			int thisGameId = allocateGameId();
					
			Player p = new Player("Deck",thisSessionId,thisGameId);
			p.setHost(true);
			playersPl.add(p);
			Game g = new War("War",thisGameId);
			g.playerJoin(p);
			gamesRn.add(g);
			return new String[] {"" + thisSessionId, "" + 
			thisGameId};
		}
		
		
		
		/* 
		* Allow a client to join an existing game, if there's open 
		* player slots and open game slots.
		*/
		
		if (query[0].equals("JoinGame")) { 
			if (query.length < 3) {
				return new String[] {"Requred: [JoinGame,"
					+ "GameId,PlayerName]."};
			}
			if (sessionIds.size() > maxSessions) {
				return new String[] {"Too many sessions."};
			}
			if (gamesRn.size() > maxGames) {
				return new String[] {"Too many games."};
			}
			int thisGameId = Integer.parseInt(query[1]);
			String playerName = query[2];				
			boolean fnd = false;
			for (Game aG : gamesRn) {
				if (aG.getGameId() == thisGameId) {
					fnd = true;
					int thisSessionId = allocateSessionId();
					Player p = new Player(playerName,
						thisSessionId,thisGameId);
					playersPl.add(p);					
					aG.playerJoin(p);
					return new String[] {"" + 
					thisSessionId};
				}
			}
			if (! fnd) {
				return new String[] {"Unable to find gameId: " +
				thisGameId};
			}
		}
		
		/* Shuffle the deck and deal the cards. */
		
		if (query[0].equals("HostDeal")) { 
				if (query.length < 4) {
					return new String[] {"Requred: " +
						"[HostDeal,CardNum,SessionId,"
						+ "GameId]."};
				}
				int n = Integer.parseInt(query[1]);
				int thisSid = Integer.parseInt(query[2]);
				int thisGid = Integer.parseInt(query[3]);
				Game thisG = findGameByGid(thisGid);
				if (thisG == null) {
					return new String[] {"Can't start " +
						"game: Game doesn't exist."};
				}	
				Player thisP = findPlayerBySid(thisSid);
				if (thisP == null) {
					return new String[] {"Can't start " + 
						"game: Unrecognized player"};
				}
				if (! thisP.isHost() ) {
					return new String[] {"Can't deal: " + 
					"Only the table can HostDeal."};
				}			
				if(thisG.getGameInPrg()) {
					return new String[] {"Dealer already"
						+ "dealt."};
				}
				else {
					thisG.setGameInPrg(true);	
					String[] response = thisG.Deal(n,MsgQ);
					return response;				
				}
		}
		
		
				
		/* Return a comma-delimited list of integers 1-52 for each card 
		* for sessionId */ 
		
		if (query[0].equals("GetPlayerCardsList")) { 
				if (query.length < 3) {
					return new String[] {"Requred: " + 
						"[GetPlayerCardsList," + 
						"SessionId,GameId]."};
				}
				int thisSid = Integer.parseInt(query[1]);
				int thisGid = Integer.parseInt(query[2]);
				Game thisG = findGameByGid(thisGid);
				if (thisG == null) {
					return new String[] {"Can't deal: " + 
						"Game doesn't exist."};
				}	
				Player thisP = findPlayerBySid(thisSid);
				if (thisP == null) {
					return new String[] {"Can't deal: " + 
						"Unrecognized player"};
				}
				if (! thisG.getGameInPrg() ) {
					return new String[] {"Can't deal: " + 
						"The game hasn't started yet."};
				}
				String[] pCardsArray = 
				thisP.myCards.getCardsArray();
				return pCardsArray;
		}
		
		
		
		/* Return the numbrer of cards a player has. */
		
		if (query[0].equals("GetPlayerCardCnt")) { 
				if (query.length < 3) {
					return new String[] {"Requred: " + 
						"[GetPlayerCardCnt,SessionId,"
						+ "GameId]."};
				}
				int thisSid = Integer.parseInt(query[1]);
				int thisGid = Integer.parseInt(query[2]);
				Game thisG = findGameByGid(thisGid);
				if (thisG == null) {
					return new String[] {"Game doesn't "
						+ "exist."};
				}	
				Player thisP = findPlayerBySid(thisSid);
				if (thisP == null) {
					return new String[] {"Unrecognized " 
						+ "player"};
				}
				if (! thisG.getGameInPrg() ) {
					return new String[] {"The game hasn't "
						+ "started yet."};
				}
				int pCardCnt = thisP.myCards.getCardCnt();
				return new String[] {"" + pCardCnt};
		}		
		
		
		
		/* When a client flings a card, they send an XML message to 
		* PlayerMove */
		
		if (query[0].equals("PlayerMove")) { 
			if (query.length < 3) {
				return new String[] {"Requred: [PlayerMove," +
					"SessionId,GameId]."};
			}
			int sessionId = Integer.parseInt(query[1]);
			int gameId = Integer.parseInt(query[2]);
			boolean fnd = false;
					
			// Find the current game.
			for (Game aG : gamesRn) {
				if (aG.getGameId() == gameId) {
					Player p = aG.getPlayer(sessionId);
					if (p != null) {
						String[] response =
						aG.playerMove(p,MsgQ);
						if(response == null) {
							System.out.println(
								"Response is"
								+ "NULL!");
						}
						return response;
					}
					else {
						return new String[] {
							"Unable to" find player"
							+ "in game"};
					}
				}
			}
			if (! fnd) {
				return new String[] {"Unable to find gameId: "
				+ gameId};
			}
			
			
		}
		
		
		/* The Host-Client polls the server for status updates. */
		
		if (query[0].equals("PollForAction")) {
			if (query.length < 3) {
				return new String[] {"Requred: [PollForAction,"
					+ "SessionId,GameId]."};
			}
			int thisSessionId = Integer.parseInt(query[1]);
			int thisGameId = Integer.parseInt(query[2]);
			String[] msg = MsgQ.peek();
			if (msg == null) {
				return new String[] {"" + thisSessionId, "" +
				thisGameId,"systemMsg","Nothing to do."};
			}			
			int qGameId = Integer.parseInt(msg[1]);
			int qSessionId = Integer.parseInt(msg[0]);
			if (qSessionId == thisSessionId && qGameId == 
				thisGameId) {
				MsgQ.poll();
				return msg;
			}
			else {
				return new String[] {"" + thisSessionId,"" +
				thisGameId,"systemMsg","Nothing to do."};
			}
		}		
			
		return new String[] {"Some non-existent method was called."};
	}
	
	
	public int allocateSessionId() {
		int sid = -1;
		for(int i = 0; i < maxSessions; i++) {
			if(! sessionIds.contains(i)) {
				sid = i;
				sessionIds.add(sid);
				break;
			}
		}
		return sid;
	}
	
	public boolean releaseSessionId(int sid) {
		return(sessionIds.remove(new Integer(sid)));
	}
	
	public int allocateGameId() {
		int gid = -1;
		for(int i = 0; i < maxGames; i++) {
			if(! gameIds.contains(i)) {
				gid = i;
				gameIds.add(new Integer(gid));
				break;
			}
		}	
		return gid;
	}
	
	public boolean releaseGameId(int gid) {
		return(gameIds.remove(new Integer(gid)));
	}
	
	Game findGameByGid(int gid) {
		for(Game g : gamesRn) {
			if (g.getGameId() == gid) {
				return g;
			}
		}
		return null;
	}
	
	Player findPlayerBySid(int sid) {
		for(Player p : playersPl) {
			if (p.getSessionId() == sid) {
				return p;
			}
		}
		return null;
	}	
	
	
	
	/* Ends all games, deletes all players, and empties the queue. */
	
	public String[] action(String[] query) {
		if (query == null || query[0] == null) {
			return new String[] {"No query specified."};
		}
		
		if (query[0].equals("Restart")) {
			while(gamesRn.size() > 0) {
				Game g = gamesRn.pop();
				g = null;
			}
			while(playersPl.size() > 0) {
				Player p = playersPl.pop();
				p = null;
			}
			while(sessionIds.size() > 0) {
				sessionIds.pop();
			}
			while(gameIds.size() > 0) {
				gameIds.pop();
			}
			while(MsgQ.peek() != null) {
				MsgQ.poll();
			}
			return new String[] { "Restart OK"};
		}	
	
	// Active games.	
	LinkedList<Game> gamesRn= new LinkedList<Game>(); 

	// Active players.
	LinkedList<Player> playersPl = new LinkedList<Player>(); 
	
	// Active sessions.
	LinkedList<Integer> sessionIds = new LinkedList<Integer>(); 
	
	// All gameIds.
	LinkedList<Integer> gameIds = new LinkedList<Integer>(); 
	
	private int maxSessions = 5;
	private int maxGames = 5;
	
	// Global message queue for Host-Client.
	Queue<String[]> MsgQ = new LinkedList<String[]>(); 

}
