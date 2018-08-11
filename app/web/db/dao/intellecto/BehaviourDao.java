package web.db.dao.intellecto;

import java.util.List;

import com.avaje.ebean.Ebean;

import play.libs.F.Promise;
import utils.general.JsonObjectMapper;
import web.constants.app.AppConstants;
import web.constants.intellecto.BehaviourDaoConstants;
import web.constants.intellecto.UsersDaoConstants;
import web.controllers.intellecto.RobotController;
import web.db.dto.intellecto.Behaviour;
import web.db.models.intellecto.Intellecto_Robot_Info;
import web.db.models.intellecto.Intellecto_Users;
import web.db.models.intellecto.Intellecto_Users_Behaviour;
import web.response.IntellectoResponse;

public class BehaviourDao {

	public static int getUserBehaviourCount(long userId) {
		Intellecto_Users user = Intellecto_Users.find.byId(userId);
		if(user == null) return 0;
		return Intellecto_Users_Behaviour.find.where().eq("user_id", userId).findRowCount();
	}
	
	
	public static List<Intellecto_Users_Behaviour> getUserBehaviourList(long userId) {
		Intellecto_Users user = Intellecto_Users.find.byId(userId);
		if(user == null) return null;
		return Intellecto_Users_Behaviour.find.where().eq("user_id", userId).findList();
	}
	
	
	public static IntellectoResponse getUserBehaviour(long userId) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		
		try {
			Intellecto_Users user = Intellecto_Users.find.byId(userId);
			if(user == null) {
			    response.setResponse(UsersDaoConstants.NO_USER_FOUND_USERID);
			    response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
			} else {
				Behaviour behaviour = new Behaviour();
				behaviour.behaviour = Intellecto_Users_Behaviour.find.where().eq("user_id", userId).orderBy("game_state ASC").findList();
				response.setResponse(JsonObjectMapper.toJsonString(behaviour, false));
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
				status = true;
			}
			
		} catch(Exception e) {
		    response.setResponse(BehaviourDaoConstants.USER_BEHAVIOUR_RETRIEVAL_FAILED_INTERNAL_ERROR);
		    response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
		    error = true;
		    response.setErrorMessage(e.getMessage());
		}
		
		response.setError(error);
		response.setStatus(status);
		return response;
	}

	
	// inserts new game states
	// updates existing game states
	public static IntellectoResponse updateUserBehaviour(long userId, Behaviour behaviours) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		Ebean.beginTransaction();
		
		try {

			for(Intellecto_Users_Behaviour behaviour : behaviours.behaviour) {
				
				if(userId != behaviour.getUserId()) {
					continue;
				}
				
				Intellecto_Users_Behaviour storedBehaviour = Intellecto_Users_Behaviour
						.find
						.where()
						.eq("user_id", behaviour.getUserId())
						.eq("game_state", behaviour.getGameState())
						.findUnique();
				
				if(storedBehaviour == null) {
					behaviour.save();
				} else {
					behaviour.setId(storedBehaviour.getId());
					behaviour.update();
				}
			}
			
			Intellecto_Robot_Info robotInfo = RobotInfoDao.getRobotInfoOf(userId);
			if(robotInfo == null) {
				robotInfo = new Intellecto_Robot_Info();
				robotInfo.setUserId(userId);
			}
			
			robotInfo.setBehaviourCount(getUserBehaviourCount(userId));
			robotInfo.save();
			
		    response.setResponse(JsonObjectMapper.toJsonString(robotInfo, false));
		    response.setResponseType(AppConstants.API_RESPONSE_TYPE_INT);
			Ebean.commitTransaction();
			
			status = true;
			
			// train model if conditions satify in async
			checkAndTrainRobot(userId);
			
		} catch(Exception e) {
		    response.setResponse(BehaviourDaoConstants.USER_BEHAVIOUR_UPDATE_FAILED_INTERNAL_ERROR);
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
	
	
	private static Promise<IntellectoResponse> checkAndTrainRobot(final long userId) {
		final List<Intellecto_Users_Behaviour> behaviourList = BehaviourDao.getUserBehaviourList(userId);
		return RobotBehaviourDao.trainRobotModel(userId, behaviourList);
	}
	
}
