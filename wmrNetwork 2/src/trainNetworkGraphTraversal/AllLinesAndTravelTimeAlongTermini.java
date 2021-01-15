package trainNetworkGraphTraversal;

import model.TrainNetworkNode;
import trainNetworkGraphBuilder.Graphs;
import trainNetworkHelper.TrainNetworkUtilityClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AllLinesAndTravelTimeAlongTermini {



    public static String AllLinesAndTotalTravelTimeBetweenStations()
    {
        long startTime = (new Date()).getTime();
        Graphs lineAndTravelTime = new Graphs();
        List<String> listOfTermini = new ArrayList<>();
         String allLinesAndTravelTimeData = lineAndTravelTime.buildGraphForTotalTravelTimeForAllLines();
        return allLinesAndTravelTimeData;
    }
}

