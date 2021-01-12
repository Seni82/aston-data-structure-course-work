package model;
import view.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class WmrNetworkUtilityClass {

	
	private Scanner wmrLinesDataReader = null;
	private Scanner stepFreeAccessStationDataReader = null;
	

	public WmrNetworkUtilityClass(String dataFolderPath) throws FileNotFoundException{
		
		if((wmrLinesDataReader == null) && (stepFreeAccessStationDataReader == null)) {
		wmrLinesDataReader = new Scanner(new File(dataFolderPath + "/WMRlines.csv")); 
		stepFreeAccessStationDataReader = new Scanner(new File(dataFolderPath + "/WMRstationsWithStepFreeAccess.csv"));
        new WmrStepFreeAccessStationModel().setListStepFreeAccessData(getAllStepFreeAccessStations());
		//new TrainNetworkModel(getAllWmrTrainNetworkDataFromCsvFileAsAMap());
			new TrainNetworkModel(forMyGraphdata());
		}
	}




	protected ArrayList<Node> forMyGraphdata()
	{
		ArrayList<Node>netWorkDataMap = new ArrayList<Node>();
		if(wmrLinesDataReader.hasNext()) {
			wmrLinesDataReader.nextLine();
		}
		while(wmrLinesDataReader.hasNextLine())
		{
			String[] trainNetworkData = wmrLinesDataReader.nextLine().split(",");
			String trainLine = trainNetworkData[0]; String fromToStationName = trainNetworkData[1];
			String toFromStationName = trainNetworkData[2]; int travelTime = Integer.parseInt(trainNetworkData[3]);
			Node eachCsvDataLine = new Node(trainLine, fromToStationName,toFromStationName,travelTime);
			netWorkDataMap.add(eachCsvDataLine);

		}
		return netWorkDataMap;
	}









	/*
	 * Retrieves a mapped sequence of data from csv
	 * return a map of a stationObject as key and list of Objects as value.
	 * See how graph building will work.
	 */
	protected Map<WmrTrainLineModel, List<WmrTrainLineModel>> getAllTrainLineNetworkDataAsMapWithObjectsAsKey()
	{
		List<WmrTrainLineModel> entireTrainLineData;
		Map<WmrTrainLineModel, List<WmrTrainLineModel>> netWorkDataMap = new LinkedHashMap<>();
		if(wmrLinesDataReader.hasNext()) {
			wmrLinesDataReader.nextLine();
		}
		while(wmrLinesDataReader.hasNextLine())
		{
			String[] trainNetworkData = wmrLinesDataReader.nextLine().split(",");
			String trainLine = trainNetworkData[0]; String fromToStationName = trainNetworkData[1];
			String toFromStationName = trainNetworkData[2]; int travelTime = Integer.parseInt(trainNetworkData[3]);
			WmrTrainLineModel eachCsvDataLine = new WmrTrainLineModel(trainLine, fromToStationName,toFromStationName,travelTime);
			if(netWorkDataMap.containsKey(eachCsvDataLine)){
				netWorkDataMap.get(eachCsvDataLine).add(eachCsvDataLine);
			}
			else {
				entireTrainLineData = new ArrayList<>();
				entireTrainLineData.add(eachCsvDataLine);
				netWorkDataMap.put(eachCsvDataLine, entireTrainLineData);
			}
		}
		return netWorkDataMap;
	}



	/*
	 * Retrieves a mapped sequence of data from csv
	 * return a map of String key and model class object as value.
	 */
	protected Map<String, ArrayList<WmrTrainLineModel>> getAllWmrTrainNetworkDataFromCsvFileAsAMap()
    {
		ArrayList<WmrTrainLineModel> entireTrainLineData;
		Map<String, ArrayList<WmrTrainLineModel>> netWorkDataMap = new LinkedHashMap<>();
    	if(wmrLinesDataReader.hasNext()) {
    		wmrLinesDataReader.nextLine();
    	}
    	while(wmrLinesDataReader.hasNextLine()) 
    	{
    		String[] trainNetworkData = wmrLinesDataReader.nextLine().split(",");
    		String trainLine = trainNetworkData[0]; String fromToStationName = trainNetworkData[1]; 
    		String toFromStationName = trainNetworkData[2]; int travelTime = Integer.parseInt(trainNetworkData[3]);
			WmrTrainLineModel eachCsvDataLine = new WmrTrainLineModel(trainLine, fromToStationName,toFromStationName,travelTime);
    		if(netWorkDataMap.containsKey(trainLine)) {
    			netWorkDataMap.get(trainLine).add(eachCsvDataLine);
    		}
    		else {
    			entireTrainLineData = new ArrayList<>();
    			entireTrainLineData.add(eachCsvDataLine);
    			netWorkDataMap.put(trainLine, entireTrainLineData);
    		}
    	}
    	return netWorkDataMap;
    }

	
	/*
     * Process step free access data and pass to model when called in constructor.
     */
	protected ArrayList<String> getAllStepFreeAccessStations() {
    	ArrayList<String> allStepFreeAccessStations = new ArrayList<String>();
    	if(stepFreeAccessStationDataReader.hasNext()) 
    	{
    		stepFreeAccessStationDataReader.nextLine();
    	}
    	while(stepFreeAccessStationDataReader.hasNextLine()) {
    	String stepFreeStations = stepFreeAccessStationDataReader.nextLine().trim();
    	allStepFreeAccessStations.add(stepFreeStations);
    	}
    	return allStepFreeAccessStations;
    }   
	    

    //map the user supplied alphabet to the actual train line name.
    public static String returnMappedTrainLineToSuppliedAlphabet(String lineId) 
    {
    	boolean doesLineIdExist = false;
		switch (lineId.toLowerCase()) 
		{
		case "a" : 
			lineId = "Birmingham – Dorridge – Leamington Spa";
			doesLineIdExist = true;
			 break;
		case "b" : 
            lineId = "Cross City Line";
			doesLineIdExist = true;
            break;							   
		case "c" :
			lineId = "Birmingham – Rugby – Northampton – London";
			doesLineIdExist = true;
			break;
		case "d" :
			lineId = "Nuneaton – Coventry";
			doesLineIdExist = true;
			break;
		case "e" :
			lineId = "Watford – St Albans Abbey";
			doesLineIdExist = true;
			break;
		case "f" :
			lineId = "Bletchley – Bedford";
			doesLineIdExist = true;
			break;
		case "g" :
			lineId = "Crewe – Stoke – Stafford – London";
			doesLineIdExist = true;
			break;
		case "h" :
			lineId = "Worcester – Birmingham";
			doesLineIdExist = true;
			break;
		case "i" :
			lineId = "Smethwick Galton Bridge Connections";
			doesLineIdExist = true;
			break;
		case "j" :
			lineId = "Birmingham – Stratford-upon-Avon";
			doesLineIdExist = true;
			break;
		case "k" :
			lineId = "Birmingham – Wolverhampton – Telford – Shrewsbury";
			doesLineIdExist = true;
			break;
		case "l" :
			lineId = "Birmingham – Worcester – Hereford";
			doesLineIdExist = true;
			break;
		case "m" :
			lineId = "Birmingham – Walsall – Rugeley";
			doesLineIdExist = true;
			break;
		default : // Not a known command option.
			while(doesLineIdExist != true)
			{
				//ensure programme doesnt crash and user can continue to retry until right command is entered.
				display(unrecogniseCommandErrorMsg(lineId));
				String[] args = {};
				Main.main(args);
			}
		}		
		return lineId;
    } 

	    
	private static void display(String info) {
		System.out.println(info);
	}

    
	/*
     * Returns an error message for an unrecognised command.
     * @param error the unrecognised command
     * @return an error message
     */
    private static String unrecogniseCommandErrorMsg(String error) {
            return String.format("Cannot recognise the given command: '%s', " +
					"please try with a different command between [a - m].%n", error);
    }
		
}


