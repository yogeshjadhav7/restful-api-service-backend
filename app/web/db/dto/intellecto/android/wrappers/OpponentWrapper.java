package web.db.dto.intellecto.android.wrappers;


import java.util.List;

import utils.general.JsonObjectMapper;
import web.db.dto.intellecto.android.models.Opponent;

public class OpponentWrapper {

    private List<Opponent> opponents;
    public OpponentWrapper() {}

    public OpponentWrapper(final List<Opponent> opponents) {
        for(Opponent opponent : opponents) {
            opponent.setDescription(null);
        }
        this.opponents = opponents;
    }

    public List<Opponent> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<Opponent> opponents) {
        this.opponents = opponents;
    }

    @Override
    public String toString() {
        return JsonObjectMapper.toJsonString(this, true);
    }
}
