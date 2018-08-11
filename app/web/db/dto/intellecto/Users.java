package web.db.dto.intellecto;

import java.util.List;

import utils.general.JsonObjectMapper;
import web.db.models.intellecto.Intellecto_Friends;
import web.db.models.intellecto.Intellecto_Users;

public class Users {
	
	public List<Intellecto_Users> users;
	
	public static Users toUsers(String usersString) {
		return (Users) JsonObjectMapper.toObject(usersString, Users.class);
	}
	
	@Override
	public String toString() {
		return JsonObjectMapper.toJsonString(this, true);
	}

}
