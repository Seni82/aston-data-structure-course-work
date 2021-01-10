package view;

import java.util.Scanner;
import control.Controller;

/**
 * A simple text-based user interface for showing various information 
 * about a West Midlands Railway network.
 * 
 * @author Sylvia Wong
 * @version 08-11-2020
 */
public class TUI {

	
	
	private Controller controller;  
	private Scanner stdIn;
	
	
	
	public TUI(Controller controller)  {
		
		this.controller = controller;
		
		// Creates a Scanner object for obtaining user input
		stdIn = new Scanner(System.in);
		
		while (true) {
			displayMenu();
			getAndProcessUserOption();
		}
	}

	/**
	 * Displays the header of this application and a summary of menu options.
	 */
	private void displayMenu() {
		display(header());
		display(menu());
	}
	
	/**
	 * Obtains an user option and processes it.
	 * @throws Exception 
	 */
	private void getAndProcessUserOption()  {
		String stationA;
		String stationB;
		
		String command = stdIn.nextLine().trim();
		switch (command) {
		case "1" : // Lists all termini along a specified line
			display("Lists all termini along a line...");
			display("Enter the ID of the required line.");
			display(allWMRlines());
			display(controller.listTermini(stdIn.nextLine().trim()));
			break;
		case "2" : // Lists all stations in a line
			display("Lists all stations along a line...");
			display("Enter the ID of the line you'd like to view:");
			display(allWMRlines());
			display(controller.listStationsInLine(stdIn.nextLine().trim()));
			break;
		case "3" : // List all lines in the network and their total travel time
			display("Lists all lines and their travel time...");
			display(controller.listAllLines());
			break;
		case "4" : // Finds an accessible path between two stations
			display("Finds a path between two stations...");
			display("Enter the name of the intended start station:");
			stationA = stdIn.nextLine().trim();
			display("Enter the name of the intended destination station:");
			stationB = stdIn.nextLine().trim();
			display(controller.showAccessiblePath(stationA, stationB));
			break;
		case "5" : // Finds all paths between two stations
			display("Finds all paths between two stations...");
			display("Enter the name of the start station:");
			stationA = stdIn.nextLine().trim();
			display("Enter the name of the destination station:");
			stationB = stdIn.nextLine().trim();
			display(controller.showAllPaths(stationA, stationB));
			break;
		case "6" : // Finds the shortest path between two stations
			display("Finds the shortest paths between two stations...");
			display("Enter the name of the start station:");
			stationA = stdIn.nextLine().trim();
			display("Enter the name of the destination station:");
			stationB = stdIn.nextLine().trim();
			display(controller.showShortestPath(stationA, stationB));
			break;
		case "7" : // Exits the application
			display("Goodbye!");
			System.exit(0);
			break;
		default : // Not a known command option
			display(unrecogniseCommandErrorMsg(command));
		}
	}

	/*
	 * Returns a string representation of all railway lines in this application.
	 * @return info about all West Midlands Railway lines
	 */
	private static String allWMRlines() {
		StringBuilder allLines = new StringBuilder();
		allLines.append("\na. Birmingham -- Dorridge -- Leamington Spa");
		allLines.append("\nb. Cross City Line"); 
		allLines.append("\nc. Birmingham -- Rugby -- Northampton -- London");
		allLines.append("\nd. Nuneaton -- Coventry");
		allLines.append("\ne. Watford -- St Albans Abbey");
		allLines.append("\nf. Bletchley -- Bedford");
		allLines.append("\ng. Crewe -- Stoke -- Stafford -- London");
		allLines.append("\nh. Worcester -- Birmingham"); 
		allLines.append("\ni. Smethwick Galton Bridge Connections");
		allLines.append("\nj. Birmingham -- Stratford-upon-Avon" );
		allLines.append("\nk. Birmingham -- Wolverhampton -- Telford -- Shrewsbury"); 
		allLines.append("\nl. Birmingham -- Worcester -- Hereford");
		allLines.append("\nm. Birmingham -- Walsall -- Rugeley");
		return allLines.toString();
	}
	
	/*
	 * Returns a string representation of a brief title for this application as the header.
	 * @return	a header
	 */
	private static String header() {
		return "\nWMR Network Route Finder\n";
	}
	
	/*
	 * Returns a string representation of the user menu.
	 * @return	the user menu
	 */
	private static String menu() {
		StringBuilder allMenuOptions = new StringBuilder();
		allMenuOptions.append("Enter the number associated with your chosen menu option.\n");
		allMenuOptions.append("1: List all termini in a specified line\n");
		allMenuOptions.append("2: List all stations along a specified line and their travel time\n");
		allMenuOptions.append("3: List all lines in the network\n");
		allMenuOptions.append("4: Find an acessible path between two stations\n");
		allMenuOptions.append("5: Find all paths between two stations\n");
		allMenuOptions.append("6: Find the shortest path between two stations\n");
		allMenuOptions.append("7: Exit this application\n");
		return allMenuOptions.toString();
	}
	
	/*
	 * Displays the specified info for the user to view.
	 * @param info	info to be displayed on the screen
	 */
	private void display(String info) {
		System.out.println(info);
	}
	
    /*
     * Returns an error message for an unrecognised command.
     * 
     * @param error the unrecognised command
     * @return      an error message
     */
    private static String unrecogniseCommandErrorMsg(String error) {
            return String.format("Cannot recognise the given command: %s.%n", error);
    }

}



