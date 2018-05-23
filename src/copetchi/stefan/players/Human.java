package copetchi.stefan.players;

import java.util.Scanner;

import copetchi.stefan.structures.Ship;

public class Human extends Player{

	// Reflection of the opp's map, used for display
	private int[][] mapAdv = new int[11][11];
	
	// ----- Constructor
	
	public Human(String name) {
		super(name);
	}

	// ----- Getters and Setters
	
	public int[][] getMapAdv() {
		return mapAdv;
	}

	public void setMapAdv(int[][] mapAdv) {
		this.mapAdv = mapAdv;
	}
	
	// ----- Class methods

	// Method for printing your own map
	public void showPlayerMap(){
		//first we generate the image of the battlefield
		int[][] map = new int[11][11];
		for(int i=0;i<this.getShips().size();i++){
			Ship s = this.getShips().get(i);
			for(int j=0;j<s.getComponents().size();j++){
				int xCoord = Integer.parseInt(s.getComponents().get(j).substring(1));
				int yCoord = ((int)s.getComponents().get(j).charAt(0))-64;
				if(s.getComponentStatus().get(s.getComponents().get(j))){
					map[xCoord][yCoord] = 2;
				} else {
					map[xCoord][yCoord] = 1;
				}
			}
		}
		//then we display this image
		System.out.println();
		System.out.println("      A  B  C  D  E  F  G  H  I  J");
		System.out.println("     ------------------------------");
		for(int i=1;i<=10;i++){
			if(i<10){
				System.out.print(" "+ i + " |");
			} else {
				System.out.print(i + " |");
			}
			for(int j=1;j<=10;j++){
				if(map[i][j] == 2){
					System.out.print("  X");
				} else if(map[i][j] == 0) {
					System.out.print("  ~");
				} else {
					System.out.print("  "+map[i][j]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	// Method for printing the opponent's battlefield
	public void showOppMap(){
		System.out.println();
		System.out.println("    A  B  C  D  E  F  G  H  I  J");
		for(int i=1;i<=10;i++){
			if(i<10){
				System.out.print(" "+i);
			} else {
				System.out.print(i);
			}
			for(int j=1;j<=10;j++){
				if(this.getMapAdv()[i][j] == 2){
					System.out.print("  X");
				} else if(this.getMapAdv()[i][j] == 0) {
					System.out.print("  ~");
				} else {
					System.out.print("  O");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	@Override
	public String toString(){
		return this.getName()+" - (Human)";
	}
	
	// ----- Inherited abstract methods

	// This method will aid the user when placing the ships on his personal battlefield
	@SuppressWarnings("resource")
	@Override
	public void shipPlacement(){
		Scanner input = new Scanner(System.in);
		String start, end;
		System.out.println("\n" + this.getName() + ", it's your turn to select the position of your battleships on the battlefield.");
		
		// Place the Carrier
		System.out.println("The first ship to be placed is the Carrier(size = 5). The map is empty so you can place it wherever you want and make sure you input the coordinates accordingly.");
		while(true){
			System.out.print("Insert the start position : ");
			start = input.nextLine();
			System.out.print("Insert the end position : ");
			end = input.nextLine();
			if(Ship.computeLength(start, end, Ship.computeOrientation(start, end)) == 5){
				System.out.println("Ship was succesfully placed!");
				getShips().add(new Ship(start, end));
				break;
			} else {
				System.out.println("Your coordinates do not meet the specified requirements. Please try again!");
			}
		}
		
		this.showPlayerMap();
		
		// Place the Battleship
		System.out.println("Next up is the Battleship(size = 4)");
		while(true){
			System.out.print("Insert the start position : ");
			start = input.nextLine();
			System.out.print("Insert the end position : ");
			end = input.nextLine();
			if(Ship.computeLength(start, end, Ship.computeOrientation(start, end)) == 4){
				if(this.checkShipCoordinates(start, end)){
					System.out.println("Ship was succesfully placed!");
					getShips().add(new Ship(start, end));
					break;
				} else {
					System.out.println("The coordinates you have entered overpass an already placed ship. Please try again!");
				}
			} else {
				System.out.println("The size of the battleship coordinates you have entered is not good. Please try again!");
			}
		}
		
		this.showPlayerMap();
		
		// Place the cruiser
		System.out.println("Next up is the Cruiser(size = 3)");
		while(true){
			System.out.print("Insert the start position : ");
			start = input.nextLine();
			System.out.print("Insert the end position : ");
			end = input.nextLine();
			if(Ship.computeLength(start, end, Ship.computeOrientation(start, end)) == 3){
				if(this.checkShipCoordinates(start, end)){
					System.out.println("Ship was succesfully placed!");
					getShips().add(new Ship(start, end));
					break;
				} else {
					System.out.println("The coordinates you have entered overpass an already placed ship. Please try again!");
				}
			} else {
				System.out.println("The size of the battleship coordinates you have entered is not good. Please try again!");
			}
		}
		
		this.showPlayerMap();
		
		// Place the submarine
		System.out.println("Next up is the Submarine(size = 3)");
		while(true){
			System.out.print("Insert the start position : ");
			start = input.nextLine();
			System.out.print("Insert the end position : ");
			end = input.nextLine();
			if(Ship.computeLength(start, end, Ship.computeOrientation(start, end)) == 3){
				if(this.checkShipCoordinates(start, end)){
					System.out.println("Ship was succesfully placed!");
					getShips().add(new Ship(start, end));
					break;
				} else {
					System.out.println("The coordinates you have entered overpass an already placed ship. Please try again!");
				}
			} else {
				System.out.println("The size of the battleship coordinates you have entered is not good. Please try again!");
			}
		}
		
		this.showPlayerMap();
		
		// Place the Destroyer
		System.out.println("Next up is the Destroyer(size = 2)");
		while(true){
			System.out.print("Insert the start position : ");
			start = input.nextLine();
			System.out.print("Insert the end position : ");
			end = input.nextLine();
			if(Ship.computeLength(start, end, Ship.computeOrientation(start, end)) == 2){
				if(this.checkShipCoordinates(start, end)){
					System.out.println("Ship was succesfully placed!");
					getShips().add(new Ship(start, end));
					break;
				} else {
					System.out.println("The coordinates you have entered overpass an already placed ship. Please try again!");
				}
			} else {
				System.out.println("The size of the battleship coordinates you have entered is not good. Please try again!");
			}
		}
		
		this.showPlayerMap();
		
		System.out.println("Well done! Now all your battleships are in place. \n");
		System.out.println("Moving on to the next turn...\n");
		
	}
	
	// This method simulates the attack phase of a human player
	@SuppressWarnings("resource")
	@Override
	public String attack(){
		Scanner input = new Scanner(System.in);
		String coord = null;
		System.out.println(this.getName()+", here is your opponent's battlefield : ");
		this.showOppMap();
		
		System.out.println("\n"+this.getName()+", insert the coordinates of the position that you want to attack : ");
		while(true) {
			coord = input.nextLine();
			if((coord.charAt(0) >= 'A' && coord.charAt(0) <= 'J') && (Integer.parseInt(coord.substring(1)) >= 1 && (Integer.parseInt(coord.substring(1)) <= 10))){
				break;
			} else {
				System.out.println("The coordinate you have entered exceeds the limit of the battlefield! Please try again.");
				System.out.println("Your choice : ");
			}
		}
		return coord;
	}
	
	// Method for marking the result of an attack on the image of the opponent's Map
	public void markResult(String result, String coord) {
		int xCoord = Integer.parseInt(coord.substring(1));
		int yCoord = ((int)coord.charAt(0))-64;
		switch(result) {
			case "success":
				this.getMapAdv()[xCoord][yCoord] = 2;
				break;
			case "headshot":
				this.getMapAdv()[xCoord][yCoord] = 2;
				break;
			case "fail":
				this.getMapAdv()[xCoord][yCoord] = 1;
				break;
		}
	}
}
