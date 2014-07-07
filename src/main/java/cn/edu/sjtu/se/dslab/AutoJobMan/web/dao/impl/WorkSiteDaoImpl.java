package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IBaseDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IWorkSiteDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.WorkSite;

@Repository("workSiteDao")
public class WorkSiteDaoImpl implements IWorkSiteDao{
	@Resource(name="baseDao")
	IBaseDao<WorkSite> baseDao;
	
	@Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
	        return sessionFactory.getCurrentSession();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkSite> queryAll(){
		Criteria criteria = getSession().createCriteria(WorkSite.class);
		criteria.add(Restrictions.eq("deleted", false));
		return criteria.list();
	}
	
	@Override 
	public void save(WorkSite site){
		baseDao.save(site);
		
	}
	
	@Override
	public void update(WorkSite site){
		 baseDao.update(site);
	}
	@Override
	public void delete(String num){
		//baseDao.update(site);
	}
	
	@Override
	public WorkSite queryById(int id){
		return baseDao.queryById(WorkSite.class, id);
	}
	@Override
	public WorkSite queryByNum(String num){
		//if(num.equals(""))
		List<WorkSite> siteList=baseDao.queryByProperty(WorkSite.class, "num", num);
		if(siteList.size()!=0){
			return siteList.get(0);
		}
		return null;
	}
	
	@Override
	public WorkSite getByName(String name){
		List<WorkSite> siteList=baseDao.queryByProperty(WorkSite.class, "sitename", name);
		if(siteList.size()!=0){
			return siteList.get(0);
		}
		return null;
	}
}
