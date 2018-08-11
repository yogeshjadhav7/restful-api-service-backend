package web.db.models.led7;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.avaje.ebean.*;

@Entity
@Table(name = "led7_data")
public class Led7_Data extends Model{
	
	@Id
	@Column(name="`id`")
	private Integer id;
	
	@Column(name="`segment_state`")
	private String segmentState;
	
	@Column(name="`type`")
	private String type;
	
	@Column(name="`request_count`")
	private int requestCount;
	
	@Column(name="`probability_0`")
	private float probability0;
	
	@Column(name="`probability_1`")
	private float probability1;
	
	@Column(name="`probability_2`")
	private float probability2;
	
	@Column(name="`probability_3`")
	private float probability3;
	
	@Column(name="`probability_4`")
	private float probability4;
	
	@Column(name="`probability_5`")
	private float probability5;
	
	@Column(name="`probability_6`")
	private float probability6;
	
	@Column(name="`probability_7`")
	private float probability7;
	
	@Column(name="`probability_8`")
	private float probability8;

	@Column(name="`probability_9`")
	private float probability9;

	
	public String getSegmentState() {
		return segmentState;
	}





	public void setSegmentState(String segmentState) {
		this.segmentState = segmentState;
	}





	public int getRequestCount() {
		return requestCount;
	}





	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}





	public float getProbability0() {
		return probability0;
	}





	public void setProbability0(float probability0) {
		this.probability0 = probability0;
	}





	public float getProbability1() {
		return probability1;
	}





	public void setProbability1(float probability1) {
		this.probability1 = probability1;
	}





	public float getProbability2() {
		return probability2;
	}





	public void setProbability2(float probability2) {
		this.probability2 = probability2;
	}





	public float getProbability3() {
		return probability3;
	}





	public void setProbability3(float probability3) {
		this.probability3 = probability3;
	}





	public float getProbability4() {
		return probability4;
	}





	public void setProbability4(float probability4) {
		this.probability4 = probability4;
	}





	public float getProbability5() {
		return probability5;
	}





	public void setProbability5(float probability5) {
		this.probability5 = probability5;
	}





	public float getProbability6() {
		return probability6;
	}





	public void setProbability6(float probability6) {
		this.probability6 = probability6;
	}





	public float getProbability7() {
		return probability7;
	}





	public void setProbability7(float probability7) {
		this.probability7 = probability7;
	}





	public float getProbability8() {
		return probability8;
	}





	public void setProbability8(float probability8) {
		this.probability8 = probability8;
	}





	public float getProbability9() {
		return probability9;
	}





	public void setProbability9(float probability9) {
		this.probability9 = probability9;
	}
	
	
	
	
	
	
	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}





	public static Finder<Integer, Led7_Data> getFind() {
		return find;
	}





	public static void setFind(Finder<Integer, Led7_Data> find) {
		Led7_Data.find = find;
	}





	public void setProbabilities(final List<Float> probabilities) {
		this.probability0 = probabilities.get(0);
		this.probability1 = probabilities.get(1);
		this.probability2 = probabilities.get(2);
		this.probability3 = probabilities.get(3);
		this.probability4 = probabilities.get(4);
		this.probability5 = probabilities.get(5);
		this.probability6 = probabilities.get(6);
		this.probability7 = probabilities.get(7);
		this.probability8 = probabilities.get(8);
		this.probability9 = probabilities.get(9);
	}
	
	public List<Float> getProbabilities() {
		return Arrays.asList(new Float[] {
				this.probability0,
				this.probability1,
				this.probability2,
				this.probability3,
				this.probability4,
				this.probability5,
				this.probability6,
				this.probability7,
				this.probability8,
				this.probability9
		});
	}
	
	public List<Integer> getLabels() {
		return Arrays.asList(new Integer[] {
				0,1,2,3,4,5,6,7,8,9
		});
	}


	public static Finder<Integer, Led7_Data> find = new Finder<Integer, Led7_Data>(Led7_Data.class);


	public String getType() {
		return type;
	}





	public void setType(String type) {
		this.type = type;
	}
	
	
	public void incrementRequestCount() {
		this.requestCount += 1;
	}
	
	
    
}