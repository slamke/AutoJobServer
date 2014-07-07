package cn.edu.sjtu.se.dslab.AutoJobMan.web.service;

import java.util.List;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.WorkSite;

public interface IWorkSiteService {
	public List<WorkSite> getAll();
	public void deleteSite(WorkSite site);
	public void addSite(WorkSite site);
	public WorkSite queryByNum(String num);
	public void saveSite(WorkSite site);
	public WorkSite getByName(String name);
	public WorkSite getById(int id);
}
