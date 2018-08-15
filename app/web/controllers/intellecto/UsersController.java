package web.controllers.intellecto;

import java.util.Map;

import sesmailer.AWSEmail;
import sesmailer.AWSEmailService;
import play.data.Form;
import play.mvc.*;
import utils.general.JsonObjectMapper;
import web.constants.app.AppConstants;
import web.constants.intellecto.UsersControlsDaoConstants;
import web.db.dao.intellecto.UsersControlsDao;
import web.db.dao.intellecto.UsersDao;
import web.db.models.intellecto.Intellecto_Users;
import web.db.models.intellecto.Intellecto_Users;
import web.response.IntellectoResponse;


public class UsersController extends IntellectoController {
	
	public Result generateOTP() {
		Map<String, String> formData = Form.form().bindFromRequest().data();
		final String userName = formData.get("userName");
		final String email = formData.get("email");
		IntellectoResponse response = UsersControlsDao.generateOtp(userName, email);
		return ok(JsonObjectMapper.toJsonString(response, true));
	}
	
	
	public Result validateOTP() {
		Map<String, String> formData = Form.form().bindFromRequest().data();
		final String userName = formData.get("userName");
		final String email = formData.get("email");
		final String OTP = formData.get("OTP");
		final String device = formData.get("device");
		String appVersion = null;
		try {
			appVersion = formData.get("app_version");
		} catch(Exception e) {}
		
		IntellectoResponse response = UsersControlsDao.validateOtp(userName, email, OTP, device, appVersion);
		System.out.println(JsonObjectMapper.toJsonString(response, true));
		return ok(JsonObjectMapper.toJsonString(response, true));
	}

}
