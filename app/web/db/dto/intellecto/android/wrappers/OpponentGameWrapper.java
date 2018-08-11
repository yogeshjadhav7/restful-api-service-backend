package web.db.dto.intellecto.android.wrappers;


import java.util.List;

import utils.general.JsonObjectMapper;
import web.db.dto.intellecto.android.models.Opponent_Game;

public class OpponentGameWrapper {

    private List<Opponent_Game> opponent_games;
    public OpponentGameWrapper() {}

    public OpponentGameWrapper(final List<Opponent_Game> opponent_games) {
        for(Opponent_Game opponent_game : opponent_games) {
            opponent_game.setPreGameDialogue(null);
            opponent_game.setPostGameDialogueWin(null);
            opponent_game.setPostGameDialogueLose(null);
        }
        this.opponent_games = opponent_games;
    }

    public List<Opponent_Game> getOpponent_games() {
        return opponent_games;
    }

    public void setOpponent_games(List<Opponent_Game> opponent_games) {
        this.opponent_games = opponent_games;
    }

    @Override
    public String toString() {
        return JsonObjectMapper.toJsonString(this, true);
    }
}
