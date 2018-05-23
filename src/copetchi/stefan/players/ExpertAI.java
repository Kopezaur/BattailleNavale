package copetchi.stefan.players;

import java.util.ArrayList;
import java.util.Random;

public class ExpertAI extends AI{

	// List containing the coordinates of all the previous attacks
	private ArrayList<String> previousAttacks;
	// Boolean which knows if the last hit was succesful or not, and if the following attack is predicted
	private boolean predictNextAttack;
	// Coordinates of the last succesful hit on the enemy
	private String lastHit;
	// Predicted coordinates for the next attack
	private String nextAttack;
	// Boolean which knows if the attacks are on the good alignment so he can keep the direction or change it
	private boolean alignment;
	// Integer which defines the alignment in which the last attack succesfully hit
	// 1 - Up, 2 - Right, 3 - Down, 4 - Left
	private int direction;
	
	// ----- Constructor
	
	public ExpertAI(String name) {
		super(name);
		this.previousAttacks = new ArrayList<String>();
		this.predictNextAttack = false;
		this.lastHit = null;
		this.nextAttack = null;
		this.alignment = false;
		this.direction = 0;
	}
	
	// ----- Getters and Setters
	
	public ArrayList<String> getPreviousAttacks() {
		return previousAttacks;
	}

	public void setPreviousAttacks(ArrayList<String> previousAttacks) {
		this.previousAttacks = previousAttacks;
	}

	public boolean isPredictNextAttack() {
		return predictNextAttack;
	}

	public void setPredictNextAttack(boolean predictNextAttack) {
		this.predictNextAttack = predictNextAttack;
	}

	public String getLastHit() {
		return lastHit;
	}

	public void setLastHit(String lastHit) {
		this.lastHit = lastHit;
	}

	public String getNextAttack() {
		return nextAttack;
	}

	public void setNextAttack(String nextAttack) {
		this.nextAttack = nextAttack;
	}

	public boolean isAlignment() {
		return alignment;
	}

	public void setAlignment(boolean alignment) {
		this.alignment = alignment;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	} 
	
	// ----- Class methods
	
	@Override
	public String toString(){
		return this.getName();
	}
	
	// ----- Inherited abstract methods
	
	// Inherited abstract attack method which returns the string coordinate of the next attack
	
	@Override
	public String attack() {
		String coord;
		Random r = new Random();
		if(this.isPredictNextAttack()) { // If the last hit was succesful, the next attack is computed
			coord = this.getNextAttack(); 
		} else { // If the last attack was a fail, it keeps shooting at random coordinates
			char c = (char)(r.nextInt(10) + 'A');
			int i = r.nextInt(10)+1;
			coord = String.valueOf(c) + String.valueOf(i);
			if(!this.previousAttacks.isEmpty() && this.previousAttacks.size() < 100) {
				while(this.getPreviousAttacks().contains(coord)) {
					c = (char)(r.nextInt(10) + 'A');
					i = r.nextInt(10)+1;
					coord = String.valueOf(c) + String.valueOf(i);
				}
			}
			if(this.previousAttacks.size() == 100) {
				this.previousAttacks.clear();
			}
		}
		return coord;
	}
	
