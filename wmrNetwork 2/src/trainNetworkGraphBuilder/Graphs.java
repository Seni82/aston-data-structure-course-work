package trainNetworkGraphBuilder;
import model.TrainNetworkModelledData;
import model.TrainNetworkNode;
import trainNetworkHelper.TrainNetworkUtilityClass;

import java.io.InputStream;
import java.util.*;


public class Graphs {

    /*
       private map field with string as key and list of networkNode as objects.
     */
    private Map<String, List<TrainNetworkNode>> adjacencyListGraphMap;


    /*
      Map initialisation with constructor!
     */
    public Graphs() {
        adjacencyListGraphMap = new LinkedHashMap<>();
    }


    /*
     Add nodes to graph.
     */
    public void addEdge(String trainLine, String fromToStation, String toFromStation, int travelTime) {
        if (!adjacencyListGraphMap.containsKey(fromToStation)) {
            adjacencyListGraphMap.put(fromToStation, new LinkedList<>());
        }
        adjacencyListGraphMap.get(fromToStation).add(new TrainNetworkNode(trainLine, fromToStation, toFromStation, travelTime));
    }


    /*
      Add bi-directional node relationship to the graph.
     */
    public void addUndirectedEdge(String trainLine, String fromToStation, String toFromStation, int travelTime) {
        addEdge(trainLine, fromToStation, toFromStation, travelTime);
        addEdge(trainLine, toFromStation, fromToStation, travelTime);
    }



    /*
       Consider requesting map as parameter, otherwise make private.
       Split by -> and get the data at index 1 for comparison.
     */
    private boolean isEdgeExistBetween(String fromToStation, String toFromStation) {
        String[] splittedNode={}; String edge;
        ArrayList<String> listOfEdges = new ArrayList<>();
        boolean isEdgePresent = false;
        if(adjacencyListGraphMap.containsKey(fromToStation))
        {
            List<TrainNetworkNode> listOfAdjacentNode = adjacencyListGraphMap.get(fromToStation);
            for(TrainNetworkNode eachConnectedNode : listOfAdjacentNode){
                splittedNode = eachConnectedNode.toString().trim().split("\\s*->\\s*");
                edge = splittedNode[1];
                listOfEdges.add(edge);
            }
            isEdgePresent = listOfEdges.contains(toFromStation);
            System.out.println("Is edge present for "+ fromToStation+" and "+toFromStation + ": "+isEdgePresent);
            System.out.println(fromToStation+ " has connection with "+ listOfEdges.toString());
        }
        else{
            System.out.println(fromToStation +" does not exist as a from (key) station in the map" +
                    "therefore know edge present between "+fromToStation+" and "+ toFromStation);
        }
        return isEdgePresent;
    }



    /*
       This should create a graph representation of all train line.
     */
    public Map<String, List<TrainNetworkNode>> buildGraphForSpecifiedTrainLine(String trainLineName) {
        //Get data as map is more faster and efficient as suppose to arraylist previously used.
        Map<String, ArrayList<TrainNetworkNode>> mapData = TrainNetworkModelledData.getListOfNodesAsAMap();
        String lineName = TrainNetworkUtilityClass.returnMappedTrainLineToSuppliedAlphabet(trainLineName);
        ArrayList<TrainNetworkNode> dataForGraph = mapData.get(lineName);
        for (TrainNetworkNode trainNetwork : dataForGraph) {
            addUndirectedEdge(trainNetwork.getTrainLine(),
                    trainNetwork.getFromToStation(),
                    trainNetwork.getToFromStation(),
                    trainNetwork.getTravelTime());
            //isEdgeExistBetween(trainNetwork.getFromToStation(), trainNetwork.getToFromStation());
        }
        TrainNetworkUtilityClass.printGraph(adjacencyListGraphMap);
        return adjacencyListGraphMap;
    }



    private static void display(String info)
    {
        System.out.println(info);
    }


}


