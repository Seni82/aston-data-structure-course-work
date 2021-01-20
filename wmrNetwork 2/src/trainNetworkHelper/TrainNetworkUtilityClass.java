package trainNetworkHelper;
import model.StepFreeAccessStations;
import model.TrainNetworkModelledData;
import model.TrainNetworkNode;
import view.Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class TrainNetworkUtilityClass {

    /*
     Scanner object for reading files.
     */
    private Scanner mainNetworkDataReader = null;
    private Scanner stepFreeDataReader = null;


    public TrainNetworkUtilityClass(String dataFolderPath) throws FileNotFoundException {

        //ensure singleton file load each app start.
        //Initialise scanner object with file location.
        // calls 2 methods for file reading and return objects as require (ArrayList / Map).
        if((mainNetworkDataReader == null) && (stepFreeDataReader == null))
        {
            mainNetworkDataReader = new Scanner(new File(dataFolderPath + "/WMRlines.csv"));
            stepFreeDataReader = new Scanner(new File(dataFolderPath + "/WMRstationsWithStepFreeAccess.csv"));
            new StepFreeAccessStations().setListStepFreeAccessData(getAllStepFreeAccessStations());
            //new TrainNetworkModelledData(readAndModelTrainNetworkDataForGraphCreation());
            new TrainNetworkModelledData(readsAndModelTrainNetworkDataAsMapForGraphCreation());
            //new TrainNetworkModelledData(readAndModelTrainNetworkDataForGraphCreation());
        }
    }

    /*
      Flexibility to have the data as a map as well if need.
     */
    protected Map<String, ArrayList<TrainNetworkNode>> readsAndModelTrainNetworkDataAsMapForGraphCreation(){
        ArrayList<TrainNetworkNode> entireTrainLineData;
        Map<String, ArrayList<TrainNetworkNode>> netWorkDataMap = new LinkedHashMap<>();
        if(mainNetworkDataReader.hasNext()) {
            mainNetworkDataReader.nextLine();
        }
        while(mainNetworkDataReader.hasNextLine())
        {
            String[] trainNetworkData = mainNetworkDataReader.nextLine().split(",");
            String trainLine = trainNetworkData[0]; String fromToStationName = trainNetworkData[1];
            String toFromStationName = trainNetworkData[2]; int travelTime = Integer.parseInt(trainNetworkData[3]);
            TrainNetworkNode eachCsvDataLine = new TrainNetworkNode(trainLine, fromToStationName,toFromStationName,travelTime);
            if(netWorkDataMap.containsKey(trainLine))
            {
                netWorkDataMap.get(trainLine).add(eachCsvDataLine);
            }
            else{
                entireTrainLineData = new ArrayList<>();
                entireTrainLineData.add(eachCsvDataLine);
                netWorkDataMap.put(trainLine, entireTrainLineData);
            }
        }
        return netWorkDataMap;
    }


    /*
       Helper function to help print graph created for visibility.
       It shows relationships and connections between nodes and there weight.
     */
    public static void printGraph(Map<String, List<TrainNetworkNode>> graph) {
        int x = 0; String trainLine = null;
        while(x == 0){
            for(String trainLineKey : graph.keySet()){
               List<TrainNetworkNode> node = graph.get(trainLineKey);
               trainLine = node.get(x).getTrainLine();
            }
            x++;
            System.out.println(trainLine + " line, has the following relationship : ");
        }
        for (Map.Entry<String, List<TrainNetworkNode>> entry : graph.entrySet()) {
            List<String> allAdjacentVertices = new ArrayList<>();
            List<TrainNetworkNode> allConnectingStations = entry.getValue();
            for(TrainNetworkNode connectStation : allConnectingStations) {
                allAdjacentVertices.add(connectStation.getToFromStation());
            }
            System.out.print("\n" + entry.getKey() + " has an adjacent vertex of : \n "+allAdjacentVertices.toString() +"\n");
        }
        System.out.println();
    }



    /*
     Read and return the data as an array list of Node objects.
    */
    public ArrayList<TrainNetworkNode>readAndModelTrainNetworkDataForGraphCreation()
    {
        ArrayList<TrainNetworkNode>netWorkDataMap = new ArrayList<TrainNetworkNode>();
        if(mainNetworkDataReader.hasNext()) {
            mainNetworkDataReader.nextLine();
        }
        while(mainNetworkDataReader.hasNextLine())
        {
            String[] trainNetworkData = mainNetworkDataReader.nextLine().split(",");
            String trainLine = trainNetworkData[0]; String fromToStationName = trainNetworkData[1];
            String toFromStationName = trainNetworkData[2]; int travelTime = Integer.parseInt(trainNetworkData[3]);
            TrainNetworkNode eachCsvDataLine = new TrainNetworkNode(trainLine, fromToStationName,toFromStationName,travelTime);
            netWorkDataMap.add(eachCsvDataLine);

        }
        return netWorkDataMap;
    }


    /*
        map the user supplied alphabet to the actual train line name.
      */
    public static String returnMappedTrainLineToSuppliedAlphabet(String lineId)
    {
        boolean doesLineIdExist = false;
        switch (lineId.toLowerCase())
        {
            case "a" :
                lineId = "Birmingham – Dorridge – Leamington Spa";
                doesLineIdExist = true;
                break;
            case "b" :
                lineId = "Cross City Line";
                doesLineIdExist = true;
                break;
            case "c" :
                lineId = "Birmingham – Rugby – Northampton – London";
                doesLineIdExist = true;
                break;
            case "d" :
                lineId = "Nuneaton – Coventry";
                doesLineIdExist = true;
                break;
            case "e" :
                lineId = "Watford – St Albans Abbey";
                doesLineIdExist = true;
                break;
            case "f" :
                lineId = "Bletchley – Bedford";
                doesLineIdExist = true;
                break;
            case "g" :
                lineId = "Crewe – Stoke – Stafford – London";
                doesLineIdExist = true;
                break;
            case "h" :
                lineId = "Worcester – Birmingham";
                doesLineIdExist = true;
                break;
            case "i" :
                lineId = "Smethwick Galton Bridge Connections";
                doesLineIdExist = true;
                break;
            case "j" :
                lineId = "Birmingham – Stratford-upon-Avon";
                doesLineIdExist = true;
                break;
            case "k" :
                lineId = "Birmingham – Wolverhampton – Telford – Shrewsbury";
                doesLineIdExist = true;
                break;
            case "l" :
                lineId = "Birmingham – Worcester – Hereford";
                doesLineIdExist = true;
                break;
            case "m" :
                lineId = "Birmingham – Walsall – Rugeley";
                doesLineIdExist = true;
                break;
            default : // Not a known command option.
                while(doesLineIdExist != true)
                {
                    //ensure programme doesn't crash and user can continue to retry until right command is entered.
                    display(unrecogniseCommandErrorMsg(lineId));
                    String[] args = {};
                    Main.main(args);
                }
        }
        return lineId;
    }


    //This returns the time difference in miliseconds.
    public static long getElapsedTime(long startTime){
        long endTime = (new Date()).getTime();
        long elapsedTime = endTime - startTime;
        return elapsedTime;
    }


    public static String[] convertDataToNeededFormat(String dataToConvert, String splitBy){
        String[] splittedData = dataToConvert.trim().split(splitBy);
        return splittedData;
    }


    /*
     * Process step free access data and pass to model when called in constructor.
     */
    private ArrayList<String> getAllStepFreeAccessStations() {
        ArrayList<String> allStepFreeAccessStations = new ArrayList<String>();
        if(stepFreeDataReader.hasNext())
        {
            stepFreeDataReader.nextLine();
        }
        while(stepFreeDataReader.hasNextLine()) {
            String stepFreeStations = stepFreeDataReader.nextLine().trim();
            allStepFreeAccessStations.add(stepFreeStations);
        }
        return allStepFreeAccessStations;
    }



    /*
     * Returns an error message for an unrecognised command.
     * @param error the unrecognised command
     * @return an error message
     */
    private static String unrecogniseCommandErrorMsg(String error) {
        return String.format("Cannot recognise the given command: '%s', " +
                "please try with a different command between [a - m].%n", error);
    }


    public static void display(String info)
    {
        System.out.println(info);
    }
}
