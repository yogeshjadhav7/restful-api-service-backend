package web.db.dto.intellecto.notification.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import web.db.dao.intellecto.UsersDao;
import web.db.dao.intellecto.UsersNotificationsDao;
import web.db.dto.intellecto.notification.models.FirebaseNotificationV0;
import web.db.dto.intellecto.notification.models.NotificationV0;

public class FirebaseNotificationUtils {
	
	public static List<FirebaseNotificationV0> buildNotifyBefriendNotification(final long userId, final long friendId) {
		
		List<FirebaseNotificationV0> list = new ArrayList<>();
		
		try {
			final String userToken = UsersNotificationsDao.getToken(userId);
			final String friendToken = UsersNotificationsDao.getToken(friendId);
			final String userName = UsersDao.getUserName(userId);
			final String friendName = UsersDao.getUserName(friendId);
			
			if(userToken != null && friendName != null) {
				
				String title = "New Friend Added!";
		        String body = "You are now friends with " + friendName + ". Let the robot wars begin!!";
				
		        final NotificationV0 notification = new NotificationV0(title, body);
		        
		        final Map<String, String> data = new HashMap<>();
		        data.put("action", "/notify/befriend/" + friendName + "/");
		        data.put("intentCode", "1");
		        data.put("title", title);
		        data.put("body", body);
				
		        list.add(new FirebaseNotificationV0(userToken, notification, data));
			}
			
			if(friendToken != null && userName != null) {
				
				String title = "New Friend Added!";
		        String body = "You are now friends with " + userName + ". Let the robot wars begin!!";
				
		        final NotificationV0 notification = new NotificationV0(title, body);
		        
		        final Map<String, String> data = new HashMap<>();
		        data.put("action", "/notify/befriend/" + userName + "/");
		        data.put("intentCode", "1");
		        data.put("title", title);
		        data.put("body", body);
				
		        list.add(new FirebaseNotificationV0(friendToken, notification, data));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}
	
	
}
