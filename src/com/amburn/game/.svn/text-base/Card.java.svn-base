/* Copyright (c) 2014 Justin Amburn. All rights reserved.*/
package com.amburn.game;

public class Card {
	Card() {}; // Default constructor
	Card(Card c) { // Copy constructor
		int Val = c.getVal();
	}
		
	Card(int num) { // Alternate copy cnstructor
		cardVal = num;
	};
	
	public void setVal(int c) {
		cardVal = c;
	}
	
	public int getVal() {
		return cardVal;
	}
	
	/* Compares two cards. Returns 0 if "this" is less. Returns 1 if cards 
	*  are equal. Returns 2 if "this" is greater.
	*/
	
	int cardCmp(Card aCard) { 
		int tmpCardVal = (cardVal  - 1) % 13;
		int aCardV = (aCard.getVal() - 1) % 13;
		for (int i = 0; i < 13; i++) {
			if (aCardV == 1) {
				aCardV += 12;
			}
			else {
				aCardV--;
			}
			if (tmpCardVal == 0) {
				tmpCardVal += 12;
			}
			else {
				tmpCardVal--;
			}	
		}
		if (tmpCardVal > aCardV) {
			return 2;
		}
		else if(tmpCardVal == aCardV) {
			return 1;
		}
		return 0;
	}	
	int cardVal;                                             
}
