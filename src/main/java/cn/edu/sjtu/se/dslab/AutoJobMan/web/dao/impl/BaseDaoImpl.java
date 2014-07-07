package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.impl;

import static org.hibernate.criterion.Restrictions.eq;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sun.xml.bind.unmarshaller.InfosetScanner;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IBaseDao;
@Repository("baseDao")
public class BaseDaoImpl<T extends Serializable> implements IBaseDao<T> {
	// constructor
    public BaseDaoImpl() {
    }

    // Properties
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    // getters and setters
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    // operations
    // create
    @Override
    public void save(T t) {
        getSession().save(t);
    }
    
   

    // update
    public void update(T t) {
        getSession().update(t);
    }

    // delete
    public void deleteById(Class<T> clazz, int id) {
        getSession().delete(queryById(clazz, id));
    }
    
    public void deleteByProperty(Class<T> clazz,String property,String value){
    	if(queryByProperty(clazz, property, value).size()!=0){
    		getSession().delete(queryByProperty(clazz, property, value).get(0));
    	}
    }
    
    public long rowCount(Class<T> clazz) {
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.setProjection(Projections.rowCount());
        List list = criteria.list();
        long count = (Long)list.get(0);
        return count;
    }
    
    @SuppressWarnings("unchecked")
    public T queryById(Class<T> clazz, int id) {
        return (T) getSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> queryByProperty(Class<T> clazz, String property, String value) {
        return (List<T>) getSession().createCriteria(clazz)
                .add(eq(property, value)).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<T> queryByProperty(Class<T> clazz, String property, Integer value) {
        return (List<T>) getSession().createCriteria(clazz)
                .add(eq(property, value)).list();
    }
    
    @SuppressWarnings("unchecked")
    public List<T> queryByProperty(Class<T> clazz, String property, boolean value) {
        return (List<T>) getSession().createCriteria(clazz)
                .add(eq(property, value)).list();
    }
    @SuppressWarnings("unchecked")
    public List<T> queryByPropertyPage(Class<T> clazz, String property, boolean value,int page,int num) {
        return (List<T>) getSession().createCriteria(clazz).setFirstResult(page*num).setMaxResults(num)
                .add(eq(property, value)).list();
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public List<T> fuzzyQueryByProperty(Class<T> clazz, String property,
			String value) {
		Criterion criterion1 = Restrictions.like(property, "%"+value.trim()+"%");
		// TODO Auto-generated method stub
		return (List<T>) getSession().createCriteria(clazz)
                .add(criterion1).list();
	}

    @SuppressWarnings("unchecked")
    public List<T> queryAll(Class<T> clazz) {
        return (List<T>) getSession().createCriteria(clazz).list();
    }

    public T queryObjectBySql(Class<T> clazz, String sql) {
        @SuppressWarnings("unchecked")
		T object = (T) getSession().createSQLQuery(sql).addEntity(clazz)
                .uniqueResult();

        return object;
    }

    @SuppressWarnings("unchecked")
	public List<T> queryAllBySql(Class<T> clazz, String sql) {
        return (List<T>) getSession().createSQLQuery(sql).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> queryByPage(int pageNum, int pageSize, Class<T> clazz) {
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.setFirstResult( (pageNum-1) * pageSize);
        criteria.setMaxResults(pageSize);
        
        return (List<T>)criteria.list();
    }

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		getSession().delete(t);
	}

	
}
