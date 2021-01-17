package trainNetworkGraphTraversal;
import model.StepFreeAccessStations;
import model.TrainNetworkNode;
import trainNetworkGraphBuilder.Graphs;
import trainNetworkHelper.TrainNetworkUtilityClass;
import java.util.*;
import java.util.logging.Logger;


/*
 * @author Ganiyu Isola
 * @version 2021 - 01 - 14
 */

public class ListOfTerminiOnATrainLine {

    static Logger println = Logger.getLogger(String.valueOf(ListOfTerminiOnATrainLine.class));

    //Query to get list of termini from a specified line
    public static String getAllTerminiOfASpecifiedLine(String trainLine)
    {
        long startTime = (new Date()).getTime();
        Graphs listOfterminiGraph = new Graphs();
        List<String> listOfTermini = new ArrayList<>();
        String trainLineRequested = TrainNetworkUtilityClass.returnMappedTrainLineToSuppliedAlphabet(trainLine);
        Map<String, List<TrainNetworkNode>> builtGraphMap = listOfterminiGraph.buildGraphForSpecifiedTrainLine(trainLine);
        for(Map.Entry<String, List<TrainNetworkNode>> stationName : builtGraphMap.entrySet()){
            if(stationName.getValue().size() <= 1)
            {
                listOfTermini.add(stationName.getKey().toString());
            }
        }
        TrainNetworkUtilityClass.display(String.format("\n**Termini query on '%s' line executes in (%s milliseconds)**.\n",
                trainLineRequested.toUpperCase(), TrainNetworkUtilityClass.getElapsedTime(startTime)));
        TrainNetworkUtilityClass.display(String.format("List of termini on '%s' below: ",trainLineRequested.toUpperCase()));
        println.info(String.format("List of terminis on '%s' line : \n %s", trainLineRequested.toUpperCase(), listOfTermini.toString()));
        return listOfTermini.toString();
    }






}