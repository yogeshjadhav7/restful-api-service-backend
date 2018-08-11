package web.db.dao.led7;

import java.util.List;

import com.avaje.ebean.Ebean;

import web.constants.app.AppConstants;
import web.constants.intellecto.FriendsDaoConstants;
import web.db.models.intellecto.Intellecto_Friends;
import web.db.models.led7.Led7_Data;
import web.response.IntellectoResponse;
import web.response.Led7Response;

public class Led7Dao {
	
	public static void addToDatabase(List<Led7_Data> dataList) {
		Ebean.beginTransaction();
		try {
			System.out.println("Deleting all records...");
			Ebean.deleteAll(Led7_Data.find.all());
			System.out.println("Deleted all records...");
			System.out.println("Saving all records...");
			Ebean.saveAll(dataList);
			System.out.println("Saved all records...");
			Ebean.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Ebean.endTransaction();
		}
	}
	
	
	public static Led7Response predict(final String segmentState, final String type) {
		Led7Response response = new Led7Response(AppConstants.API_LED7);
		boolean status = false;
		boolean error = true;
		
		try {
			Led7_Data data = Led7_Data.find.where()
					.eq("segment_state", segmentState)
					.eq("type", type)
					.findUnique();
			
			if(data == null) {
				response.setErrorMessage("Invalid Segment State :(");
			} else {
				response.setResponse(data);
				status = true;
				error = false;
				data.incrementRequestCount();
				saveLed7Data(data);
			}   
		} catch(Exception e) {
		    response.setErrorMessage(e.getMessage());
		}
		
		response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
		response.setError(error);
		response.setStatus(status);
		response.setQuery(segmentState);
		return response;
	}
	
	
	public static void saveLed7Data(Led7_Data data) {
		Ebean.beginTransaction();
		try {
			data.save();
			Ebean.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Ebean.endTransaction();
		}
	}
	

}
