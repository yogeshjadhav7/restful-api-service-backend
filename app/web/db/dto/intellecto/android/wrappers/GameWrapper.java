package web.db.dto.intellecto.android.wrappers;

import java.util.List;

import utils.general.JsonObjectMapper;
import web.db.dto.intellecto.android.models.Game;

public class GameWrapper {

    private List<Game> games;
    public GameWrapper() {}

    public GameWrapper(final List<Game> games) {
        for(Game game : games) {
            game.setGuide(null);
        }
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return JsonObjectMapper.toJsonString(this, true);
    }
}
