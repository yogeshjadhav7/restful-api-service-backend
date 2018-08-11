package web.db.models.intellecto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.avaje.ebean.*;

@Entity
@Table(name = "intellecto_users_notifications")
public class Intellecto_Users_Notifications extends Model{
	
	@Id
	@Column(name="`user_id`")
	private long userId;
	
	@Column(name="`token`")
	private String token;
	
	@Column(name="`is_notification_on`")
	private int isNotificationOn = 1;


	
	
	

	public long getUserId() {
		return userId;
	}





	public void setUserId(long userId) {
		this.userId = userId;
	}





	public String getToken() {
		return token;
	}





	public void setToken(String token) {
		this.token = token;
	}





	public int getIsNotificationOn() {
		return isNotificationOn;
	}





	public void setIsNotificationOn(int isNotificationOn) {
		this.isNotificationOn = isNotificationOn;
	}





	public static Finder<Long, Intellecto_Users_Notifications> getFind() {
		return find;
	}





	public static void setFind(Finder<Long, Intellecto_Users_Notifications> find) {
		Intellecto_Users_Notifications.find = find;
	}





	public static Finder<Long, Intellecto_Users_Notifications> find = new Finder<Long, Intellecto_Users_Notifications>(Intellecto_Users_Notifications.class);
    
}