package model;
import com.ibm.icu.text.UFormat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class TrainNetworkModel {


	private static ArrayList<Node> myNodeData;
	/*
	 * Map field - key as a string and value as arrayList of trainLine and station object.
	 */
	private static Map<String, ArrayList<WmrTrainLineModel>> trainLinesAndStationObjectMap;


	private ArrayList<WmrTrainLineModel> trainLineData;


	private static Map<String, ArrayList<String>> trainLinesAndStationMap;


	/*
	 * This map contains the data modelled and already inputted into the map.
	 * This initialise the field map below.
	 * Map<String, ArrayList<WmrTrainLineModel>> trainLinesAndStationObjectMap
	 * This is has a unique of trainLine name and list of stations in the map.
	 */
	public TrainNetworkModel(Map<String, ArrayList<WmrTrainLineModel>> trainLinesAndStationObjectsMap) {
		trainLinesAndStationObjectMap = trainLinesAndStationObjectsMap;
	}


	/*
	 * Constructor receiving an object of the train line data.
	 * private Map<String, ArrayList<String>> trainLinesAndStationMap;
	 */
	public void TrainNetworkModel(ArrayList<WmrTrainLineModel> trainLineData)
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




	public TrainNetworkModel(ArrayList<Node> myNodeData){
		this.myNodeData = myNodeData;
	}

	/*
	 * Getter for exposing the data model object as a Map
	 * private Map<String, ArrayList<WmrTrainLineModel>> trainLinesAndStationObjectMap;
	 * map containing the train lines as keys and List<(objects(stations + weights))> as values
	 */
	public static Map<String, ArrayList<WmrTrainLineModel>> getTrainLineDataAsAMapOfObjectList()
	{
		return trainLinesAndStationObjectMap;
	}


	public static Map<String, ArrayList<String>> getTrainLineDataAsAMapOfStringList(){
		return trainLinesAndStationMap;
	}



	public static ArrayList<Node> getNodeDataObjectForTest()
	{
		return myNodeData;
	}
}