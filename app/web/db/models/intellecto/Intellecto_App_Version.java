package web.db.models.intellecto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.avaje.ebean.*;

@Entity
@Table(name = "intellecto_app_version")
public class Intellecto_App_Version extends Model{
	
	@Id
	private String version;
	
	@Column(name="`mandatory`")
	private int mandatory;
	
	

	public String getVersion() {
		return version;
	}





	public void setVersion(String version) {
		this.version = version;
	}





	public int getMandatory() {
		return mandatory;
	}





	public void setMandatory(int mandatory) {
		this.mandatory = mandatory;
	}





	public static Finder<Long, Intellecto_App_Version> getFind() {
		return find;
	}





	public static void setFind(Finder<Long, Intellecto_App_Version> find) {
		Intellecto_App_Version.find = find;
	}





	public static Finder<Long, Intellecto_App_Version> find = new Finder<Long, Intellecto_App_Version>(Intellecto_App_Version.class);
    
}