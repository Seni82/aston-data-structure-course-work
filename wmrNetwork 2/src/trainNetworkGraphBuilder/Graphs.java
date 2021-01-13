package trainNetworkGraphBuilder;
import model.TrainNetworkModelledData;
import model.TrainNetworkNode;
import trainNetworkHelper.TrainNetworkUtilityClass;
import java.util.*;


public class Graphs {

    /*
       private map field with string as key and list of networkNode as objects.
     */
    private Map<String, List<TrainNetworkNode>> adjacencyListGraphMap;

    /*
      Map initialisation with constructor!
     */
    public Graphs()
    {
        adjacencyListGraphMap = new LinkedHashMap<>();
    }


    /*
     Add nodes to graph.
     */
    public void addEdge(String trainLine, String fromToStation, String toFromStation, int travelTime)
    {
        if(!adjacencyListGraphMap.containsKey(fromToStation))
        {
            adjacencyListGraphMap.put(fromToStation, new LinkedList<>());
        }
        adjacencyListGraphMap.get(fromToStation).add(new TrainNetworkNode(trainLine, fromToStation, toFromStation, travelTime));
    }



    /*
      Add bi-directional node relationship to the graph.
     */
    public void addUndirectededge(String trainLine, String fromToStation, String toFromStation, int travelTime) {
        addEdge(trainLine, fromToStation, toFromStation, travelTime);
        addEdge(trainLine, toFromStation, fromToStation, travelTime);
    }


    /*
       This should create a graph representation of all train line.
     */
    public Map<String, List<TrainNetworkNode>> buildListOfTerminiGraph(String trainLineName) {

        List<TrainNetworkNode> dataForGraph = TrainNetworkModelledData.getListOfNodeObjects();
        String lineName = TrainNetworkUtilityClass.returnMappedTrainLineToSuppliedAlphabet(trainLineName);
        for (TrainNetworkNode trainNetwork : dataForGraph) {
            if (trainNetwork.getTrainLine().equalsIgnoreCase(lineName)) {
                addUndirectededge(trainNetwork.getTrainLine(),
                        trainNetwork.getFromToStation(),
                        trainNetwork.getToFromStation(),
                        trainNetwork.getTravelTime());
            }
        }
        TrainNetworkUtilityClass.printGraph(adjacencyListGraphMap);
        return adjacencyListGraphMap;
    }

    private static void display(String info)
    {
        System.out.println(info);
    }





        /*
    public void printGraph(Map<String, List<TrainNetworkNode>> graph){

        for(String key : graph.keySet()){
            List<TrainNetworkNode> value = graph.get(key);
            for(TrainNetworkNode eachValue : value){
                System.out.println(String.format("Vertex of '%s' : \n  %s  <->  %s - %s (min)",eachValue.getFromToStation(),
                        eachValue.getFromToStation(),
                        eachValue.getToFromStation(),
                        eachValue.getTravelTime()));
            }
        }
    }
     */
}


