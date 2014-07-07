package cn.edu.sjtu.se.dslab.AutoJobMan.web.service;

import java.text.ParseException;
import java.util.List;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.Query;

public interface IJobInfoService {
	public int saveJobInfo(JobInfo jobinfo);
	public void updateJobInfo(JobInfo jobinfo);
	public JobInfo queryJobInfoById(int id);
	public List<JobInfo> getByItemName(String itemName);
	public JobInfo getByItemNameAcc(String itemName);
	public List<JobInfo> getByRealName(String userName);
	public List<JobInfo> listAll();
	public List<JobInfo> searchJobInfos(Query query);
	public JobInfo queryByINameDate(String itemName,String date) throws ParseException;
}
