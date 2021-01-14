package trainNetworkGraphTraversal;
import model.TrainNetworkNode;
import trainNetworkGraphBuilder.Graphs;
import trainNetworkHelper.TrainNetworkUtilityClass;

import java.util.*;


public class ListOfTerminiOnATrainLine {


    //Query to get list of termini from a specified line
    public static String getAllTerminOfASpecifiedLine(String trainLine)
    {
        long startTime = (new Date()).getTime();
        Graphs listOfterminiGraph = new Graphs();
        List<String> listOfTermini = new LinkedList<>();
        String trainLineRequested = TrainNetworkUtilityClass.returnMappedTrainLineToSuppliedAlphabet(trainLine);
        Map<String, List<TrainNetworkNode>> builtGraphMap = listOfterminiGraph.buildGraphForSpecifiedTrainLine(trainLine);
        for(Map.Entry<String, List<TrainNetworkNode>> stationName : builtGraphMap.entrySet()){
            if(stationName.getValue().size() <= 1)
            {
                listOfTermini.add(stationName.getKey().toString());
            }
        }
        long endTime = (new Date()).getTime();
        long elapsedTime = endTime - startTime;
        TrainNetworkUtilityClass.display(String.format("\n**Termini query on '%s' line executes in '(%s milliseconds.)'\n",
                trainLineRequested, elapsedTime));
        TrainNetworkUtilityClass.display(String.format("List of termini on '%s' below: ",trainLineRequested));
        return listOfTermini.toString();
    }






}