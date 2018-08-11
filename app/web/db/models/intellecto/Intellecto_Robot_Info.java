package web.db.models.intellecto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.avaje.ebean.*;

@Entity
@Table(name = "intellecto_robot_info")
public class Intellecto_Robot_Info extends Model{
	
	@Id
	@Column(name="`user_id`")
	private long userId;
	
	@Column(name="`behaviour_count`")
	private int behaviourCount = 0;
	
	@Column(name="`trained_on_behaviour_count`")
	private int trainedOnBehaviourCount = 0;
	
	@Column(name="`training_count`")
	private int trainingCount = 0;

	@Column(name="`is_training_in_progress`")
	private int isTrainingInProgress = 0;
	
	@Column(name="`last_training_started_on`")
	private long lastTrainingStartedOn = 0;
	
	@Column(name="`last_training_ended_on`")
	private long lastTrainingEndedOn = 0;
	
	@Column(name="`queued_on`")
	private long queuedOn = 0;
	
	@Column(name="`is_queued`")
	private int isQueued = 0;


	public long getUserId() {
		return userId;
	}





	public void setUserId(long userId) {
		this.userId = userId;
	}





	public int getBehaviourCount() {
		return behaviourCount;
	}





	public void setBehaviourCount(int behaviourCount) {
		this.behaviourCount = behaviourCount;
	}





	public int getTrainedOnBehaviourCount() {
		return trainedOnBehaviourCount;
	}





	public void setTrainedOnBehaviourCount(int trainedOnBehaviourCount) {
		this.trainedOnBehaviourCount = trainedOnBehaviourCount;
	}





	public int getTrainingCount() {
		return trainingCount;
	}





	public void setTrainingCount(int trainingCount) {
		this.trainingCount = trainingCount;
	}

	



	public int isTrainingInProgress() {
		return isTrainingInProgress;
	}





	public void setTrainingInProgress(int isTrainingInProgress) {
		this.isTrainingInProgress = isTrainingInProgress;
	}





	public long getLastTrainingStartedOn() {
		return lastTrainingStartedOn;
	}





	public void setLastTrainingStartedOn(long lastTrainingStartedOn) {
		this.lastTrainingStartedOn = lastTrainingStartedOn;
	}





	public long getLastTrainingEndedOn() {
		return lastTrainingEndedOn;
	}





	public void setLastTrainingEndedOn(long lastTrainingEndedOn) {
		this.lastTrainingEndedOn = lastTrainingEndedOn;
	}


	
	



	public int getIsTrainingInProgress() {
		return isTrainingInProgress;
	}





	public void setIsTrainingInProgress(int isTrainingInProgress) {
		this.isTrainingInProgress = isTrainingInProgress;
	}





	public long getQueuedOn() {
		return queuedOn;
	}





	public void setQueuedOn(long queuedOn) {
		this.queuedOn = queuedOn;
	}





	public int getIsQueued() {
		return isQueued;
	}





	public void setIsQueued(int isQueued) {
		this.isQueued = isQueued;
	}





	public static Finder<Long, Intellecto_Robot_Info> getFind() {
		return find;
	}





	public static void setFind(Finder<Long, Intellecto_Robot_Info> find) {
		Intellecto_Robot_Info.find = find;
	}





	public static Finder<Long, Intellecto_Robot_Info> find = new Finder<Long, Intellecto_Robot_Info>(Intellecto_Robot_Info.class);
    
}