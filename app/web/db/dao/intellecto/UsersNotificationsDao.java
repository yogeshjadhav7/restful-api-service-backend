package web.db.dao.intellecto;

import com.avaje.ebean.Ebean;

import web.constants.app.AppConstants;
import web.constants.intellecto.UsersDaoConstants;
import web.db.models.intellecto.Intellecto_Users;
import web.db.models.intellecto.Intellecto_Users_Notifications;
import web.response.IntellectoResponse;

public class UsersNotificationsDao {
	
	public static String getToken(final long userId) {
		Intellecto_Users user = Intellecto_Users.find.byId(userId);
		if(user == null) return null;
		Intellecto_Users_Notifications userNotifications = Intellecto_Users_Notifications.find.where().eq("user_id", userId).findUnique();
		if(userNotifications == null) return null;
		return userNotifications.getToken();
	}

	public static IntellectoResponse saveToken(final long userId, final String token) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = true;
		boolean error = false;
		response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
		response.setResponse(null);
		
		if(userId < 1) {
			response.setResponse(UsersDaoConstants.NO_USER_FOUND_USERID);
			response.setError(error);
			response.setStatus(status);
			return response;
		}
		
		if(token == null) {
		    response.setResponse("Invalid token provided");
			response.setError(error);
			response.setStatus(status);
			return response;
		}
		
		Ebean.beginTransaction();
		try {
			Intellecto_Users user = Intellecto_Users.find.byId(userId);
			if(user == null) {
			    response.setResponse(UsersDaoConstants.NO_USER_FOUND_USERID);
				response.setError(error);
				response.setStatus(status);
				return response;
			}
			
			Intellecto_Users_Notifications userNotifications = Intellecto_Users_Notifications.find.where().eq("user_id", userId).findUnique();
			if(userNotifications == null) {
				userNotifications = new Intellecto_Users_Notifications();
				userNotifications.setUserId(userId);
			}
		
			userNotifications.setToken(token);
			userNotifications.save();
			Ebean.commitTransaction();

		} catch(Exception e) {
		    response.setResponse("Token updating failed...");
		    error = true;
		    response.setErrorMessage(e.getMessage());
		    
		} finally {
			Ebean.endTransaction();
		}
		
		response.setError(error);
		response.setStatus(status);
		return response;
	}
	
}
