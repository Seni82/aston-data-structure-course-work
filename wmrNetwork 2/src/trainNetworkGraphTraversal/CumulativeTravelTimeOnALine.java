package trainNetworkGraphTraversal;

import model.TrainNetworkNode;
import trainNetworkGraphBuilder.Graphs;
import trainNetworkHelper.TrainNetworkUtilityClass;

import java.util.*;

public class CumulativeTravelTimeOnALine {


    //Query for returning list of stations and cumulative travel time on a line.
    public static String listAllStationsAndCumulativeTravelTime(String trainLine) {
        Graphs graph = new Graphs();
        String trainLineRequested = TrainNetworkUtilityClass.returnMappedTrainLineToSuppliedAlphabet(trainLine);
        return graph.performDFSOnGraphToGetCumulativeTravelTime(trainLineRequested);
    }

}



