package mru.game.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import mru.game.model.Player;
import mru.game.view.AppMenu;

/**
 * This method sets the file path for the CasinoInfo file, as well as the player list and app menu.
 * @author Xavier Yick
 * @author Kaydence Eng
 *
 */

public class GameManager {
	
	/* In this class you'll need these methods:
	 * A constructor
	 * A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	 * A save method to store the arraylist into the the txt file 
	 * A method to search for a player based their name
	 * A method to find the top players
	 * Depending on your designing technique you may need and you can add more methods here 
	 */

	/**
	 * sets the file path to set location
	 */
	private final String FILE_PATH = "res/CasinoInfo.txt";
	/**
	 * initializes the player list
	 */
	ArrayList<Player> players;
	/**
	 * initializes the app menu
	 */
	AppMenu appMenu;
	
	/**
	 * This constructor initializes the players, appMenu, loads the data, and launches the application
	 * @throws Exception
	 */
	
	public GameManager() throws Exception {
		players = new ArrayList<>();
		appMenu = new AppMenu();
		loadData();
		launchApplication();
	}

	/**
	 * This method will call the startMenu method and break off into three different choices
	 * depending on the user's input
	 * @throws Exception
	 */
	
	private void launchApplication() throws Exception {
		boolean flag = true; 
		//need to break out of this loop when play doesn't want to keep playing the game
		//when player says no in bancogame and gamemenu, we bring the result to here 
		int option;
		
		while (flag) {
			option = appMenu.startMenu();
			switch(option) {
			case 'p':
				playGame();
				break;
			case 's':
				search();
				break;
			case 'e':
				save();
				flag = false;
			}
		}
	}
	
	/**
	 * This method will ask the user for a name, and will either return the user's information
	 * or will create a new user account if it does not exist. Then it will initialize the game
	 */
	
	private void playGame(){ 
		String name = appMenu.userName();
		Player p = searchByName(name);
		int baseBalance = 100;
		
		if (p == null) {
			// = appMenu.userID();
			//System.out.println("Creating new player profile...");
			players.add(new Player (name, baseBalance, 0));
			p = searchByNameNew(name);
		}
		
		PuntoBancoGame game = new PuntoBancoGame(p);
		//add wins to player code here 
	}

	/**
	 * This method will call the searchMenu method and break off into three different choices
	 * depending on the user's input
	 */
	
	private void search() {
		char option = appMenu.searchMenu();
		
		switch(option) {
		case 't':
			findTopPlayer();
			break;
		case 'n':
			String name = appMenu.userName();
			searchByNameMenu(name);
			break;
		case 'b':
			break;
		}
		
	}
	
	/**
	 * This method prints the top player, and will print up to three if they are tied
	 */
	
	private void findTopPlayer() {
		String topPlayerOne = null;
		String topPlayerTwo = null;
		String topPlayerThree = null;
		int currentTopWins = 0;
		Scanner input = new Scanner(System.in);
		String choice;
		
		for (Player p: players) {
			if (p.getNumberOfWins() > currentTopWins) {
				currentTopWins = p.getNumberOfWins();
				topPlayerOne = p.getName();
			}
			else if (p.getNumberOfWins() == currentTopWins && topPlayerTwo == null) {
				topPlayerTwo = p.getName();
			}
			else if (p.getNumberOfWins() == currentTopWins && topPlayerTwo != null) {
				topPlayerThree = p.getName();
			}
		}
		
		if (topPlayerTwo == null) {
			System.out.println(
					  "            - TOP PLAYERS -                 \n"
					+ "+==================+======================+ \n"
					+ "|       NAME       +    NUMBER OF WINS    | \n"
					+ "+==================+======================+ ");
				System.out.printf(
					  "| " + "%-16s" + " + " + "%-20d" + " | \n"
					+ "+------------------+----------------------+ \n", topPlayerOne, currentTopWins);
				System.out.println("\nPress 'Enter' to continue...");
		}
		if (topPlayerTwo != null) {
			System.out.println(
					  "            - TOP PLAYERS -                 \n"
					+ "+==================+======================+ \n"
					+ "|       NAME       +    NUMBER OF WINS    | \n"
					+ "+==================+======================+ ");
				System.out.printf(
					  "| " + "%-16s" + " + " + "%-20d" + " | \n"
					+ "+------------------+----------------------+ \n", topPlayerOne, currentTopWins);
				System.out.printf(
						  "| " + "%-16s" + " + " + "%-20d" + " | \n"
						+ "+------------------+----------------------+ \n", topPlayerTwo, currentTopWins);
				System.out.println("\nPress 'Enter' to continue...");
		}
		if (topPlayerTwo != null && topPlayerThree != null) {
			System.out.println(
					  "            - TOP PLAYERS -                 \n"
					+ "+==================+======================+ \n"
					+ "|       NAME       +    NUMBER OF WINS    | \n"
					+ "+==================+======================+ ");
				System.out.printf(
						  "| " + "%-16s" + " + " + "%-20d" + " | \n"
						+ "+------------------+----------------------+ \n", topPlayerOne, currentTopWins);
				System.out.printf(
						  "| " + "%-16s" + " + " + "%-20d" + " | \n"
						+ "+------------------+----------------------+ \n", topPlayerTwo, currentTopWins);
				System.out.printf(
						  "| " + "%-16s" + " + " + "%-20d" + " | \n"
						+ "+------------------+----------------------+ \n", topPlayerThree, currentTopWins);
				System.out.println("\nPress 'Enter' to continue...");
		}
		do {
			choice = input.nextLine();
		} while (choice.length() != 0);
	}

