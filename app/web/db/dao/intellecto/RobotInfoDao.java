package web.db.dao.intellecto;

import java.util.Date;
import java.util.List;

import com.avaje.ebean.Ebean;

import play.libs.F.Promise;
import utils.general.JsonObjectMapper;
import web.constants.app.AppConstants;
import web.constants.intellecto.BehaviourDaoConstants;
import web.constants.intellecto.UsersDaoConstants;
import web.db.models.intellecto.Intellecto_Robot_Info;
import web.db.models.intellecto.Intellecto_Users;
import web.db.models.intellecto.Intellecto_Users_Behaviour;
import web.response.IntellectoResponse;

public class RobotInfoDao {

	public static int TRAINING_BEHAVIOUR_COUNT_THRESHOLD = 50;
	public static long TRAINING_TIME_THRESHOLD = 24 * 60 * 60 * 1000; // 1 day
	public static int MAX_TRAINING_INSTANCES_SIMULTANEOUSLY = 1;
	
	public static class TRAINING_NOT_ALLOWED_REASONS {
		public static String NO_NEW_ENOUGH_DATA_POINTS = "Robot doesn't like training on less than " + TRAINING_BEHAVIOUR_COUNT_THRESHOLD + " moves of yours at a time. Please play more Challenge games to generate more moves for the robot to learn from!";
		public static String PREV_TRAINING_IN_PROGRESS = "Robot is still training on the previous request. Plase allow robot to finish before you can train it futher :)";
		public static String TRAINING_IS_NOW_QUEUED = "Your robot is queued for training!";
	}
	
	public static Intellecto_Robot_Info getRobotInfoOf(long userId) {
		List<Intellecto_Robot_Info> probables = Intellecto_Robot_Info.find.where().eq("user_id", userId).findList();
		if(probables == null || probables.isEmpty() || probables.size() > 1) return null;
		return probables.get(0);
	}
	
	// Initializes robot_info model too in DB
	// returns null if given permission to proceed
	// returns the reason as to why we can't proceed with training
	public static String proceedWithTraining(final long userId, final int behaviourCount) {
		Intellecto_Robot_Info robotInfo = getRobotInfoOf(userId);
		if(robotInfo == null) {
			robotInfo = new Intellecto_Robot_Info();
			robotInfo.setUserId(userId);
			robotInfo.setBehaviourCount(behaviourCount);
			save(robotInfo);
			return behaviourCount > 0 ? null : TRAINING_NOT_ALLOWED_REASONS.NO_NEW_ENOUGH_DATA_POINTS;
		}
		
		if(robotInfo.isTrainingInProgress() == 1) {
			return TRAINING_NOT_ALLOWED_REASONS.PREV_TRAINING_IN_PROGRESS;
		}
		
		if(getNumberOfActiveTrainingInstances() >= MAX_TRAINING_INSTANCES_SIMULTANEOUSLY) {
			queueTheTraining(userId);
			return TRAINING_NOT_ALLOWED_REASONS.TRAINING_IS_NOW_QUEUED;
		}
		
		if(getNumberOfQueuedInstances() > 0 && robotInfo.getIsQueued() == 0) {
			queueTheTraining(userId);
			return TRAINING_NOT_ALLOWED_REASONS.TRAINING_IS_NOW_QUEUED;
		}
		
		if(robotInfo.getTrainedOnBehaviourCount() + TRAINING_BEHAVIOUR_COUNT_THRESHOLD <= behaviourCount) return null;
		if(robotInfo.getLastTrainingEndedOn() + TRAINING_TIME_THRESHOLD <= new Date().getTime()) return null;
		if(behaviourCount <= 0) return TRAINING_NOT_ALLOWED_REASONS.NO_NEW_ENOUGH_DATA_POINTS;
		
		return TRAINING_NOT_ALLOWED_REASONS.PREV_TRAINING_IN_PROGRESS;
	}
	
	public static boolean queueTheTraining(final long userId) {
		Intellecto_Robot_Info robotInfo = getRobotInfoOf(userId);
		if(robotInfo == null) return false;
		if(robotInfo.getIsQueued() != 0) return false;
		robotInfo.setIsQueued(1);
		robotInfo.setQueuedOn(new Date().getTime());
		Ebean.beginTransaction();
		try {
			robotInfo.save();
			Ebean.commitTransaction();
			return true;
			
		} catch(Exception e) {
			return false;
		} finally {
			Ebean.endTransaction();
		}
	}
	
	public static int getNumberOfActiveTrainingInstances() {
		return Intellecto_Robot_Info.find.where().ne("is_training_in_progress", 0).findRowCount();
	}
	
	public static int getNumberOfQueuedInstances() {
		return Intellecto_Robot_Info.find.where().ne("is_queued", 0).orderBy("queued_on asc").findRowCount();
	}
	
	public static Intellecto_Robot_Info getNextQueuedRobot() {
		List<Intellecto_Robot_Info> queuedList = Intellecto_Robot_Info.find.where().ne("is_queued", 0).orderBy("queued_on asc").findList();
		if(queuedList == null || queuedList.size() == 0) return null;
		return queuedList.get(0);
	}
	
	public static boolean update(Intellecto_Robot_Info robotInfo) {
		if(robotInfo == null) return false;
		Ebean.beginTransaction();
		
		try {
			robotInfo.save();
			Ebean.commitTransaction();
			return true;
			
		} catch(Exception e) {
			return false;
		} finally {
			Ebean.endTransaction();
		}
	}
	
	public static boolean save(final Intellecto_Robot_Info robotInfo) {
		Ebean.beginTransaction();
		
		try {
			robotInfo.save();
			Ebean.commitTransaction();
			return true;
			
		} catch(Exception e) {
			return false;
		} finally {
			Ebean.endTransaction();
		}
	}
	
	
	public static Promise<IntellectoResponse> fetchRobotInfo(final long userId) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
		
		try {
			Intellecto_Users user = Intellecto_Users.find.byId(userId);
			if(user == null) {
			    response.setResponse(UsersDaoConstants.NO_USER_FOUND_USERID);
				response.setError(error);
				response.setStatus(status);
				return Promise.pure(response);
			}
			
			final Intellecto_Robot_Info robotInfo = getRobotInfoOf(userId);
			if(robotInfo == null) {
			    response.setResponse(UsersDaoConstants.NO_USER_FOUND_USERID);
				response.setError(error);
				response.setStatus(status);
				return Promise.pure(response);
			}
			
			response.setResponse(JsonObjectMapper.toJsonString(robotInfo, false));
			status = true;
			
		} catch(Exception e) {
			e.printStackTrace();
		    response.setResponse(BehaviourDaoConstants.USER_BEHAVIOUR_RETRIEVAL_FAILED_INTERNAL_ERROR);
		    error = true;
		    response.setErrorMessage(e.getMessage());
		}
		
		response.setError(error);
		response.setStatus(status);
		return Promise.pure(response);
	}
	
}
