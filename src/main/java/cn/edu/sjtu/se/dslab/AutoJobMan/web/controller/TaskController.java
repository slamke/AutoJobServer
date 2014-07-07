package cn.edu.sjtu.se.dslab.AutoJobMan.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sun.jmx.snmp.tasks.Task;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.Staff;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.TaskInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.WorkSite;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IJobInfoService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IStaffService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.ITaskService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IWorkSiteService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.Const;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.DateParse;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.Message;

@Controller
@RequestMapping(value="/task")
public class TaskController {
	private final static String TASKNUM = "tasknum";
	private final static String ITEMNAME = "itemname";
	private final static String CHGSTAFF = "chgstaff";
	private final static String PHOTO="photo";
	private final static String STATE="state";
	private final static String DEPARTMENT="department";
	private final static String SCORE="score";
	private final static String DESCRIPTION="description";
	@Resource(name="taskService")
	ITaskService taskService;
	@Resource(name="staffService")
	IStaffService staffService;
	@Resource(name="jobInfoService")
	IJobInfoService jobInfoService;
	@Resource(name="workSiteService")
	IWorkSiteService workSiteService;
	@RequestMapping(value="/home")
	public ModelAndView showTask(){
		ModelAndView mav = new ModelAndView();
        mav.setViewName("task");
        return mav;
	}
	
	@RequestMapping(value="/2addTask")
	public ModelAndView showAddTask(TaskInfo taskInfo){
		ModelAndView mav = new ModelAndView();
        mav.setViewName("addTask");
        return mav;
	}
	
	@RequestMapping(value="/addTask", method = RequestMethod.POST)
	public ModelAndView addTask(@RequestParam(value="addStaff")String addStaff,@RequestParam(value="tasknum")String tasknum,
			@RequestParam(value="taskstaff")String taskstaff,@RequestParam(value="itemname")String itemname,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value="starttime")String starttime,
			@RequestParam(value="deadline")String deadline,
			HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("task");
		if(tasknum.isEmpty()||taskstaff.isEmpty()||itemname.isEmpty()||file.isEmpty()||starttime.isEmpty()||deadline.isEmpty()){
			mv.addObject("error", "请填写所有内容");
			return mv;
		}
		