	/**
	 * This method searches for a username given by the user (used for game)
	 * @param name is the user's given username
	 * @return the player and info
	 */
	
	private Player searchByName(String name) {
		Player ply = null;
		Scanner input = new Scanner(System.in);
		String choice;
		int currentWins = 0;
		int currentBalance = 0;
		String currentName;
		
		for (Player p: players) {
			if (p.getName().equals(name)) {
				ply = p;
				currentName = ply.getName();
				currentWins = ply.getNumberOfWins();
				currentBalance = ply.getBalance();
				System.out.println(
						  "                      - Welcome Back! -             \n"
						+ "+==================+======================+======================+ \n"
						+ "|       NAME       +    NUMBER OF WINS    +    CURRENT BALANCE   | \n"
						+ "+==================+======================+======================+   ");
				System.out.printf(
						  "| " + "%-16s" + " + " + "%-20d" + " + " + "%-21s" + 				"|\n"
						+ "+------------------+----------------------+----------------------+ \n", currentName, currentWins, currentBalance);
				System.out.println("\nPress 'Enter' to continue...");
				do {
					choice = input.nextLine();
				} while (choice.length() != 0);
				break;
			}
		}
		return ply;
	}

	/**
	 * This method creates a new player with the user's given username
	 * @param name is the user's given username
	 * @return the new player and base info
	 */
	
	private Player searchByNameNew(String name) {
		Player ply = null;
		Scanner input = new Scanner(System.in);
		String choice;
		int currentWins;
		int currentBalance;
		String currentName;
		
		for (Player p: players) {
			if (p.getName().equals(name)) {
				ply = p;
				currentName = ply.getName();
				currentWins = ply.getNumberOfWins();
				currentBalance = ply.getBalance();
				System.out.println(
						  "                         - Welcome! -             \n"
						+ "+==================+======================+======================+ \n"
						+ "|       NAME       +    NUMBER OF WINS    +    CURRENT BALANCE   | \n"
						+ "+==================+======================+======================+   ");
				System.out.printf(
						  "| " + "%-16s" + " + " + "%-20d" + " + " + "%-21s" + 				"|\n"
						+ "+------------------+----------------------+----------------------+ \n", currentName, currentWins, currentBalance);
				System.out.println("\nPress 'Enter' to continue...");
				do {
					choice = input.nextLine();
				} while (choice.length() != 0);
				break;
			}
		}
		return ply;
	}

	/**
	 * This method searches for a username given by the user (used for menu)
	 * @param name is the user's given username
	 */
	
	private void searchByNameMenu(String name) {
		Player ply = null;
		Scanner input = new Scanner(System.in);
		String choice;
		int currentWins = 0;
		int currentBalance = 0;
		String currentName;
		
		for (Player p: players) {
			if (p.getName().equals(name)) {
				ply = p;
				currentName = ply.getName();
				currentWins = ply.getNumberOfWins();
				currentBalance = ply.getBalance();
				System.out.println(
						  "                      - Welcome Back! -             \n"
						+ "+==================+======================+======================+ \n"
						+ "|       NAME       +    NUMBER OF WINS    +    CURRENT BALANCE   | \n"
						+ "+==================+======================+======================+   ");
				System.out.printf(
						  "| " + "%-16s" + " + " + "%-20d" + " + " + "%-21s" + 				"|\n"
						+ "+------------------+----------------------+----------------------+ \n", currentName, currentWins, currentBalance);
				System.out.println("\nPress 'Enter' to continue...");
				do {
					choice = input.nextLine();
				} while (choice.length() != 0);
				break;
			}
			else {
				System.out.println("Player not found!");
				System.out.println("\nPress 'Enter' to continue...");
				do {
					choice = input.nextLine();
				} while (choice.length() != 0);
				break;
			}
		}
	}
	
	/**
	 * This method saves the current game info to the CasinoInfo file using the format method
	 * @throws Exception
	 */
	
	public void save() throws Exception { //originally private
		File db = new File(FILE_PATH);
		PrintWriter printWriter = new PrintWriter(db);
		
		System.out.println("Saving...\n"
						+ "Your progress has been saved.");
		
		for (Player p: players) {
			printWriter.println(p.format());
		}
		printWriter.close();
	}

	/**
	 * This method finds the CasinoInfo file and will create one if it does not exist, 
	 * and will load the information from the file
	 * @throws Exception
	 */
	
	private void loadData() throws Exception {
		File db = new File(FILE_PATH);
		String currentLine;
		String[] splitLine;
		
		if (db.exists()) {
			Scanner fileReader = new Scanner(db);
			while (fileReader.hasNextLine()) {
				currentLine = fileReader.nextLine();
				splitLine = currentLine.split(";");
				Player p = new Player(splitLine[0], Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]));
				players.add(p);
			}
			fileReader.close();
		}
		else {
			db.createNewFile();
		}
	}
	
}
