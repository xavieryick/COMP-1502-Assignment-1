package mru.game.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardTest {
	
	Card card;
	
	@Test
	void getRankTest() {
		card = new Card(1, "Hearts");
		int rank = card.getRank();
		assertEquals(rank, 1);
	}
	
	@Test
	void setRankTest() {
		card = new Card(1, "Hearts");
		card.setRank(1);
		int rank = card.getRank();
		assertEquals(rank, 1 );
		
	}
	
	@Test
	void getSuitTest() {
		card = new Card(1, "Hearts");
		String suit = card.getSuit();
		assertEquals(suit, "Hearts");
	}
	
	@Test
	void setSuitTest() {
		card = new Card(1, "Hearts");
		card.setSuit("Hearts");
		String suit = card.getSuit();
		assertEquals(suit, "Hearts");
	}
	
	@Test
	void toStringTest1() {
		card = new Card(1, "Hearts");
		String toFormat = card.toString();
		assertEquals(toFormat, "Ace of Hearts");
		
	}
	
	@Test
	void toStringTest2() {
		card = new Card(11, "Hearts");
		String toFormat = card.toString();
		assertEquals(toFormat, "Jack of Hearts");
		
	}
	
	@Test
	void toStringTest3() {
		card = new Card(12, "Hearts");
		String toFormat = card.toString();
		assertEquals(toFormat, "Queen of Hearts");
		
	}
	
	@Test
	void toStringTest4() {
		card = new Card(13, "Hearts");
		String toFormat = card.toString();
		assertEquals(toFormat, "King of Hearts");
		
	}
	
}
