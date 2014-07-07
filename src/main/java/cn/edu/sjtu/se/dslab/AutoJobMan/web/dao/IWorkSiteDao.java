package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao;

import java.util.List;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.WorkSite;

public interface IWorkSiteDao {
	public List<WorkSite> queryAll();
	public void save(WorkSite site);
	public void update(WorkSite site);
	public void delete(String num);
	public WorkSite queryByNum(String num);
	public WorkSite getByName(String name);
	public WorkSite queryById(int id);
}
