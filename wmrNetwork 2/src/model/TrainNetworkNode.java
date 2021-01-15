package model;

/*
   @author Ganiyu Isola.
 */

public class TrainNetworkNode {

    private String trainLine;
    private String fromToStation;
    private String toFromStation;
    private int travelTime;
    public boolean visited;


    /*
       Constructor for initialising private fields during object creation.
     */
    public TrainNetworkNode(String trainLine, String fromToStation, String toFromStation, int travelTime)
    {
        this.trainLine = trainLine;
        this.fromToStation = fromToStation;
        this.toFromStation = toFromStation;
        this.travelTime = travelTime;
        visited = false;
    }


    /*
     * fields below been exposed with the getter methods below.
     * @trainLine, @fromToStation, @toFromStation, @travelTime
     */
    public String getTrainLine()
    {
        return trainLine;
    }


    public String getFromToStation()
    {
        return fromToStation;
    }


    public String getToFromStation()
    {
        return toFromStation;
    }


    public int getTravelTime()
    {
        return travelTime;
    }


    public void unvisited(){
        visited = false;
    }

    public void visit(){
        visited = true;
    }

    /*
       Overrides @equals() , @hashcode() and @toString() method to help with object comparison
       avoid/mitigate collision.
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
        {
            return true;
        }
        if(!(obj instanceof  TrainNetworkNode))
        {
            return false;
        }
        TrainNetworkNode trainLine = (TrainNetworkNode) obj;
        return trainLine.getTrainLine().equalsIgnoreCase(this.trainLine);
    }

    @Override
    public int hashCode()
    {
        int result = 60;
        result = 41 * result + trainLine.hashCode();
        return result;
    }


    @Override
    public String toString()
    {
        return //getFromToStation(), getFromToStation(), getTravelTime());

                //String.format("%s:\n", getTrainLine()) +
                //String.format("%s  -> %s  - %s \n",
                String.format("%s -> %s - %s",
                        getFromToStation(),
                        getToFromStation(),
                        getTravelTime());

    }
}
