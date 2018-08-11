package web.controllers.intellecto;

import java.util.Map;

import play.data.Form;
import play.mvc.*;
import utils.general.JsonObjectMapper;
import web.db.dao.intellecto.BehaviourDao;
import web.db.dao.intellecto.FriendsDao;
import web.db.dao.intellecto.UsersDao;
import web.db.dao.intellecto.UsersGameInfoDao;
import web.db.dto.intellecto.Behaviour;
import web.db.dto.intellecto.android.wrappers.BehaviourWrapper;
import web.db.dto.intellecto.android.wrappers.UserGameInfoWrapper;
import web.response.IntellectoResponse;

public class UserGameInfoController extends Controller {
	
	public Result updateUserGameInfo() {
		Map<String, String> formData = Form.form().bindFromRequest().data();
		final long userId = Long.parseLong(formData.get("userId"));
		System.out.println("GAME " + formData.get("userId"));
		final UserGameInfoWrapper userGameInfo = (UserGameInfoWrapper) JsonObjectMapper.toObject(formData.get("userGameInfo"), UserGameInfoWrapper.class);
		final IntellectoResponse response = UsersGameInfoDao.updateUserGameInfoData(userId, userGameInfo);
		System.out.println(JsonObjectMapper.toJsonString(response, true));
		return ok(JsonObjectMapper.toJsonString(response, true));
	}
}
