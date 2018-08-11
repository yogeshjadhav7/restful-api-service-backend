package web.controllers.intellecto;

import java.util.HashMap;
import java.util.Map;

import play.data.Form;
import play.mvc.*;
import utils.general.JsonObjectMapper;
import web.db.dao.intellecto.UsersNotificationsDao;
import web.db.dto.intellecto.notification.models.FirebaseNotificationV0;
import web.db.dto.intellecto.notification.models.NotificationV0;
import web.db.dto.intellecto.notification.services.FirebaseNotificationService;
import web.response.IntellectoResponse;


public class NotificationController extends Controller {
	
	public Result saveFirebaseNotificationToken() {
		Map<String, String> formData = Form.form().bindFromRequest().data();
		final String userId = formData.get("userId");
		final String token = formData.get("token");
		IntellectoResponse response = UsersNotificationsDao.saveToken(userId == null ? -1 : Long.parseLong(userId), token);
		return ok(JsonObjectMapper.toJsonString(response, true));
	}
	
	public Result testNotificationV0() {
		
		long userId = 2;
		final String token = UsersNotificationsDao.getToken(userId);
        final String title = "TITLE HERE";
        final String body = "BODY HERE...";
        
        Map<String, String> data = new HashMap<>();
        data.put("action", "/notify/befriend/Jojo/");
        data.put("intentCode", "1");
        data.put("title", title);
        data.put("body", body);

        final NotificationV0 notification = new NotificationV0(title, body);
        final String responseStr = FirebaseNotificationService.sendNotification(new FirebaseNotificationV0(token, notification, data));
		return ok(responseStr);
	}

}
