package web.db.models.intellecto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.avaje.ebean.*;

@Entity
@Table(name = "intellecto_friends")
public class Intellecto_Friends extends Model{
	
	@Id
	private long id;
	
	@Column(name="`user_id`")
	private long userId;

	@Column(name="`friend_id`")
	private long friendId;
	
	@Column(name="`user_winning_efficiency`")
	private double userWinningEfficiency;
	
	@Column(name="`friend_winning_efficiency`")
	private double friendWinningEfficiency;
	
	@Column(name="`user_winning_count`")
	private long userWinningCount;
	
	@Column(name="`friend_winning_count`")
	private long friendWinningCount;
	
	@Column(name="`last_game_time`")
    private long lastGameTime;
	

	
    public long getId() {
		return id;
	}





	public void setId(long id) {
		this.id = id;
	}





	public long getUserId() {
		return userId;
	}





	public void setUserId(long userId) {
		this.userId = userId;
	}





	public long getFriendId() {
		return friendId;
	}





	public void setFriendId(long friendId) {
		this.friendId = friendId;
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





	public static Finder<Long, Intellecto_Friends> getFind() {
		return find;
	}





	public static void setFind(Finder<Long, Intellecto_Friends> find) {
		Intellecto_Friends.find = find;
	}





	public static Finder<Long, Intellecto_Friends> find = new Finder<Long, Intellecto_Friends>(Intellecto_Friends.class);
    
}