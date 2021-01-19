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


    public static String getAllTerminiOfASpecifiedLine(String trainLine)
    {
        long startTime = (new Date()).getTime();
        Graphs listOfterminiGraph = new Graphs();
        List<String> listOfTermini = new ArrayList<>();
        String trainLineRequested = TrainNetworkUtilityClass.returnMappedTrainLineToSuppliedAlphabet(trainLine);
        Map<String, List<TrainNetworkNode>> builtGraphMap = listOfterminiGraph.buildGraphForSpecifiedTrainLine(trainLineRequested);
        for(Map.Entry<String, List<TrainNetworkNode>> stationName : builtGraphMap.entrySet()){
            if(stationName.getValue().size() <= 1)
            {
                listOfTermini.add(stationName.getKey());
            }
        }
        TrainNetworkUtilityClass.display(String.format("\n**Termini query on '%s' line executes in (%s milliseconds)**.\n",
                trainLineRequested.toUpperCase(), TrainNetworkUtilityClass.getElapsedTime(startTime)));
        println.info(String.format("Terminus on '%s' line : \n %s", trainLineRequested.toUpperCase(), listOfTermini.toString()));
        return listOfTermini.toString();
    }


}