package web.db.dto.intellecto.android.models;

public class Opponent_Game {

	public long id;
    private int opponentId;
    private int gameId;
    private boolean defeated;
    private double efficiency;
    private double weight;
    private String preGameDialogue;
    private String postGameDialogueWin;
    private String postGameDialogueLose;

    public Opponent_Game() {}

    public Opponent_Game(int opponentId, int gameId, boolean defeated, double efficiency, double weight, String... dialogues) {
        this.opponentId = opponentId;
        this.gameId = gameId;
        this.defeated = defeated;
        this.efficiency = efficiency;
        this.weight = weight;
        this.preGameDialogue = dialogues[0] + "~" + dialogues[1] + "~" + dialogues[2];
        this.postGameDialogueWin = dialogues[3] + "~" + dialogues[4] + "~" + dialogues[5];
        this.postGameDialogueLose = dialogues[6] + "~" + dialogues[7] + "~" + dialogues[8];
    }


    public int getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(int opponentId) {
        this.opponentId = opponentId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPreGameDialogue() {
        return preGameDialogue;
    }

    public String getPostGameDialogueWin() {
        return postGameDialogueWin;
    }

    public String getPostGameDialogueLose() {
        return postGameDialogueLose;
    }

    public void setPreGameDialogue(String preGameDialogue) {
        this.preGameDialogue = preGameDialogue;
    }

    public void setPostGameDialogueWin(String postGameDialogueWin) {
        this.postGameDialogueWin = postGameDialogueWin;
    }

    public void setPostGameDialogueLose(String postGameDialogueLose) {
        this.postGameDialogueLose = postGameDialogueLose;
    }
}
