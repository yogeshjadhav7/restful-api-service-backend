package web.controllers.intellecto;

import java.util.Date;
import java.util.List;
import java.util.Map;
import play.data.Form;
import play.libs.F.Promise;
import play.mvc.*;
import utils.general.IntellectoUtils;
import utils.general.JsonObjectMapper;
import web.db.dao.intellecto.BehaviourDao;
import web.db.dao.intellecto.RobotBehaviourDao;
import web.db.dao.intellecto.RobotInfoDao;
import web.db.dto.intellecto.RobotGameBehaviour;
import web.db.models.intellecto.Intellecto_Robot_Info;
import web.db.models.intellecto.Intellecto_Users_Behaviour;
import web.response.IntellectoResponse;


public class RobotController extends IntellectoController {

	public Result fetchRobotPredictions() {
		Map<String, String> formData = Form.form().bindFromRequest().data();
		
		final IntellectoResponse authorizationResponse = authorizeRequest(formData);
		if(authorizationResponse != null) return ok(JsonObjectMapper.toJsonString(authorizationResponse, true));
		
		final long userId = Long.parseLong(formData.get("userId"));
		final RobotGameBehaviour robotGameBehaviour = (RobotGameBehaviour) JsonObjectMapper.toObject(formData.get("behaviour"), RobotGameBehaviour.class);
		List<Intellecto_Users_Behaviour> behaviourList = BehaviourDao.getUserBehaviourList(userId);
		IntellectoUtils.lookAheadGameStates(robotGameBehaviour, behaviourList);
		IntellectoResponse response = RobotBehaviourDao.getRobotBehaviour(userId, robotGameBehaviour);
		return ok(JsonObjectMapper.toJsonString(response, false));
	}
	
	public Promise<Result> trainRobot() {
		Map<String, String> formData = Form.form().bindFromRequest().data();
		final long userId = Long.parseLong(formData.get("userId"));
		List<Intellecto_Users_Behaviour> behaviourList = BehaviourDao.getUserBehaviourList(userId);
		IntellectoResponse response = RobotBehaviourDao.trainRobotModel(userId, behaviourList).get(100000);
		return Promise.pure(ok(JsonObjectMapper.toJsonString(response, false)));
	}
	
	public Promise<Result> fetchRobotInfo() {
		Map<String, String> formData = Form.form().bindFromRequest().data();
		
		final IntellectoResponse authorizationResponse = authorizeRequest(formData);
		if(authorizationResponse != null) return Promise.pure(ok(JsonObjectMapper.toJsonString(authorizationResponse, true)));
		
		final long userId = Long.parseLong(formData.get("userId"));
		IntellectoResponse response = RobotInfoDao.fetchRobotInfo(userId).get(100000);
		return Promise.pure(ok(JsonObjectMapper.toJsonString(response, false)));
	}
	
	public Result notifyTrainingCompletion() {
		Map<String, String> formData = Form.form().bindFromRequest().data();
		final long userId = Long.parseLong(formData.get("userId"));
		
		final Intellecto_Robot_Info robotInfo = RobotInfoDao.getRobotInfoOf(userId);
		robotInfo.setTrainingCount(robotInfo.getTrainingCount() + 1);
		robotInfo.setTrainingInProgress(0);
		robotInfo.setLastTrainingEndedOn(new Date().getTime());
		
		if(robotInfo.getIsQueued() == 1 && robotInfo.getQueuedOn() < robotInfo.getLastTrainingStartedOn()) {
			robotInfo.setIsQueued(0);
			robotInfo.setQueuedOn(0);
		}
		
		// Save the robot info
		robotInfo.save();
		
		final Intellecto_Robot_Info queuedRobotInfo = RobotInfoDao.getNextQueuedRobot();
		if(queuedRobotInfo != null) trainQueuedRobot(queuedRobotInfo);
		
		final String response = "Notification captured for userId : " + Long.toString(userId);
		System.out.println(response);
		return ok(response);
	}
	
	public static Promise<IntellectoResponse> trainQueuedRobot(final Intellecto_Robot_Info robotInfo) {
		if(robotInfo == null) return Promise.pure(null);
		final long userId = robotInfo.getUserId();
		List<Intellecto_Users_Behaviour> behaviourList = BehaviourDao.getUserBehaviourList(userId);
		IntellectoResponse response = RobotBehaviourDao.trainRobotModel(userId, behaviourList).get(100000);
		return Promise.pure(response);
	}
	
}
