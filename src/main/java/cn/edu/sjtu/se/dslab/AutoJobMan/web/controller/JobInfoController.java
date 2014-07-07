package cn.edu.sjtu.se.dslab.AutoJobMan.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.TaskInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.WorkSite;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IJobInfoService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.ITaskService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IWorkSiteService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.DateParse;

@Controller
@RequestMapping("/jobInfo")
public class JobInfoController {
	@Resource(name="jobInfoService")
	IJobInfoService jobInfoService;
	@Resource(name="taskService")
	ITaskService taskService;
	@Resource(name="workSiteService")
	IWorkSiteService workSiteService;
	
	@RequestMapping(value = "/realSituation",method = RequestMethod.GET)
	public ModelAndView showRealSituation() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("realSituation");
		//WorkSiteController wk=new WorkSiteController();
		List<Map<String, Object>> infoList=new ArrayList<Map<String, Object>>();
		List<String> allSiteName=new ArrayList<String>();
		List<WorkSite> workSites=workSiteService.getAll();
		mav.addObject("wksize",workSites.size());
        if(workSites!=null){
        	List<String> workSiteNames=new ArrayList<String>();
            for(WorkSite workSite: workSites){
            	allSiteName.add(workSite.getSitename());
            }
        }
		for(String siteName:allSiteName){
			Map<String, Object> map=new HashMap<String, Object>();
			
			TaskInfo task=taskService.getByItemName(siteName);
			DateParse dateParse=new DateParse();
			try {
				if(task==null){
					map.put("siteName", siteName);
					map.put("taskerror","工作尚未开始");
					infoList.add(map);
					continue;
				}
				map.put("siteName", siteName);
				int siteTime=dateParse.daysBetween(task.getStartTime(), task.getDeadline());
				map.put("siteTime", siteTime);
				WorkSite site=workSiteService.getByName(siteName);
				int doDay=site.getJoinfos().size();
				map.put("doDay", doDay);
				if(jobInfoService.queryByINameDate(siteName,"")!=null){
					String progress=jobInfoService.queryByINameDate(siteName,"").getProgress()+"%";
					map.put("progress", progress);
				}
				JobInfo jobInfo=jobInfoService.queryByINameDate(siteName, null);
				if(jobInfo==null){
					mav.addObject("JobInfoError","工作尚未开始");
					map.put("jobInfo", new JobInfo());
				}
				map.put("jobInfo", jobInfo);
				infoList.add(map);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		mav.addObject("infoList",infoList);
		return mav;
	}
	
	
	@RequestMapping(value = "/searchRealSituation",method = RequestMethod.GET)
	public ModelAndView toSearchRealSituation() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("searchRealSituation");
		return mav;
	}
	@RequestMapping(value = "/searchSituation",method = RequestMethod.POST)
	public ModelAndView searchSituation(@RequestParam(value="itemname")String itemname,@RequestParam(value="date")String date){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("searchRealSituation");
		TaskInfo task=taskService.getByItemName(itemname);
		if(task==null){
			mav.addObject("error", "项目工期不存在");
			return mav;
		}
		try {
			DateParse dateParse=new DateParse();
			int siteTime=dateParse.daysBetween(task.getStartTime(), task.getDeadline());
			mav.addObject("siteTime", siteTime);
			WorkSite site=workSiteService.getByName(itemname);
			int doDay=site.getJoinfos().size();
			mav.addObject("doDay", doDay);
			if(jobInfoService.queryByINameDate(itemname,"")!=null){
				String progress=jobInfoService.queryByINameDate(itemname,"").getProgress()+"%";
				mav.addObject("progress", progress);
			}
			JobInfo jobInfo=jobInfoService.queryByINameDate(itemname, date);
			mav.addObject("jobInfo", jobInfo);
			//mav.addObject(attributeName, attributeValue)
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mav;
	}
}
