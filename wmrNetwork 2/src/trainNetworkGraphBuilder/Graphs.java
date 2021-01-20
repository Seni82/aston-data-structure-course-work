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
       true if you want to use method as string comparison or false if object
     */
    public boolean isEdgeExistBetween(String fromToStation, String toFromStation) {
        ArrayList<String> listOfEdges = new ArrayList<>();
        boolean isEdgePresent = false;
        if(adjacencyListGraphMap != null && adjacencyListGraphMap.containsKey(fromToStation))
        {
            List<TrainNetworkNode> listOfAdjacentNode = adjacencyListGraphMap.get(fromToStation);
            for(TrainNetworkNode eachConnectedNode : listOfAdjacentNode){
                listOfEdges.add(eachConnectedNode.getToFromStation());
            }
            isEdgePresent = listOfEdges.contains(toFromStation);
        }
        return isEdgePresent;
    }


    /*
       This should create a graph representation of any specified train line.
     */
    public Map<String, List<TrainNetworkNode>> buildGraphForSpecifiedTrainLine(String trainLineName) {
        Map<String, ArrayList<TrainNetworkNode>> mapData = TrainNetworkModelledData.getListOfNodesAsAMap();
        TrainNetworkModelledData.getListOfNodeObjects();
        ArrayList<TrainNetworkNode> dataForGraph = mapData.get(trainLineName);
        for (TrainNetworkNode trainNetwork : dataForGraph) {
            addUndirectedEdge(trainNetwork.getTrainLine(),
                    trainNetwork.getFromToStation(),
                    trainNetwork.getToFromStation(),
                    trainNetwork.getTravelTime());
        }
        //TrainNetworkUtilityClass.printGraph(adjacencyListGraphMap);
        return adjacencyListGraphMap;
    }



    //Depth first search traversal for cumulative travel time.
    public String performDFSOnGraphToGetCumulativeTravelTime(String trainLine) {
        long startTime = (new Date()).getTime();
        Stack<String> dfs_stack = new Stack<>();
        ArrayList<String> visitedStation = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        int cumulativeTime = 0;
        int i = 1; int x = 0;
        Map<String, List<TrainNetworkNode>> builtGraphMap = buildGraphForSpecifiedTrainLine(trainLine);
        for (Map.Entry<String, List<TrainNetworkNode>> graphEntry : builtGraphMap.entrySet()) {
                dfs_stack.add(graphEntry.getKey());
                if (i == 1) {
                    visitedStation.add(graphEntry.getKey());
                }
                while (!dfs_stack.isEmpty()) {
                    dfs_stack.pop();
                    List<TrainNetworkNode> allAdjacentVertex = graphEntry.getValue();
                    for (TrainNetworkNode eachAdjacentVertex : allAdjacentVertex) {
                        if (!visitedStation.contains(eachAdjacentVertex.getToFromStation())) {
                            cumulativeTime += eachAdjacentVertex.getTravelTime();
                            dfs_stack.add(eachAdjacentVertex.getToFromStation());
                            visitedStation.add(eachAdjacentVertex.getToFromStation());
                            builder.append(String.format("%s <%s> ", graphEntry.getKey(), cumulativeTime));
                            x++;
                        }
                    }
                }
                if (i == builtGraphMap.size()) {
                    builder.append(String.format("%s", graphEntry.getKey()));
                }
                i++;
            }
             TrainNetworkUtilityClass.display(String.format("\n**Cumulative query on '%s' line executes in (%s milliseconds)**.\n",
                     trainLine.toUpperCase(), TrainNetworkUtilityClass.getElapsedTime(startTime)));
            System.out.println(String.format("%s (%s mins) :", trainLine, cumulativeTime));
            return builder.toString();
        }





        public String pathsBetweenTwoStations(String sourceStation, String destinationStation){

        //check if edge exists, if yes then keep the to in the list of visited


           return null;
        }


        public static String pathsBetweenStations(String sourceStation, String destinationStation){
            ArrayList<String> paths = new ArrayList<>();
            ArrayList<TrainNetworkNode> lisOfStations = TrainNetworkModelledData.getListOfNodeObjects();
            for(TrainNetworkNode eachStation : lisOfStations){
                paths.add(eachStation.getFromToStation());
            }
              if(sourceStation.equals(destinationStation)){
                  System.out.println(paths);
              }

            return null;
        }


        public void dfs(String source, String destination, ArrayList<String> path){
            path.add(source);
            if(source.equalsIgnoreCase(destination)){
                System.out.println(path);
                return;
            }
            ArrayList<TrainNetworkNode> lisOfStations = TrainNetworkModelledData.getListOfNodeObjects();
            for(TrainNetworkNode eachStation : lisOfStations){

            }
        }






    //this is for a different question.
    public String buildGraphForTotalTravelTimeForAllLines() {
        ArrayList<String> allLinesAndTravelTime = new ArrayList<String>();
        Map<String, ArrayList<TrainNetworkNode>> mapData = TrainNetworkModelledData.getListOfNodesAsAMap();
        for (String key : mapData.keySet()) {
            allLinesAndTravelTime = new ArrayList<String>();
            String trainLineName = "";
            adjacencyListGraphMap.clear();
            ArrayList<TrainNetworkNode> dataForGraph = mapData.get(key);
            for (TrainNetworkNode trainNetwork : dataForGraph) {
                addUndirectedEdge(trainNetwork.getTrainLine(),
                        trainNetwork.getFromToStation(),
                        trainNetwork.getToFromStation(),
                        trainNetwork.getTravelTime());
                trainLineName = trainNetwork.getTrainLine();
            }
            allLinesAndTravelTime.add(String.format("%s:",trainLineName));
        }
        return allLinesAndTravelTime.toString();
    }



    private static void display(String info)
    {
        System.out.println(info);
    }



}















//Entire network map
//List<Map<String, List<TrainNetworkNode>>> arrayListOfMapsOfEachLine = new ArrayList<Map<String, List<TrainNetworkNode>>>();