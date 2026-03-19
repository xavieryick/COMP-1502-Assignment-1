package mru.game.view;

import java.util.Scanner;

/**
 * This class will display the app menu.
 * @author Xavier Yick
 * @author Kaydence Eng
 *
 */

public class AppMenu {

	/**
	 * This class will be used to show the menus and sub menus to the user
	 * It also prompts the user for the inputs and validates them 
	 */
	Scanner input;
	
	public AppMenu() {
		input = new Scanner(System.in);
	}
	
	/**
	 * This method prints the main game menu for the user
	 * @return the user's choice to play, search, or exit
	 */
	
	public char startMenu() {
		System.out.println("Select one of these options: \n"
				+ "\n"
				+ "		(P) Play game \n"
				+ "		(S) Search \n"
				+ "		(E) Exit \n"
				+ "\n"
				+ "Enter a choice: ");
		char option = input.nextLine().toLowerCase().charAt(0);
		
		return option;
	}
	
	/**
	 * This method prints the search menu for the user
	 * @return the user's choice to search for the top player, search by name, or return to the main menu
	 */
	
	public char searchMenu() {
		System.out.println("Select one of these options: \n"
				+ "\n"
				+ "		(T) Top player (Most number of wins) \n"
				+ "		(N) Looking for a name \n"
				+ "		(B) Back to Main menu \n"
				+ "\n"
				+ "Enter a choice: ");
		char option = input.nextLine().toLowerCase().charAt(0);
		return option;
	}
	
	/**
	 * This method asks the user for a username
	 * @return the user's given username
	 */
	
	public String userName() {
		System.out.println("Enter a name here: ");
		String name = input.nextLine().trim().toLowerCase();
		return name;
	}
	
//	public String userID() {
//		System.out.println("Enter an ID here: ");
//		String id = input.nextLine().trim();
//		return id;
//	}
//	
//	public void showPlayer(Player ply) {
//		System.out.println(ply);
//	}
}
