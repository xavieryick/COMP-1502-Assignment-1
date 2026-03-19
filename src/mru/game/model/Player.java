package mru.game.model;

public class Player {
	
	/**
	 * This class represent each player record in the Database
	 * It is basically a model class for each record in the txt file
	 */
	
	String name;
	int balance;
	int numberOfWins;
	
	/**
	 * This method initializes the Player's name, balance and number of wins
	 * @param name is the user's name
	 * @param balance is the user's balance
	 * @param numberOfWins is the user's number of wins
	 */
	
	public Player(String name, int balance, int numberOfWins) {
		this.name = name;
		this.balance = balance;
		this.numberOfWins = numberOfWins;
	}
	
	/**
	 * This method sets the user's name
	 * @param name is the user's name
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method returns the user's stored name
	 * @return the user's stored name
	 */
	
	public String getName() {
		return name;
	}

	/**
	 * This method sets the user's balance
	 * @param balance is the user's balance
	 */
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	/**
	 * This method returns the user's stored balance
	 * @return the user's stored balance
	 */
	
	public int getBalance() {
		return balance;
	}
	
	/** 
	 * This method sets the user's number of wins
	 * @param numberOfWins is the user's number of wins
	 */
	
	public void setNumberOfWins(int numberOfWins) {
		this.numberOfWins = numberOfWins;
	}
	
	/**
	 * This method returns the user's stored number of wins
	 * @return the user's stored number of wins
	 */
	
	public int getNumberOfWins() {
		return numberOfWins;
	}
	
	/**
	 * This method takes the user's information and puts it into a format to save in the CasinoInfo file
	 * @return the user's information in a string for the CasinoInfo file
	 */
	
	public String format() {
		return name + ";" + balance + ";" + numberOfWins;
	}
	
}
