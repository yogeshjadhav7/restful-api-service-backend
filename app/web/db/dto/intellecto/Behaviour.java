package web.db.dto.intellecto;

import java.util.List;

import utils.general.JsonObjectMapper;
import web.db.models.intellecto.Intellecto_Friends;
import web.db.models.intellecto.Intellecto_Users;
import web.db.models.intellecto.Intellecto_Users_Behaviour;

public class Behaviour {
	
	public List<Intellecto_Users_Behaviour> behaviour;
	
	public static Behaviour toBehaviour(String behaviourString) {
		return (Behaviour) JsonObjectMapper.toObject(behaviourString, Behaviour.class);
	}
	
	@Override
	public String toString() {
		return JsonObjectMapper.toJsonString(this, true);
	}

}
