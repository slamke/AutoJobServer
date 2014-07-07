package cn.edu.sjtu.se.dslab.AutoJobMan.web.service;

import java.util.List;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.TaskInfo;

public interface ITaskService {
	public int save(TaskInfo taskInfo);
	public void update(TaskInfo taskInfo);
	public void delete(TaskInfo taskInfo);
	/*public JobInfo queryJobInfoById(int id);
	public List<JobInfo> getByItemName(String itemName);
	public List<JobInfo> getByRealName(String userName);
	public List<JobInfo> listAll();*/
	public TaskInfo getByNum(String num);
	public TaskInfo getById(int id);
	public List<TaskInfo> getByStaffId(int id);
	public List<TaskInfo> searchTaskInfos(String taskNum, String taskstaff,String itemname,String state);
	public List<TaskInfo> searchTaskInfos(String taskNum, String taskstaff,String itemname,String state,int pos,int num);
	public TaskInfo getByItemName(String name);
}
