package model;

public class WmrTrainLineModel {
	
	private String trainLine;
	private String fromToStationName;
	private String toFromStationName;
	private int travelTime;
	
	
	public WmrTrainLineModel(String trainLine, String fromToStationName, String toFromStationName, int travelTime) {
		
		this.trainLine = trainLine;
		this.fromToStationName = fromToStationName;
		this.toFromStationName = toFromStationName;
		this.travelTime = travelTime;
	}
	
	
	/*
	 * Overloaded costructor that doesnt take the train line name.
	 * This allows the object created to be saved in a map as just objects with fields of vertex and weight.
	 */
   public WmrTrainLineModel(String fromToStationName, String toFromStationName, int travelTime) {
		
		this.fromToStationName = fromToStationName;
		this.toFromStationName = toFromStationName;
		this.travelTime = travelTime;
	}
	
	
   /*
    * Add a check to ensure when the constructor without trainLine is used to initialise the field
    * attempts are not made to try and call the getTrainLineName(); as the field would be null it will 
    * throw an error - to avaoid that the if statement check is added.
    */
	public String getTrainLineName() {
		return trainLine;
	}

	
	public String getfromToStationName() {
		return fromToStationName;
	}
	
	public String getToFromStationName() {
		return toFromStationName;
	}

	
	public int getTravelTime() {
		return travelTime;
	}
	
}
