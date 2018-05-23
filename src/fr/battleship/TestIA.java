package fr.battleship;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import copetchi.stefan.players.AI;
import copetchi.stefan.players.Player;

public class TestIA {

	public static void main(String[] args) throws FileNotFoundException {
		
		int beginnerWins, intermediateWins, expertWins;
		PrintWriter pw = new PrintWriter(new File("ai_proof.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("AI Name");
        sb.append(';');
        sb.append("score");
        sb.append(';');
        sb.append("AI Name2");
        sb.append(';');
        sb.append("score2");
        sb.append('\n');

		
		// Beginner vs Intermediate
		beginnerWins = 0;
		intermediateWins = 0;
		for(int i=1;i<=100;i++) {
			Player p1,p2;
			// initialisation
			if(i%2 == 0) { // beginner AI begins first
				p1 = AI.createAIforDifficultyLevel(1);
				p2 = AI.createAIforDifficultyLevel(2);
			} else { // intermediate AI begins first
				p1 = AI.createAIforDifficultyLevel(2);
				p2 = AI.createAIforDifficultyLevel(1);
			}
			
			// ship placement
			p1.shipPlacement();
			p2.shipPlacement();
			
			// battle phase
			int noOfTurns = 0;
			String attackCoord, result;
			boolean endGame = false;
			while(!endGame) {
				if(noOfTurns % 2 == 0){ // It's P1's turn
					attackCoord = p1.attack();
					result = p2.incomingMissile(attackCoord);
					p1.markResult(result, attackCoord);
					if(result.equals("headshot") && p2.getTotalHits() == 17) {
						if(i%2 == 0) { // if the beginner is P1
							beginnerWins++;
						} else { 
							intermediateWins++;
						}
						endGame = true;						
					}
				} else {
					attackCoord = p2.attack();
					result = p1.incomingMissile(attackCoord);
					p2.markResult(result, attackCoord);
					if(result.equals("headshot") && p1.getTotalHits() == 17) {
						if(i%2 == 0) { // if the beginner is P1
							intermediateWins++;
						} else { 
							beginnerWins++;
						}
						endGame = true;						
					}
				}
				noOfTurns++;
			}
		}
		
		// results processing
		System.out.println("Beginner AI score : "+beginnerWins);
		System.out.println("Intermediate AI score : "+intermediateWins);
		sb.append("AI Level Beginner");
        sb.append(';');
        sb.append(beginnerWins);
        sb.append(';');
        sb.append("AI Level Medium");
        sb.append(';');
        sb.append(intermediateWins);
        sb.append('\n');
		
		// Beginner vs Expert
		beginnerWins = 0;
		expertWins = 0;
		for(int i=1;i<=100;i++) {
			Player p1,p2;
			// initialisation
			if(i%2 == 0) { // beginner AI begins first
				p1 = AI.createAIforDifficultyLevel(1);
				p2 = AI.createAIforDifficultyLevel(3);
			} else { // expert AI begins first
				p1 = AI.createAIforDifficultyLevel(3);
				p2 = AI.createAIforDifficultyLevel(1);
			}

			// ship placement
			p1.shipPlacement();
			p2.shipPlacement();

			// battle phase
			int noOfTurns = 0;
			String attackCoord, result;
			boolean endGame = false;
			while(!endGame) {
				if(noOfTurns % 2 == 0){ // It's P1's turn
					attackCoord = p1.attack();
					result = p2.incomingMissile(attackCoord);
					p1.markResult(result, attackCoord);
					if(result.equals("headshot") && p2.getTotalHits() == 17) {
						if(i%2 == 0) { // if the beginner is P1
							beginnerWins++;
						} else { 
							expertWins++;
						}
						endGame = true;						
					}
				} else {
					attackCoord = p2.attack();
					result = p1.incomingMissile(attackCoord);
					p2.markResult(result, attackCoord);
					if(result.equals("headshot") && p1.getTotalHits() == 17) {
						if(i%2 == 0) { // if the beginner is P1
							expertWins++;
						} else { 
							beginnerWins++;
						}
						endGame = true;						
					}
				}
				noOfTurns++;
			}
		}
		
		// results processing
		System.out.println("\nBeginner AI score : "+beginnerWins);
		System.out.println("Expert AI score : "+expertWins);
		sb.append("AI Level Beginner");
        sb.append(';');
        sb.append(beginnerWins);
        sb.append(';');
        sb.append("AI Level Expert");
        sb.append(';');
        sb.append(expertWins);
        sb.append('\n');
		
		// Intermediate vs Expert
		intermediateWins = 0;
		expertWins = 0;
		for(int i=1;i<=100;i++) {
			Player p1,p2;
			// initialisation
			if(i%2 == 0) { // beginner AI begins first
				p1 = AI.createAIforDifficultyLevel(2);
				p2 = AI.createAIforDifficultyLevel(3);
			} else { // expert AI begins first
				p1 = AI.createAIforDifficultyLevel(3);
				p2 = AI.createAIforDifficultyLevel(2);
			}

			// ship placement
			p1.shipPlacement();
			p2.shipPlacement();

			// battle phase
			int noOfTurns = 0;
			String attackCoord, result;
			boolean endGame = false;
			while(!endGame) {
				if(noOfTurns % 2 == 0){ // It's P1's turn
					attackCoord = p1.attack();
					result = p2.incomingMissile(attackCoord);
					p1.markResult(result, attackCoord);
					if(result.equals("headshot") && p2.getTotalHits() == 17) {
						if(i%2 == 0) { // if the beginner is P1
							intermediateWins++;
						} else { 
							expertWins++;
						}
						endGame = true;						
					}
				} else {
					attackCoord = p2.attack();
					result = p1.incomingMissile(attackCoord);
					p2.markResult(result, attackCoord);
					if(result.equals("headshot") && p1.getTotalHits() == 17) {
						if(i%2 == 0) { // if the beginner is P1
							expertWins++;
						} else { 
							intermediateWins++;
						}
						endGame = true;						
					}
				}
				noOfTurns++;
			}
		}
		
		// results processing
		System.out.println("\nIntermediate AI score : "+intermediateWins);
		System.out.println("Expert AI score : "+expertWins);
		sb.append("AI Level Medium");
        sb.append(';');
        sb.append(intermediateWins);
        sb.append(';');
        sb.append("AI Level Expert");
        sb.append(';');
        sb.append(expertWins);
        sb.append('\n');

		// After the three game turns have ended ...
        
        // Write into the csv file
        pw.write(sb.toString());
        pw.close();
		
	}

}
