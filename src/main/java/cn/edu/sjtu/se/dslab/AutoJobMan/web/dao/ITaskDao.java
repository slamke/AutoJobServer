package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao;

import java.util.List;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.TaskInfo;

public interface ITaskDao {
	public int save(TaskInfo taskInfo);
	public void update(TaskInfo taskInfo);
	public void delete(TaskInfo taskInfo);
	//public TaskInfo queryById(int id);
	/*public List<JobInfo> queryByItemName(String itemName);
	public List<JobInfo> queryByStaffId(int id);
	public List<JobInfo> queryAll();*/
	public TaskInfo getByNum(String num);
	public TaskInfo getById(int id);
	public List<TaskInfo> getAll();
	public List<TaskInfo> getByStaffId(int id);
	public List<TaskInfo> queryByMultiCond(String taskNum, String taskstaff,String itemname,String state);
	public List<TaskInfo> queryByMultiCond(String taskNum, String taskstaff,String itemname,String state,int pos,int num);
	public List<TaskInfo> getByItemName(String name);
}
