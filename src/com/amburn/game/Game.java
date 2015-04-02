/* Copyright (c) 2014 Justin Amburn. All rights reserved.*/
package com.amburn.game;
import java.util.*;


public class Game {
	Game() {};
	Game(String name, int gid) {
		gameName = name;
		gameId = gid;
		gameInPrg = false;
	}
	
	public Player getPlayer(int sid) {
		for(Player p : Players) {
			if(p.getSessionId() == sid) {
				return p;
			}
		}
		return null;
	}
	
	public LinkedList<Player> getPlayers() {
		return Players;
	}
	
	public int getNumPlayers() {
		return Players.size();
		
	}
	
	public String playerJoin(Player p) {
		System.out.println("playerjoin dummy");
		return "dummy";
	}
	
	public String[] playerMove(Player p, Queue<String[]> Q) {
		return new String[] {"dummy move"};
	}
	
	public String getGameName() {
		return gameName;
	}
	
	public int getGameId() {
		return gameId;
	}

	public int getPlayerCnt() {
		return Players.size();
	}
	
	public boolean getGameInPrg() {
		return gameInPrg;
	}
	
	public void setGameInPrg(boolean b) {
		gameInPrg = b;
	}
	
	public String[] Deal(int n, Queue<String[]> Q) {
		return new String[] {"dummy deal"};
	}
	
	public Player getHost() {
		for(Player p : Players) {
			if(p.isHost()) {
				return p;
			}
		}
		return null;
	}
	
	private int playerNum;
	
	String gameName;
	int gameId;
	protected int minPlayers;
	protected int maxPlayers;	
	boolean gameInPrg;
	protected LinkedList<Player> Players = new LinkedList<Player>();
	Queue<String[]> MsgQ = new LinkedList<String[]>();
}
