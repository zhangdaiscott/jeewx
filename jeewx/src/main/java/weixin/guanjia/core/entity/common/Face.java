package weixin.guanjia.core.entity.common;

/**
 * Face Model
 * 
 * @author SunHaiFeng
 * @date 2013-12-18
 */
public class Face implements Comparable<Face> {
	// 被检测出的每一张人脸都在Face++系统中的标识符
	private String faceId;
	// 年龄估计值
	private int ageValue;
	// 年龄估计值的正负区间
	private int ageRange;
	// 性别：Male/Female
	private String genderValue;
	// 性别分析的可信度
	private double genderConfidence;
	// 人种：Asian/White/Black
	private String raceValue;
	// 人种分析的可信度
	private double raceConfidence;
	// 微笑程度
	private double smilingValue;
	// 人脸框的中心点坐标
	private double centerX;
	private double centerY;

	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public int getAgeValue() {
		return ageValue;
	}

	public void setAgeValue(int ageValue) {
		this.ageValue = ageValue;
	}

	public int getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(int ageRange) {
		this.ageRange = ageRange;
	}

	public String getGenderValue() {
		return genderValue;
	}

	public void setGenderValue(String genderValue) {
		this.genderValue = genderValue;
	}

	public double getGenderConfidence() {
		return genderConfidence;
	}

	public void setGenderConfidence(double genderConfidence) {
		this.genderConfidence = genderConfidence;
	}

	public String getRaceValue() {
		return raceValue;
	}

	public void setRaceValue(String raceValue) {
		this.raceValue = raceValue;
	}

	public double getRaceConfidence() {
		return raceConfidence;
	}

	public void setRaceConfidence(double raceConfidence) {
		this.raceConfidence = raceConfidence;
	}

	public double getSmilingValue() {
		return smilingValue;
	}

	public void setSmilingValue(double smilingValue) {
		this.smilingValue = smilingValue;
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	// 根据人脸中心点坐标从左至右排序
	public int compareTo(Face face) {
		int result = 0;
		if (this.getCenterX() > face.getCenterX())
			result = 1;
		else
			result = -1;
		return result;
	}

}