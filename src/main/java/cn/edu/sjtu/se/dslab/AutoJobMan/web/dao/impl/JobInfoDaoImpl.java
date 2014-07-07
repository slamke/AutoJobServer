package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.impl;

import static org.hibernate.criterion.Restrictions.eq;

import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IBaseDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IJobInfoDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.DateParse;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.Query;

@Repository("jobinfodao")
public class JobInfoDaoImpl implements IJobInfoDao{
	@Resource(name="baseDao")
	IBaseDao<JobInfo> baseDao;
	
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
	public int save(JobInfo jobinfo){
		baseDao.save(jobinfo);
		return jobinfo.getId();
	}
	
	@Override
	public JobInfo queryById(int id){
		return baseDao.queryById(JobInfo.class, id);
	}
	
	@Override
	public List<JobInfo> queryByItemName(String itemName){
		Criteria criteria = getSession().createCriteria(JobInfo.class).createCriteria("site");
		criteria.add(Restrictions.like("sitename", "%"+itemName+"%")); 
		return baseDao.fuzzyQueryByProperty(JobInfo.class, "site.sitename", itemName);
	}
	
	@Override
	public List<JobInfo> queryByINameDate(String itemName,String date) throws ParseException{
		
		Criteria criteria = getSession().createCriteria(JobInfo.class);
		Criteria siteCriteria=criteria.createCriteria("site");
		siteCriteria.add(Restrictions.eq("sitename", itemName)); 
		if(date!=null&&date!=""){
			DateParse dateParse=new DateParse();
			criteria.add(Restrictions.ge("startTime", dateParse.s2Date(date)));
			criteria.add(Restrictions.le("startTime",dateParse.s2Date2(date+" 23:59:59")));
		}else{
			//to-do;
			criteria.addOrder(Order.desc("id"));
		}
		
			return   criteria.list();
		
	}
	
	@Override
	public JobInfo queryByItemNameAcc(String itemName){
		Criteria criteria = getSession().createCriteria(JobInfo.class).createCriteria("site");
		
		criteria.add(Restrictions.eq("sitename", itemName));
		@SuppressWarnings("unchecked")
		List<JobInfo> jobInfoList=criteria.list();
		if(jobInfoList.size()>0){
			System.out.print("size="+jobInfoList.size());
			return jobInfoList.get(0);
		}
		return null;
	}
	
	@Override
	public List<JobInfo> queryByStaffId(int id){
		return baseDao.queryByProperty(JobInfo.class, "staff.id", ""+id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobInfo> queryAll() {
		Criteria criteria = getSession().createCriteria(JobInfo.class);
		Criteria siteCriteria=criteria.createCriteria("site");
		siteCriteria.add(Restrictions.eq("deleted", false));
		return criteria.list();
	}

	@Override
	public void update(JobInfo info) {
		// TODO Auto-generated method stub
		baseDao.update(info);
	}
	@SuppressWarnings({ "null", "unchecked" })
	@Override
	public List<JobInfo> queryByMultiCond(Query query){
		Criteria criteria = getSession().createCriteria(JobInfo.class);
		Criteria siteCriteria=criteria.createCriteria("site");
		Criteria userCriteria=criteria.createCriteria("staff");
		siteCriteria.add(Restrictions.eq("deleted", false));
		if(query.getSiteName()!=null){
			query.setSiteName(query.getSiteName().trim());
			if(!query.getSiteName().equals("")){
				siteCriteria.add(Restrictions.like("sitename","%"+query.getSiteName()+"%"));
			}
		}
		if(query.getItemNum()!=null){
			query.setItemNum(query.getItemNum().trim());
			if(!query.getItemNum().equals("")){
				siteCriteria.add(Restrictions.like("num","%"+query.getItemNum()+"%"));
			}
		}
		if(query.getUserName()!=null){
			query.setUserName(query.getUserName().trim());
			if(!query.getUserName().equals("")){
				
				userCriteria.add(Restrictions.like("username","%"+query.getUserName()+"%"));
			}
		}
		DateParse dateParse=new DateParse();
		Calendar startDate=null;
		Calendar endDate=null;
		if(query.getStartTime()!=null){
			query.setStartTime(query.getStartTime().trim());
			if(!query.getStartTime().equals("")){
					try {
						startDate=dateParse.s2Date(query.getStartTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		if(query.getEndTime()!=null){
			query.setEndTime(query.getEndTime().trim());
			if(!query.getEndTime().equals("")){
				try {
					endDate=dateParse.s2Date(query.getEndTime());
					System.out.print("endTime="+endDate.getTime().toString()+"\n");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(startDate!=null){
			//System.out.print(startDate.toString()+"\n");
			criteria.add(Restrictions.ge("startTime",startDate));
		}
		if(endDate!=null){
			//System.out.print(endDate.toString()+"\n");
			criteria.add(Restrictions.le("endTime",endDate));
		}
		return criteria.list();
	}

}
