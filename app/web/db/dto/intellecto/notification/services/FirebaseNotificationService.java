package web.db.dto.intellecto.notification.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import utils.general.JsonObjectMapper;
import web.db.dto.intellecto.notification.models.FirebaseNotificationV0;
import web.db.dto.intellecto.notification.utils.FirebaseNotificationUtils;

public class FirebaseNotificationService {
	
	public static final String FIREBASE_NOTIFICATION_V0_ENDPOINT_URL = play.Play.application().configuration().getString("firebase.notification.v0.endpoint.url"); 
	public static final String FIREBASE_NOTIFICATION_V0_AUTHORIZATION_KEY = play.Play.application().configuration().getString("firebase.notification.v0.authorization.key"); 
	
	public static void sendBefriendNotification(final long userId, final long friendId) {
		final List<FirebaseNotificationV0> firebaseNotifications = FirebaseNotificationUtils.buildNotifyBefriendNotification(userId, friendId);
		for(FirebaseNotificationV0 firebaseNotification : firebaseNotifications) {
			System.out.println(sendNotification(firebaseNotification));
		}
	}
	
	public static String sendNotification(final FirebaseNotificationV0 firebaseNotification) {
		
        HttpURLConnection client = null;
        StringBuilder response = new StringBuilder();
        String responseStr = "";
       
        try {
            URL url = new URL(FIREBASE_NOTIFICATION_V0_ENDPOINT_URL);
            client = (HttpURLConnection) url.openConnection();
            client.setDoOutput(true);
            client.setDoInput(true);
            client.setRequestProperty("Content-Type", "application/json");
            client.setRequestProperty ("Authorization", "key=" + FIREBASE_NOTIFICATION_V0_AUTHORIZATION_KEY);
            client.setRequestMethod("POST");
            client.connect();
            
            final String jsonRequest = JsonObjectMapper.toJsonString(firebaseNotification, true);
            
            OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
            writer.write(jsonRequest);
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                response.append(line.trim());
                line = reader.readLine();
            }

            responseStr = response.toString();
            responseStr = responseStr.substring(1, responseStr.length() - 1);
            responseStr = responseStr.replace("\\\"", "\"");
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
        		client.disconnect();
		}
		
        return responseStr;
	}

}
