package web.db.dto.intellecto.android.models;

import web.db.models.intellecto.Intellecto_Users_Behaviour;

public class Robot_Behaviour {

    private String gameState;
    private int robotResponse = -1;

    public Robot_Behaviour(){}
    
    public Robot_Behaviour(String gameState){
    		this.gameState = gameState;
    }
    
    public Robot_Behaviour(int robotResponse){
		this.robotResponse = robotResponse;
    }
    
    public Robot_Behaviour(Intellecto_Users_Behaviour userBehaviour){
    		this.gameState = userBehaviour.getGameState();
    		this.robotResponse = userBehaviour.getUserResponse();
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public int getRobotResponse() {
        return robotResponse;
    }

    public void setRobotResponse(int robotResponse) {
        this.robotResponse = robotResponse;
    }

}
