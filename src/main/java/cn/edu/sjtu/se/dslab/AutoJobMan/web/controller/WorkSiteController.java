package cn.edu.sjtu.se.dslab.AutoJobMan.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.WorkSite;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IWorkSiteService;
@Controller
public class WorkSiteController {
	@Resource(name = "workSiteService")
	private IWorkSiteService workSiteService;
	@RequestMapping(value="/getAllItemName")
	@ResponseBody
	public List<String> getAllItemName(){
        List<WorkSite> workSites=workSiteService.getAll();
        if(workSites!=null){
        	List<String> workSiteNames=new ArrayList<String>();
            for(WorkSite workSite: workSites){
            	workSiteNames.add(workSite.getSitename());
            }
            return workSiteNames;
        }
        return null;
	}
	
	@RequestMapping(value="/getAllItemNum")
	@ResponseBody
	public List<String> getAllItemNum(){
        List<WorkSite> workSites=workSiteService.getAll();
        List<String> workSiteNum=new ArrayList<String>();
        for(WorkSite workSite: workSites){
        	workSiteNum.add(workSite.getNum());
        }
        return workSiteNum;
	}
	
}
