package view;
import java.io.FileNotFoundException;
import control.WmrNetworkController;
import model.GraphAdjacencyList;
import model.WmrNetworkUtilityClass;


/*
 * 
 */

/**
 * @author ganiyu isola
 * @version 2020 - 12 - 31
 */
public class Main {

	/*
	 *Initialise the application.
	 */
	public static void main(String[] args) {
		try {
			 new TUI(new WmrNetworkController(new WmrNetworkUtilityClass("./wmrNetwork_data/")));
			 System.out.println("Able to locate data folder path / location.");
		}
		catch(FileNotFoundException e) 
		{
			System.out.println("Cannot find the specified file(s): "+ e);
		}
	}

}

