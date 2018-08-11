package web.db.dto.intellecto;

import java.util.HashMap;
import java.util.List;

import utils.general.JsonObjectMapper;
import web.db.models.intellecto.Intellecto_Friends;
import web.db.models.intellecto.Intellecto_Users;

public class Friends {
	
	public HashMap<String, Intellecto_Friends> friendship;
	
	public static Friends toFriends(String friendshipString) {
		return (Friends) JsonObjectMapper.toObject(friendshipString, Friends.class);
	}
	
	@Override
	public String toString() {
		return JsonObjectMapper.toJsonString(this, true);
	}

}
