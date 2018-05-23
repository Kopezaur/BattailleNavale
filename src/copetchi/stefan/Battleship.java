package copetchi.stefan;
import java.io.IOException;
import java.util.Scanner;

import copetchi.stefan.players.AI;
import copetchi.stefan.players.Human;
import copetchi.stefan.players.Player;

/**
 * @author Kopezaur
 *
 */
public class Battleship {
	
	private static Player p1;
	private static Player p2;
	// Boolean variable which serves as a detective to know when the game is finished
	private static boolean endGame = false;
	// Boolean variable which checks if a new game is going to start after the end of this one
	private static boolean newGame = true;
	// Integer with values (1, 2 or 3) to know the gamemode 
	private static int gamemode;
	private static Scanner input;

	// Main method for launching the game
	public static void main(String[] args) throws IOException {
		// Launch Screen Initialization
		System.out.println("	Welcome to BATTLESHIPS!");
		System.out.println("Are you ready to test your strategic skills in a battle against the AI? Well then, you came to the right place!\n");
		input = new Scanner(System.in);
		
		while(newGame) { // this loop keeps on going as long as the user wants to keep playing a new game
			// Game mode select
			System.out.println("Choose your game mode : ");
			System.out.println("  1) P1 vs P2");
			System.out.println("  2) P1 vs AI");
			System.out.println("  3) AI vs AI\n");
			
			// Creation of the two players
			System.out.print("\nYour choice : ");
			gamemode = Integer.parseInt(input.nextLine());
			while(true){
				boolean x = true;
				p1 = null;
				p2 = null;
				switch(gamemode) {
					case 1:	System.out.print("\nGood choice! Now input the name of the first player : ");
							String name = input.nextLine();
							p1 = new Human(name);
							System.out.print("\nPerfect! Now input the name of the second player : ");
							name = input.nextLine();
							p2 = new Human(name);
							break;
					case 2: System.out.print("\nExcellent choice! Now input the name of the first player : ");
							name = input.nextLine();
							p1 = new Human(name);
							System.out.println("\nPerfect! Now choose the difficulty level of the AI from the following list : ");
							System.out.println("  1 - Please don't hurt me! (Beginner)");
							System.out.println("  2 - Make it a little challenging! (Intermediate)");
							System.out.println("  3 - I want to slay a god! (Expert)");
							System.out.print("Your choice : ");
							int level = Integer.parseInt(input.nextLine());
							break;
					case 3: System.out.println("\nFormidable choice! Now choose the difficulty level of the first AI from the following list : ");
							System.out.println("  1 - Mortal (Beginner)");
							System.out.println("  2 - Demi-god (Intermediate)");
							System.out.println("  3 - God (Expert)");
							System.out.print("Your choice : ");
							level = Integer.parseInt(input.nextLine());
							p1 = AI.createAIforDifficultyLevel(level);
							System.out.println(p1.getClass());
							System.out.println("\nPerfect! Now choose the difficulty level of the second AI from the following list : ");
							System.out.println("  1 - Mortal (Beginner)");
							System.out.println("  2 - Demi-god (Intermediate)");
							System.out.println("  3 - God (Expert)");
							System.out.print("Your choice : ");
							level = Integer.parseInt(input.nextLine());
							p2 = AI.createAIforDifficultyLevel(level);
							break;
					default: System.out.println("\nThe option you have chosen is not available! Please try again!");
							 x = false;
							 break;
				}
				if(x){
					break;
				}
			}
			
			System.out.println("\nThe contestants from today's game are : ");
			System.out.println("  -> Player 1 : " + p1);
			System.out.println("  -> Player 2 : " + p2);
			System.out.println("Let the game begin!\n");
			
			// Placement of the battleships on the battlefields
			System.out.println("Initiating placement phase for Player 1 :");
			p1.shipPlacement();
			System.out.println("Initiating placement phase for Player 2 :");
			p2.shipPlacement();
			
			// Beginning of the gaming phase
			System.out.println("Now the battle phase begins...");
			int noOfTurns = 0;
			String attackCoord, result;
			endGame = false;
			while(!endGame){ // each while loop represents the tour of a player
				if(noOfTurns % 2 == 0){ // It's P1's turn
					System.out.println("\nIt's Player 1's turn!");
					attackCoord = p1.attack();
					result = p2.incomingMissile(attackCoord);
					p1.markResult(result, attackCoord);
					switch(result) {
					 	case "success": System.out.println("The hit was succesful!");
					 					System.out.println("Your opponent is bleeding but the battle goes on!");
					 					break;
					 	case "headshot": System.out.println("BOOOOOM.....HEADSHOT!");
					 					 System.out.println("You've just fully destroyed one of the opponent's ships!");
					 					 if(p2.getTotalHits() == 17) { // checking if the game is finished
					 						 System.out.println("\nEt VOILA! That was the final hit!");
					 						 System.out.println("P1 - Hits taken : "+p1.getTotalHits());
					 						 System.out.println("P2 - Hits taken : "+p2.getTotalHits());
					 						 System.out.println("Congratulations Player 1! All of the opponent's ships have been sunked and you have won the game!");
					 						 System.out.println("\nThank you for playing!");
					 						 endGame = true;
					 					 } else {
											System.out.println("Your opponent is bleeding but the battle goes on!");
					 					 }
					 					 break;
					 	case "fail": System.out.println("MISSED! Your missile ended up on the bottom of the sea!");
					 				 break;
					}
				} else { // It's P2's turn
					System.out.println("\nIt's Player 2's turn!");
					attackCoord = p2.attack();
					result = p1.incomingMissile(attackCoord);
					p2.markResult(result, attackCoord);
					switch(result) {
					 	case "success": System.out.println("The hit was succesful!");
					 					System.out.println("Your opponent is bleeding but the battle goes on!");
					 					break;
					 	case "headshot": System.out.println("BOOOOOM.....HEADSHOT!");
					 					 System.out.println("You've just fully destroyed one of the opponent's ships!");
					 					 if(p1.getTotalHits() == 17) { // checking if the game is finished
					 						 System.out.println("\nEt VOILA! That was the final hit!");
					 						 System.out.println("P1 - Hits taken : "+p1.getTotalHits());
					 						 System.out.println("P2 - Hits taken : "+p2.getTotalHits());
					 						 System.out.println("Congratulations Player 2! All of the opponent's ships have been sunked and you have won the game!");
					 						 System.out.println("\n		Thank you for playing!\n");
					 						 endGame = true;
					 					 } else {
											System.out.println("Your opponent is bleeding but the battle goes on!");
					 					 }
					 					 break;
					 	case "fail": System.out.println("MISSED! Your missile ended up on the bottom of the sea!");
					 				 break;
					} 
				}
				noOfTurns++;
			} // while(endGame)
			System.out.println("Would you like to play a new game? (y/n)");
			while(true) {
				String opt = input.nextLine().toLowerCase();
				int i;
				switch(opt) {
					case "y": 
						newGame = true;
						i=1;
						break;
					case "n":
						newGame = false;
						i=2;
						break;
					default:
						i=3;
						System.out.println("Your command is unreadable! Please use 'y' or 'n' to answer.");
						System.out.println("So, would you like to play a new game? (y/n)");
						break;
				}
				if(i==1) {
					break;
				} else if(i==2) {
					System.out.println("Thank you for playing Battleships! Have a nice day!");
					break;
				}
			}
		} // while(newGame)
	}

}
