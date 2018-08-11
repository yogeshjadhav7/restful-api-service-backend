package web.db.dto.intellecto.android.wrappers;

import java.util.HashMap;
import utils.general.JsonObjectMapper;
import web.db.models.intellecto.Intellecto_Friends;

public class FriendWrapper {

    public HashMap<String, Intellecto_Friends> friendship;

    public static FriendWrapper toFriendWrapper(String friendshipString) {
        return (FriendWrapper) JsonObjectMapper.toObject(friendshipString, FriendWrapper.class);
    }

    @Override
    public String toString() {
        return JsonObjectMapper.toJsonString(this, true);
    }
}
