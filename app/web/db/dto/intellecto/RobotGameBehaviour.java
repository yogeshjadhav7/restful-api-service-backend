package web.db.dto.intellecto;

import java.util.List;
import java.util.Map;

import utils.general.JsonObjectMapper;
import web.db.dto.intellecto.android.models.Robot_Behaviour;
import web.db.models.intellecto.Intellecto_Users_Behaviour;

public class RobotGameBehaviour {
	
    public Map<String, Robot_Behaviour> behaviourMap;
    public List<Integer> gameValues;
    public int behaviorCount = -1;
    public boolean robotsTurn = false;
    public long userId;
    public int depth = 5;
    
    public RobotGameBehaviour() {}
    
    public static RobotGameBehaviour toRobotGameBehaviourWrapper(String behaviourMapString) {
        return (RobotGameBehaviour) JsonObjectMapper.toObject(behaviourMapString, RobotGameBehaviour.class);
    }

    @Override
    public String toString() {
        return JsonObjectMapper.toJsonString(this, true);
    }


}
