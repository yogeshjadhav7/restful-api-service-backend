package web.db.dto.intellecto.android.wrappers;

import java.util.List;

import utils.general.JsonObjectMapper;
import web.db.models.intellecto.Intellecto_Users_Behaviour;

public class BehaviourWrapper {

    public List<Intellecto_Users_Behaviour> behaviour;

    public static BehaviourWrapper toBehaviourWrapper(String behaviourString) {
        return (BehaviourWrapper) JsonObjectMapper.toObject(behaviourString, BehaviourWrapper.class);
    }

    @Override
    public String toString() {
        return JsonObjectMapper.toJsonString(this, true);
    }
}
