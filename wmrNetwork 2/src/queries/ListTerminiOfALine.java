package queries;
import model.WmrNetworkGraphBuilder;
import model.TrainNetworkModel;
import model.WmrNetworkUtilityClass;
import model.WmrTrainLineModel;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class ListTerminiOfALine {

    //private Map<String, ArrayList<WmrTrainLineModel>> trainLinesAndStationObjectMapDemo;


    private Map<String, ArrayList<WmrTrainLineModel>> trainNetworkDataMap;



    public String listAllTerminiOfASpecifiedLine(String trainLineId) {

        long startTime = (new Date()).getTime();
        String endTermini;
        int x = 0;
        List<String> listOfTermini = new ArrayList<>();
        String trainLine = WmrNetworkUtilityClass.returnMappedTrainLineToSuppliedAlphabet(trainLineId);
        trainNetworkDataMap = TrainNetworkModel.getTrainLineDataAsAMapOfObjectList();
        ArrayList<WmrTrainLineModel> trainLineObjects = trainNetworkDataMap.get(trainLine);

        /*
         * loop through the entire station a line to determine a termini.
         * value of i used to track the current toFromStation on a train line.
         */
        for (int i = 0; i < trainLineObjects.size(); i++) {

            /*
             * value of x used to track the next fromToStation on a train line.
             * set value of x by -1 to avoid IndexOutOfRangeException when used later in the loop.
             */
            x = x - 1;

            //take the first fromToStation on the line as this is always a termini.
            if (i == 0) {
                /*
                 * Add into termini list
                 */
                listOfTermini.add(trainLineObjects.get(i).getfromToStationName());
            }

            /*
             * Set x to value of i (both pointing to same current location of stations on a line).
             */
            x = i;

            /*
             *increase by 1 more that the value of i (This makes x pointing to next row of stations on a line).
             * while i still pointing to current row of stations on a line)
             */
            x = x + 1;

            /*
             * Do a check to ensure there is a next station to avoid IndexOutOfBound issue.
             */
            if (x != trainLineObjects.size()) {
                /*
                 * Check if the current toStationName (isNotEqual) to next fromToStationName.
                 * If they are equal then the current toStation is not a termini as it is the next fromToStation where the journey continues from.
                 * If they are not equal then code block is executed and current toFromStation is a termini.
                 * Station name is collected and its added into the listOfTermini.
                 */
                if (!trainLineObjects.get(i).getToFromStationName().equals(trainLineObjects.get(x).getfromToStationName())) {
                    endTermini = trainLineObjects.get(i).getToFromStationName();
                    listOfTermini.add(endTermini);
                }
            } else {
                    /*
                     When the entire row is exhausted then we come in here to toStationName which is also always a termini.
                     */
                listOfTermini.add(trainLineObjects.get(i).getToFromStationName());
            }
        }
        long endTime = (new Date()).getTime();
        long elapsedTime = endTime - startTime;
        display((String.format("\n************Termini query on '%s' line executes in '(%s milliseconds)'************.\n",trainLine, elapsedTime)));
        return String.format("\t\tTermini on %s line : \n\n \t\t\t'%s' \n\n *****************************" +
                "**********" +
                "*********************************",trainLine, listOfTermini.toString());
    }



    private static void display(String info) {
        System.out.println(info);
    }



    //First Implementation - either way both worked when called when query is executed.
    public String listStationsInLine(String lineId){
        long startTime = (new Date()).getTime();
        //mapping user alphabet input to an actual train line name and return it.
        String trainLine = WmrNetworkUtilityClass.returnMappedTrainLineToSuppliedAlphabet(lineId);

        //retrieve map object that contains list of model objects
        //trainLinesAndStationObjectMapDemo = WmrNetworkGraphBuilder.getTrainLineMapWithObjectAsKey();
        StringBuilder result = new StringBuilder();
        AtomicInteger cumulateTravelTime = new AtomicInteger(0);
        result.append(String.format("See below list of stations on '%s' line and travel time as requested :\n ", trainLine));
        trainNetworkDataMap = TrainNetworkModel.getTrainLineDataAsAMapOfObjectList();
        ArrayList<WmrTrainLineModel> listOfStations = trainNetworkDataMap.get(trainLine);
                listOfStations.forEach(station -> {
                    cumulateTravelTime.addAndGet(station.getTravelTime());
                    result.append("\n")
                            .append(station.getfromToStationName())
                            .append(" => ")
                            .append(station.getToFromStationName()).append(" - ")
                            .append(String.format("(%s mins).", cumulateTravelTime))
                            .append(" \n");
                });
        long endTime = (new Date()).getTime();
        long elapsedTime = endTime - startTime;
        result.append(String.format("\nThe Total travel time between stations on '%s' line is : (%s mins). \n", trainLine, cumulateTravelTime));
        result.append(String.format("\n************List of station query between '%s' line was executed in '%s milliseconds'.************ \n", trainLine, elapsedTime));
        return result.toString();
    }

}