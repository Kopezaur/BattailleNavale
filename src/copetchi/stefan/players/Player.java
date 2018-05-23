package copetchi.stefan.players;
import java.util.ArrayList;

import copetchi.stefan.structures.Ship;

/**
 * @author Kopezaur
 *
 */
 
public abstract class Player {

	//-----Variables
	
	// String representing the name of the Player
	private String name;
	// Total number of hits the player's ships have taken(It can be maximum 17)
	private int totalHits;
	// The list of ships
	private ArrayList<Ship> ships;
	
	//-----Constructors
	
	public Player(String name){
		this.name = name;
		this.totalHits = 0;
		this.setShips(new ArrayList<Ship>());
	}

	//-----Getters and setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getTotalHits() {
		return totalHits;
	}
	
	public void setTotalHits(int totalHits) {
		this.totalHits = totalHits;
	}
	
	public void addToTotalHits(){
		this.totalHits++;
	}
	
	public ArrayList<Ship> getShips() {
		return ships;
	}

	public void setShips(ArrayList<Ship> ships) {
		this.ships = ships;
	}
	
	//-----Class methods
	
	// Method for checking if the inputted coordinates are not interfering with other already placed ships
	public final boolean checkShipCoordinates(String start, String end){
		//declare 'detective' variable
		boolean det = true;
		//iterate the ships
		for(int i=0;i<this.getShips().size();i++){
			Ship s = this.getShips().get(i);
			if(s.isOrientation()){ //already placed ship is horizontal
				if(Ship.computeOrientation(start, end)){ //new ship is horizontal as well
					if(s.getComponents().contains(start) || s.getComponents().contains(end)){
						det = false;
						break;
					}
				} else { //new ship is vertical
					//iterate ship components
					for(int j=0;j<s.getComponents().size();j++){
						if((s.getComponents().get(j).charAt(0) == start.charAt(0)) && (Integer.parseInt(s.getComponents().get(j).substring(1)) >= Integer.parseInt(start.substring(1))) && (Integer.parseInt(s.getComponents().get(j).substring(1)) <= Integer.parseInt(end.substring(1)))){
							det = false;
							break;
						}
					}
				}
			} else { //vertical
				if(Ship.computeOrientation(start, end)){ //new ship is horizontal 
					//iterate ship components
					for(int j=0;j<s.getComponents().size();j++){
						if((s.getComponents().get(j).charAt(0) == start.charAt(0)) && (Integer.parseInt(s.getComponents().get(j).substring(1)) >= Integer.parseInt(start.substring(1))) && (Integer.parseInt(s.getComponents().get(j).substring(1)) <= Integer.parseInt(end.substring(1)))){
							det = false;
							break;
						}
					}
				} else { //new ship is vertical as well
					if(s.getComponents().contains(start) || s.getComponents().contains(end)){
						det = false;
						break;
					}
				}
			}
		}
		return det;
	}
	
	// Method for checking if an incoming missile has succesfully hit one of the player's ships
	// This method also saves the information of a succesful hit by updating the necessary components
	public final String incomingMissile(String pos) {
		String msg = "fail";
		//iterate all the ships
		for(int i=0;i<this.getShips().size();i++){
			// this if gelps making sure we iterate only the ships which have not yet been destroyed
			if(!this.getShips().get(i).isDead()) {
				Ship s = this.getShips().get(i);
				//check if the coordinate overpolates with the ship's components
				if(s.isHit(pos)) { 
					// check if the ship has already been hit in that component
					if(!s.getComponentStatus().get(pos)) { 
						msg = "success";
						this.getShips().get(i).getComponentStatus().put(pos, true);
						this.getShips().get(i).addToNoHits();
						// check if this ship has been fully destroyed so we can skip it from iterating next time
						if(this.getShips().get(i).getLength() == this.getShips().get(i).getNoHits()) {
							this.getShips().get(i).setDead(true);
							msg = "headshot";
						}
						// we add to the total hits of the player 
						this.addToTotalHits();
						break;
					}
				}
			}
		}
		return msg;
	}
	
	// Default toString method
	public String toString(){
		return "Player";
	}
	
	// ----- Abstract methods
	public abstract void markResult(String result, String coord);
	
	// Method for ship placement 
	public abstract void shipPlacement();
	
	// Method for the attack phase
	public abstract String attack();
	
}
