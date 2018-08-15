package web.controllers.intellecto;

import java.util.Map;

import sesmailer.AWSEmail;
import sesmailer.AWSEmailService;
import play.data.Form;
import play.mvc.*;
import utils.general.JsonObjectMapper;
import web.constants.app.AppConstants;
import web.constants.intellecto.UsersControlsDaoConstants;
import web.db.dao.intellecto.AppVersioningDao;
import web.db.dao.intellecto.UsersControlsDao;
import web.db.dao.intellecto.UsersDao;
import web.db.models.intellecto.Intellecto_Users;
import web.db.models.intellecto.Intellecto_Users;
import web.response.IntellectoResponse;


public class VersioningController extends IntellectoController {
	
	public Result checkAppVersion() {
		Map<String, String> formData = Form.form().bindFromRequest().data();
		final String appVersion = formData.get("app_version");
		IntellectoResponse response = AppVersioningDao.checkAppVersion(appVersion);
		return ok(JsonObjectMapper.toJsonString(response, true));
	}
	
}
