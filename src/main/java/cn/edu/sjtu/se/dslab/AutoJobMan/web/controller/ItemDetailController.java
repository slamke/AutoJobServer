package cn.edu.sjtu.se.dslab.AutoJobMan.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.PhotoInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IJobInfoService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IPhotoInfoService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.DateParse;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.TmpPhoto;

@Controller
public class ItemDetailController {
	@Resource(name="jobInfoService")
	IJobInfoService jobInfoService;
	@Resource(name="photoInfoService")
	IPhotoInfoService photoInfoService;
	
	public static final String TYPE_BRIEF = "完工照片";
	public static final String TYPE_IMPROVEMENT = "改进照片";
	public static final String TYPE_PROBLEM = "问题照片";

	public static final String TYPE_CHECK_IN = "上班签到拍照片";
	public static final String TYPE_BEFORE = "开工前照片";

	
	@RequestMapping(value="/itemDetail")
	public ModelAndView show(@RequestParam(value="location") String itemName,@RequestParam(value="jobinfoid" )int jobInfoId){
		ModelAndView mav = new ModelAndView();
        mav.setViewName("itemDetail");
        JobInfo jobInfo=jobInfoService.getByItemName(itemName).get(0);
        mav.addObject("jobInfo",jobInfo);
        mav.addObject("photoInfoList",jobInfo.getInfos());
        return mav;
	}
	
	
	
	@RequestMapping(value="/beforeWork")
	public ModelAndView showBeforeWork(@RequestParam(value="siteName") String siteName,@RequestParam(value="userName") String userName,
			@RequestParam(value="date") String date,@RequestParam(value="type") String type,@RequestParam(value="id") int id){
		ModelAndView mav = new ModelAndView();
        mav.setViewName("beforeWork");
        mav.addObject("type",type);
        
        JobInfo jobInfo=jobInfoService.queryJobInfoById(id);
        
        if(jobInfo!=null){
        	DateParse parse = new DateParse();
            mav.addObject("date",parse.date2String(jobInfo.getStartTime().getTime()));
            mav.addObject("userName",jobInfo.getStaff().getUsername());
            mav.addObject("siteName",jobInfo.getSite().getSitename());
        	Set<PhotoInfo> photoList=jobInfo.getInfos();
            List<TmpPhoto> photoPathList=new ArrayList<TmpPhoto>();
            for(PhotoInfo photo:photoList){
            	if(photo.getType().trim().equals(TYPE_CHECK_IN)){
            		mav.addObject("photoBeforeWork",photo.getPath());
            		mav.addObject("photoBeforeWorkDes",photo.getDescription());
            	}
            	if(photo.getType().equals(TYPE_BEFORE)){
            		photoPathList.add(new TmpPhoto(photo.getPath(), photo.getDescription()));
            	}
            }
            
            if(photoPathList!=null){
            	mav.addObject("photoPathList",photoPathList);
            }
        }
        return mav;
	}
	
	@RequestMapping(value="/afterWork")
	public ModelAndView showAfterWork(@RequestParam(value="siteName") String siteName,@RequestParam(value="userName") String userName,
			@RequestParam(value="date") String date,@RequestParam(value="type") String type,@RequestParam(value="id") int id){
		ModelAndView mav = new ModelAndView();
        mav.setViewName("afterWork");
        mav.addObject("siteName",siteName);
        mav.addObject("userName",userName);
        mav.addObject("type",type);
        JobInfo jobInfo=jobInfoService.queryJobInfoById(id);
        if(jobInfo!=null){
        	mav.addObject("userName",jobInfo.getStaff().getUsername());
            mav.addObject("siteName",jobInfo.getSite().getSitename());
        	mav.addObject("brief",jobInfo.getBrief());
            mav.addObject("improvement",jobInfo.getImprovement());
            mav.addObject("problem",jobInfo.getProblem());
            List<TmpPhoto> improveList=new ArrayList<TmpPhoto>();
            List<TmpPhoto> briefList=new ArrayList<TmpPhoto>();
            List<TmpPhoto> problemList=new ArrayList<TmpPhoto>();
            Set<PhotoInfo> photos=jobInfo.getInfos();
            for(PhotoInfo photo:photos){
            	if(photo.getType().equals(TYPE_BRIEF)){
            		briefList.add(new TmpPhoto(photo.getPath(), photo.getDescription()));
            	}
            	if(photo.getType().equals(TYPE_IMPROVEMENT)){
            		improveList.add(new TmpPhoto(photo.getPath(), photo.getDescription()));
            	}
            	if(photo.getType().equals(TYPE_PROBLEM)){
            		problemList.add(new TmpPhoto(photo.getPath(), photo.getDescription()));
            	}
            }
            if (date != null && !date.equals("")) {
            	mav.addObject("date",date);
			}else {
				SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
				mav.addObject("date",format.format(jobInfo.getEndTime().getTime()).subSequence(0, 11).toString());
			}
            mav.addObject("improveList",improveList);
            mav.addObject("briefList",briefList);
            mav.addObject("problemList",problemList);
        }
        return mav;
	}
	
