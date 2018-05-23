package copetchi.stefan.structures;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Kopezaur
 *
 */
public class Ship {
	
	//-----Variables
	
	//The coordinates for the start and the end of the ship
	private String startCoord;
	private String endCoord;
	//An integer representing the length of the ship
	private int length;
	//An integer representing the number of hits the ship has taken
	private int noHits;
	//An array of strings determining the components of the ship which have not yet been hit
	private ArrayList<String> components;
	//Hashmap which knows which position has been hit and which hasn't
	private HashMap<String, Boolean> componentStatus;
	//A boolean representing the orientation of the ship
	// true - horizontal
	// false - vertical
	private boolean orientation;
	//A boolean variable representing the state of the ship
	private boolean dead;
	
	
	//-----Constructors
	
	public Ship(String startCoord, String endCoord) {
		super();
		this.startCoord = startCoord;
		this.endCoord = endCoord;
		this.orientation = computeOrientation(startCoord, endCoord);
		this.length = computeLength(startCoord, endCoord, this.orientation);
		this.components = computeComponents(startCoord, endCoord, this.orientation);
		this.setComponentStatus(initializeComponentStatusFor(this.components));
		this.noHits = 0;
		this.dead = false;
	}
	
	//-----Getters and setters

	public String getStartCoord() {
		return startCoord;
	}

	public void setStartCoord(String startCoord) {
		this.startCoord = startCoord;
	}

	public String getEndCoord() {
		return endCoord;
	}

	public void setEndCoord(String endCoord) {
		this.endCoord = endCoord;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public ArrayList<String> getComponents() {
		return components;
	}

	public void setComponents(ArrayList<String> components) {
		this.components = components;
	}

	public HashMap<String, Boolean> getComponentStatus() {
		return componentStatus;
	}

	public void setComponentStatus(HashMap<String, Boolean> componentStatus) {
		this.componentStatus = componentStatus;
	}

	public int getNoHits() {
		return noHits;
	}

	public void setNoHits(int noHits) {
		this.noHits = noHits;
	}
	
	public void addToNoHits() {
		this.noHits++;
	}

	public boolean isOrientation() {
		return orientation;
	}

	public void setOrientation(boolean orientation) {
		this.orientation = orientation;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	//-----Class methods
	
	// Given the coordinates, this methodes computes the orientation of the ship
	public static boolean computeOrientation(String start, String end){
		if(start.substring(0,1).equals(end.substring(0,1)))
			return false;
		else
			return true;
	}
	
	// Given the coordinates and the orientation, this method computes the length of the ship
	public static int computeLength(String start, String end, boolean orientation){
		int length = 0;
		if(orientation){ //horizontal
			String s = start.substring(0,1);
			String e = end.substring(0,1);
			length = ((int)e.toLowerCase().charAt(0) - (int)s.toLowerCase().charAt(0)) + 1;
		} else { //vertical
			int s = Integer.parseInt(start.substring(1));
			int e = Integer.parseInt(end.substring(1));
			length= 1+e-s;
		}
		return length;
	}
	
	//Given the coordinates and the orientation, this method computes the array of components for the ship
	public static ArrayList<String> computeComponents(String start, String end, boolean orientation){
		ArrayList<String> components = new ArrayList<String>();
		StringBuilder sb;
		if(orientation){ //horizontal
			for(char c = start.charAt(0); c <= end.charAt(0); c++){
				sb = new StringBuilder();
				sb.append(c);
				sb.append(start.substring(1));
				components.add(sb.toString());
			}
		} else { //vertical
			int endOfFor;
			if(end.substring(1).equals("10")){
				endOfFor = ((int)'9')+1;
			} else {
				endOfFor = (int)end.charAt(1);
			}
			for(int i = (int)start.charAt(1); i <= endOfFor; i++){
				sb = new StringBuilder();
				sb.append(start.charAt(0));
				sb.append(i-48);
				components.add(sb.toString());
			}
		}
		return components;
	}
	
	// Method to initialize the Hashmap for the status of the components
	public HashMap<String, Boolean> initializeComponentStatusFor(ArrayList<String> components){
		HashMap<String, Boolean> m = new HashMap<String, Boolean>();
		for(int i=0;i<this.getComponents().size();i++){
			m.put(this.getComponents().get(i), false);
		}
		return m;
	}
	
	// Method which checks if the incoming attack succesfully touched the ship
	public boolean isHit(String pos){
		if(orientation){ //horizontal
			char s = this.startCoord.substring(0,1).charAt(0);
			char e = this.endCoord.substring(0,1).charAt(0);
			int lineNo = Integer.parseInt(startCoord.substring(1));
			for(char c=s;c<=e;c++){
				if(pos.charAt(0) == c && Integer.parseInt(pos.substring(1)) == lineNo)
					return true;
			}
			return false;
		} else { //vertical
			if(startCoord.substring(0, 1).equals(pos.substring(0, 1))){
				if(Integer.parseInt(pos.substring(1)) >= Integer.parseInt(this.startCoord.substring(1)) && Integer.parseInt(pos.substring(1)) <= Integer.parseInt(this.endCoord.substring(1))){
					return true;
				} else { 
					return false;
				}
			} else {
				return false;
			}
		}
	}

	@Override
	public String toString() {
		return "Ship [startCoord=" + startCoord + ", endCoord=" + endCoord + ", length=" + length + ", orientation="
				+ orientation + ", dead=" + dead + "]";
	}

}
