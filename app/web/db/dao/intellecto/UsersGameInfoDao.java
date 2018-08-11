package web.db.dao.intellecto;

import play.mvc.*;
import utils.general.MD5;
import web.constants.app.AppConstants;
import web.constants.intellecto.BehaviourDaoConstants;
import web.constants.intellecto.UsersDaoConstants;
import web.db.dto.intellecto.Behaviour;
import web.db.dto.intellecto.android.wrappers.UserGameInfoWrapper;
import web.db.models.intellecto.Intellecto_Users;
import web.db.models.intellecto.Intellecto_Users_Game_Info;
import web.db.models.intellecto.Intellecto_Users;
import web.response.IntellectoResponse;
import web.response.Response;

import com.avaje.ebean.*;

public class UsersGameInfoDao {

	public static IntellectoResponse updateUserGameInfoData(long userId, UserGameInfoWrapper userGameInfo) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		
		Ebean.beginTransaction();
		try {
			final Behaviour behaviours = new Behaviour();
			boolean behaviourUpdateStatus = true;
			if(userGameInfo.getBehaviourWrapper() != null) {
				behaviours.behaviour = userGameInfo.getBehaviourWrapper().behaviour;
				IntellectoResponse behaviourResponse = BehaviourDao.updateUserBehaviour(userId, behaviours);
				System.out.println("Behaviour Response : " + behaviourResponse.toString());
				behaviourUpdateStatus = !behaviourResponse.isError() && behaviourResponse.isStatus();
			}
			
			if(behaviourUpdateStatus) {
				final String gameInfoSerialized = userGameInfo.getGameWrapper().toString();
				final String userInfoSerialized = userGameInfo.getUserWrapper().toString();
				final String opponentInfoSerialized = userGameInfo.getOpponentWrapper().toString();
				final String opponentGameInfoSerialized = userGameInfo.getOpponentGameWrapper().toString();
				
				Intellecto_Users_Game_Info userGame = Intellecto_Users_Game_Info.find.where()
						.eq("id", userId)
						.findUnique();
				
				if(userGame == null) {
					response.setResponse(UsersDaoConstants.NO_USER_FOUND_USERNAME);
					response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
				} else {
					userGame.setGameInfo(gameInfoSerialized);
					userGame.setUserInfo(userInfoSerialized);
					userGame.setOpponentInfo(opponentInfoSerialized);
					userGame.setOpponentGameInfo(opponentGameInfoSerialized);
					userGame.update();
					Ebean.commitTransaction();
					
					response.setResponse(Boolean.toString(true));
					response.setResponseType(AppConstants.API_RESPONSE_TYPE_BOOLEAN);
					status = true;
				}
			
			} else {
				response.setResponse(BehaviourDaoConstants.USER_BEHAVIOUR_UPDATE_FAILED_INTERNAL_ERROR);
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
				error = true;
				response.setErrorMessage(BehaviourDaoConstants.USER_BEHAVIOUR_UPDATE_FAILED_INTERNAL_ERROR);
			} 
		
		} catch(Exception e) {
			e.printStackTrace();
			response.setResponse(UsersDaoConstants.NO_USER_FOUND_USERNAME_INTERNAL_EEROR);
			response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
			error = true;
			response.setErrorMessage(e.getMessage());
		} finally {
			Ebean.endTransaction();
		}
		
		response.setStatus(status);
		response.setError(error);
		return response;
	}
	
}
