package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.impl;

import static org.hibernate.criterion.Restrictions.eq;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IBaseDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IDeclarationInfoDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.DeclarationInfo;

@Repository("declarationInfoDao")
public class DeclarationInfoDaoImpl implements IDeclarationInfoDao {
	@Resource(name="baseDao")
	IBaseDao<DeclarationInfo> baseDao;
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
	public int save(DeclarationInfo dInfo) {
		// TODO Auto-generated method stub
		baseDao.save(dInfo);
		return dInfo.getId();
	}

	@Override
	public void update(DeclarationInfo dInfo) {
		// TODO Auto-generated method stub
		baseDao.update(dInfo);
	}

	@Override
	public DeclarationInfo getById(int id) {
		// TODO Auto-generated method stub
		return baseDao.queryById(DeclarationInfo.class, id);
	}
	@Override
	public List<DeclarationInfo> getDone(){
		Criteria criteria = getSession().createCriteria(DeclarationInfo.class);
		criteria.add(Restrictions.eq("state", true));
		return criteria.list();
	}
	@Override
	public List<DeclarationInfo> getDonePage(int page,int num){
		return getSession().createCriteria(DeclarationInfo.class).setFirstResult(page*num).setMaxResults(num)
		        .add(Restrictions.eq("state", true)).list();
	}
	@Override
	public List<DeclarationInfo> getUnDone(){
		Criteria criteria = getSession().createCriteria(DeclarationInfo.class);
		criteria.add(Restrictions.eq("state", false)).add(Restrictions.eq("cancel",false));
		return criteria.list();
	}
	
	@Override
	public List<DeclarationInfo> getUnDonePage(int page,int num){
		return getSession().createCriteria(DeclarationInfo.class).setFirstResult(page*num).setMaxResults(num)
        .add(Restrictions.eq("state", false)).add(Restrictions.eq("cancel",false)).list();
	}
}
