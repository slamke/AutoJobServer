package cn.edu.sjtu.se.dslab.AutoJobMan.web.util;


public class Query {
	public Query(String siteName,String userName,String startTime, String endTime,String itemNum){
		this.siteName=siteName;
		this.userName=userName;
		this.startTime=startTime;
		this.endTime=endTime;
		this.itemNum=itemNum;
	}
	public Query(){}
	private String  siteName;
	private String userName;
	private String   startTime;
	private String endTime;
	private String itemNum;
	public String getItemNum() {
		return itemNum;
	}
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
