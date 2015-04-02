/* Copyright (c) 2014 Justin Amburn. All rights reserved.*/
package com.amburn.game;
import java.lang.System;
import java.util.*;

public class War extends Game {
	public War(String name, int gid) {
		minPlayers = 3;
		maxPlayers = 5;
		gameName = name;
		gameId = gid;
		gameInPrg = false;		
		totCardsInHands = 0;
		dealt = false;			
	};
	
	
	/* Deal out the cards to the players. */
	
	public String[] Deal(int n, Queue<String[]> Q) {      
		if (dealt == false) { 
			Deck.newDeck(n);
			boolean dealing = true;
			while(dealing) {
				for (Player p : Players) {
					if ((p.getPlayerName()).equals("Deck"))
					{ // Don't deal to the deck.
						continue;
					}
					if (Deck.getCardCnt() == 0) {
						dealing = false;
						break;
					}
					Card c = Deck.giveTopCard();
					p.myCards.addCard(c);
				}
				if(dealing == false) {
					break;
				}
			}
			dealt = true;
			String[] rsp = new String[2 *  Players.size() + 2];
			rsp[0] = "Deal OK";
			int playerCnt = Players.size() - 1;
			rsp[1] = "" + playerCnt;
			int i = 2;
			for (Player p : Players) {
				if (p.isHost()) {
					continue;
				}
				rsp[i] = "" + p.getSessionId();
				rsp[i + 1] = p.getPlayerName();
				i++; i++;
			}
			return rsp;
		}
		return new String[] {"Already Dealt"};
	}
	
	
	
	/* Add a new player to the game. */	
	
