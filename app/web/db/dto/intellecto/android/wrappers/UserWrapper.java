package web.db.dto.intellecto.android.wrappers;


import utils.general.JsonObjectMapper;
import web.db.dto.intellecto.android.models.User;
import web.db.models.intellecto.Intellecto_Users_Notifications;

public class UserWrapper {

    private User user;
    private Intellecto_Users_Notifications usersNotifications;
    
    
    public UserWrapper() {}

    public UserWrapper(final User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Intellecto_Users_Notifications getUsersNotifications() {
		return usersNotifications;
	}

	public void setUsersNotifications(Intellecto_Users_Notifications usersNotifications) {
		this.usersNotifications = usersNotifications;
	}

	@Override
    public String toString() {
        return JsonObjectMapper.toJsonString(this, true);
    }
}
