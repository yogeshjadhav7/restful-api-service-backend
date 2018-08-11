package web.db.models.intellecto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.avaje.ebean.*;

@Entity
@Table(name = "intellecto_users_controls")
public class Intellecto_Users_Controls extends Model{

	@Id
	@Column(name="`id`")
	private long id;
	
	@Column(name="`otp`")
	private String otp;
	
	@Column(name="`otp_used`")
	private boolean otpUsed;
	
	@Column(name="`last_otp_sent_at`")
	private long lastOtpSentAt;
	
	@Column(name="`otp_sent`")
	private int otpSent;
	
	@Column(name="`blocked_till`")
	private long blockedTill;
	
	@Column(name="`ads_flag`")
	private boolean adsFlag;
	
	
	
    
    public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getOtp() {
		return otp;
	}




	public void setOtp(String otp) {
		this.otp = otp;
	}




	public boolean isOtpUsed() {
		return otpUsed;
	}




	public void setOtpUsed(boolean otpUsed) {
		this.otpUsed = otpUsed;
	}




	public long getLastOtpSentAt() {
		return lastOtpSentAt;
	}




	public void setLastOtpSentAt(long lastOtpSentAt) {
		this.lastOtpSentAt = lastOtpSentAt;
	}




	public int getOtpSent() {
		return otpSent;
	}




	public void setOtpSent(int otpSent) {
		this.otpSent = otpSent;
	}




	public long getBlockedTill() {
		return blockedTill;
	}




	public void setBlockedTill(long blockedTill) {
		this.blockedTill = blockedTill;
	}




	public boolean isAdsFlag() {
		return adsFlag;
	}




	public void setAdsFlag(boolean adsFlag) {
		this.adsFlag = adsFlag;
	}




	public static Finder<Long, Intellecto_Users_Controls> getFind() {
		return find;
	}




	public static void setFind(Finder<Long, Intellecto_Users_Controls> find) {
		Intellecto_Users_Controls.find = find;
	}




	public static Finder<Long, Intellecto_Users_Controls> find = new Finder<Long, Intellecto_Users_Controls>(Intellecto_Users_Controls.class);
    
}