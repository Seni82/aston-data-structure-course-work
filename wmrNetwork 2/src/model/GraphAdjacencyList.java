package model;

import com.google.common.graph.Graph;
import com.intellij.icons.AllIcons;
import org.jetbrains.jsonProtocol.JsonParseMethod;

import java.util.*;


public class GraphAdjacencyList {

    private Map<String, List<Node>> graphMap;
    private GraphAdjacencyList generatedGraph;


    public GraphAdjacencyList() {
        graphMap = new LinkedHashMap<>();
    }


    public boolean addEdge(String line, String fromStat, String toStation, int time) {
        if (!graphMap.containsKey(fromStat)) {
            graphMap.put(fromStat, new LinkedList<>());
        }
        graphMap.get(fromStat).add(new Node(line, fromStat, toStation, time));

        return true;
    }

    public void addUndirectededge(String line, String fromStat, String toStation, int time) {
        addEdge(line, fromStat, toStation, time);
        addEdge(line, toStation, fromStat, time);
    }


    public Map<String, List<Node>> buildListOfTerminiGraph(String trainLineName) {
        //GraphAdjacencyList listOfTermini = new GraphAdjacencyList(trainLineName);
        ArrayList<Node> dataForGraph = TrainNetworkModel.getNodeDataObjectForTest();
        String lineName = WmrNetworkUtilityClass.returnMappedTrainLineToSuppliedAlphabet(trainLineName);
        int count = 0;
        for (Node data : dataForGraph) {
            if (data.getTrainLineName().equalsIgnoreCase(lineName)) {
                count++;
                addUndirectededge(data.getTrainLineName(), data.getFromTo(), data.getToFrom(), data.getTime());
            }
        }
        printGraph();
        return graphMap;
    }


    public void printGraph(){
          System.out.println(graphMap);
    }
}


