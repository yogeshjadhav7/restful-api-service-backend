package web.controllers.intellecto;

import java.util.Map;

import play.data.Form;
import play.mvc.*;
import utils.general.JsonObjectMapper;
import web.db.dao.intellecto.BehaviourDao;
import web.db.dto.intellecto.Behaviour;
import web.response.IntellectoResponse;


public class UsersBehaviourController extends IntellectoController {
	
	public Result getUserBehaviour() {
		Map<String, String> formData = Form.form().bindFromRequest().data();	
		
		final IntellectoResponse authorizationResponse = authorizeRequest(formData);
		if(authorizationResponse != null) return ok(JsonObjectMapper.toJsonString(authorizationResponse, true));
		
		final long userId = Long.parseLong(formData.get("userId"));
		IntellectoResponse response = BehaviourDao.getUserBehaviour(userId);
		return ok(JsonObjectMapper.toJsonString(response, true));
	}

	public Result updateUserBehaviour() {
		Map<String, String> formData = Form.form().bindFromRequest().data();

		final IntellectoResponse authorizationResponse = authorizeRequest(formData);
		if(authorizationResponse != null) return ok(JsonObjectMapper.toJsonString(authorizationResponse, true));
			
		final long userId = Long.parseLong(formData.get("userId"));
		final Behaviour behaviours = (Behaviour) JsonObjectMapper.toObject(formData.get("behaviour"), Behaviour.class);
		IntellectoResponse response = BehaviourDao.updateUserBehaviour(userId, behaviours);
		return ok(JsonObjectMapper.toJsonString(response, true));
	}

}
