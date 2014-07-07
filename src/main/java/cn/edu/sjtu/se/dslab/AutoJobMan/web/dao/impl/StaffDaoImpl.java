package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.impl;

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
		return staffList.get(0);
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
		baseDao.delete(staff);
	}
	
	@Override
	public void modify(Staff staff){
		baseDao.update(staff);
	}
	
	@Override
	public List<Staff> getAll(){
		return baseDao.queryAll(Staff.class);
	}
	
	@Override
	public void removeByUserName(String username,String property){
		baseDao.deleteByProperty(Staff.class, property, username);
	}
	
	@Override 
	public Staff getUserByUN(String username){
		if(baseDao.queryByProperty(Staff.class, "username", username).size()!=0){
			return baseDao.queryByProperty(Staff.class, "username", username).get(0);
		}
		return null;
	}

	@Override
	public Staff queryByRealName(String realname) {
		
		List<Staff> staffList =baseDao.queryByProperty(Staff.class, "realname", realname);
		if(staffList.size()==0){
			System.out.print("name=="+realname+"\n");
			return null;
		}
		return staffList.get(0);
		
	}
}
