package web.db.dto.intellecto.android.models;

public class Friend {

    private long friendId;
    private String name;
    private double userWinningEfficiency = 0;
    private double friendWinningEfficiency = 0;
    private long userWinningCount = 0;
    private long friendWinningCount = 0;
    private long lastGameTime = 0;
    private boolean isChanged = false;
    private boolean isTappedOn;

    public Friend() {}

    public Friend(final long friendId, final String name) {
        this.friendId = friendId;
        this.name = name;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUserWinningEfficiency() {
        return userWinningEfficiency;
    }

    public void setUserWinningEfficiency(double userWinningEfficiency) {
        this.userWinningEfficiency = userWinningEfficiency;
    }

    public double getFriendWinningEfficiency() {
        return friendWinningEfficiency;
    }

    public void setFriendWinningEfficiency(double friendWinningEfficiency) {
        this.friendWinningEfficiency = friendWinningEfficiency;
    }

    public long getUserWinningCount() {
        return userWinningCount;
    }

    public void setUserWinningCount(long userWinningCount) {
        this.userWinningCount = userWinningCount;
    }

    public long getFriendWinningCount() {
        return friendWinningCount;
    }

    public void setFriendWinningCount(long friendWinningCount) {
        this.friendWinningCount = friendWinningCount;
    }

    public long getLastGameTime() {
        return lastGameTime;
    }

    public void setLastGameTime(long lastGameTime) {
        this.lastGameTime = lastGameTime;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setIsChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }

    public boolean isTappedOn() {
        return isTappedOn;
    }

    public void setIsTappedOn(boolean isTappedOn) {
        this.isTappedOn = isTappedOn;
    }
}
