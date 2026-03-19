package mru.game.controller;



import java.util.ArrayList;

import mru.game.model.Player;
//import mru.game.controller.CardDeck;
import mru.game.view.GameMenu;

/**
 * This class initializes the gameMenu, cardDeck, gameManager
 * betChoice, betAmount, playerScore, bankerScore, fullDeck,
 * playerDeck and bankerDeck for future reference
 * @author Xavier Yick
 * @author Kaydence Eng
 * 
 *
 */

public class PuntoBancoGame {
	
	/**
	 * In this class you implement the game
	 * You should use CardDeck class here
	 * See the instructions for the game rules
	 */

	/**
	 * this initializes the gameMenu
	 */
	GameMenu gm;
	/**
	 * this initializes the cardDeck
	 */
	CardDeck cd;
	/**
	 * this initializes the gameManager
	 */
	GameManager gManager;
	/**
	 * this initializes the player 
	 */
	Player ply;

	/**
	 * This initializes the user's betChoice
	 */
	char betChoice;
	/**
	 * This initializes the user's betAmount
	 */
	int betAmount;
	
	/**
	 * This initializes the player's deck score
	 */
	int playerScore;
	/**
	 * This initializes the banker's deck score
	 */
	int bankerScore;
	
	/**
	 * This initializes the fullDeck of cards
	 */
	ArrayList<Card> fullDeck = new ArrayList<>();
	/**
	 * This initializes the playerDeck of cards
	 */
	ArrayList<Card> playerDeck = new ArrayList<>();
	/**
	 * This initializes the bankerDeck of cards
	 */
	ArrayList<Card> bankerDeck = new ArrayList<>();
	
	/**
	 * This method initializes the user's balance and number of wins using separate methods,
	 * as well as the game menu and card deck, and launches the game
	 * @param p is the current player and their associated info
	 */
	
	public PuntoBancoGame(Player p) {
		int balance = p.getBalance();
		int numOfWins = p.getNumberOfWins();

		gm = new GameMenu();
		cd = new CardDeck();
		
		fullDeck = createFullDeck(); //not helping in the way you thought 
		//could move it back doesn't really matter
		launchGame(p, balance,numOfWins);
	}
	
	/**
	 * This is the main game method
	 * @param p is the player 
	 * @param balance is the player's balance
	 * @param numOfWins is the player's number of wins
	 * @return the user's balance
	 */
	
	public int launchGame(Player p, int balance, int numOfWins) {
		boolean flag = true;
		
		while (flag) {
			//make another nested loop for when the user is repeating the game and we still have enough cards
//			System.out.println("Game is currently running!");
			betChoice = gm.showBetMenu();
	
			
			betAmount = gm.showBetAmount(balance);
//			betAmount = checkBetAmount(betAmount);
			setBetAmount(betAmount);
			
			
			//just for testing, but you call menus then decks
			Card nextCard; //do this technique to move cards between arraylists
	
			playerDeck = createPlayerDeck(fullDeck);
			bankerDeck = createBankerDeck(fullDeck);
			
			playerScore = valuePlayerDeck(playerDeck);
			bankerScore = valueBankerDeck(bankerDeck);
			
			checkSum(playerScore, bankerScore);
			gm.displayDeck(playerDeck, bankerDeck, playerScore, bankerScore);
			balance = checkWinner(p, playerScore, bankerScore, balance, numOfWins);
					
			//clear both array lists 
			playerDeck = clearDeck(playerDeck);
			bankerDeck = clearDeck(bankerDeck);
			
			//reset scores to zero 
			playerScore = 0;
			bankerScore = 0;
			
			//keep this at the end
			flag = gm.promptPlayAgain();
			
		}
		
		if (flag == false) {
			System.out.println("Exiting the game...\n" 
								+ "Heading back to the main menu.\n"
								+ "Your progress will not be saved until you select the exit option.");			
		}
		return balance;
	}
//	/**
//	 * This method ensures that the player can't bet over the amount in their balance
//	 * @param betAmount
//	 * @return betAmount
//	 */
//	
//	private int checkBetAmount(int betAmount) {
//		int userBalance; 
//		userBalance = ply.getBalance();
//		while (betAmount > userBalance)
//			System.out.println("You can't bet more money than you have!");
//			betAmount = gm.showBetAmount();
//		
//		return betAmount;
//	}

	/**
	 * This method sets the user's bet amount
	 * @param betAmount is the user's given bet amount
	 */
	
