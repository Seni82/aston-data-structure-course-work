package model;
import java.util.ArrayList;



public class WmrStepFreeAccessStationModel {


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
	
	

	public boolean isStationAStrepFreeAccessStation()
	{
		return false;
	}
	
	
	
	
	
	
	
	
	
	/*
	 * Implementation here
	 */
	/*possibly return index if possible
	public <T> T returnNearestStepFreeStationToMe(T nearestStepFreeAccessToMyStation) 
	{
		return T;
	}
	*/
	
}
