package cn.edu.sjtu.se.dslab.AutoJobMan.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.dao.IPhotoInfoDao;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.PhotoInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IPhotoInfoService;

@Service("photoInfoService")
@Transactional
public class PhotoInfoServiceImpl implements IPhotoInfoService{
	
	@Resource(name="photoInfoDao")
	IPhotoInfoDao photodaoDao;
	@Override
	public int savePhotoInfo(PhotoInfo photoinfo){
		return photodaoDao.save(photoinfo);
	}
	@Override
	public List<PhotoInfo> queryByJobInfoId(JobInfo jobInfo) {
		return photodaoDao.queryByJobInfoId(jobInfo);
	}
}
