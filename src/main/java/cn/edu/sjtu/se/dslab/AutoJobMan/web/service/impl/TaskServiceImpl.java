package cn.edu.sjtu.se.dslab.AutoJobMan.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.ITaskDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.TaskInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.ITaskService;
@Service("taskService")
@Transactional
public class TaskServiceImpl implements ITaskService{
	@Resource(name="taskDao")
	ITaskDao taskDao;
	@Override
	public int save(TaskInfo taskInfo){
		return taskDao.save(taskInfo);
	}
	@Override
	public void update(TaskInfo taskInfo){
		taskDao.update(taskInfo);
	}
	public void delete(TaskInfo taskInfo){
		taskDao.delete(taskInfo);
	}
	/*public JobInfo queryJobInfoById(int id);
	public List<JobInfo> getByItemName(String itemName);
	public List<JobInfo> getByRealName(String userName);*/
	@Override
	public TaskInfo getByNum(String num){
		return taskDao.getByNum(num);
	}
	@Override
	public List<TaskInfo> searchTaskInfos(String taskNum, String taskstaff,String itemname,String state){
		return taskDao.queryByMultiCond(taskNum, taskstaff, itemname, state);
	}
	@Override
	public List<TaskInfo> searchTaskInfos(String taskNum, String taskstaff,String itemname,String state,int pos,int num){
		return taskDao.queryByMultiCond(taskNum, taskstaff, itemname, state,pos, num);
	}
	@Override
	public List<TaskInfo> getByStaffId(int id){
		return taskDao.getByStaffId(id);
	}
	@Override
	public TaskInfo getById(int id) {
		// TODO Auto-generated method stub
		return taskDao.getById(id);
	}
	
	@Override
	public TaskInfo getByItemName(String name){
		List<TaskInfo> tasks=taskDao.getByItemName(name);
		if(!tasks.isEmpty()){
			return tasks.get(0);
		}
		return null;
	}
	
}
