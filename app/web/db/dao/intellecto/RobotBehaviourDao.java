package web.db.dao.intellecto;

import java.util.Date;
import java.util.List;

import play.libs.F.Promise;
import utils.general.JsonObjectMapper;
import web.constants.app.AppConstants;
import web.constants.intellecto.BehaviourDaoConstants;
import web.constants.intellecto.UsersDaoConstants;
import web.db.dto.intellecto.RobotGameBehaviour;
import web.db.dto.intellecto.android.models.Robot_Behaviour;
import web.db.models.intellecto.Intellecto_Robot_Info;
import web.db.models.intellecto.Intellecto_Users;
import web.db.models.intellecto.Intellecto_Users_Behaviour;
import web.mlservice.intellecto.RobotService;
import web.response.IntellectoResponse;

public class RobotBehaviourDao {

	public static Promise<IntellectoResponse> trainRobotModel(long userId, final List<Intellecto_Users_Behaviour> behaviourList) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		
		try {
			if(behaviourList == null) {
			    response.setResponse("No data points exists");
			    response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
				response.setError(error);
				response.setStatus(status);
				return Promise.pure(response);
			}
			
			Intellecto_Users user = Intellecto_Users.find.byId(userId);
			if(user == null) {
			    response.setResponse(UsersDaoConstants.NO_USER_FOUND_USERID);
			    response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
				response.setError(error);
				response.setStatus(status);
				return Promise.pure(response);
			}
			
			final int behaviourCount = behaviourList.size();
			final String canProceed = RobotInfoDao.proceedWithTraining(userId, behaviourCount);
			if(canProceed == null) {
				final Intellecto_Robot_Info robotInfo = RobotInfoDao.getRobotInfoOf(userId);
				robotInfo.setBehaviourCount(behaviourCount);
				robotInfo.setTrainedOnBehaviourCount(behaviourCount);
				robotInfo.setTrainingInProgress(1);
				robotInfo.setLastTrainingStartedOn(new Date().getTime());
				robotInfo.save();
				
				status = RobotService.train(userId, behaviourList);
				response.setResponse(Boolean.toString(status));
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
			} else {
				response.setResponse(canProceed);
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		    response.setResponse(BehaviourDaoConstants.USER_BEHAVIOUR_RETRIEVAL_FAILED_INTERNAL_ERROR);
		    response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
		    error = true;
		    response.setErrorMessage(e.getMessage());
		}
		
		response.setError(error);
		response.setStatus(status);
		return Promise.pure(response);
	}
	
	public static IntellectoResponse getRobotBehaviour(long userId, final RobotGameBehaviour request) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
		
		try {
			Intellecto_Users user = Intellecto_Users.find.byId(userId);
			if(user == null) {
			    response.setResponse(UsersDaoConstants.NO_USER_FOUND_USERID);
			} else {
				
				if(request.behaviorCount <= 0) {
				    response.setResponse(user.getUserName() + "'s robot is not trained enough to play against you. Ask " + user.getUserName() + " to play more Challenge games to train the robot!" );
					response.setError(error);
					response.setStatus(status);
					return response;
				}
				
				String responseString = RobotService.predict(userId, request);
				RobotGameBehaviour robotGameBehaviourResponse =  (RobotGameBehaviour) JsonObjectMapper.toObject(responseString, RobotGameBehaviour.class);
				response.setResponse(JsonObjectMapper.toJsonString(robotGameBehaviourResponse, false));
				status = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		    response.setResponse(BehaviourDaoConstants.USER_BEHAVIOUR_RETRIEVAL_FAILED_INTERNAL_ERROR);    
		    error = true;
		    response.setErrorMessage(e.getMessage());
		}
		
		response.setError(error);
		response.setStatus(status);
		return response;
	}
	
}
