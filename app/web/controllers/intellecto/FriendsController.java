package web.controllers.intellecto;

import java.util.Map;

import play.data.Form;
import play.mvc.*;
import utils.general.JsonObjectMapper;
import web.db.dao.intellecto.FriendsDao;
import web.db.dao.intellecto.UsersDao;
import web.response.IntellectoResponse;


public class FriendsController extends IntellectoController {
	

	public Result getFriendsOf() {
		Map<String, String> formData = Form.form().bindFromRequest().data();
		
		final IntellectoResponse authorizationResponse = authorizeRequest(formData);
		if(authorizationResponse != null) return ok(JsonObjectMapper.toJsonString(authorizationResponse, true));
		
		final long userId = Long.parseLong(formData.get("userId"));
		IntellectoResponse response = FriendsDao.getFriendsOfUser(userId);
		return ok(JsonObjectMapper.toJsonString(response, true));
	}
	
	public Result updateFriendship() {
		Map<String, String> formData = Form.form().bindFromRequest().data();
		
		final IntellectoResponse authorizationResponse = authorizeRequest(formData);
		if(authorizationResponse != null) return ok(JsonObjectMapper.toJsonString(authorizationResponse, true));
		
		final String friendshipString = formData.get("friends");
		final long userId = Long.parseLong(formData.get("userId"));
		IntellectoResponse response = FriendsDao.updateFriendship(userId, friendshipString);
		return ok(JsonObjectMapper.toJsonString(response, true));
	}
	
	public Result addFriend() {
		Map<String, String> formData = Form.form().bindFromRequest().data();
		
		final IntellectoResponse authorizationResponse = authorizeRequest(formData);
		if(authorizationResponse != null) return ok(JsonObjectMapper.toJsonString(authorizationResponse, true));
		
		final String friendName = formData.get("friendName");
		final long userId = Long.parseLong(formData.get("userId"));
		IntellectoResponse friendExistResponse = UsersDao.getIdOfUser(friendName);
		if(friendExistResponse.isError() || !friendExistResponse.isStatus()) {
			return ok(JsonObjectMapper.toJsonString(friendExistResponse, true));
		} 
		
		final long friendId = Long.parseLong(friendExistResponse.getResponse());
		IntellectoResponse response = FriendsDao.addFriend(userId, friendId);
		return ok(JsonObjectMapper.toJsonString(response, true));
	}
	
	


}
