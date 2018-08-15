package web.controllers.intellecto;

import java.util.Map;

import play.data.Form;
import play.mvc.*;
import utils.general.JsonObjectMapper;
import web.db.dao.intellecto.UsersNotificationsDao;
import web.response.IntellectoResponse;


public class NotificationController extends IntellectoController {
	
	public Result saveFirebaseNotificationToken() {
		Map<String, String> formData = Form.form().bindFromRequest().data();

		final IntellectoResponse authorizationResponse = authorizeRequest(formData);
		if(authorizationResponse != null) return ok(JsonObjectMapper.toJsonString(authorizationResponse, true));
		
		final String userId = formData.get("userId");
		final String token = formData.get("token");
		IntellectoResponse response = UsersNotificationsDao.saveToken(userId == null ? -1 : Long.parseLong(userId), token);
		return ok(JsonObjectMapper.toJsonString(response, true));
	}
	
}
