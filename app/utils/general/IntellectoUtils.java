package utils.general;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import web.db.dto.intellecto.RobotGameBehaviour;
import web.db.dto.intellecto.android.models.Robot_Behaviour;
import web.db.models.intellecto.Intellecto_Users_Behaviour;

public class IntellectoUtils {
	
    private static final String SEP = ",";
    private static final String NULL_PADDING = "#";

	
	public static RobotGameBehaviour lookAheadGameStates(final RobotGameBehaviour request, final List<Intellecto_Users_Behaviour> behaviourList) {
		
		request.behaviorCount = behaviourList.size();
		final Map<String, Robot_Behaviour> storedResponses = new HashMap<>();
		for(Intellecto_Users_Behaviour behaviour : behaviourList) storedResponses.put(behaviour.getGameState(), new Robot_Behaviour(behaviour));
        request.behaviourMap = new LinkedHashMap<>();
        request.behaviorCount = behaviourList.size();
        boolean robotsTurn = request.robotsTurn;
        List<Integer> gameValues = request.gameValues;
        int depth = request.depth;
        if(request.behaviorCount > 0) accumulateGameStates(gameValues, request, robotsTurn, storedResponses, depth, new HashSet<>());
        return request;
    }
	
	
    private static void accumulateGameStates(List<Integer> gameValues, RobotGameBehaviour request, boolean robotsTurn, final Map<String, Robot_Behaviour> storedResponses, int depth, final HashSet<String> dp) {

		if(depth <= 0 || !proceedWithLookAhead(gameValues)) return;
		
		final String gameValuesState = getGameValuesState(gameValues, robotsTurn);
		if(dp.contains(gameValuesState)) return;
		
		if(robotsTurn) {
			final String gameState = getGameState(gameValues);
			if(storedResponses.containsKey(gameState)) request.behaviourMap.put(gameState, storedResponses.get(gameState));
			else request.behaviourMap.put(gameState, new Robot_Behaviour(gameState));
			depth--;
		}
		
		Integer next = gameValues.size() > 5 ? gameValues.get(5) : null;
		for(int i = 0; i < 5; i++) {
			Integer val = gameValues.get(i);
			if(val == null) continue;
			List<Integer> clonedValues = clone(gameValues);
			if(next == null) {
			clonedValues.set(i, null);
			} else {
			clonedValues.set(i, next);
			clonedValues.remove(5);
			}
			
			accumulateGameStates(clonedValues, request, !robotsTurn, storedResponses, depth, dp);
		}
		
		dp.add(gameValuesState);
    }
    
	
    private static List<Integer> clone(final List<Integer> gameValues) {
        List<Integer> cloned = new ArrayList<>();
        for(Integer gameValue : gameValues) cloned.add(gameValue);
        return cloned;
    }

    
    private static boolean proceedWithLookAhead(List<Integer> gameValues) {
        for(Integer gameValue : gameValues) if(gameValue != null) return true;
        return false;
    }
    
	
    public static String getGameState(List<Integer> gameValues) {
        Integer next = (gameValues.size() == 5) ? null : gameValues.get(5);
        String nextPartString = getNextPartString(next);
        String gameStateString = getMainPartString(gameValues);
        return gameStateString + nextPartString;
    }
    
    public static String getGameValuesState(List<Integer> gameValues, boolean robotsTurn) {
    		StringBuilder sb = new StringBuilder();
    		sb.append(Boolean.toString(robotsTurn));
    		
    		for(Integer gameValue : gameValues) {
    			String value = null;
                if(gameValue != null) value = Integer.toString(gameValue);
                else value = NULL_PADDING;
                sb.append(SEP + value);
    		}

    		return sb.toString();
    }
    
    
    private static String getMainPartString(List<Integer> gameValues) {
        StringBuilder sb = null;
        for(int i = 0; i < 5; i++) {
            Integer gameValue = gameValues.get(i);
            String value = null;
            if(gameValue != null) value = Integer.toString(gameValue);
            else value = NULL_PADDING;

            if(sb == null) {sb = new StringBuilder(); sb.append(value); }
            else sb.append(SEP + value);
        }

        return sb != null ? sb.toString() : null;
    }
    

    private static String getNextPartString(Integer next) {
        return next == null ? SEP + NULL_PADDING : SEP + Integer.toString(next);
    }

}
