package model;

import java.util.ArrayList;

public class StepFreeAccessStations {

    /*
     * This holds the list of step free access train staions.
     */
    @SuppressWarnings("unused")
    private ArrayList<String> listOfStepFreeAccess;


    /*
     * list of data gets set into the private variable..
     */
    public void setListStepFreeAccessData(ArrayList<String> listOfStepFreeAccess)
    {
        this.listOfStepFreeAccess = listOfStepFreeAccess;
    }


    /*
      Template method to check if a station is step free.
     */
    public boolean isStationAStrepFreeAccessStation()
    {
        return false;
    }



}
