package control;
import model.WmrNetworkUtilityClass;
import queries.ListTerminiOfALine;
import queries.StationsInLineAndTotalTravelTime;


public class WmrNetworkController implements Controller{

	/*
	 * @author Ganiyu Isola
	 * @version 2020 - 12 - 31
	 */

	/**
	 *
	 */
	private final StationsInLineAndTotalTravelTime trainLine;
	private final ListTerminiOfALine terminiOnTrainLine;
	/*
	 * Instance of model object to be initialised in the controller constructor.
	 * This will be used to access the model methods when implementing the inner methods of the inherited methods from the interface.
	 * Controller calls the methods in the models for specific query
	 */



	/*
	 * Constructor
	 * @param wmrNetworkModel
	 */
	public WmrNetworkController(WmrNetworkUtilityClass wmrUtility) {
		trainLine = new StationsInLineAndTotalTravelTime();
		terminiOnTrainLine = new ListTerminiOfALine();
	}
	
	

	@Override
	public String listTermini(String line) {
		// TODO Auto-generated method stub
		return terminiOnTrainLine.listAllTerminiOfASpecifiedLine(line);
	}



	@Override
	public String listStationsInLine(String line) {
		return trainLine.listAllStationsOnLineAndTotalTravelTime(line);
	}


	@Override
	public String listAllLines() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String showAccessiblePath(String fromStation, String toStation) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String showAllPaths(String fromStation, String toStation) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String showShortestPath(String fromStation, String toStation) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	private static void display(String info) {
		System.out.println(info);
	}
	

}
