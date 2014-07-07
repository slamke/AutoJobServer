package cn.edu.sjtu.se.dslab.AutoJobMan.web.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IWorkSiteDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.WorkSite;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IWorkSiteService;

@Service("workSiteService")
@Transactional
public class WorkSiteServiceImpl implements IWorkSiteService{
	@Resource(name = "workSiteDao")
	private IWorkSiteDao workSiteDao;
	
	@Override
	public List<WorkSite> getAll(){
		return workSiteDao.queryAll();
	}
	
	
	@Override
	public void deleteSite(WorkSite site){
		site.setDeleted(true);
		workSiteDao.update(site);
	}
	
	@Override
	public void addSite(WorkSite site){
		workSiteDao.save(site);
	}
	
	@Override
	public WorkSite queryByNum(String num){
		WorkSite site=workSiteDao.queryByNum(num);
		if(site!=null){
			return site;
		}
		return null;
	}
	
	@Override
	public void saveSite(WorkSite site){
		workSiteDao.update(site);
	}
	@Override
	public WorkSite getByName(String name){
		return workSiteDao.getByName(name);
	}
	
	@Override
	public WorkSite getById(int id){
		return workSiteDao.queryById(id);
	}
}	

