package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IBaseDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IStaffDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.Staff;

@Repository("staffDao")
public class StaffDaoImpl implements IStaffDao{
	@Resource(name = "baseDao")
	private IBaseDao<Staff> baseDao;
	
	@Override
    public Staff queryByUserName(String username) {
		List<Staff> staffList =baseDao.queryByProperty(Staff.class, "username", username);
		if(staffList.size()==0){
			return null;
		}
		if(!staffList.get(0).isDeleted()){
			return staffList.get(0);
		}
		return null;
    }
	
	@Override
	public void save(Staff staff){
		 baseDao.save(staff);
	}
	
	@Override
	public void update(Staff staff){
		baseDao.update(staff);
	}
	@Override
	public void remove(Staff staff){
		staff.setDeleted(true);
		baseDao.update(staff);
	}
	
	@Override
	public void modify(Staff staff){
		baseDao.update(staff);
	}
	
	@Override
	public List<Staff> getAll(){
		List<Staff> staffList=baseDao.queryAll(Staff.class);
		if(staffList!=null){
			List<Staff> result=new ArrayList<Staff>();
			for(Staff staff:staffList){
				if(!staff.isDeleted()){
					result.add(staff);
				}
			}
			return result;
		}
		return null;
	}
	
	@Override
	public void removeByUserName(String username,String property){
		List<Staff> staffList=baseDao.queryByProperty(Staff.class, property, username);
		if(staffList!=null){
			staffList.get(0).setDeleted(true);
			baseDao.update(staffList.get(0));
		}
	}
	
	@Override 
	public Staff getUserByUN(String username){
		List<Staff> staffList=baseDao.queryByProperty(Staff.class, "username", username);
		if(staffList.size()!=0){
			if(!staffList.get(0).isDeleted()){
				return staffList.get(0);
			}
		}
		return null;
	}

	@Override
	public Staff queryByRealName(String realname) {
		
		List<Staff> staffList =baseDao.queryByProperty(Staff.class, "realname", realname);
		if(staffList.size()!=0){
			if(!staffList.get(0).isDeleted()){
				return staffList.get(0);
			}
		}
		return null;
		
	}
}
