package web.controllers.led7;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import play.data.Form;
import play.libs.F.Promise;
import play.mvc.*;
import utils.general.IntellectoUtils;
import utils.general.JsonObjectMapper;
import utils.general.Led7Utils;
import web.constants.app.AppConstants;
import web.controllers.intellecto.RobotController;
import web.db.dao.intellecto.BehaviourDao;
import web.db.dao.intellecto.RobotInfoDao;
import web.db.dao.led7.Led7Dao;
import web.db.dto.intellecto.RobotGameBehaviour;
import web.db.models.intellecto.Intellecto_Robot_Info;
import web.db.models.intellecto.Intellecto_Users_Behaviour;
import web.db.models.led7.Led7_Data;
import web.mlservice.intellecto.RobotService;
import web.response.Led7Response;

public class PredictorController extends Controller {
	
	final static String ALL_CASES_INPUT_DATA = "all_cases_input_data.csv";
	final static String ALL_CASES_PREDICTIONS = "all_cases_predictions.csv";
	
	public Promise<Result> predict() {
		Map<String, String> formData = Form.form().bindFromRequest().data();
		final String segmentState = formData.get("segment_state");
		String type = formData.get("type");
		if(type == null) {
			type = AppConstants.LED7_TYPE_MLP;
		}
		
		if(type.equals(AppConstants.LED7_TYPE_SVM)) {
			type = AppConstants.LED7_TYPE_SGD;
		}
		
		
		Led7Response response = Led7Dao.predict(segmentState, type);
	 	return Promise.<Result> pure(
              Results.ok(JsonObjectMapper.toJsonString(response, true))
	 	);
	}
	
	
	public Promise<Result> databaseInitializer() {
		List<Led7_Data> dataList = Led7Utils.csvToLed7DataList(ALL_CASES_INPUT_DATA, ALL_CASES_PREDICTIONS);
		Led7Dao.addToDatabase(dataList);
		
	 	return Promise.<Result> pure(
              Results.ok(JsonObjectMapper.toJsonString(dataList, true))
	 	);
	}
	

	public Promise<Result> test() {
		
		/*
		long userId = 2;
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
		if(queuedRobotInfo != null) RobotController.trainQueuedRobot(queuedRobotInfo);
		
		final String response = "Notification captured for userId : " + Long.toString(userId);
		System.out.println(response);
		
		return Promise.pure(ok(Integer.toString(RobotInfoDao.getNumberOfActiveTrainingInstances())));
		*/
		return Promise.pure(ok());
	}
		
}
