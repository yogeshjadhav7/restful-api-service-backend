package web.db.dao.intellecto;

import utils.general.JsonObjectMapper;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import web.constants.app.AppConstants;
import web.constants.intellecto.FriendsDaoConstants;
import web.constants.intellecto.UsersDaoConstants;
import web.db.dto.intellecto.Friends;
import web.db.dto.intellecto.notification.services.FirebaseNotificationService;
import web.db.dto.intellecto.notification.utils.FirebaseNotificationUtils;
import web.db.models.intellecto.Intellecto_Friends;
import web.db.models.intellecto.Intellecto_Users;
import web.response.IntellectoResponse;
import com.avaje.ebean.*;

public class FriendsDao {

	public static IntellectoResponse addFriend(final long userId, final long friendId) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		Ebean.beginTransaction();
		try {
			if(!(UsersDao.doesUserExist(userId).getResponse().equals("true") 
					&& UsersDao.doesUserExist(friendId).getResponse().equals("true"))) {
				
				response.setResponse(UsersDaoConstants.NO_USER_FOUND_USERID);
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
				response.setStatus(status);
				response.setError(error);
				return response;
			}
				
			if(doesFriendshipExist(userId, friendId).getResponse().equals("false")) {
				Intellecto_Friends friendship = new Intellecto_Friends();
				friendship.setUserId(userId);
				friendship.setFriendId(friendId);
				friendship.save();
			}
			
			if(doesFriendshipExist(friendId, userId).getResponse().equals("false")) {
				Intellecto_Friends friendship = new Intellecto_Friends();
				friendship.setUserId(friendId);
				friendship.setFriendId(userId);
				friendship.save();
			}
			

			Ebean.commitTransaction();
		    status = true;
		    response.setResponse(Long.toString(friendId));
		    response.setResponseType(AppConstants.API_RESPONSE_TYPE_LONG);
		    	FirebaseNotificationService.sendBefriendNotification(userId, friendId);
		  
		} catch(Exception e) {
		    response.setResponse(FriendsDaoConstants.ADDING_FRIENDSHIP_FAILED_INTERNAL_EEROR);
		    response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
		    error = true;
		    response.setErrorMessage(e.getMessage());
		} finally {
		    Ebean.endTransaction();
		}
		
		response.setError(error);
		response.setStatus(status);
		return response;
	}
	

	public static IntellectoResponse doesFriendshipExist(final long userId, final long friendId) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		
		try {
			if(Intellecto_Friends.find.where()
					.eq("user_id", userId)
					.eq("friend_id", friendId)
					.findUnique() != null)
				response.setResponse(Boolean.toString(true));
			else
				response.setResponse(Boolean.toString(false));
		    
			response.setResponseType(AppConstants.API_RESPONSE_TYPE_BOOLEAN);
		    status = true;
		} catch(Exception e) {
		    response.setResponse(FriendsDaoConstants.FRIENDSHIP_EXISTENCE_CHECK_FAILED_INTERNAL_EEROR);
		    response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
		    error = true;
		    response.setErrorMessage(e.getMessage());
		}
		
		response.setError(error);
		response.setStatus(status);
		return response;
	}
	
	
	public static IntellectoResponse getFriendsOfUser(final long userId) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		try {
			Intellecto_Users user = Intellecto_Users.find.byId(userId);
			if(user == null || !user.isVerified()) {
				response.setResponse(UsersDaoConstants.NO_USER_FOUND_USERID);
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
			} else {
				Friends friendship = new Friends();
				friendship.friendship = new HashMap<>();
				
				List<Intellecto_Friends> friends =  Intellecto_Friends.find.where()
						.eq("user_id", userId)
						.findList();
				
				for(Intellecto_Friends friend : friends) {
					Intellecto_Users friendUser = Intellecto_Users.find.byId(friend.getFriendId());
					if(friendUser == null || !friendUser.isVerified()) {
						continue;
					}
					
					friendship.friendship.put(friendUser.getUserName(), friend);
				}
				
				response.setResponse(JsonObjectMapper.toJsonString(friendship, true));
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_ARRAYLIST);
				status = true;
			}

		} catch(Exception e) {
		    response.setResponse(FriendsDaoConstants.FRIENDSHIP_RETRIEVAL_OF_USER_FAILED_INTERNAL_EEROR);
		    response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
		    error = true;
		    response.setErrorMessage(e.getMessage());
		}
		
		response.setError(error);
		response.setStatus(status);
		return response;
	}
	
	
	public static IntellectoResponse updateFriendship(final long userId, final String friendshipString) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		Ebean.beginTransaction();
		
		try {
			Friends friendships = (Friends) JsonObjectMapper.toObject(friendshipString, Friends.class);
			
			for (Map.Entry<String, Intellecto_Friends> entry : friendships.friendship.entrySet()) {
			    Intellecto_Friends friendship = entry.getValue();
				if(userId != friendship.getUserId()) {
					throw new Exception("Bad request");
				}
			}
			
			IntellectoResponse fetchFriendsResponse = getFriendsOfUser(userId);
			if(fetchFriendsResponse.isError() || !fetchFriendsResponse.isStatus()) {
				return fetchFriendsResponse;
			}
			
			HashMap<Long, Intellecto_Friends> existingFriendsMap = new HashMap<>();
			Friends existingFriends = (Friends)JsonObjectMapper.toObject(fetchFriendsResponse.getResponse(), Friends.class);
			
			for (Map.Entry<String, Intellecto_Friends> entry : existingFriends.friendship.entrySet()) {
			    Intellecto_Friends existingFriend = entry.getValue();
				existingFriendsMap.put(existingFriend.getFriendId(), existingFriend);
			}
			
			DecimalFormat formatter = new DecimalFormat("#.##");
			for (Map.Entry<String, Intellecto_Friends> entry : friendships.friendship.entrySet()) {
				Intellecto_Friends newFriend = entry.getValue();
				if(!existingFriendsMap.containsKey(newFriend.getFriendId())) {
					continue;
				}
				
				Intellecto_Friends existingFriend = existingFriendsMap.get(newFriend.getFriendId());
				newFriend.setId(existingFriend.getId());
				newFriend.setFriendWinningEfficiency(Double.parseDouble(formatter.format(newFriend.getFriendWinningEfficiency())));
				newFriend.setUserWinningEfficiency(Double.parseDouble(formatter.format(newFriend.getUserWinningEfficiency())));
				newFriend.update();
			}
			
			response.setResponse(Boolean.toString(true));
			response.setResponseType(AppConstants.API_RESPONSE_TYPE_BOOLEAN);
			status = true;
			Ebean.commitTransaction();
			
		} catch(Exception e) {
		    response.setResponse(FriendsDaoConstants.UPDATING_FRIENDSHIP_OF_USER_FAILED_INTERNAL_EEROR);
		    response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
		    error = true;
		    response.setErrorMessage(e.getMessage());
		} finally {
			Ebean.endTransaction();
		}
		
		response.setError(error);
		response.setStatus(status);
		return response;
	}
	
	
}
