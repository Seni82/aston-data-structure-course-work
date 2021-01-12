package queries;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import model.TrainNetworkModel;
import model.WmrNetworkGraphBuilder;
import model.WmrNetworkUtilityClass;
import model.WmrTrainLineModel;




   public class StationsInLineAndTotalTravelTime {




	   private Map<String, ArrayList<WmrTrainLineModel>> trainNetworkDataMap;


	   /**
		* @param lineId is taken from userInput and converted into a line name matching our system line name.
		* @return
		* Doing a lookup with passed in lineId by user from map.
		* return list of corresponding trainObjects (value) mapped to trainId (key)
		* parameter lineId as per TUI.
		* THIS IMPLEMENTATION WAS DONE WHEN INITIALLY BEFORE I DISCOVERED I MIGHT HAVE READ THE REQUIREMENT WRONGLY!!!!!
		* KEPT UNTIL SYLVIA CONFIRMS
		*/
	   public String listStationsInLine(String lineId)
	   {
		   long startTime = (new Date()).getTime();
		   //mapping user alphabet input to an actual train line name and return it.
		   String trainLine = WmrNetworkUtilityClass.returnMappedTrainLineToSuppliedAlphabet(lineId);

		   //retrieve map object that contains list of model objects
		   trainNetworkDataMap = TrainNetworkModel.getTrainLineDataAsAMapOfObjectList();
		   StringBuilder result = new StringBuilder();
		   AtomicInteger cumulateTravelTime = new AtomicInteger(0);
		   result.append(String.format("See below list of stations on '%s' line and travel time as requested :\n ",trainLine));

		   //collect all list of train line object value with train line name as specified by user input.
		   ArrayList<WmrTrainLineModel> trainLineObjects = trainNetworkDataMap.get(trainLine);

		   trainLineObjects.forEach(trainObject -> {
			   String fromToStation = trainObject.getfromToStationName();
			   String toFromStation = trainObject.getToFromStationName();
			   int travelTime = trainObject.getTravelTime();
			   cumulateTravelTime.addAndGet(travelTime);
			   result.append("\n").append(fromToStation).append(" => ").append(toFromStation).append(" - ").append(String.format("(%s mins).",cumulateTravelTime)).append(" \n");
		   });
		   long endTime = (new Date()).getTime();
		   long elapsedTime = endTime - startTime;
		   result.append(String.format("\nThe Total travel time between stations on '%s' line is : (%s mins). \n", trainLine, cumulateTravelTime));
		   result.append(String.format("\n************List of station query between '%s' line was executed in '%s milliseconds'.************ \n",trainLine, elapsedTime));
		   return result.toString();
	   }


	   /*
		*!!!!IMPLEMENTED BASED ON AVAILABLE ROUTE AND CUMULATE THE TRAVEL TIME ALONG THAT ROUTE!!!!!!!!!!
		*/
	   public String listAllStationsOnLineAndTotalTravelTime(String trainLineId) {

		   long startTime = (new Date()).getTime();
		   String endTermini;
		   String firstStation="";
		   int travelTime = 0;
		   List<String> listOfPossibleRoutesOnLine = new ArrayList<>();
		   int x = 0;

		   String trainLine = WmrNetworkUtilityClass.returnMappedTrainLineToSuppliedAlphabet(trainLineId);
		   trainNetworkDataMap = TrainNetworkModel.getTrainLineDataAsAMapOfObjectList();
		   ArrayList<WmrTrainLineModel> trainLineObjects = trainNetworkDataMap.get(trainLine);

		   /*
			* loop through the entire station a line to determine a termini.
			* value of i used to track the current toFromStation on a train line.
			*/
		   for (int i = 0; i < trainLineObjects.size(); i++) {
			   travelTime += trainLineObjects.get(i).getTravelTime();
			   /*
				* value of x used to track the next fromToStation on a train line.
				* set value of x by -1 to avoid IndexOutOfRangeException when used later in the loop.
				*/
			   x = x - 1;

			   //take the first fromToStation on the line as this is always a termini.
			   if (i == 0) {
				   firstStation = trainLineObjects.get(i).getfromToStationName();
				   /*
					* Add into termini list
					*/
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
					   listOfPossibleRoutesOnLine.add(String.format("%s <....> %s : (%s mins). \n\n  \t", firstStation, endTermini,travelTime));
					   travelTime -= trainLineObjects.get(i).getTravelTime();
				   }
			   } else {
                    /*
                     When the entire row is exhausted then we come in here to toStationName which is station at the end of a line(termini)
                     */
				   endTermini = trainLineObjects.get(i).getToFromStationName();
				   listOfPossibleRoutesOnLine.add(String.format("%s <...> %s : (%s mins).", firstStation, endTermini,travelTime));
			   }
		   }
		   long endTime = (new Date()).getTime();
		   long elapsedTime = endTime - startTime;
		   display((String.format("\n*********Cumulative time query on '%s' line executes in '(%s milliseconds)'********.\n", trainLine, elapsedTime)));
		   return String.format("Stations and cumulative travel time along stations on '%s' line : \n\n" +
						   "******************** '%s' ***************** \n\n \t %s\n%s",
				   trainLine, trainLine.toUpperCase(), listOfPossibleRoutesOnLine.toString(),"************************************************************************");
	   }



	   private static void display(String info) {
		   System.out.println(info);
	   }
   }

