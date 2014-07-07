package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IBaseDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.ITaskDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.TaskInfo;

@Repository("taskDao")
public class TaskDaoImpl implements ITaskDao {
	@Resource(name="baseDao")
	IBaseDao<TaskInfo> baseDao;
	
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
	@Override
	public int save(TaskInfo taskInfo){
		baseDao.save(taskInfo);
		return taskInfo.getId();
	}
	@Override
	public void update(TaskInfo taskInfo){
		baseDao.update(taskInfo);
	}
	
	@Override
	public void delete(TaskInfo taskInfo){
		baseDao.delete(taskInfo);
	}
	
	@Override
	public List<TaskInfo> getAll(){
		return baseDao.queryAll(TaskInfo.class);
	}
	//public TaskInfo queryById(int id);
	/*public List<JobInfo> queryByItemName(String itemName);
	public List<JobInfo> queryByStaffId(int id);
	public List<JobInfo> queryAll();*/
	@Override
	public TaskInfo getByNum(String num){
		if(baseDao.queryByProperty(TaskInfo.class, "num", num).size()!=0){
			return baseDao.queryByProperty(TaskInfo.class, "num", num).get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskInfo> queryByMultiCond(String taskNum, String taskstaff,String itemname,String state,int pos,int num){
		Criteria criteria = getSession().createCriteria(TaskInfo.class);
		
		Criteria workSiteCriteria=criteria.createCriteria("info");
		Criteria chgstaffCriteria=criteria.createCriteria("chargeStaff");
		if(taskNum!=null&&taskNum!=""){
			System.out.print("1\n");
			criteria.add(Restrictions.eq("num",taskNum));
		}
		if(taskstaff!=null&&taskstaff!=""){
			System.out.print("2\n");
			chgstaffCriteria.add(Restrictions.like("realname","%"+taskstaff+"%"));
		}
		if(itemname!=null&&itemname!=""){
			System.out.print("3\n");
			workSiteCriteria.add(Restrictions.like("sitename","%"+itemname+"%"));
		}
		if(state!=null&&state!=""){
			System.out.print("4\n");
			boolean tstate=false;
			if(state.equals("已完工")){
				tstate=true;
			}
			criteria.add(Restrictions.eq("state",tstate));
		}
		criteria.setFirstResult(pos*num);
		criteria.setMaxResults(num);
		criteria.add(Restrictions.eq("deleted", false));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskInfo> queryByMultiCond(String taskNum, String taskstaff,String itemname,String state){
		Criteria criteria = getSession().createCriteria(TaskInfo.class);
		Criteria workSiteCriteria=criteria.createCriteria("info");
		Criteria chgstaffCriteria=criteria.createCriteria("chargeStaff");
		if(taskNum!=null&&taskNum!=""){
			System.out.print("1\n");
			criteria.add(Restrictions.eq("num",taskNum));
		}
		if(taskstaff!=null&&taskstaff!=""){
			System.out.print("2\n");
			chgstaffCriteria.add(Restrictions.like("realname","%"+taskstaff+"%"));
		}
		if(itemname!=null&&itemname!=""){
			System.out.print("3\n");
			workSiteCriteria.add(Restrictions.like("sitename","%"+itemname+"%"));
		}
		if(state!=null&&state!=""){
			System.out.print("4\n");
			boolean tstate=false;
			if(state.equals("已完工")){
				tstate=true;
			}
			criteria.add(Restrictions.eq("state",tstate));
		}
		criteria.add(Restrictions.eq("deleted", false));
		return criteria.list();
	}
	@Override
	public List<TaskInfo> getByStaffId(int id){
		Criteria criteria = getSession().createCriteria(TaskInfo.class);
		Criteria staffCriteria=criteria.createCriteria("chargeStaff");
		staffCriteria.add(Restrictions.eq("id", id));
		return criteria.list();
	}
	@Override
	public TaskInfo getById(int id) {
		// TODO Auto-generated method stub
		if(baseDao.queryByProperty(TaskInfo.class, "id", id).size()!=0){
			return baseDao.queryByProperty(TaskInfo.class, "id", id).get(0);
		}
		return null;
	}
	@Override
	public List<TaskInfo> getByItemName(String name){
		Criteria criteria=getSession().createCriteria(TaskInfo.class);
		Criteria siteCriteria=criteria.createCriteria("info");
		siteCriteria.add(Restrictions.eq("sitename", name));
		return criteria.list();
	}
}
