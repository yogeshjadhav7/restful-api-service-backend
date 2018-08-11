package web.db.models.intellecto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.avaje.ebean.*;

@Entity
@Table(name = "intellecto_users_behaviour")
public class Intellecto_Users_Behaviour extends Model{
	
	@Id
	private Long id;
	
	@Column(name="`user_id`")
	private long userId;

	@Column(name="`game_state`")
	private String gameState;
	
	@Column(name="`user_response`")
	private int userResponse = -1;

    public Intellecto_Users_Behaviour() {}

    public Intellecto_Users_Behaviour(String gameState, long userId) {
        this.gameState = gameState;
        this.userId = userId;
    }


	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






	public long getUserId() {
		return userId;
	}






	public void setUserId(long userId) {
		this.userId = userId;
	}






	public String getGameState() {
		return gameState;
	}






	public void setGameState(String gameState) {
		this.gameState = gameState;
	}





	public int getUserResponse() {
		return userResponse;
	}






	public void setUserResponse(int userResponse) {
		this.userResponse = userResponse;
	}






	public static Finder<Long, Intellecto_Users_Behaviour> getFind() {
		return find;
	}






	public static void setFind(Finder<Long, Intellecto_Users_Behaviour> find) {
		Intellecto_Users_Behaviour.find = find;
	}






	public static Finder<Long, Intellecto_Users_Behaviour> find = new Finder<Long, Intellecto_Users_Behaviour>(Intellecto_Users_Behaviour.class);
    
}