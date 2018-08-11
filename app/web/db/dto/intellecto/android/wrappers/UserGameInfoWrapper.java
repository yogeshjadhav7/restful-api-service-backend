package web.db.dto.intellecto.android.wrappers;

import utils.general.JsonObjectMapper;

public class UserGameInfoWrapper {

    private GameWrapper gameWrapper;
    private OpponentWrapper opponentWrapper;
    private OpponentGameWrapper opponentGameWrapper;
    private UserWrapper userWrapper;
    private BehaviourWrapper behaviourWrapper;
    private FriendWrapper friendWrapper;
    private RobotInfoWrapper robotInfoWrapper;

    public UserGameInfoWrapper() {}

    public UserGameInfoWrapper(final GameWrapper gameWrapper, final OpponentWrapper opponentWrapper,
                               final OpponentGameWrapper opponentGameWrapper, final UserWrapper userWrapper,
                               final BehaviourWrapper behaviourWrapper, final FriendWrapper friendWrapper,
                               final RobotInfoWrapper robotInfoWrapper) {

        this.gameWrapper = gameWrapper;
        this.opponentWrapper = opponentWrapper;
        this.opponentGameWrapper = opponentGameWrapper;
        this.userWrapper = userWrapper;
        this.behaviourWrapper = behaviourWrapper;
        this.friendWrapper = friendWrapper;
        this.robotInfoWrapper = robotInfoWrapper;
    }

    public GameWrapper getGameWrapper() {
        return gameWrapper;
    }

    public void setGameWrapper(GameWrapper gameWrapper) {
        this.gameWrapper = gameWrapper;
    }

    public UserWrapper getUserWrapper() {
        return userWrapper;
    }

    public void setUserWrapper(UserWrapper userWrapper) {
        this.userWrapper = userWrapper;
    }

    public OpponentGameWrapper getOpponentGameWrapper() {
        return opponentGameWrapper;
    }

    public void setOpponentGameWrapper(OpponentGameWrapper opponentGameWrapper) {
        this.opponentGameWrapper = opponentGameWrapper;
    }

    public OpponentWrapper getOpponentWrapper() {
        return opponentWrapper;
    }

    public void setOpponentWrapper(OpponentWrapper opponentWrapper) {
        this.opponentWrapper = opponentWrapper;
    }

    public BehaviourWrapper getBehaviourWrapper() {
        return behaviourWrapper;
    }

    public void setBehaviourWrapper(BehaviourWrapper behaviourWrapper) {
        this.behaviourWrapper = behaviourWrapper;
    }

    public FriendWrapper getFriendWrapper() {
        return friendWrapper;
    }

    public void setFriendWrapper(FriendWrapper friendWrapper) {
        this.friendWrapper = friendWrapper;
    }
    
    public RobotInfoWrapper getRobotInfoWrapper() {
		return robotInfoWrapper;
	}

	public void setRobotInfoWrapper(RobotInfoWrapper robotInfoWrapper) {
		this.robotInfoWrapper = robotInfoWrapper;
	}

	@Override
    public String toString() {
        return JsonObjectMapper.toJsonString(this, true);
    }
}
