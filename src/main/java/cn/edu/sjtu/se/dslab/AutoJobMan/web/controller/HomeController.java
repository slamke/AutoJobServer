package cn.edu.sjtu.se.dslab.AutoJobMan.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.WorkSite;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IJobInfoService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IStaffService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IWorkSiteService;

@Controller
public class HomeController {
	@Resource(name="jobInfoService")
	IJobInfoService jobInfoService;
	@Resource(name="staffService")
	IStaffService staffService;
	@Resource(name="workSiteService")
	IWorkSiteService workSiteService;
	
	private final static String STAFF = "staff";
	private final static String DATE = "date";
	private final static String ID = "id";
	
	private final static String NUM = "num";
	private final static String SITENAME = "sitename";
	private final static String DESCR = "description";
	
	@RequestMapping(value="/home")
	public ModelAndView show(){
		ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        List<JobInfo> allJobInfos =jobInfoService.listAll();
        System.out.print("inhome\n");
        mav.addObject("itemNum",allJobInfos.size());
        return mav;
	}
	
	/*@ResponseBody
	@RequestMapping(value="/home/jobinfo")
	public Map<String, Object> showJobInfo(@RequestParam(value="pageIndex") int pageIndex,@RequestParam(value="pageSize")int pageSize){
	
        Map<String, Object> modelMap=new HashMap<String,Object>(2);
        List<Map<String, String>> data = new ArrayList<Map<String,String>>();
        
        List<JobInfo> allJobInfos =jobInfoService.listAll();
        if(allJobInfos==null){
        	return null;
        }
        List<JobInfo> jobInfoList;
        if((pageIndex+1)*pageSize<=allJobInfos.size()){
        	jobInfoList=allJobInfos.subList(pageIndex*pageSize, (pageIndex+1)*pageSize);
        }
        else {
			jobInfoList=allJobInfos.subList(pageIndex*pageSize, allJobInfos.size());
		}
        if (jobInfoList != null) {
			for (int i = 0; i < jobInfoList.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				//map.put(LOCATION,jobInfoList.get(i).getLocation());
				map.put(STAFF, jobInfoList.get(i).getStaff().getRealname());
				map.put(ID, ""+jobInfoList.get(i).getId());
				map.put(DATE,new DateParse().date2String(jobInfoList.get(i).getTime()));
				data.add(map);
			}
		}
        modelMap.put("success",true);
        modelMap.put("jobInfoList",data);
        return modelMap;
     
	}*/
	
	@RequestMapping(value="/siteInfo")
	public ModelAndView showSiteInfo(){
		ModelAndView mav = new ModelAndView();
        mav.setViewName("siteInfo");
        List<WorkSite> workSiteList=workSiteService.getAll();
        if(workSiteList!=null){
	        mav.addObject("wkSiteList",workSiteList);
	        mav.addObject("itemNum",workSiteList.size());
        }else{
        	mav.addObject("itemNum",0);
        }
        return mav;
	}
	@ResponseBody
	@RequestMapping(value="/siteInfo/pagination" , method = RequestMethod.POST)
	public Map<String, Object> showSiteInfoPage(@RequestParam(value="pageIndex") int pageIndex,@RequestParam(value="pageSize")int pageSize){
		
		Map<String, Object> modelMap=new HashMap<String,Object>(2);
	    List<Map<String, String>> data = new ArrayList<Map<String,String>>();
		List<WorkSite> workSiteList=workSiteService.getAll();
        if(workSiteList==null||workSiteList.size()==0){
        	modelMap.put("success",false);
        }else{
        	List<WorkSite> siteSubList=new ArrayList<WorkSite>();
            if((pageIndex+1)*pageSize<=workSiteList.size()){
            	siteSubList=workSiteList.subList(pageIndex*pageSize, (pageIndex+1)*pageSize);
            }
            else {
            	siteSubList=workSiteList.subList(pageIndex*pageSize, workSiteList.size());
    		}
            if (siteSubList != null) {
    			for (int i = 0; i < siteSubList.size(); i++) {
    				Map<String, String> map = new HashMap<String, String>();
    				//map.put(LOCATION,jobInfoList.get(i).getLocation());
    				map.put(NUM, siteSubList.get(i).getNum());
    				map.put(SITENAME, ""+siteSubList.get(i).getSitename());
    				map.put(DESCR,siteSubList.get(i).getDescription());
    				map.put(ID,""+siteSubList.get(i).getId());
    				data.add(map);
    			}
    		}
            modelMap.put("success",true);
            modelMap.put("siteSubList",data);
        }
        return modelMap;
	}
	

	@RequestMapping(value="/workInfo")
	public ModelAndView showWorkInfo(){
		ModelAndView mav = new ModelAndView();
        mav.setViewName("workInfo");
        return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/addWorkSite")
	public Map<String, Object> addSite(WorkSite site) {
		Map<String, Object> modelMap=new HashMap<String,Object>();
		List<WorkSite> workSiteList=workSiteService.getAll();
		if(workSiteList!=null){
			List<String> errdata=new ArrayList<String>();
			for(WorkSite wkSite : workSiteList){
				if(site.getNum().equals(wkSite.getNum())){
					errdata.add("请填写不同的工地编号");
				}
				if(site.getSitename().equals(wkSite.getSitename())){
					errdata.add("请填写不同的项目工地简称");
				}
			}
			if(errdata.size()!=0){
				modelMap.put("error", errdata);
				return modelMap;
			}
		}
		site.setDeleted(false);
		workSiteService.addSite(site);
		modelMap.put("success", true);
		return modelMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteWorkSite")
	public void deleteSite(@RequestParam(value="id") int id) {
		WorkSite site=workSiteService.getById(id);
		if(site!=null){
			workSiteService.deleteSite(site);
		}
	}
	
	@RequestMapping(value="/changeSiteInfo")
	public ModelAndView changeSiteInfo(@RequestParam(value="num") String num){
		ModelAndView mav = new ModelAndView();
		WorkSite site=workSiteService.queryByNum(num);
		if(site!=null){
			mav.addObject("num",site.getNum());
			mav.addObject("sitename",site.getSitename());
			mav.addObject("description",site.getDescription());
		}
        mav.setViewName("changeSiteInfo");
        return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/saveSiteInfo")
	public Map<String, Object> changeSite(@RequestParam(value="num") String num,@RequestParam(value="sitename") String sitename,@RequestParam(value="description") String description,@RequestParam(value="oldnum") String oldnum) {
		Map<String, Object> modelMap=new HashMap<String,Object>();
		WorkSite site=workSiteService.queryByNum(oldnum);
		if(site!=null){
			List<WorkSite> workSiteList=workSiteService.getAll();
			List<String> errdata=new ArrayList<String>();
			for(WorkSite wkSite : workSiteList){
				if(num.equals(oldnum)){
					if(!sitename.equals(site.getSitename())){
						if(wkSite.getSitename().equals(sitename)){
							errdata.add("请填写不同的项目工地简称");
						}
					}
				}else{
					if(wkSite.getNum().equals(num)){
						errdata.add("请填写不同的工地编号");
					}
					if(wkSite.getSitename().equals(sitename)){
						errdata.add("请填写不同的项目工地简称");
					}
				}
			}
			if(errdata.size()!=0){
				modelMap.put("error", errdata);
				return modelMap;
			}
			System.out.print("sitename="+sitename+"description="+description+"\n");
			site.setNum(num);
			site.setSitename(sitename);
			site.setDescription(description);
			workSiteService.saveSite(site);
			modelMap.put("success", true);
		}
		return modelMap;
	}
}