	// Inherited abstract method which marks the result of a launched attack(if necessary)
	@Override
	public void markResult(String result, String coord) {
		char c;
		int i;
		Random r = new Random();
		switch(result) {
		case "success":
			this.previousAttacks.add(coord);
			this.predictNextAttack = true;
			boolean det = false;
			if(lastHit != null ) { // if the last hit was succesful as well
				while(true) { // this loops keeps going until we find the next coordinate
					det = false;
					if(this.alignment) { // if he is on the correct alignment, he keeps attacking in the same direction
						switch(this.direction) {
							case 1:
								c = coord.charAt(0);
								i = Integer.parseInt(coord.substring(1)) - 1;
								if(i >= 1) {
									this.nextAttack = String.valueOf(c) + String.valueOf(i);
								} else { // this means the nextAttack is out of bounds
									det = true;
								}
								break;
							case 2:
								c = (char) (coord.charAt(0) + 1);
								i = Integer.parseInt(coord.substring(1));
								if(c <= 'J') {
									this.nextAttack = String.valueOf(c) + String.valueOf(i);
								} else { // this means the nextAttack is out of bounds
									det = true;
								}
								break;
							case 3:
								c = coord.charAt(0);
								i = Integer.parseInt(coord.substring(1)) + 1;
								if(i <= 10) {
									this.nextAttack = String.valueOf(c) + String.valueOf(i);
								} else { // this means the nextAttack is out of bounds
									det = true;
								}
								break;
							case 4:
								c = (char) (coord.charAt(0) - 1);
								i = Integer.parseInt(coord.substring(1));
								if(c >= 'A') {
									this.nextAttack = String.valueOf(c) + String.valueOf(i);
								} else { // this means the nextAttack is out of bounds
									det = true;
								}
								break;
						}
					} else { // if he did not kill the battleship yet, but he touched it
						this.alignment = true;
						switch(this.direction) {
							case 1:
								c = coord.charAt(0);
								i = Integer.parseInt(coord.substring(1)) - 1;
								if(i >= 1) {
									this.nextAttack = String.valueOf(c) + String.valueOf(i);
								} else { // this means the nextAttack is out of bounds
									det = true;
								}
								break;
							case 2:
								c = (char) (coord.charAt(0) + 1);
								i = Integer.parseInt(coord.substring(1));
								if(c <= 'J') {
									this.nextAttack = String.valueOf(c) + String.valueOf(i);
								} else { // this means the nextAttack is out of bounds
									det = true;
								}
								break;
							case 3:
								c = coord.charAt(0);
								i = Integer.parseInt(coord.substring(1)) + 1;
								if(i <= 10) {
									this.nextAttack = String.valueOf(c) + String.valueOf(i);
								} else { // this means the nextAttack is out of bounds
									det = true;
								}
								break;
							case 4:
								c = (char) (coord.charAt(0) - 1);
								i = Integer.parseInt(coord.substring(1));
								if(c >= 'A') {
									this.nextAttack = String.valueOf(c) + String.valueOf(i);
								} else { // this means the nextAttack is out of bounds
									det = true;
								}
								break;
						}
					}
					if(det) { // if the next Attack was out of boundaries
						this.alignment = false;
						this.direction++;
						if(this.direction == 5) {
							c = (char)(r.nextInt(10) + 'A');
							i = r.nextInt(10)+1;
							this.nextAttack = String.valueOf(c) + String.valueOf(i);
							if(!this.previousAttacks.isEmpty() && this.previousAttacks.size() < 100) {
								while(this.getPreviousAttacks().contains(this.nextAttack)) {
									c = (char)(r.nextInt(10) + 'A');
									i = r.nextInt(10)+1;
									this.nextAttack = String.valueOf(c) + String.valueOf(i);
								}
							}
							this.direction = 1;
							break;
						}
					} else { // this means a coordinate has been found without exceptions
						if(this.getPreviousAttacks().contains(this.nextAttack)) { // the coordinate has already gotten shot once
							this.alignment = false;
							this.direction++;
							if(this.direction == 5) {
								c = (char)(r.nextInt(10) + 'A');
								i = r.nextInt(10)+1;
								this.nextAttack = String.valueOf(c) + String.valueOf(i);
								if(!this.previousAttacks.isEmpty() && this.previousAttacks.size() < 100) {
									while(this.getPreviousAttacks().contains(this.nextAttack)) {
										c = (char)(r.nextInt(10) + 'A');
										i = r.nextInt(10)+1;
										this.nextAttack = String.valueOf(c) + String.valueOf(i);
									}
								}
								this.direction = 1;
								break;
							}
						} else { // the coordinate is available
							this.alignment = true;
							break;
						}
					}
				}
			} else { // if the last hit was a fail
				// first time he attacks UP
				c = coord.charAt(0);
				i = Integer.parseInt(coord.substring(1)) - 1;
				this.nextAttack = String.valueOf(c) + String.valueOf(i);
				if(i >= 1 && !this.previousAttacks.contains(this.nextAttack)) {
					this.direction = 1;
				} else { // this means the nextAttack is out of bounds
					// then he attacks RIGHT
					c = (char) (coord.charAt(0) + 1);
					i = Integer.parseInt(coord.substring(1));
					this.nextAttack = String.valueOf(c) + String.valueOf(i);
					if(c <= 'J' && !this.previousAttacks.contains(this.nextAttack)) {
						this.direction = 2;
					} else { // this means the nextAttack is out of bounds
						// then he attacks DOWN
						c = coord.charAt(0);
						i = Integer.parseInt(coord.substring(1)) + 1;
						this.nextAttack = String.valueOf(c) + String.valueOf(i);
						if(i <= 10 && !this.previousAttacks.contains(this.nextAttack)) {
							this.direction = 3;
						} else { // this means the nextAttack is out of bounds
							// and finally, he attacks LEFT if the others failed
							c = (char) (coord.charAt(0) - 1);
							i = Integer.parseInt(coord.substring(1));
							this.direction = 4;
							this.nextAttack = String.valueOf(c) + String.valueOf(i);
						}
					}
				}
				this.alignment = false;
			}
			this.lastHit = coord;
			break;
		case "headshot": case "fail":
			this.previousAttacks.add(coord);
			this.predictNextAttack = false;
			this.lastHit = null;
			this.nextAttack = null;
			this.alignment = false;
			this.direction = 0;
			break;
		}
	}

}
