package cn.edu.sjtu.se.dslab.AutoJobMan.web.controller;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IJobInfoService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.ClassParse;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.DateParse;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.Message;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.Query;

@Controller
public class SearchController {
	private final static String STAFFNAME = "staffName";
	private final static String DATE = "date";
	private final static String ID = "id";
	private final static String SITENAME="siteName";
	private final static String JOBNUM="jobNum";
	private final static String TIME="time";
	private final static String TYPE="type";
	
	@Resource(name="jobInfoService")
	IJobInfoService jobInfoService;
	@RequestMapping(value="/search")
	@ResponseBody
	public Map<String, Object> searchCenter(Query query, @RequestParam(value="recordType") String recordType){
		Map<String, Object> modelMap=new HashMap<String,Object>(2);
        List<JobInfo> jobInfoList=null;
		jobInfoList=jobInfoService.searchJobInfos(query);
		int num=0;
		if(jobInfoList!=null){
			if(recordType!=null){
				if(!"".equals(recordType)){
					num=jobInfoList.size();
				}else{
					num=jobInfoList.size()*2;
				}
			}
			else {
				num=jobInfoList.size()*2;
			}
		}
		System.out.print("__________________-------------------num=="+num+"\n");
		modelMap.put("itemNum", num);
		return modelMap;
	}
	
	private List<Map<String, String>>  outInfo(List<Map<String, String>> data,List<JobInfo> jobInfoList,String type) {
		if(jobInfoList!=null){
			Calendar calendar=null;
			for (int i = 0; i < jobInfoList.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				System.out.print("jobinfolis.sitename========="+jobInfoList.get(i).getSite().getSitename()+"\n");
				if(type.equals("上班签到")){
					calendar=jobInfoList.get(i).getStartTime();
				}else{
					calendar=jobInfoList.get(i).getEndTime();
				}
				String date=calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月"+calendar.get(Calendar.DATE)+"日";
				String time=calendar.get(Calendar.HOUR_OF_DAY)+"时"+calendar.get(Calendar.MINUTE)+"分"+calendar.get(Calendar.SECOND)+"秒";
				map.put(STAFFNAME, jobInfoList.get(i).getStaff().getUsername());
				map.put(ID, ""+jobInfoList.get(i).getId());
				map.put(SITENAME, jobInfoList.get(i).getSite().getSitename());
				map.put(DATE, date);
				map.put(TIME, time);
				map.put(TYPE, type);
				data.add(map);
			}
		}
		return data;
	}
	
	@RequestMapping(value="/search/pagination")
	@ResponseBody
	public Map<String, Object> searchCenterPagination(Query query, @RequestParam(value="recordType") String recordType,@RequestParam(value="pageIndex") int pageIndex,@RequestParam(value="pageSize")int pageSize){
		Map<String, Object> modelMap=new HashMap<String,Object>(2);
        List<Map<String, String>> data = new ArrayList<Map<String,String>>();
        List<Map<String, String>> subData = new ArrayList<Map<String,String>>();
        List<JobInfo> jobInfoList=null;
		jobInfoList=jobInfoService.searchJobInfos(query);
		if(jobInfoList==null){
			return null;
		}
		if (jobInfoList != null) {
			if(recordType!=null){
				if(!"".equals(recordType)){
					outInfo(data, jobInfoList, recordType);
					System.out.print("data.size="+data.size()+"\n");
				}else{
					outInfo(data, jobInfoList, "上班签到");
					System.out.print("data.size="+data.size()+"\n");
					outInfo(data, jobInfoList, "下班填单");
					System.out.print("data.size2="+data.size()+"\n");
				}
			}
			else {
				outInfo(data, jobInfoList, "上班签到");
				outInfo(data, jobInfoList, "下班填单");
			}
	    }
		 if((pageIndex+1)*pageSize<=data.size()){
	        	subData=data.subList(pageIndex*pageSize, (pageIndex+1)*pageSize);
	        }
	        else {
	        	subData=data.subList(pageIndex*pageSize, data.size());
			}
		modelMap.put("jobInfoList",subData);
		return modelMap;
	}
	
		@RequestMapping(value="/search/mobile")
	 @ResponseBody
	 public String searchMobile(@RequestParam(value="siteName") String siteName,
			 @RequestParam(value="userName")String userName,
			 @RequestParam(value="startTime")String startTime,
			 @RequestParam(value="endTime") String endTime, @RequestParam(value="recordType") String recordType,
			 @RequestParam(value="jobNum")String itemNum){
		List<JobInfo> jobInfoList=null;
		System.out.print("siteName="+siteName+"userName="+userName+"startTime="+startTime+"endTime="+endTime+"recordType="+recordType+"\n");
        Query  query=new Query(siteName,userName,startTime,endTime,itemNum);
        
		jobInfoList=jobInfoService.searchJobInfos(query);
		if (jobInfoList != null && jobInfoList.size()>0) {
			
			for(JobInfo job:jobInfoList){
				job.setInfos(null);
				job.getStaff().setJobInfos(null);
				job.getSite().setJoinfos(null);
			}
			ClassParse parse =  new ClassParse();
			String result = parse.jobList2String(jobInfoList);
			System.out.print("result"+result+"\n");
			return result == null?Message.SUCCESS:result;
		}else {
			return Message.SUCCESS;
		}
	}
	
}
