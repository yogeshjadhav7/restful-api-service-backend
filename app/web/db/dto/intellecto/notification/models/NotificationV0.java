package web.db.dto.intellecto.notification.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationV0 {

	private String title;
	private String body;
	
	public NotificationV0() {}
	
	public NotificationV0(final String title, final String body) {
		this.title = title;
		this.body = body;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
	
}
