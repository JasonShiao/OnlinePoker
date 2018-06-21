package com.onlinePoker.server;

public class Card {

	private String suit;
	private String pips;
	
	
	public Card(String suit, String pips) {
		this.suit = suit;
		this.pips = pips;
	}


	public String getSuit() {
		return suit;
	}


	public void setSuit(String suit) {
		this.suit = suit;
	}


	public String getPips() {
		return pips;
	}


	public void setPips(String pips) {
		this.pips = pips;
	}
	
	public int compare(Card opponent) {
		
		int myValue;
		int opponentValue;
		
		if(this.getPriority() > opponent.getPriority()) {
			return 1;
		}else {
			return -1;
		}
		
	}
	
	private int getPriority() {
		
		int retVal = 0;
		
		switch(this.pips) {
			case "Ace": 
				retVal = 4 * 12;
				break;
			case "2":
				retVal = 4 * 13;
				break;
			case "3":
				retVal = 4 * 1;
				break;
			case "4":
				retVal = 4 * 2;
				break;
			case "5":
				retVal = 4 * 3;
				break;
			case "6":
				retVal = 4 * 4;
				break;
			case "7":
				retVal = 4 * 5;
				break;
			case "8":
				retVal = 4 * 6;
				break;
			case "9":
				retVal = 4 * 7;
				break;
			case "10":
				retVal = 4 * 8;
				break;
			case "Jack":
				retVal = 4 * 9;
				break;
			case "Queen":
				retVal = 4 * 10;
				break;
			case "King":
			default:
				retVal = 4 * 11;
				break;
		}
		
		switch(this.suit) {
			case "Spade":
				retVal += 3;
				break;
			case "Heart":
				retVal += 2;
				break;
			case "Diamond":
				retVal += 1;
				break;
			case "Club":
			default:
				break;
		}
		return retVal;
	}
	
	
	
}
