package web.controllers.intellecto;

import java.util.Map;

import play.mvc.Controller;
import utils.general.StringUtils;
import web.constants.app.AppConstants;
import web.db.models.intellecto.Intellecto_Users;
import web.response.IntellectoResponse;

public class IntellectoController extends Controller {
	
	
	public IntellectoResponse authorizeRequest(final Map<String, String> formData) {
		
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		response.setResponse("You are not authorized to use this API. Please email me at yogse7en@gmail.com if you have any questions.");
		
		try {
			
			final long userId = Long.parseLong(formData.get("userId"));
			final String authKey = formData.get("authKey");
			
			Intellecto_Users user = Intellecto_Users.find.byId(userId);
			if(user == null) {
				response.setError(error);
				response.setStatus(status);
				response.setResponse("Invalid userId. " + response.getResponse());
				return response;
			}
			
			if(authKey == null || authKey.isEmpty()) {
				response.setError(error);
				response.setStatus(status);
				response.setResponse("No auth key provided. " + response.getResponse());
				return response;
			}
			
			final String userName = user.getUserName();
			final String apiKey = play.Play.application().configuration().getString("intellecto.api.key");
			final String computedAuthKey = StringUtils.getAuthKey(userId, userName, apiKey);
			if(!authKey.equals(computedAuthKey)) {
				response.setError(error);
				response.setStatus(status);
				response.setResponse("Invalid auth key provided. " + response.getResponse());
				return response;
			}
		
			return null;
			
		} catch(Exception e) { 
			error = true;
			response.setError(error);
			response.setStatus(status);
			return response;
		}
	
	}
	

}