	public String playerJoin(Player p) {
		Players.add(p);
		System.out.println("Player: " + p.getPlayerName() + " id " + 
			p.getSessionId() + " joined the game");
		return ("sessionId,gameId");
	}
	
	
	public String[] playerMove(Player p, Queue<String[]> Q) {
		if(! p.getMyTurn()) {
			return new String[] {"It's not your turn"};
		}
		if(p.myCards.size() == 0) { 
			return new String[] {"You don't have any cards."};
		}
		if(p.isHost()) {
			return new String[] {"Host doesn't get a turn."};
		}
		
		p.setPlayedCard(p.myCards.giveTopCard());
		System.out.println(p.getPlayerName() + " places a " + 
			(p.getPlayedCard()).getVal());
					
		if (Battle.size() == 0) {
			Battle.add(p); // The first player goes in without 
			// worrying about order.
		}
		else { // For the rest, we iterate over the list.
			Player a = null;			
		 	ListIterator<Player> itr = Battle.listIterator();
		 	boolean addToTail = false;
		 	while(itr.hasNext()) {
		 		a = itr.next();
		 	if((p.getPlayedCard()).cardCmp(a.getPlayedCard())
		 			== 0 || 
		 		(p.getPlayedCard()).cardCmp(a.getPlayedCard())
		 		== 1) { // "this" card is less than or equal 
		 		// to the card passed in parameter.
		 			itr.previous();
		 			itr.add(p);
		 			break;
		 		}
		 		else if (! itr.hasNext()) {
		 			addToTail = true;
		 		}
			}
			 if (addToTail) {
			 	Battle.add(p);
			 }			
		}
				
		p.setMyTurn(false);
		String[] msg = new String[] { "" + (getHost()).getSessionId(),
		"" + gameId, "systemMsg","TableReceivePlayersCard", 
		p.getPlayerName(),"" + p.getSessionId(),"" +
		(p.getPlayedCard()).getVal() ,"SoundEvent", 
		chooseRandString(HThrowSnds)};
		Q.add(msg);
		
		boolean playerStillNeedsToMove = false;
		for(Player aP : Players) { 
			if (aP.isHost()) {
				continue;
			}
			if(aP.getMyTurn()) {
				playerStillNeedsToMove = true;
			}
		}
			
		if (playerStillNeedsToMove) {
			return new String[]{"Cards Remaining:", "" + 
			p.myCards.getCardCnt()}; // OK that the player moved.
		}
		
		
		
		/*
		* If we made it this far, the last player has just moved and 
		* it's time to resolve the Battle. If player has no cards, 
		* the player picks up his discard pile and continues.
		*/
		
		if(p.myCards.size() == 0) {
			if(p.myDiscardPile.size() != 0) {
				while(p.myDiscardPile.size() != 0) {
				p.myCards.addCard(p.myDiscardPile.removeCard());					
			}
			msg = new String[] { "" + (getHost()).getSessionId(), 
			"" + gameId,"systemMsg", "playerPickUpHisDiscardPile",""
			+ p.getSessionId(),"SoundEvent", 
		chooseRandString(PickupSnds) };
			Q.add(msg); 				
			}
		}	
		System.out.println("Time to see who won the round");
		Tie = whoWonRound(Battle);
		if (Tie == null) {
			System.out.println("Error Tie is null.");
		}
		while(Tie.size() > 1) // Jack and Marty need to fix a bug in
			// the client where the GUI hangs after tie. Until then,
			// I've put this workaround in place.
			Tie.pop();

		if(Tie.size() > 1) { // All players involved in the tie get 
			// another turn. If a tie ends up in a tie, add the 
			// cards to the TieOverFlow container. The winner takes
			// all. To Do: Once clients support global messaging, 
			// add Message Announce "Tie breaker between p1 
			// and p2 and ...
						
			for (Player pB : Battle ) {
				TieOverflow.add(pB.getPlayedCard());
				pB.setPlayedCard(null);
			}
			
			Battle.clear(); 
			
			// Reset the turns of the players that tied.
			for (Player pT : Tie) {
				pT.setMyTurn(true);
			}   		
		}
		else {  // The winner gets all cards in the Battle and 
			// TieOverflow containers.
			
			Player winner = Tie.getFirst();
			System.out.println(winner.getPlayerName() + " won the 
				round.");
			
			for (Player pB : Battle ) {
			winner.myDiscardPile.addCard(pB.getPlayedCard());
				System.out.println(winner.getPlayerName() + 
				" gets " + (pB.getPlayedCard()).getVal());
				pB.setPlayedCard(null);
			}			
			for(Card c : TieOverflow) {
				winner.myDiscardPile.addCard(c);
				System.out.println(winner.getPlayerName() + 
					" gets " + (c.getVal()));
			}
			Battle.clear();
			Tie.clear();
			TieOverflow.clear();
			for(Player pl : Players) {
				if(pl.isHost()) {
					msg = new String[] { "" + 
					pl.getSessionId(), "" + gameId,
					"systemMsg","GiveDiscardPileToPlayer",
					"" + winner.getSessionId(), 
					winner.getPlayerName(),"SoundEvent", 
		chooseRandString(WinSnds)};
					Q.add(msg);
					continue;
				}
				if (pl.myCards.size() != 0) {
						pl.setMyTurn(true);
				}
			}
		}
		int playersOut = 0;
		Player gameWinner = null;
		for(Player pl : Players) {
			if(pl.myCards.size() == 0) {
				playersOut++;
			}
		}
		if (playersOut == Players.size() - 1) {	
			for(Player pl : Players) {
				if(pl.myCards.size() != 0) {
					gameWinner = pl;
				}
			}			
			msg = new String[] { "" + (getHost()).getSessionId(), ""
			+ gameId,"systemMsg", "Winner", ""+  + 
			gameWinner.getSessionId(),"SoundEvent", 
		chooseRandString(FinSnds) };
			Q.add(msg);			
		}
		return new String[] { "Cards Remaining:","" + 
		p.myCards.getCardCnt() };
	}

	
	/* The Battle LL contains all of the Players battling, and the players
	*  their card. The highest card or cards if tied are returned as LL. */

	public LinkedList<Player> whoWonRound(LinkedList<Player> Battle) { 
		LinkedList<Player> winners = new LinkedList<Player>();	
		
		if (Battle.size() == 0) {
			return null;
		}
		if (Battle.size() == 1) {
			return Battle;
		}
		ListIterator<Player> itr = Battle.listIterator();
		Player withHighestCard = Battle.getFirst();
		while(itr.hasNext()) {
			Player a = itr.next(); 
		if((a.getPlayedCard()).cardCmp(withHighestCard.getPlayedCard()) 
			== 2) { // The first card is equal to the second.
				withHighestCard = a;
			}
		}
		itr = Battle.listIterator();
		while(itr.hasNext()) {
			Player a = itr.next(); 
		if((a.getPlayedCard()).cardCmp(withHighestCard.getPlayedCard()) 
			== 1) { // The first card is equal to the second.
				 winners.add(a);
			}
		}
		return winners;
	}	

	private int totCardsInHands;
	private Cards Deck = new Cards(); // The deck of cards.
	private boolean dealt; // Whether or not the deal has happened.
	
	// Container for cards to be held until ties are resolved.
	private LinkedList<Card> TieOverflow = new LinkedList<Card>(); 
		
	// Holds cards that a player flicks to the table.
	private LinkedList<Player> Battle = new LinkedList<Player>(); 
	
	// Container for resolving ties.
	private LinkedList<Player> Tie = new LinkedList<Player>();
	
	// Sound containers for Table SoundEvent
	private String[] CThrowSnds = { "CTHROW1", "CTHROW2" };
	private String[] HThrowSnds = { "HTHROW1", "HTHROW2" };
	private String[] PickupSnds = { "PICKUP1", "PICKUP2" };
	private String[] MusTracks = { "MUS1", "MUS2" };
	private String[] TieSnds = { "TIE1", "TIE2" };
	private String[] WinSnds = { "WIN1", "WIN2" };
	private String[] FinSnds = { "FIN1", "FIN2" };
	private String[] ErrSnds = { "ERR1", "ERR2" };
	private String[] MiscSnds = { "MISC1", "MISC2", "MISC3", "MISC4", 
	"MISC5" };	
}
