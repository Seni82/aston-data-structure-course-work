package model;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TrainNetworkModel {
	
	/*
	 * Map field - key as string value as arrayList of strings.
	 */
	private Map<String, ArrayList<String>> trainLinesAndStationMap;
	
	
	/*
	 * Array list of train line and station object
	 */
	private ArrayList<WmrTrainLineModel> trainLineData;
	
	
	/*
	 * Map field - key as a string and value as arrayList of trainLine and station object.
	 */
	private static Map<String, ArrayList<WmrTrainLineModel>> trainLinesAndStationObjectMap;
	
	
	
	/*
	 * Constructor receiving an object of the train line data.
	 * private Map<String, ArrayList<String>> trainLinesAndStationMap;
	 */
	public TrainNetworkModel(ArrayList<WmrTrainLineModel> trainLineData) 
	{
		this.trainLineData = trainLineData;
		 trainLinesAndStationMap = new LinkedHashMap<>();	
		 ArrayList<String> listForMap;
		 for(WmrTrainLineModel eachTrainLineData : trainLineData)
		 {
			 //StringBuilder allTrainLineTravelData = new StringBuilder();
			 String trainLineName = eachTrainLineData.getTrainLineName();
			 String fromToStation = eachTrainLineData.getfromToStationName();
			 String toFromStation = eachTrainLineData.getToFromStationName();
			 int travelTime = eachTrainLineData.getTravelTime();
			 String value = trainLineName + ": " + fromToStation + "=> " + toFromStation + "=> " + travelTime + "\n.";
			 //allTrainLineTravelData.append(trainLineName).append(":").append(fromToStation).append(" => ").append(toFromStation).append(" => ").append(travelTime).append("\n");
			 if(trainLinesAndStationMap.containsKey(trainLineName)) {
			 
				 value = fromToStation + "=> " + toFromStation + "=> " + travelTime + "\n.";
				 trainLinesAndStationMap.get(trainLineName).add(value);
			 }
			 else {
				 listForMap = new ArrayList<String>();
				 listForMap.add(value);
				 trainLinesAndStationMap.put(trainLineName, listForMap);
			 }	
		 }
	}
	
		
	
	
	/*ThIS IS THE CONSTRUCTOR I AM REFERENCING WITHIN MY QUERIES!!!
	 * Overloaded constructor to allow object initialisation with map passed in.
	 * This map contains the data modelled and already inputed into the map.
	 * This initialise the field map below.
	 * Map<String, ArrayList<WmrTrainLineModel>> trainLinesAndStationObjectMap
	 * This is has a unique of trainLine name and list of stations in the map.
	 */
	public TrainNetworkModel(Map<String, ArrayList<WmrTrainLineModel>> trainLinesAndStationObjectsMap) {
		trainLinesAndStationObjectMap = trainLinesAndStationObjectsMap;
	}


	
	
	/*
	 * Getter for exposing the data model object as a Map
	 * private Map<String, ArrayList<WmrTrainLineModel>> trainLinesAndStationObjectMap;
	 * map containing the train lines as keys and List<(objects(stations + weights))> as values
	 */
	public static Map<String, ArrayList<WmrTrainLineModel>> getTrainLineDataAsAMap()
	{
		return trainLinesAndStationObjectMap;
	}
		 

	
	
	
	/*
	 * Getter for the data model object as an arrayList of data-model objects
	 * private ArrayList<WmrTrainLineModel> trainLineData;
	 */
	public List<WmrTrainLineModel> getTrainLineDataObject(){
		return this.trainLineData;
	}

		
}