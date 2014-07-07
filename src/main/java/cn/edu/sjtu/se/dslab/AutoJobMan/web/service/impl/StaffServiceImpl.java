package cn.edu.sjtu.se.dslab.AutoJobMan.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IStaffDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.Staff;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IStaffService;

@Service("staffService")
@Transactional
public class StaffServiceImpl implements IStaffService{
	@Resource(name = "staffDao")
    private IStaffDao dao;
	@Override
	public void saveUser(Staff staff){
		dao.save(staff);
	}
	@Override
	public void removeUser(Staff staff){
		dao.remove(staff);
	}
	
	@Override
	public void changeInfo(Staff staff){
		dao.update(staff);
	}
	
	@Override
	public Staff getUserByUN(String username){
		return dao.getUserByUN(username);
	}
	
	@Override
	public void removeUserByUserName(String username){
		dao.removeByUserName(username, "username");
	}
	@Override
	public void modifyUser(Staff staff){
		dao.modify(staff);
	}
	@Override
	public List<Staff> getUserList(){
		return dao.getAll();
	}
	
	@Override
	public Staff getByRealName(String realname){
		return dao.queryByRealName(realname);
	}
	
	public Staff checklogin(String username , String password) {
		
		Staff staff=dao.queryByUserName(username);
		if(staff==null){
			return null;
		}
		if(staff.getPassword().equals(password)){
			return staff;
		}
		else {
			return null;
		}
		
	}
}
