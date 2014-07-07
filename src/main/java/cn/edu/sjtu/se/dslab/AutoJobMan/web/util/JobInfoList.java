package cn.edu.sjtu.se.dslab.AutoJobMan.web.util;

import java.util.List;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;

public class JobInfoList {
	public  JobInfoList(){};
	private  static List<JobInfo> jobInfos;
	public static List<JobInfo> getJobInfos() {
		return jobInfos;
	}
	public static void setJobInfos(List<JobInfo> jobInfos) {
		JobInfoList.jobInfos = jobInfos;
	}
}
