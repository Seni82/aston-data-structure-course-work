package model;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

/*
  @author Ganiyu Isola
  12-01-2020.
 */

public class TrainNetworkModelledData {

    static Logger println = Logger.getLogger(String.valueOf(TrainNetworkModelledData.class));

            /********** 2 PRIVATE FIELDS BELOW *********/
    /*
      TrainNode instance used to hold the train network data modelled,
      this will be exposed for other classes later.
     */
    private static ArrayList<TrainNetworkNode> listOfNodeObjects;

    /*
      TrainNode instance used to hold the train network data modelled as a map
      key of string and value of arrayList<trainNetworkNode>.
      This will be exposed later for use.
     */
    private static Map<String, ArrayList<TrainNetworkNode>> listOfNodesAsAMap;





                     /*****CONSTRUCTOR ARRAYLIST*******/
    /*              This is used currently at initialisation.
       constructor for initialising objects received as an arrayList of @listOfNodeObjects
     */
    public TrainNetworkModelledData(ArrayList<TrainNetworkNode> listOfNodeObjects)
    {
        this.listOfNodeObjects = listOfNodeObjects;
    }



                      /*****CONSTRUCTOR MAP*******/
    /*
      Initialising the map of node list.
     */
    public TrainNetworkModelledData(Map<String, ArrayList<TrainNetworkNode>> listOfNodesAsAsMap)
    {
        this.listOfNodesAsAMap = listOfNodesAsAsMap;
    }



            /************EXPOSE / GET NODE OBJECT - AS ARRAYLIST (GETTER METHOD)************/
    /*
       Getter method to expose field @listOfNodeObjects
       I am getting this at the moment for graph generation.
     */
    public static ArrayList<TrainNetworkNode> getListOfNodeObjects()
    {
        return listOfNodeObjects;
    }




                /********EXPOSE / GET NODE OBJECT - AS MAP(GETTER METHOD)************/
    /*
       Getter method to expose field @listOfNodeAsMap.
     */
    public static Map<String, ArrayList<TrainNetworkNode>> getListOfNodesAsAMap()
    {
        return listOfNodesAsAMap;
    }
}
