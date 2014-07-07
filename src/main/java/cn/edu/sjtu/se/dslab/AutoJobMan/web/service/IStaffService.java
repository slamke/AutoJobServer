package cn.edu.sjtu.se.dslab.AutoJobMan.web.service;

import java.util.List;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.Staff;

public interface IStaffService {
	public Staff checklogin(String username,String password);
	public void saveUser(Staff staff);
	public void changeInfo(Staff staff);
	public void removeUser(Staff staff);
	public void removeUserByUserName(String username);
	public void modifyUser(Staff staff);
	public List<Staff> getUserList();
	public Staff getUserByUN(String username);
	public Staff getByRealName(String realname);
}