	@RequestMapping(value="/mobile/before/{id}")
	public ModelAndView checkMobileBefore(@PathVariable(value="id")int id){
		ModelAndView mav=new ModelAndView();
		JobInfo job=jobInfoService.queryJobInfoById(id);
		mav.setViewName("mobile_before");
		if(job!=null){
			mav.addObject("siteName",job.getSite().getSitename());
			mav.addObject("userName",job.getStaff().getUsername());
			if (job.getStartTime() != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
				mav.addObject("date",format.format(job.getStartTime().getTime()).subSequence(0, 11).toString());
			}else {
				mav.addObject("date","");
			}
			Set<PhotoInfo> photos=job.getInfos();
			List<String> photoList=new ArrayList<String>();
			List<String> photoDesList=new ArrayList<String>();
			for(PhotoInfo photo: photos){
				if(photo.getType().equals(TYPE_CHECK_IN)){
					mav.addObject("photoBeforeWork",photo.getPath());
					mav.addObject("photoBeforeWorkDes",photo.getDescription());
				}else if(photo.getType().equals(TYPE_BEFORE)){
					photoList.add(photo.getPath());
					photoDesList.add(photo.getDescription());
				}
			}
			if(photoList.size()!=0){
				mav.addObject("photoList",photoList);
				mav.addObject("photoDesList",photoDesList);
			}
		}
		return mav;
	}
	
	@RequestMapping(value="/mobile/after/{id}")
	public ModelAndView checkMobileAfter(@PathVariable(value="id")int id){
		ModelAndView mav=new ModelAndView();
		JobInfo job=jobInfoService.queryJobInfoById(id);
		mav.setViewName("mobile_after");
		if(job!=null){
			mav.addObject("siteName",job.getSite().getSitename());
			mav.addObject("userName",job.getStaff().getUsername());
			if (job.getEndTime() != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
				mav.addObject("date",format.format(job.getEndTime().getTime()).subSequence(0, 11).toString());
			}else {
				mav.addObject("date","");
			}
			mav.addObject("briefInfo",job.getBrief());
			mav.addObject("improveInfo",job.getImprovement());
			mav.addObject("problemInfo",job.getProblem());
			Set<PhotoInfo> photos = job.getInfos();
			List<String> improveList = new ArrayList<String>();
			List<String> briefList = new ArrayList<String>();
			List<String> problemList = new ArrayList<String>();
			List<String> improveDesList = new ArrayList<String>();
			List<String> briefDesList = new ArrayList<String>();
			List<String> problemDesList = new ArrayList<String>();
			if(photos!=null){
				for(PhotoInfo photo: photos){
					if(photo.getType().equals(TYPE_BRIEF)){
		        		briefList.add(photo.getPath());
		        		briefDesList.add(photo.getDescription());
		        	}
		        	if(photo.getType().equals(TYPE_IMPROVEMENT)){
		        		improveList.add(photo.getPath());
		        		improveDesList.add(photo.getDescription());
		        	}
		        	if(photo.getType().equals(TYPE_PROBLEM)){
		        		problemList.add(photo.getPath());
		        		problemDesList.add(photo.getDescription());
		        	}
				}
			}
			if(briefList.size()!=0){
				mav.addObject("briefList",briefList);
				mav.addObject("briefDesList",briefDesList);
			}
			if(improveList.size()!=0){
				mav.addObject("improveList",improveList);
				mav.addObject("improveDesList",improveDesList);
			}
			if(problemList.size()!=0){
				mav.addObject("problemList",problemList);
				mav.addObject("problemDesList",problemDesList);
			}
		}
		return mav;
	}
}
