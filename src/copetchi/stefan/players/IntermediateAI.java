package copetchi.stefan.players;

import java.util.ArrayList;
import java.util.Random;

public class IntermediateAI extends AI{

	// List containing the coordinates of all the previous attacks
	private ArrayList<String> previousAttacks;
	
	// ----- Constructor
	
	public IntermediateAI(String name) {
		super(name);
		this.previousAttacks = new ArrayList<String>();
	}
	
	// ----- Getters and Setters
	
	public ArrayList<String> getPreviousAttacks() {
		return previousAttacks;
	}

	public void setPreviousAttacks(ArrayList<String> previousAttacks) {
		this.previousAttacks = previousAttacks;
	}
	
	// ----- Class methods
	
	// - NONE - 
	
	// ----- Inherited abstract methods
	
	// Inherited abstract attack method which returns the string coordinate of the next attack
	@Override
	public String attack() {
		String coord;
		Random r = new Random();
		char c = (char)(r.nextInt(10) + 'A');
		int i = r.nextInt(10)+1;
		coord = String.valueOf(c) + String.valueOf(i);
		if(!this.previousAttacks.isEmpty() && this.previousAttacks.size() < 100) {
			while(this.previousAttacks.contains(coord)) {
				c = (char)(r.nextInt(10) + 'A');
				i = r.nextInt(10)+1;
				coord = String.valueOf(c) + String.valueOf(i);
			}
		}
		if(this.previousAttacks.size() == 100) {
			this.previousAttacks.clear();
		}
		return coord;
	}
	
	// Inherited abstract method which marks the result of a launched attack(if necessary)
	@Override
	public void markResult(String result, String coord) {
		// For the Intermediate AI, the result doesn't matter so we just store the coordinate of the attack
		this.previousAttacks.add(coord);
	}

}
