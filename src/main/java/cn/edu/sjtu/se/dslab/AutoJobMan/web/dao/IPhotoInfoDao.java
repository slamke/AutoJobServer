package cn.edu.sjtu.se.dslab.AutoJobMan.web.dao;

import java.util.List;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.PhotoInfo;

public interface IPhotoInfoDao {
	public int save(PhotoInfo photoInfo);
	public List<PhotoInfo> queryByJobInfoId(JobInfo jobinfo);
}
