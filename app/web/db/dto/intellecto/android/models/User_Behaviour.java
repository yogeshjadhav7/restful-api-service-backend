package web.db.dto.intellecto.android.models;

public class User_Behaviour {

    private String gameState;
    private int behaviourState;

    public User_Behaviour() {}

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public int getBehaviourState() {
        return behaviourState;
    }

    public void setBehaviourState(int behaviourState) {
        this.behaviourState = behaviourState;
    }
}
