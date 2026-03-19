package mru.game.view;

import java.util.ArrayList;
import java.util.Scanner;

import mru.game.controller.Card;

/**
 * This class will show the GameMenu.
 * @author Xavier Yick 
 * @author Kaydence Eng
 *
 */


public class GameMenu {

	Scanner input;
	
	public GameMenu() {
		input = new Scanner(System.in);
	}
	
	/**
	 * This method shows the bet menu for the user
	 * @return the user's choice to bet on the player, banker or tie
	 */
	
	public char showBetMenu() {
		char bet = ' ';
//		boolean flag = true;
		
		while (bet != 'p' && bet != 'b' && bet != 't') {
			
		
		System.out.println("Who do you want to bet on?\n"
				+ "\n"
				+ "		(P) Player Wins \n"
				+ "		(B) Banker Wins \n"
				+ "		(T) Tie Game \n"
				+ "\n"
				+ "Enter a choice: ");
		
		
		bet = input.next().charAt(0);
		bet = Character.toLowerCase(bet);
		
		if (bet != 'p' && bet != 'b' && bet != 't') {
			System.out.println("Invalid input. Please enter a valid input!");
			}
		}
		return bet;
	}
	
	/**
	 * This method asks the user for a bet amount
	 * @return the user's given bet amount
	 */
	
	public int showBetAmount(int balance) {
		int betAmount;
		
		do {
			System.out.println("How much do you want to bet this round? ");
			betAmount = input.nextInt();
		} while (betAmount>balance);
		return betAmount;

	}
	
	/**
	 * This method asks the user if they want to play again
	 * @return the user's choice to play again or exit the game
	 */
	
	public boolean promptPlayAgain() {
		boolean playAgain = true;
		char choice;
		
		do {
		System.out.println("Do you want to play again? (Y/N): ");
		choice = input.next().charAt(0);
		choice = Character.toUpperCase(choice);
		} while (choice != 'Y' && choice != 'N');
		
		if (choice == 'N') {
			playAgain = false;
		}
		
		input.nextLine(); //clears scanner buffer
		//https://stackoverflow.com/questions/10604125/how-can-i-clear-the-scanner-buffer-in-java
		
		return playAgain;
		
	}
	
	/**
	 * This method prints if the player has a higher card count
	 */
	
	public void playerWin() { //PLEASE FIX FORMATTING
		System.out.println("+====================+====================+");
		System.out.println("|     PLAYER WON     +                    |");
		System.out.println("+====================+====================+");
//		currBalance = currBalance + betAmount;
	}
	
	/**
	 * This method prints if the banker has a higher card count
	 */
	
	public void bankerWin() {
		
		System.out.println("+====================+====================+");
		System.out.println("|                    +     BANKER WON     |");
		System.out.println("+====================+====================+");
//		currBalance = currBalance - betAmount;
	}
	
	/**
	 * This method prints if the player and banker have an equal card count
	 */
	
	public void tieWin() {
		
		System.out.println("+====================+====================+");
		System.out.println("|                   TIE                   |");
		System.out.println("+====================+====================+");
	}
	
	/**
	 * This method prints if the user's bet matches the game winner
	 * @param betAmount is the amount that the user had bet and won
	 */
	
	public void successfulBet(int betAmount) {
		System.out.println("|                 YOU WON                 |");
		System.out.println("+====================+====================+");
		System.out.printf ("|    AMOUNT WON: %21d    |\n",betAmount);
		System.out.println("+====================+====================+");
	}
	
	/**
	 * This method prints if the user's bet does not match the game winner
	 * @param betAmount is the amount that the user had bet and lost
	 */
	
	public void unsuccessfulBet(int betAmount) {
		System.out.println("|             YOU DID NOT WIN             |");
		System.out.println("+====================+====================+");
		System.out.printf("|    AMOUNT LOST: %20d    |\n",betAmount);	
		System.out.println("+====================+====================+");
	}

	/**
	 * This method prints a table with the player's and banker's cards, depending on how many each has
	 * @param playerDeck is used to get the number of cards the player has
	 * @param bankerDeck is used to get the number of cards the banker has
	 */
	
	public void displayDeck(ArrayList<Card> playerDeck, ArrayList<Card> bankerDeck, int playerScore, int bankerScore) {
		// TODO Auto-generated method stub
		//make new if condition is banker has two cards or player has two, but other has three
		if(playerDeck.size() == 2 && bankerDeck.size() == 2) {
		System.out.println("              - PUNTO BANCO -              \n"
						 + "+====================+====================+\n"
						 + "|       PLAYER       |       BANKER       |\n"
						 + "+====================+====================+");
		System.out.printf("| " + "%-19s" + "| " + "%-19s" +"|\n", playerDeck.get(0), bankerDeck.get(0));
		System.out.printf("| " + "%-19s" + "| " + "%-19s" +"|\n", playerDeck.get(1), bankerDeck.get(1));
		System.out.println("|====================+====================|");
		System.out.println("|Player Score: " + playerScore + "     |" + "Banker Score: " + bankerScore + "     |");
		}
		
		else if(playerDeck.size() == 2 && bankerDeck.size() == 3){
			System.out.println("              - PUNTO BANCO -             \n"
					 		+ "+====================+====================+\n"
					 		+ "|       PLAYER       |       BANKER       |\n"
					 		+ "+====================+====================+");
			System.out.printf("| " + "%-19s" + "| " + "%-19s" +"|\n", playerDeck.get(0), bankerDeck.get(0));
			System.out.printf("| " + "%-19s" + "| " + "%-19s" +"|\n", playerDeck.get(1), bankerDeck.get(1));
			System.out.printf("|                    | " + "%-19s" +"|\n", bankerDeck.get(2));
			System.out.println("|====================+====================|");
			System.out.println("|Player Score: " + playerScore + "     |" + "Banker Score: " + bankerScore + "     |");
		}
		else if(playerDeck.size() == 3 && bankerDeck.size() == 2){
			System.out.println("              - PUNTO BANCO -             \n"
					 		+ "+====================+====================+\n"
					 		+ "|       PLAYER       |       BANKER       |\n"
					 		+ "+====================+====================+");
			System.out.printf("| " + "%-19s" + "| " + "%-19s" +"|\n", playerDeck.get(0), bankerDeck.get(0));
			System.out.printf("| " + "%-19s" + "| " + "%-19s" +"|\n", playerDeck.get(1), bankerDeck.get(1));
			System.out.printf("| " + "%-19s" + "|                    |\n", playerDeck.get(2));
			System.out.println("|====================+====================|");
			System.out.println("|Player Score: " + playerScore + "     |" + "Banker Score: " + bankerScore + "     |");

		}
		else if(playerDeck.size() == 3 && bankerDeck.size() == 3){	
		System.out.println("              - PUNTO BANCO -              \n"
				 		 + "+====================+====================+\n"
				 		 + "|       PLAYER       |       BANKER       |\n"
				 		 + "+====================+====================+");
		System.out.printf("| " + "%-19s" + "| " + "%-19s" +"|\n", playerDeck.get(0), bankerDeck.get(0));
		System.out.printf("| " + "%-19s" + "| " + "%-19s" +"|\n", playerDeck.get(1), bankerDeck.get(1));
		System.out.printf("| " + "%-19s" + "| " + "%-19s" +"|\n", playerDeck.get(2), bankerDeck.get(2));
		System.out.println("|====================+====================|");
		System.out.println("|Player Score: " + playerScore + "     |" + "Banker Score: " + bankerScore + "     |");
		}
	}
}
