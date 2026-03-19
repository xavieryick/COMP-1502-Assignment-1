package mru.game.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class CardDeckTest {
	
	CardDeck deck;
	
	@Test
	void createDeckTest() {
		deck = new CardDeck();
		CardDeck deckCompareTo;
		deckCompareTo = deck;
		assertEquals(deck, deckCompareTo);
		
	}
	
	//can't do shuffle test because it's private??
	//changed it to public because i can
	
	@Test 
	void shuffleTest() {
		deck = new CardDeck();
		ArrayList<Card> compareTo = deck.getDeck();
		deck.shuffleDeck();
		ArrayList<Card> shuffled = deck.getDeck();
		assertEquals(shuffled, compareTo);
	}
	
	@Test
	void getDeckTest() {
		deck = new CardDeck();
		ArrayList<Card> compareTo = deck.getDeck();
		ArrayList<Card> newDeck = deck.getDeck();
		assertEquals(newDeck, compareTo);
	}

}
