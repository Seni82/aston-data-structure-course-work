package trainNetworkGraphTraversal;

import model.TrainNetworkNode;
import trainNetworkGraphBuilder.Graphs;
import trainNetworkHelper.TrainNetworkUtilityClass;

import java.util.*;

public class CumulativeTravelTimeOnALine {



    //Query for returning list of stations and cumulative travel time on a line.

    public static String listAllStationsAndCumulativeTravelTime(String trainLine){
/*
        long startTime = (new Date()).getTime();
        Graphs stationsAndTravelTimeGraph = new Graphs();
        List<String> listOfTerminiAndTravelTime = new ArrayList<>();
        //Map<String, List<TrainNetworkNode>> builtGraphMap = stationsAndTravelTimeGraph.buildGraphForSpecifiedTrainLine(trainLine);
        int i =0; String startStation = ""; int cumulativeTime=0;
        for(Map.Entry<String, List<TrainNetworkNode>> stationName : builtGraphMap.entrySet()){
            if(i == 0){i++; startStation = stationName.getKey();}
            String[] convertedData = TrainNetworkUtilityClass.convertDataToNeededFormat(stationName.getValue().toString(),"\\s*->\\s*");
            int travelTime = Integer.parseInt(convertedData[2]);
            cumulativeTime += travelTime;
            if(stationName.getValue().size() <= 1){
                listOfTerminiAndTravelTime.add(String.format("%s <....> %s : (%s mins).", startStation,stationName.getKey().toString(), cumulativeTime));
                cumulativeTime -= travelTime;
            }
            //String toFromStation = convertedData[1];

            //access each objects and get details needed.
            //get the first station.
            //open the object and get the time
            // get the value and check if its a termini

*/
        //TrainNetworkUtilityClass.display(String.format("\n**Cumulative travel time query on '%s' line executes in (%s milliseconds).\n",
                //trainLineRequested.toUpperCase(), TrainNetworkUtilityClass.getElapsedTime(startTime)));
        return null;

    }






        /*
        for(Map.Entry<String, List<TrainNetworkNode>> stationName : builtGraphMap.entrySet()) {
        if(counter == 0){ counter++; startStation = stationName.getKey(); }
        for(List<String> stationKey  : stationName) stationName


        for(String fromToStationKey : builtGraphMap.keySet()){
            List<TrainNetworkNode> listOfConnectingNode = builtGraphMap.get(fromToStationKey);
            for(TrainNetworkNode path : listOfConnectingNode){
                String connectNode = path.getToFromStation();
                int travelTime = path.getTravelTime();
                int cumulateTime=+travelTime;
                if(stationsAndTravelTimeGraph.isEdgeExistBetween(fromToStationKey,path.getToFromStation())){
                    System.out.print(String.format("%s <%s> %s",fromToStationKey,cumulateTime,connectNode));
                }//look at the else when connection is not possible.
            }
        }
        /*
        for(Map.Entry<String, List<TrainNetworkNode>> stationName : builtGraphMap.entrySet()){
            String[] convertedData = TrainNetworkUtilityClass.convertDataToNeededFormat(stationName.getValue().toString(),"\\s*->\\s*");
            String toFromStation = convertedData[1];
            int initialTime = con
            if(stationsAndTravelTimeGraph.isEdgeExistBetween(stationName.getKey(),toFromStation)){
                //cumulate time

                //format string with string.format

            }
         */

            //System.out.println("Edge present ="+ isEdgePresent + stationName.getValue().toString() + "Key :"+stationName.getKey());
           // get the key and get the values.
            //check they contain eachother
            //if yes - get the first time between both and construct
            //check between next iteration if yes take the time and cumulate it.
            //place the time in front with the destination after. (if not coment that out as one path).

}


