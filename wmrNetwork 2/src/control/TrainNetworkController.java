package control;
import trainNetworkGraphBuilder.Graphs;
import trainNetworkHelper.TrainNetworkUtilityClass;

public class TrainNetworkController implements Controller{


    /*
     * @author Ganiyu Isola
     * @version 2020 - 12 - 31
     */

    private Graphs graphObject;

    /*
      Initialise controller with utility class object for access
      application initialisation when main method is executed.
      Utility class will hold the path and file reading responsibility.
     */
    public TrainNetworkController(TrainNetworkUtilityClass utility)
    {
        graphObject = new Graphs();
    }

    @Override
    public String listTermini(String line)
    {
        graphObject.buildListOfTerminiGraph(line);
        return null;
    }

    @Override
    public String listStationsInLine(String line) {
        return null;
    }

    @Override
    public String listAllLines() {
        return null;
    }

    @Override
    public String showAccessiblePath(String fromStation, String toStation) {
        return null;
    }

    @Override
    public String showAllPaths(String fromStation, String toStation) {
        return null;
    }

    @Override
    public String showShortestPath(String fromStation, String toStation) {
        return null;
    }
}
