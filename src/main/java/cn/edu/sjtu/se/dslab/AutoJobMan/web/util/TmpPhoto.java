package cn.edu.sjtu.se.dslab.AutoJobMan.web.util;

public class TmpPhoto {
	private String path;
	private String description;
	public TmpPhoto(String path,String des){
		this.path = path;
		this.description = des;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
