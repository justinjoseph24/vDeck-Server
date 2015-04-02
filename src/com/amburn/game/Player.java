/* Copyright (c) 2014 Justin Amburn. All rights reserved.*/
package com.amburn.game;
import java.util.*;

public class Player {
	public Player(String name, int sid, int pid) { // Default constructor
		playerName = name;
		sessionId = sid;
		myTurn = true;
		playerOut = false;
	}
	
	public Player(Player p) { // Copy constructor
		playerName = p.getPlayerName(); 
		sessionId = p.getSessionId();
		myTurn = p.getMyTurn();
		playerOut = false;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public int getSessionId() {
		return sessionId;
	}
	
	public Card popTopCard() { 
		if (myCards.size() == 0) {
			return null;
		}
		return myCards.giveTopCard();
	}
	
	public void receiveCard(Card c) {
		myCards.addCard(c);
	}
	
	public boolean isHost() {
		return isHost;
	}
	
	public void setHost(boolean ih) {
		isHost = ih;
	}
	
	public void setSessionId(int sid) {
		sessionId = sid;
	}
		
	public void setMyTurn(boolean b) {
		myTurn = b;
	}
	
	public boolean getMyTurn() {
		return myTurn;
	}
	
	public void setPlayedCard(Card c) {
		playedCard = c;
	}
	
	public Card getPlayedCard() {
		return playedCard;
	}
		
	public static void main(String args[]) {
	}
	
	private String playerName;
	private int sessionId;
	private boolean isHost;
	private boolean myTurn;
	public boolean playerOut;
	public Cards myCards = new Cards();
	public Cards myDiscardPile = new Cards();
	public Card playedCard = new Card();
}
