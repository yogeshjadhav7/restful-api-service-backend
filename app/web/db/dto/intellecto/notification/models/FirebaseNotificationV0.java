package web.db.dto.intellecto.notification.models;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FirebaseNotificationV0 {
	
	private String to;
	private String priority = "high";
	private NotificationV0 notification;
	private Map<String, String> data;
	
	public FirebaseNotificationV0() {}
	
	public FirebaseNotificationV0(final String to, final NotificationV0 notification, final Map<String, String> data) {
		this.to = to;
		this.notification = notification;
		this.data = data;
	}
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public NotificationV0 getNotification() {
		return notification;
	}
	public void setNotification(NotificationV0 notification) {
		this.notification = notification;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	
}
