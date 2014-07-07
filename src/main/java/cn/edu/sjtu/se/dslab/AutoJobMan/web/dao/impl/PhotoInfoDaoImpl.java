package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IBaseDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IPhotoInfoDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.PhotoInfo;

@Repository("photoInfoDao")
public class PhotoInfoDaoImpl implements IPhotoInfoDao {
	@Resource(name="baseDao")
	IBaseDao<PhotoInfo> baseDao ;
	
	@Override
	public int save(PhotoInfo photoInfo){
		baseDao.save(photoInfo);
		return photoInfo.getId();
	}
	
	@Override
	public List<PhotoInfo> queryByJobInfoId(JobInfo jobinfo){
		//String sql="select s from PhotoInfo as s where s.staff.id="+staffId;
		return baseDao.queryByProperty(PhotoInfo.class, "info", jobinfo.getId());
	}
	
}
