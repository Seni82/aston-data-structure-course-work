package model;


//this is like my vertex class.
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



	//required as the object is stored as key in the map.
	// The trainLine name is used to map stations together hence needs to be unique-
	//but still hold an array of stations mapped to it. - this will ensure the equality check is done
	// on the inner value of the object.trainLine when checking of map contains.
	@Override
	public boolean equals(Object obj)
	{
		if(obj == this)
			return true;
		if(!(obj instanceof WmrTrainLineModel)){
			return false;
		}
		WmrTrainLineModel line = (WmrTrainLineModel) obj;
		return line.getTrainLineName().equalsIgnoreCase(this.trainLine);
	}

	//avoid collusion
	@Override
	public int hashCode()
	{
		int result = 27;
		result = 31 * result + trainLine.hashCode();
		return result;
	}


	//override the toString method here.
}
