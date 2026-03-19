package mru.game.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {
	
	Player player;

	@Test
	void getNameTest() {
		player = new Player("kaydence", 0, 0);
		String playerName = player.getName();
		assertEquals(playerName, "kaydence");
		

	}
	
	@Test 
	void setNameTest() {
		player = new Player("kaydence", 0, 0);
		player.setName("kaydence");
		String playerName = player.getName();
		assertEquals(playerName, "kaydence");
		
	}
	
	@Test 
	void getBalanceTest() {
		player = new Player("kaydence", 0, 0);
		int balance = player.getBalance();
		assertEquals(balance, 0);
	}

	@Test 
	void setBalanceTest() {
		player = new Player("kaydence", 0, 0);
		player.setBalance(0);
		int balance = player.getBalance();
		assertEquals(balance, 0);
	}
	
	@Test 
	void getNumWins() {
		player = new Player("kaydence", 0, 0);
		player.setNumberOfWins(0);
		int numWins = player.getNumberOfWins();
		assertEquals(numWins, 0);	
		
	}
	@Test	
	void setNumWins() {
		player = new Player("kaydence", 0, 0);
		player.setNumberOfWins(0);
		int numWins = player.getNumberOfWins();
		assertEquals(numWins, 0);
	}
	@Test
	void formatTest() {
		player = new Player("kaydence", 0, 0);
		String toFormat = player.format();
		assertEquals(toFormat, "kaydence;0;0");
		
	}
}
