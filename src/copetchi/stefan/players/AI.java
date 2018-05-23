package copetchi.stefan.players;

import java.util.Random;

import copetchi.stefan.structures.Ship;

public abstract class AI extends Player{
	
	// ----- Constructors

	public AI(String name) {
		super(name);
	}
	
	public static final AI createAIforDifficultyLevel(int level) {
		AI ai = null;
		switch(level) {
			case 1:
				ai = new BeginnerAI("AI(Beginner)");
				break;
			case 2:
				ai = new IntermediateAI("AI(Intermediate)");
				break;
			case 3:
				ai = new ExpertAI("AI(Expert)");
				break;
		}
		return ai;
	}
	
	// ----- Getters and Setters
	
	// - NONE -
	
	// ----- Class methods

	@Override
	public String toString(){
		return "AI (Difficulty level not defined)";
	}

	// ----- Inherited abstract methods
	
	// Default ship placement method overridden from the 'Player' class
	// It does an automatic ship placement in random positions with random orientations
	@Override
	public final void shipPlacement(){
//		System.out.println("Beginning placement of the ships for "+this.toString()+"...");
		// Local variables
		Random r = new Random();
		boolean orientation;
		char c;
		int i;
		String start, end;
		
		// Place the Carrier(size = 5)
		orientation = r.nextBoolean();
		if(orientation){ //horizontal
			c = (char)(r.nextInt(6) + 'A');
			i = r.nextInt(10)+1;
			start = String.valueOf(c) + String.valueOf(i);
			end = String.valueOf((char)(c + 4)) + String.valueOf(i);
		} else { //vertical
			c = (char)(r.nextInt(10) + 'A');
			i = r.nextInt(5)+1;
			start = String.valueOf(c) + String.valueOf(i);
			end = String.valueOf(c) + String.valueOf(i+4);
		}
		this.getShips().add(new Ship(start, end));
//		System.out.println("Carrier placed!");
		
		// Place the Battleship(size = 4)
		while(true){
			orientation = r.nextBoolean();
			if(orientation){ //horizontal
				c = (char)(r.nextInt(7) + 'A');
				i = r.nextInt(10)+1;
				start = String.valueOf(c) + String.valueOf(i);
				end = String.valueOf((char)(c + 3)) + String.valueOf(i);
			} else { //vertical
				c = (char)(r.nextInt(10) + 'A');
				i = r.nextInt(6)+1;
				start = String.valueOf(c) + String.valueOf(i);
				end = String.valueOf(c) + String.valueOf(i+3);
			}
			if(this.checkShipCoordinates(start, end)){
				this.getShips().add(new Ship(start, end));
//				System.out.println("Battleship placed!");
				break;
			}
		}
		
		// Place the Cruiser(size = 3)
		while(true){
			orientation = r.nextBoolean();
			if(orientation){ //horizontal
				c = (char)(r.nextInt(8) + 'A');
				i = r.nextInt(10)+1;
				start = String.valueOf(c) + String.valueOf(i);
				end = String.valueOf((char)(c + 2)) + String.valueOf(i);
			} else { //vertical
				c = (char)(r.nextInt(10) + 'A');
				i = r.nextInt(7)+1;
				start = String.valueOf(c) + String.valueOf(i);
				end = String.valueOf(c) + String.valueOf(i+2);
			}
			if(this.checkShipCoordinates(start, end)){
				this.getShips().add(new Ship(start, end));
//				System.out.println("Cruiser placed!");
				break;
			}
		}
		
		// Place the Submarine(size = 3)
		while(true){
			orientation = r.nextBoolean();
			if(orientation){ //horizontal
				c = (char)(r.nextInt(8) + 'A');
				i = r.nextInt(10)+1;
				start = String.valueOf(c) + String.valueOf(i);
				end = String.valueOf((char)(c + 2)) + String.valueOf(i);
			} else { //vertical
				c = (char)(r.nextInt(10) + 'A');
				i = r.nextInt(7)+1;
				start = String.valueOf(c) + String.valueOf(i);
				end = String.valueOf(c) + String.valueOf(i+2);
			}
			if(this.checkShipCoordinates(start, end)){
				this.getShips().add(new Ship(start, end));
//				System.out.println("Submarine placed!");
				break;
			}
		}
		
		// Place the Destroyer(size = 2)
		while(true){
			orientation = r.nextBoolean();
			if(orientation){ //horizontal
				c = (char)(r.nextInt(9) + 'A');
				i = r.nextInt(10)+1;
				start = String.valueOf(c) + String.valueOf(i);
				end = String.valueOf((char)(c + 1)) + String.valueOf(i);
			} else { //vertical
				c = (char)(r.nextInt(10) + 'A');
				i = r.nextInt(8)+1;
				start = String.valueOf(c) + String.valueOf(i);
				end = String.valueOf(c) + String.valueOf(i+1);
			}
			if(this.checkShipCoordinates(start, end)){
				this.getShips().add(new Ship(start, end));
//				System.out.println("Destroyer placed!\n");
				break;
			}
		}
		
	}
	
}
