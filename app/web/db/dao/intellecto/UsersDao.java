package web.db.dao.intellecto;

import play.mvc.*;
import utils.general.MD5;
import web.constants.app.AppConstants;
import web.constants.intellecto.UsersDaoConstants;
import web.db.models.intellecto.Intellecto_Users;
import web.db.models.intellecto.Intellecto_Users;
import web.response.IntellectoResponse;
import web.response.Response;

import com.avaje.ebean.*;

public class UsersDao {

	public static String getUserName(final long userId) {
		if(!Boolean.parseBoolean(doesUserExist(userId).getResponse())) return null;
		Intellecto_Users user = Intellecto_Users.find.byId(userId);
		return user.getUserName();
	}
	
	public static IntellectoResponse getIdOfUser(String userName) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		try {
			Intellecto_Users user = Intellecto_Users.find.where()
					.eq("username", userName)
					.eq("verified", true)
					.findUnique();
			
			if(user == null) {
				response.setResponse(UsersDaoConstants.NO_USER_FOUND_USERNAME);
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
			} else {
				response.setResponse(Long.toString(user.getId()));
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_LONG);
				status = true;
			}
		
		} catch(Exception e) {
			response.setResponse(UsersDaoConstants.NO_USER_FOUND_USERNAME_INTERNAL_EEROR);
			response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
			error = true;
			response.setErrorMessage(e.getMessage());
		}
		
		response.setStatus(status);
		response.setError(error);
		return response;
	}
	
	
	
	public static IntellectoResponse doesUserExist(long userId) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		Intellecto_Users user = Intellecto_Users.find.byId(userId);
		try {
			if(user != null && user.isVerified())
				response.setResponse(Boolean.toString(true));
			else
				response.setResponse(Boolean.toString(false));
		    
			response.setResponseType(AppConstants.API_RESPONSE_TYPE_BOOLEAN);
		    status = true;
		} catch(Exception e) {
		    response.setResponse(UsersDaoConstants.USER_EXISTENCE_CHECK_FAILED_INTERNAL_EEROR);
		    response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
		    error = true;
		    response.setErrorMessage(e.getMessage());
		}
		
		response.setStatus(status);
		response.setError(error);
		return response;
	}

	
}
