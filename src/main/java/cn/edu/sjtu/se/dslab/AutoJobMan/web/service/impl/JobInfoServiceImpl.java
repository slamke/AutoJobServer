package cn.edu.sjtu.se.dslab.AutoJobMan.web.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IJobInfoDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IStaffDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.impl.JobInfoDaoImpl;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.Staff;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IJobInfoService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.Query;

@Service("jobInfoService")
@Transactional
public class JobInfoServiceImpl implements IJobInfoService{
	@Resource(name="jobinfodao")
	IJobInfoDao jobInfoDao;
	@Resource(name="staffDao")
	IStaffDao staffDao;
	@Override
	public int saveJobInfo(JobInfo jobinfo){
		return jobInfoDao.save(jobinfo);
	}
	
	@Override
	public JobInfo queryJobInfoById(int id){
		return jobInfoDao.queryById(id);
	}
	
	@Override
	public List<JobInfo> getByItemName(String itemName){
		return jobInfoDao.queryByItemName(itemName);
	}
	
	@Override
	public JobInfo getByItemNameAcc(String itemName){
		return jobInfoDao.queryByItemNameAcc(itemName);
	}
	
	
	@Override
	public List<JobInfo> getByRealName(String realName){
		Staff staff=staffDao.queryByRealName(realName);
		if(staff==null){
			return null;
		}
		List<JobInfo> jobInfoList=new ArrayList<JobInfo>();
		jobInfoList.addAll(staff.getJobInfos());
		return jobInfoList;
	}
	
	@Override
	public List<JobInfo> listAll(){
		return jobInfoDao.queryAll();
	}

	@Override
	public void updateJobInfo(JobInfo jobinfo) {
		// TODO Auto-generated method stub
		jobInfoDao.update(jobinfo);
	}
	
	@Override
	public List<JobInfo> searchJobInfos(Query query){
		return jobInfoDao.queryByMultiCond(query);
	}
	@Override
	public JobInfo queryByINameDate(String itemName,String date) throws ParseException{
		if(!jobInfoDao.queryByINameDate(itemName, date).isEmpty()){
			return jobInfoDao.queryByINameDate(itemName, date).get(0);
		}
		return null;
	}
	
}
