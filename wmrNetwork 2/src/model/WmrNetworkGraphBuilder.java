package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WmrNetworkGraphBuilder {


    private Map<String, ArrayList<WmrTrainLineModel>> trainLinesAndStationObjectMapDemo;


    private Map<WmrTrainLineModel, ArrayList<WmrTrainLineModel>> mapForObjectRepresentation;



    public WmrNetworkGraphBuilder(Map<String, ArrayList<WmrTrainLineModel>> trainLinesAndStationObjectMapDemo)
    {
        this.trainLinesAndStationObjectMapDemo = trainLinesAndStationObjectMapDemo;
    }


    public Map<String, ArrayList<WmrTrainLineModel>> getTrainLineMapWithObjectAsKey(){
        return trainLinesAndStationObjectMapDemo;
    }


    //Build graph structure for each query using adjacency list
}
