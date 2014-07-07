package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao;

import java.text.ParseException;
import java.util.List;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.Query;

public interface IJobInfoDao {
	public int save(JobInfo jobinfo);
	public void update(JobInfo info);
	public JobInfo queryById(int id);
	public List<JobInfo> queryByItemName(String itemName);
	public JobInfo queryByItemNameAcc(String itemName);
	public List<JobInfo> queryByStaffId(int id);
	public List<JobInfo> queryAll();
	public List<JobInfo> queryByMultiCond(Query query);
	public List<JobInfo> queryByINameDate(String itemName,String date) throws ParseException;
}