		String folder = request.getSession().getServletContext().getRealPath("/");
		folder = folder.replace("\\", "/");
		String fileName = Const.FOLDER_UPLOAD;
		
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());
		fileName += "/"+System.currentTimeMillis()+suffix;
		File targetFile = new File(folder, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String filePath=fileName;
		Staff staff=staffService.getByRealName(taskstaff);
		Staff aStaff=staffService.getByRealName(addStaff);
		
		if(staff==null||aStaff==null){
			mv.addObject("error", "项目负责人不存在");
			return mv;
		}
		WorkSite site=workSiteService.getByName(itemname);
		if(site==null){
			mv.addObject("error", "项目名称不存在");
			return mv;
		}
		if(taskService.getByNum(tasknum)==null){
			DateParse dateParse=new DateParse();
			TaskInfo taskInfo=new TaskInfo(tasknum,staff, site,filePath,aStaff,dateParse.getCurrentDate());
			taskInfo.setStartTime(dateParse.string2Date2(starttime+" 00:00:01"));
			taskInfo.setDeadline(dateParse.string2Date2(deadline+" 00:00:01"));
			taskService.save(taskInfo);
		}else{
			mv.addObject("error", "项目编号已存在");
			return mv;
		}
		mv.addObject("success", "新建成功");
		
		return mv;
	}
	
	
	@RequestMapping(value="/searchTask")
	@ResponseBody
	public Map<String, Object> searchTask(
			@RequestParam(value="tasknum")String taskNum,@RequestParam(value="taskstaff") String taskstaff,
			@RequestParam(value="itemname")String itemname,@RequestParam(value="state")String state){
		Map<String, Object> modelmap=new HashMap<String,Object>(2);
		taskNum=taskNum.trim();
		taskstaff=taskstaff.trim();
		itemname=itemname.trim();
		state=state.trim();
		List<TaskInfo> allTasks=taskService.searchTaskInfos(taskNum, taskstaff, itemname, state);
		int taskSize=0;
		if(allTasks!=null&&!allTasks.isEmpty()){
			taskSize=allTasks.size();
			System.out.print("---------------tasksize="+taskSize+"\n");
			modelmap.put("taskSize", taskSize);
		}
		return modelmap;
	}
	
	@RequestMapping(value="/searchTask2")
	@ResponseBody
	public Map<String, Object> searchTask2(
			@RequestParam(value="tasknum")String taskNum,
			@RequestParam(value="taskstaff") String taskstaff,
			@RequestParam(value="itemname")String itemname){
		Map<String, Object> modelmap=new HashMap<String,Object>(2);
		taskNum=taskNum.trim();
		taskstaff=taskstaff.trim();
		itemname=itemname.trim();
		String state="";
		List<TaskInfo> taskList=taskService.searchTaskInfos(taskNum, taskstaff, itemname, state);
		int taskSize=0;
		if(taskList!=null){
			taskSize=taskList.size();
			modelmap.put("taskSize", taskSize);
		}
		List<Map<String, String>> data=new ArrayList<Map<String,String>>();
		if(taskList!=null){
			for(TaskInfo task:taskList){
				Map<String,String> map=new HashMap<String, String>();
				if(task.isState()){
					if(task.getScorePhoto()!=0||task.getScoreConclusion()!=0||task.getScoreFinish()!=0){
						int sumscore=task.getScorePhoto()+task.getScoreConclusion()+task.getScoreFinish();
						map.put(SCORE, ""+sumscore);
					}else{
						map.put(SCORE, "unevaluated");
					}
				}else{
					continue;
				}
				map.put(TASKNUM, task.getNum());
				map.put(ITEMNAME, task.getInfo().getSitename());
				map.put(CHGSTAFF, task.getChargeStaff().getRealname());
				map.put(DEPARTMENT, task.getChargeStaff().getDepartment());
				data.add(map);
			}
		}
		modelmap.put("taskList", data);
		return modelmap;
	}
	
	@RequestMapping(value="/paginationTask")
	@ResponseBody
	public Map<String, Object> paginationTask(
			@RequestParam(value="tasknum")String taskNum,@RequestParam(value="taskstaff") String taskstaff,
			@RequestParam(value="itemname")String itemname,@RequestParam(value="state")String state,@RequestParam(value="pos")int pos,@RequestParam(value="num")int num){
		Map<String, Object> modelmap=new HashMap<String,Object>(2);
		taskNum=taskNum.trim();
		taskstaff=taskstaff.trim();
		itemname=itemname.trim();
		state=state.trim();
		List<TaskInfo> taskList=taskService.searchTaskInfos(taskNum, taskstaff, itemname, state,pos,num);
		List<Map<String, String>> data=new ArrayList<Map<String,String>>();
		if(taskList!=null){
			for(TaskInfo task:taskList){
				Map<String,String> map=new HashMap<String, String>();
				map.put(TASKNUM, task.getNum());
				map.put(ITEMNAME, task.getInfo().getSitename());
				map.put(CHGSTAFF, task.getChargeStaff().getRealname());
				map.put(PHOTO, task.getPath());
				map.put(STATE, ""+task.isState());
				data.add(map);
			}
		}
		modelmap.put("taskList", data);
		return modelmap;
	}

	@RequestMapping(value = "/2chgTaskInfo")
	public ModelAndView tochgTaskInfo(
			@RequestParam(value = "tasknum") String taskNum) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("chgTaskInfo");
		TaskInfo taskInfo = taskService.getByNum(taskNum);
		if (taskInfo == null) {
			mv.addObject("error", "该项目不存在，请检查项目编号");
			return mv;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put(TASKNUM, taskInfo.getNum());
		map.put(ITEMNAME, taskInfo.getInfo().getSitename());
		map.put(CHGSTAFF, taskInfo.getChargeStaff().getRealname());
		map.put(PHOTO, taskInfo.getPath());
		map.put(STATE, "" + taskInfo.isState());
		mv.addObject("taskInfo", map);
		return mv;
	}
	
	@RequestMapping(value = "/chgTaskInfo", method = RequestMethod.POST)
	public ModelAndView chgTaskInfo(
			@RequestParam(value="tasknum")String tasknum,
			@RequestParam(value="taskstaff")String taskstaff,
			@RequestParam(value="itemname")String itemname,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("task");
		tasknum=tasknum.trim();
		taskstaff=taskstaff.trim();
		itemname=itemname.trim();
		String folder = request.getSession()
				.getServletContext().getRealPath("/");
		folder = folder.replace("\\", "/");
		String fileName = Const.FOLDER_UPLOAD;
		String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());
		fileName += "/"+System.currentTimeMillis()+suffix;
		File targetFile = new File(folder, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String filePath=fileName;
		TaskInfo taskInfo = taskService.getByNum(tasknum);
		if(taskInfo==null){
			mv.addObject("error", "项目不存在");
			return mv;
		}
		Staff staff=staffService.getByRealName(taskstaff);
		if(staff==null){
			mv.addObject("error", "项目负责人不存在");
			return mv;
		}
		taskInfo.setChargeStaff(staff);
		WorkSite site=workSiteService.getByName(itemname);
		if(site==null){
			mv.addObject("error", "项目名称不存在");
			return mv;
		}
		taskInfo.setInfo(site);
		taskInfo.setPath(filePath);
		taskService.update(taskInfo);
		mv.addObject("success", "修改成功");
		return mv;
	}
	
	@RequestMapping(value = "/deleteTask")
	@ResponseBody
	public Map<String, Object> deleteTask(@RequestParam(value="tasknum")String tasknum) {
		Map<String, Object> modelMap=new HashMap<String,Object>();
		tasknum=tasknum.trim();
		TaskInfo taskInfo = taskService.getByNum(tasknum);
		if(taskInfo==null){
			modelMap.put("error", "项目不存在");
			return modelMap;
		}
		System.out.print("itemnum="+taskInfo.getNum()+"staff="+taskInfo.getChargeStaff().getRealname()+"\n");
		taskInfo.setDeleted(true);
		taskService.update(taskInfo);
		modelMap.put("success", "删除成功");
		return modelMap;
	}
	
	@RequestMapping(value="/toevaluate",method = RequestMethod.GET)
	public ModelAndView toevaluateTask(){
			ModelAndView mav = new ModelAndView();
	        mav.setViewName("totaskEvaluate");
	        return mav;
	}
	@RequestMapping(value="/evaluate",method = RequestMethod.GET)
	public ModelAndView evaluateTask(@RequestParam(value="tasknum")String taskNum){
	ModelAndView mav = new ModelAndView();
	        mav.setViewName("taskEvaluate");
	        TaskInfo info=taskService.getByNum(taskNum);
	        mav.addObject("taskInfo", info);
	        return mav;
	}
	
	@RequestMapping(value="/saveevaluate",method = RequestMethod.POST)
	public ModelAndView saveEvaluateTask(@RequestParam(value="tasknum")String taskNum,
			@RequestParam(value="scorefinish")int scorefinish,
			@RequestParam(value="scorephoto")int scorephoto,
			@RequestParam(value="scoreconclusion")int scoreconclusion){
			ModelAndView mav = new ModelAndView();
	        mav.setViewName("taskEvaluate");
	        TaskInfo info=taskService.getByNum(taskNum);
	        info.setScoreFinish(scorefinish);
	        info.setScorePhoto(scorephoto);
	        info.setScoreConclusion(scoreconclusion);
	        taskService.update(info);
	        mav.addObject("success", "评价成功");
	        return mav;
	}

	//根据id获取task 获取当前未完成的最老的一个
	@RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
	@ResponseBody
	public String getTaskByStaff(@PathVariable(value = "id") String id) {
		List<TaskInfo>  tasks=taskService.getByStaffId(Integer.valueOf(id));
		if(tasks!=null){
			System.out.print("size="+tasks.size()+"  in\n");
			TaskInfo tmp = null;
			for(TaskInfo task:tasks){
				if(task.isState()){
					continue;
				}else {
					tmp = task;
					break;
				}
			}
			if(tmp==null){
				return "null";
			}
			@SuppressWarnings("deprecation")
			Date old=tasks.get(0).getAddTime();
			for(TaskInfo task:tasks){
				if(task.isState()){
					continue;
				}else{
					if(task.getAddTime().before(old)){
						old=task.getAddTime();
						tmp=task;
					}
				}
			}
			String path="";
			if(tmp.getPath()!=null&&tmp.getPath()!=""){
				path=tmp.getId()+"#"+tmp.getPath();
			}
			
			return path;
		}
		System.out.print("in\n");
		return "null";
	}
	//根据id完成task 完成当前未完成的最老的一个
	@RequestMapping(value = "/finish",method = RequestMethod.POST)
	@ResponseBody
	public String finishTaskByStaff(String staff_id,String task_id) {
		System.out.println("staff_id:"+staff_id);
		if (task_id != null && !task_id.equals("")) {
			TaskInfo taskInfo = taskService.getById(Integer.valueOf(task_id));
			taskInfo.setState(true);
			taskService.update(taskInfo);
		}else {
			List<TaskInfo>  tasks=taskService.getByStaffId( Integer.parseInt(staff_id));
			if(tasks!=null){
				TaskInfo tmp = null;
				for(TaskInfo task:tasks){
					if(task.isState()){
						continue;
					}else {
						tmp = task;
						break;
					}
				}
				@SuppressWarnings("deprecation")
				Date old=tasks.get(0).getAddTime();
				for(TaskInfo task:tasks){
					if(task.isState()){
						continue;
					}else{
						if(task.getAddTime().before(old)){
							old=task.getAddTime();
							tmp=task;
						}
					}
				}
				if(tmp!=null){
					tmp.setState(true);
					taskService.update(tmp);
				}else{
					return Message.ERROR;
				}
			}
		}
		return Message.SUCCESS;
	}
 
	
	
}