	private void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}
	
	/**
	 * This method returns the user's stored bet amount
	 * @return the user's stored bet amount
	 */
	
	public int getBetAmount() {
		return betAmount;
	}

	
	/**
	 * This method creates a full deck of cards when called
	 * @return a full deck of cards for the game to use
	 */
	
	public ArrayList<Card> createFullDeck(){
		//	Help for passing and returning ArrayLists in methods
		//	https://stackoverflow.com/questions/38416546/how-can-i-pass-list-in-my-method-parameter
		cd = new CardDeck();
		fullDeck = cd.getDeck();
		setFullDeck(fullDeck);
		
		return fullDeck;
	}
	
	/**
	 * This method creates the initial deck for the player
	 * @param fullDeck is used for the available cards
	 * @return an arraylist of the player's pulled cards
	 */
	
	public ArrayList<Card> createPlayerDeck(ArrayList<Card> fullDeck) {
		//playerDeck = fullDeck;
		//return and send toFormat 
		
		//check if we enough cards to begin with
		
		fullDeck = getFullDeck();
		Card nextCard = null;
		int i;
		
		//initiate for loop here 
		for(i = 0; i < 2; i++) {
			//if statement to check length of cards left in deck 
			//else will call createFullDeck
			
			if (fullDeck.size() != 0) {
				
				nextCard = fullDeck.get(0);
				playerDeck.add(nextCard);
				fullDeck.remove(0);
				
			}
			
			else if (fullDeck.size() == 0) {
				fullDeck = createFullDeck();
				nextCard = fullDeck.get(0);
				playerDeck.add(nextCard);
				fullDeck.remove(0);

			}
				
			
		}
				
//		int rank = nextCard.getRank();
//		System.out.println("The rank of this card is: " + rank);
		
//		valuePlayerDeck(playerDeck);
		setFullDeck(fullDeck); //might not be necessary, keep it for now
		
//		System.out.println("The playerDeck is: " + playerDeck);
		
		return playerDeck;
	}
	
	/**
	 * This method creates the initial deck for the banker
	 * @param fullDeck is used for the available cards
	 * @return an arraylist of the banker's pulled cards
	 */
	
	public ArrayList<Card> createBankerDeck(ArrayList<Card> fullDeck) {
		fullDeck = getFullDeck();
		Card nextCard = null;
		int i;
		//initiate for loop here 
		for(i = 0; i < 2; i++) {
			//if statement to check length of cards left in deck 
			//else will call createFullDeck
			if (fullDeck.size() != 0) {
				
				nextCard = fullDeck.get(0);
				bankerDeck.add(nextCard);
				fullDeck.remove(0);
				
			}
			
			else if (fullDeck.size() == 0) {
				fullDeck = createFullDeck();
				nextCard = fullDeck.get(0);
				bankerDeck.add(nextCard);
				fullDeck.remove(0);
			}
		}
		
//		valueBankerDeck(bankerDeck);
		setFullDeck(fullDeck);
		
//		System.out.println("The bankerDeck is: " + bankerDeck);
		return bankerDeck;
		
	}
	
	/**
	 * This method counts the value of the player's current cards
	 * @param playerDeck is the cards the player currently holds
	 * @return the value of the player's current cards after being scored by the game rules
	 */
	
	public int valuePlayerDeck(ArrayList<Card> playerDeck){
		Card nextCard = null;
		int playerValue = 0;
		int rank;
		int i;
		
		for(i = 0; i < playerDeck.size(); i++) {
			nextCard = playerDeck.get(i);
			
			if (nextCard != null) {
				rank = nextCard.getRank();
				if (rank >= 10) {
					
					switch (rank) {
					case 10:
						rank = 0;
						break;
						
					case 11:
						rank = 0;
						break;
					
					case 12: 
						rank = 0;
						break;
					
					case 13: 
						rank = 0;
						break;
						
					default:
						break;
						}
				}
				playerValue += rank;	
			}
			
		}
		playerValue = playerValue % 10; //get rid of this as check sum will do it for you 
		
//		System.out.println("The player's hand is worth: " + playerValue);
		return playerValue;
	}
	
	/**
	 * This method counts the value of the banker's current cards
	 * @param bankerDeck is the cards the banker currently holds
	 * @return the value of the banker's current cards after being scored by the game rules
	 */
	
	public int valueBankerDeck(ArrayList<Card> bankerDeck){
		//bankerDeck.add(rank)
		
		Card nextCard = null;
		int bankerValue = 0;
		int rank;
		int i;
		
		for(i = 0; i < bankerDeck.size(); i++) {
			nextCard = bankerDeck.get(i);
			
			if (nextCard != null) {
				rank = nextCard.getRank();
				if (rank >= 10) {
					
					switch (rank) {
					case 10:
						rank = 0;
						break;
						
					case 11:
						rank = 0;
						break;
					
					case 12: 
						rank = 0;
						break;
					
					case 13: 
						rank = 0;
						break;
						
					default:
						break;
						}
				}
				bankerValue += rank;	
			}
			
		}
		bankerValue = bankerValue % 10; //get rid of this as check sum will do it for you 
		
//		System.out.println("The banker's hand is worth: " + bankerValue);
		
		
		return bankerValue;
	}
	
	/**
	 * This method will add one card to the player's deck if their total is under 5
	 * @param playerDeck is the player's current deck of cards
	 * @return the player's deck of cards after having one added
	 */
	
	public ArrayList<Card> addToPlayerDeck(ArrayList<Card> playerDeck){
		fullDeck = getFullDeck();
		Card nextCard = null;
		int cardRank;
		
		if (fullDeck.size() != 0) {
			
			nextCard = fullDeck.get(0);
			playerDeck.add(nextCard);
			fullDeck.remove(0);
			
		}
		
		else if (fullDeck.size() == 0) {
			fullDeck = createFullDeck();
			nextCard = fullDeck.get(0);
			playerDeck.add(nextCard);
			fullDeck.remove(0);
		}
		
		setFullDeck(fullDeck);
		
		cardRank = nextCard.getRank();
		
		switch(cardRank) {
		case 10: 
			cardRank = 0;
			break;
		case 11:
			cardRank = 0;
			break;
		case 12:
			cardRank = 0;
			break;
		case 13: 
			cardRank = 0;
			break;
		}
		
//		System.out.println("The new player card is worth: " + cardRank);
//		System.out.println("The new playerDeck is: " + playerDeck);
		bankerCardDraw(cardRank);
		
		playerScore += cardRank;
		if (playerScore >= 10) {
			playerScore -= 10;
		}		
		valuePlayerDeck(playerDeck);
		return playerDeck;
	}
	
	/**
	 * 
	 * @param playerCardRank
	 */
	
	private void bankerCardDraw(int playerCardRank) {
		//the logic fell apart here
		if (playerCardRank == 2 || playerCardRank == 3) {
			if (bankerScore >= 0 || bankerScore <= 4) {
				addToBankerDeck(bankerDeck);
			}
		}
		
		else if (playerCardRank == 4 || playerCardRank == 5) {
			if (bankerScore >= 0 || bankerScore <= 5) {
				addToBankerDeck(bankerDeck);
			}
		}
		
		else if (playerCardRank == 6 || playerCardRank == 7) {
			if (bankerScore >= 0 || bankerScore <= 6) {
				addToBankerDeck(bankerDeck);
			}
		}
		
		else if (playerCardRank == 8) {
			if (bankerScore >= 0 || bankerScore <= 2) {
				addToBankerDeck(bankerDeck);
			}
		}
		
		else if (playerCardRank <= 1 || playerCardRank == 10) {
			if (bankerScore >= 0 || bankerScore <= 3) {
				addToBankerDeck(bankerDeck);
			}
		}
	}

	/**
	 * This method will add one card to the banker's deck if their total is under 5
	 * @param bankerDeck is the player's current deck of cards
	 * @return the banker's deck of cards after having one added
	 */
	
	public ArrayList<Card> addToBankerDeck(ArrayList<Card> bankerDeck){
		fullDeck = getFullDeck();
		Card nextCard = null;
		int cardRank;
		
		if (fullDeck.size() != 0) {
			
			nextCard = fullDeck.get(0);
			bankerDeck.add(nextCard);
			fullDeck.remove(0);
			
		}
		
		else if (fullDeck.size() == 0) {
			fullDeck = createFullDeck();
			nextCard = fullDeck.get(0);
			bankerDeck.add(nextCard);
			fullDeck.remove(0);
		}
		
		setFullDeck(fullDeck);
		
		cardRank = nextCard.getRank();
		//add conditions to check for card rank above 10 
		switch(cardRank) {
		case 10: 
			cardRank = 0;
			break;
		case 11:
			cardRank = 0;
			break;
		case 12:
			cardRank = 0;
			break;
		case 13: 
			cardRank = 0;
			break;
		}
		
//		System.out.println("The new banker card is worth:" + cardRank);
//		System.out.println("The new bankerDeck is: " + bankerDeck);
		
		bankerScore += cardRank;
		if (bankerScore >= 10) {
			bankerScore -= 10;
		}
		
		valueBankerDeck(bankerDeck);
		return bankerDeck;
	}
	
	/**
	 * This method checks the values of the player and banker hands, and will draw a card if necessary
	 * @param playerValue is the value of the player's hand
	 * @param bankerValue is the value of the banker's hand
	 */
	
	public void checkSum(int playerValue, int bankerValue) {
//		System.out.println("Player value:" + playerValue);
//		System.out.println("Banker value:" + bankerValue);
		//step 2 
		if (playerValue >= 8 || bankerValue >= 8) {
			
//			checkWinner(playerValue, bankerValue);
			//empty as we're going back to scoring 
			
		}
		else if (playerValue <= 5 && playerDeck.size() == 2){
		//step 3
			addToPlayerDeck(playerDeck);
		}
		else if (playerValue == 6 || playerValue == 7) {
			//step 4a
			if (bankerValue <= 5 && bankerDeck.size() == 2) {
				addToBankerDeck(bankerDeck);
			}
			else {
//				checkWinner(playerValue,bankerValue);
				//empty as we're just going back to the main method
			}
		
		}
//		else if (playerValue == 6 || playerValue == 7 ) {
//			if(bankerValue == 6 || bankerValue == 7) {
//				checkWinner(playerValue, bankerValue);
//			}
//		}
		
	}
	
	/**
	 * This method will check the winner of the game
	 * @param p is used for the user's number of wins and balance
	 * @param playerValue is the value of the player's hand
	 * @param bankerValue is the value of the banker's hand
	 * @param balance is the user's stored balance
	 * @param numOfWins is the user's stored number of wins
	 * @return the balance of the user after they win or lose
	 */
	
	public int checkWinner(Player p, int playerValue, int bankerValue, int balance, int numOfWins) {
		//check to see what the value of the cards are 
		//will return the appropriate message 
		
//		playerValue = playerValue % 10;
//		bankerValue = bankerValue % 10;
		int betAmount;
		betAmount = getBetAmount();
		
		if (playerValue > bankerValue && betChoice == 'p') {
			gm.playerWin();
			gm.successfulBet(betAmount);
			p.setBalance(balance+betAmount);
			p.setNumberOfWins(numOfWins+1);
		}
		else if (playerValue > bankerValue && betChoice != 'p') {
			gm.playerWin();
			gm.unsuccessfulBet(betAmount);
			p.setBalance(balance-betAmount);
		}
		else if (playerValue < bankerValue && betChoice == 'b'){
			gm.bankerWin();
			gm.successfulBet(betAmount);
			p.setBalance(balance+betAmount);
			p.setNumberOfWins(numOfWins+1);
		}
		else if (playerValue < bankerValue && betChoice != 'b'){
			gm.bankerWin();
			gm.unsuccessfulBet(betAmount);
			p.setBalance(balance-betAmount);
		}
		else if (playerValue == bankerValue && betChoice == 't'){
			betAmount = betAmount * 5;
			gm.tieWin();
			gm.successfulBet(betAmount);
			p.setBalance(balance+betAmount);
			p.setNumberOfWins(numOfWins+1);
		}
		else if (playerValue == bankerValue && betChoice != 't'){
//			betAmount = betAmount * 5; //just lose money if tie wasn't picked
			gm.tieWin();
			gm.unsuccessfulBet(betAmount);
			p.setBalance(balance-betAmount);
		}
		return balance;
	}
	
	/**
	 * This method will clear the deck if the user does not want to play again
	 * @param deck is the current deck of cards
	 * @return a clear deck
	 */
	
	public ArrayList<Card> clearDeck(ArrayList<Card>deck){
		deck.clear();
		
		return deck;
	}
	
	/**
	 * This method sets the full deck of cards
	 * @param fullDeck is the current full deck
	 */
	
	public void setFullDeck(ArrayList<Card>fullDeck) {
		//holds the current fullDeck
		this.fullDeck = fullDeck;
	}
	
	/**
	 * This method gets the full deck of cards
	 * @return the full deck of cards
	 */
	
	public ArrayList<Card> getFullDeck(){
		//holds the current fullDeck 
		return fullDeck;
	}
}
