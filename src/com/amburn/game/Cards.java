/* Copyright (c) 2014 Justin Amburn. All rights reserved.*/
package com.amburn.game;

import java.util.*;

public class Cards {
	Cards() { };
	
	public int getCardCnt() {
		return cards.size();
	}	
	
	public String getCardsList() {
		String cardsStr = "";
		for(Card card : cards) {
			cardsStr = cardsStr + " " + card.getVal();
		}
		return cardsStr;
	}	
	public String[] getCardsArray() {
		String[] cardsArray = new String[cards.size()];
		int i = 0;
		for ( Card c : cards ) {
			cardsArray[i] = Integer.toString(c.getVal());
			++i;
		}
		return cardsArray;
	}	

	Card removeCard() {
		return cards.remove();
	}	
		
	public void removeCard(Card c) {
		cards.remove(c);
	}
	
	public Card giveTopCard() {
		return cards.pollFirst();
	}
	
	public void addCard(Card c) {
		cards.add(c);
	}	

	public int size() {
		return cards.size();
	}
	
	public void clearCards() {
		cards.clear();
	}
	

	
	/* Insert the card so it comes before the next higher value card. */ 
	
	public void addCardSrtd(Card c) {
		if (cards.size() == 0) {
			cards.add(c); 
			return;
		}
		else { 
			Card a = null;			
		 	ListIterator<Card> itr = cards.listIterator();
		 	boolean addToTail = false;
		 	while(itr.hasNext()) {
		 		a = itr.next();
		 		if(c.cardCmp(a) == 0 || (c.cardCmp(a) == 1)) { 
		 		// "this" card is less than or equal to the card
		 		// passed in parameter.
		 			itr.previous();
		 			itr.add(c);
		 			break;
		 		}
		 		else if (! itr.hasNext()) {
		 			addToTail = true;
		 		}
			}
			 if (addToTail) {
			 	cards.add(c);
			 }			
		}
	}
	
	
	/* Build a random deck of n cards. */
	
	public void newDeck(int n) { 
		Random rgen = new Random();
		int[] tmp = new int[n];
		for(int i = 0; i < n; i++) {
			tmp[i] = i + 1;
		}
		for (int i=0; i < tmp.length; i++) { // Randomize the array.
		    int randomPosition = rgen.nextInt(tmp.length);
		    int temp = tmp[i];
		    tmp[i] = tmp[randomPosition];
		    tmp[randomPosition] = temp;
		}		
		for (int i = 0; i < n; i++) {
			Card aCard = new Card(tmp[i]);
			cards.push(aCard);
		}
		if (n == 0) { // A debugging helper for the client team to 
			// produce lots of ties.
			tmp = new int[12];
			for(int i = 0; i < 12; i++) {
				tmp[i] = i + 1;
			}
			for (int i=0; i < tmp.length; i++) { 
			    tmp[0] = 13;
			    tmp[1] = 26;
			    tmp[2] = 39;
			    tmp[3] = 52;
			    tmp[4] = 13;
			    tmp[5] = 26;
			    tmp[6] = 39;
			    tmp[7] = 52;	
			    tmp[8] = 13;
			    tmp[9] = 4;
			    tmp[10] = 5;
			    tmp[11] = 6;
			    tmp[12] = 7;
			    tmp[13] = 8;				    
			}		
			for (int i = 0; i < 12; i++) {
				Card aCard = new Card(tmp[i]);
				cards.push(aCard);
			}
		}			
	}
	
	public void printDeck() {
		for ( Card c : cards ) {
			System.out.println(c.getVal() % 13);
		}
	}
	
	public void sortDeck() {
		Cards tmpCards = new Cards();
		while(getCardCnt() != 0) {
			Card c = giveTopCard();
			tmpCards.addCardSrtd(c);
		}
		for(Card c : tmpCards.cards) {
			cards.push(c);
		}
		tmpCards = null;
		
	}
	
	private LinkedList<Card> cards = new LinkedList<Card>();
	
	public static void main(String args[]) { // Test the Cards functions.
		Cards cds = new Cards();
		cds.newDeck(52);
		System.out.println("Random deck");
		cds.printDeck();
		System.out.println("------");
		cds.sortDeck();
		cds.printDeck();
	}	
}
