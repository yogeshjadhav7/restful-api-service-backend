package web.db.dto.intellecto.android.models;

import java.util.ArrayList;
import java.util.List;

public class Opponent {

	public long id;
    private int opponentId;
    private String name;
    private boolean defeated;
    private String description;
    private double progress;
    private double efficiency;
    private boolean onGoing;
    private List<Game> games;

    public Opponent() {}

    public Opponent(int opponentId, String name, String description, boolean defeated, double progress, double efficiency, boolean onGoing) {
        this.opponentId = opponentId;
        this.name = name;
        this.description = description;
        this.defeated = defeated;
        this.progress = progress;
        this.efficiency = efficiency;
        this.onGoing = onGoing;
        this.games = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOpponentId() {
        return opponentId;
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean isOnGoing() {
        return onGoing;
    }

    public void setOnGoing(boolean onGoing) {
        this.onGoing = onGoing;
    }

    public void setOpponentId(int opponentId) {
        this.opponentId = opponentId;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
