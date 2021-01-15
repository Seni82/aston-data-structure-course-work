package trainNetworkGraphTraversal;
import model.TrainNetworkNode;
import trainNetworkGraphBuilder.Graphs;
import trainNetworkHelper.TrainNetworkUtilityClass;
import java.util.*;


/*
 * @author Ganiyu Isola
 * @version 2021 - 01 - 14
 */

public class ListOfTerminiOnATrainLine {


    //Query to get list of termini from a specified line
    public static String getAllTerminiOfASpecifiedLine(String trainLine)
    {
        long startTime = (new Date()).getTime();
        Graphs listOfterminiGraph = new Graphs();
        List<String> listOfTermini = new ArrayList<>();
        String trainLineRequested = TrainNetworkUtilityClass.returnMappedTrainLineToSuppliedAlphabet(trainLine);
        TrainNetworkNode trainLineName = new TrainNetworkNode(trainLineRequested,"","", 0);
        Map<TrainNetworkNode, List<TrainNetworkNode>> builtGraphMap = listOfterminiGraph.buildGraphForSpecifiedTrainLine(trainLineName);
        for(Map.Entry<TrainNetworkNode, List<TrainNetworkNode>> stationName : builtGraphMap.entrySet()){
            if(stationName.getValue().size() <= 1)
            {
                listOfTermini.add(stationName.getKey().toString());
            }
        }
        TrainNetworkUtilityClass.display(String.format("\n**Termini query on '%s' line executes in (%s milliseconds)**.\n",
                trainLineRequested.toUpperCase(), TrainNetworkUtilityClass.getElapsedTime(startTime)));
        TrainNetworkUtilityClass.display(String.format("List of termini on '%s' below: ",trainLineRequested.toUpperCase()));
        return listOfTermini.toString();
    }






}