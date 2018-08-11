package web.db.models.intellecto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.avaje.ebean.*;

@Entity
@Table(name = "intellecto_users_game_info")
public class Intellecto_Users_Game_Info extends Model{
	
	@Id
	@Column(name="`id`")
	private long id;
	
	@Column(name="`user_info`")
	private String userInfo;
	
	@Column(name="`opponent_info`")
	private String opponentInfo;
	
	@Column(name="`opponent_game_info`")
	private String opponentGameInfo;
	
	@Column(name="`game_info`")
	private String gameInfo;
	
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getOpponentInfo() {
		return opponentInfo;
	}

	public void setOpponentInfo(String opponentInfo) {
		this.opponentInfo = opponentInfo;
	}

	public String getOpponentGameInfo() {
		return opponentGameInfo;
	}

	public void setOpponentGameInfo(String opponentGameInfo) {
		this.opponentGameInfo = opponentGameInfo;
	}

	public String getGameInfo() {
		return gameInfo;
	}

	public void setGameInfo(String gameInfo) {
		this.gameInfo = gameInfo;
	}

	public static Finder<Long, Intellecto_Users_Game_Info> getFind() {
		return find;
	}



	public static void setFind(Finder<Long, Intellecto_Users_Game_Info> find) {
		Intellecto_Users_Game_Info.find = find;
	}



	public static Finder<Long, Intellecto_Users_Game_Info> find = new Finder<Long, Intellecto_Users_Game_Info>(Intellecto_Users_Game_Info.class);
    
}