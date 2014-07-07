package cn.edu.sjtu.se.dslab.AutoJobMan.web.service;

import java.util.List;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.PhotoInfo;

public interface IPhotoInfoService {
	public int savePhotoInfo(PhotoInfo photoinfo);
	public List<PhotoInfo> queryByJobInfoId(JobInfo jobInfo);
}
