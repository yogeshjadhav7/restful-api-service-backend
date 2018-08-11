package web.db.dto.intellecto.android.models;

public class User {

	public long id;
    private Long userId;
    private String userName;
    private String email;
    private String phoneNumber;
    private String nationality;
    private double efficiency = 0;
    private double progress = 0;
    private long lastPromptedOn = 0;
    private long gamesPlayedOfChase = 0;
    private long gamesPlayedOfMinefield = 0;
    private long gamesPlayedOfChallenge = 0;
    private long timeSpentOnChase = 0;
    private long timeSpentOnMinefield = 0;
    private long timeSpentOnChallenge = 0;
    private long adsImpressions = 0;
    private boolean adsFlag = true;
    private long lastSyncedAt = 0;
    private long lastImportedAt = 0;
    private long friendsLastSyncedAt = 0;

    public User() {}

    public User(final long userId, final String userName, final String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getFriendsLastSyncedAt() {
        return friendsLastSyncedAt;
    }

    public void setFriendsLastSyncedAt(long friendsLastSyncedAt) {
        this.friendsLastSyncedAt = friendsLastSyncedAt;
    }

    public long getLastSyncedAt() {
        return lastSyncedAt;
    }

    public void setLastSyncedAt(long lastSyncedAt) {
        this.lastSyncedAt = lastSyncedAt;
    }

    public long getLastImportedAt() {
        return lastImportedAt;
    }

    public void setLastImportedAt(long lastImportedAt) {
        this.lastImportedAt = lastImportedAt;
    }

    public boolean isAdsFlag() {
        return adsFlag;
    }

    public void setAdsFlag(boolean adsFlag) {
        this.adsFlag = adsFlag;
    }

    public long getAdsImpressions() {
        return adsImpressions;
    }

    public void setAdsImpressions(long adsImpressions) {
        this.adsImpressions = adsImpressions;
    }

    public long getTimeSpentOnChallenge() {
        return timeSpentOnChallenge;
    }

    public void setTimeSpentOnChallenge(long timeSpentOnChallenge) {
        this.timeSpentOnChallenge = timeSpentOnChallenge;
    }

    public long getTimeSpentOnMinefield() {
        return timeSpentOnMinefield;
    }

    public void setTimeSpentOnMinefield(long timeSpentOnMinefield) {
        this.timeSpentOnMinefield = timeSpentOnMinefield;
    }

    public long getTimeSpentOnChase() {
        return timeSpentOnChase;
    }

    public void setTimeSpentOnChase(long timeSpentOnChase) {
        this.timeSpentOnChase = timeSpentOnChase;
    }

    public long getGamesPlayedOfChallenge() {
        return gamesPlayedOfChallenge;
    }

    public void setGamesPlayedOfChallenge(long gamesPlayedOfChallenge) {
        this.gamesPlayedOfChallenge = gamesPlayedOfChallenge;
    }

    public long getGamesPlayedOfMinefield() {
        return gamesPlayedOfMinefield;
    }

    public void setGamesPlayedOfMinefield(long gamesPlayedOfMinefield) {
        this.gamesPlayedOfMinefield = gamesPlayedOfMinefield;
    }

    public long getGamesPlayedOfChase() {
        return gamesPlayedOfChase;
    }

    public void setGamesPlayedOfChase(long gamesPlayedOfChase) {
        this.gamesPlayedOfChase = gamesPlayedOfChase;
    }

    public long getLastPromptedOn() {
        return lastPromptedOn;
    }

    public void setLastPromptedOn(long lastPromptedOn) {
        this.lastPromptedOn = lastPromptedOn;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
