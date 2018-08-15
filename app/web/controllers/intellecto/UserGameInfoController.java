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

public class UserGameInfoController extends IntellectoController {
	
	public Result updateUserGameInfo() {
		Map<String, String> formData = Form.form().bindFromRequest().data();
		
		final IntellectoResponse authorizationResponse = authorizeRequest(formData);
		if(authorizationResponse != null) return ok(JsonObjectMapper.toJsonString(authorizationResponse, true));
		
		final long userId = Long.parseLong(formData.get("userId"));
		final UserGameInfoWrapper userGameInfo = (UserGameInfoWrapper) JsonObjectMapper.toObject(formData.get("userGameInfo"), UserGameInfoWrapper.class);
		final IntellectoResponse response = UsersGameInfoDao.updateUserGameInfoData(userId, userGameInfo);
		return ok(JsonObjectMapper.toJsonString(response, true));
	}
}
