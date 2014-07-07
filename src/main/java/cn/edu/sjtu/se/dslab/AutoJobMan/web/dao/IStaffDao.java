package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao;

import java.util.List;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.Staff;

public interface IStaffDao {
	public Staff queryByUserName(String username);
	public Staff queryByRealName(String realname);
	
	public void save(Staff staff);
	public void remove(Staff staff);
	public void update(Staff staff);
	public void removeByUserName(String username,String property);
	public void modify(Staff staff);
	public List<Staff> getAll();
	public Staff getUserByUN(String username);
	
}
