package tools;

public class Result {
	// 编号
	private String staffNo;
	// 姓名
	private String staffName;
	// 应到小时数
	private double shouldHours;
	// 实到小时数
	private double actualHours;
	// 差值（实到-应到）
	private double dValue;
	// 出勤率
	private double attendRate;
	// 迟到次数
	private int lateTimes;

	public Result() {
		shouldHours = 0.0;
		actualHours = 0.0;
		dValue = 0.0;
		lateTimes = 0;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public double getShouldHours() {
		return shouldHours;
	}
	public void setShouldHours(double shouldHours) {
		this.shouldHours = shouldHours;
	}
	public double getActualHours() {
		return actualHours;
	}
	public void setActualHours(double actualHours) {
		this.actualHours = actualHours;
	}
	public double getdValue() {
		return dValue;
	}
	public void setdValue(double dValue) {
		this.dValue = dValue;
	}
	public double getAttendRate() {
		return attendRate;
	}
	public void setAttendRate(double attendRate) {
		this.attendRate = attendRate;
	}
	public int getLateTimes() {
		return lateTimes;
	}
	public void setLateTimes(int lateTimes) {
		this.lateTimes = lateTimes;
	}

}

