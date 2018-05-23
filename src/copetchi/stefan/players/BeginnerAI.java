package copetchi.stefan.players;

import java.util.Random;

public class BeginnerAI extends AI{

	// ----- Constructor
	
	public BeginnerAI(String name) {
		super(name);
	}
	
	// ----- Getters and Setters
	
	// - NONE - 
	
	// ----- Class methods
	
	// - NONE - 
	
	// ----- Inherited abstract methods
	
	// Inherited abstract method of attack that returns the string coordinate of the next attack
	@Override
	public String attack() {
		String coord;
		Random r = new Random();
		char c = (char)(r.nextInt(10) + 'A');
		int i = r.nextInt(10)+1;
		coord = String.valueOf(c) + String.valueOf(i);
		return coord;
	}
	
	// Inherited abstract method which marks the result of a launched attack(if necessary)
	@Override
	public void markResult(String result, String coord) {
		// BeginnerAI does not store any information on the result of the attack
		// Therefore this method's body is empty
	}

}
