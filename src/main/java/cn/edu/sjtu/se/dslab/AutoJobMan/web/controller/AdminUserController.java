package cn.edu.sjtu.se.dslab.AutoJobMan.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.Staff;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.secret.EncryptorService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IStaffService;


@Controller
public class AdminUserController {
	
	private final static String AUTH = "auth";
	private final static String REALNAME = "realname";
	private final static String TELEPHONE = "telephone";
	private final static String USERNAME = "username";
	private final static String PASSWORD = "password";
	
	@Resource(name="staffService")
	IStaffService staffService;
	
	@RequestMapping(value="/adminUser")
	public ModelAndView showAdminUser(){
		ModelAndView mav = new ModelAndView();
        mav.setViewName("adminUser");
        List<Staff> staffList=staffService.getUserList();
        int num=0;
        if(staffList!=null){
        	num=staffList.size();
        }
        mav.addObject("staffNum",num);	
        return mav;
	}
	
	@RequestMapping(value="/getAllUser")
	@ResponseBody
	public List<String> getAllUser(){
        List<Staff> staffList=staffService.getUserList();
        List<String> staffNameList=new ArrayList<String>();
        if(staffList!=null){
        	for(Staff staff:staffList){
        		staffNameList.add(staff.getRealname());
        	}
        }
        return staffNameList;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/adminUser/list")
	public Map<String, Object> showAllStaff(@RequestParam(value="pageIndex") int pageIndex,@RequestParam(value="pageSize")int pageSize){
        Map<String, Object> modelMap=new HashMap<String,Object>(2);
        List<Map<String, String>> data = new ArrayList<Map<String,String>>();
        List<Staff> staffList=staffService.getUserList();
        if(staffList!=null){
        	List<Staff> staffSubList=null;
            if((pageIndex+1)*pageSize<=staffList.size()){
            	staffSubList=staffList.subList(pageIndex*pageSize, (pageIndex+1)*pageSize);
            }                                                    
            else {
    			staffSubList=staffList.subList(pageIndex*pageSize, staffList.size());
    		}

    		for (int i = 0; i < staffSubList.size(); i++) {
    			Map<String, String> map = new HashMap<String, String>();
    			map.put(AUTH, "" + staffSubList.get(i).getAuth());
    			map.put(REALNAME, staffSubList.get(i).getRealname());
    			map.put(TELEPHONE, staffSubList.get(i).getTel());
    			map.put(USERNAME, staffSubList.get(i).getUsername());
    			map.put(PASSWORD, staffSubList.get(i).getPassword());
    			map.put("department", staffSubList.get(i).getDepartment());
    			data.add(map);
    		}
            modelMap.put("success",true);
            modelMap.put("staffSubList",data);
        }
        return modelMap;
        
	}
	
	@RequestMapping(value="/addUser")
	@ResponseBody
	public Map<String, Object> addUser(Staff staff){
		Map<String, Object> modelMap=new HashMap<String,Object>();
		if(staff!=null){
			EncryptorService service = new EncryptorService();
			String real = service.encrypt("123456");
			staff.setPassword(real);
			List<Staff> staffList=staffService.getUserList();
			for(Staff stf:staffList){
				if(stf.getUsername().equals(staff.getUsername())){
					String errMess="这个用户名已经存在";
					modelMap.put("error",errMess);
					return modelMap;
				}
			}
			staffService.saveUser(staff);
			modelMap.put("success",true);
		}
		return modelMap;
	}
	
	@RequestMapping(value="/changeInfo")
	public  ModelAndView changeInfo(@RequestParam(value="username")String username){
		ModelAndView mav = new ModelAndView();
        mav.setViewName("changeInfo");
        Staff staff=staffService.getUserByUN(username);
        if(staff!=null){
        	mav.addObject("staff",staff);
        }
        return mav;
	}
	
	@RequestMapping(value="/changeInfo/submit")
	@ResponseBody
	public  Map<String, Object> change(@RequestParam(value="oldun")String oldUserName,Staff staff){
		Map<String, Object> modelMap=new HashMap<String,Object>();
		Staff staffreal=staffService.getUserByUN(oldUserName);
		if(staffreal!=null){
			staffreal.setRealname(staff.getRealname());
			staffreal.setTel(staff.getTel());
			staffreal.setUsername(staff.getUsername());
			staffreal.setDepartment(staff.getDepartment());
			staffService.changeInfo(staffreal);
			modelMap.put("success", true);
			modelMap.put("newUserName", staff.getUsername());
		}
		else {
			modelMap.put("error", "请填写正确的密码");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/admin/changeInfo/submit")
	@ResponseBody
	public  Map<String, Object> adminChange(@RequestParam(value="oldun")String oldUserName,Staff staff){
		Map<String, Object> modelMap=new HashMap<String,Object>();
		Staff staffreal=staffService.getUserByUN(oldUserName);
		if(staffreal!=null){
			staffreal.setRealname(staff.getRealname());
			staffreal.setTel(staff.getTel());
			staffreal.setUsername(staff.getUsername());
			staffService.changeInfo(staffreal);
			modelMap.put("success", true);
		}
		else {
			modelMap.put("error", "请填写正确的密码");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/changePassword")
	@ResponseBody
	public  Map<String, Object> change(@RequestParam(value="newcode")String newcode ,@RequestParam(value="oldun")String oldUserName,Staff staff){
		
		Map<String, Object> modelMap=new HashMap<String,Object>();
		EncryptorService service = new EncryptorService();
		String real=service.encrypt(staff.getPassword());
		Staff staffreal=staffService.checklogin(oldUserName,real);
		if(staffreal!=null){
			staffreal.setPassword(service.encrypt(newcode));
			staffService.changeInfo(staffreal);
			modelMap.put("success", true);
		}
		else {
			modelMap.put("error", "请填写正确的密码");
		}
		return modelMap;
	}
	@RequestMapping(value="/adminChangePassword")
	@ResponseBody
	public  Map<String, Object> adminChange(@RequestParam(value="newcode")String newcode ,@RequestParam(value="oldun")String oldUserName,Staff staff){
		
		Map<String, Object> modelMap=new HashMap<String,Object>();
		EncryptorService service = new EncryptorService();
		Staff temp = staffService.getUserByUN(oldUserName);
		if(temp!=null){
			temp.setPassword(service.encrypt(newcode));
			staffService.changeInfo(temp);
			System.out.println("newcode:"+newcode+"password:"+service.encrypt(newcode));
			modelMap.put("success", true);
		}
		else {
			modelMap.put("error", "系统内部错误，请稍后再试");
		}
		return modelMap;
	}
	@RequestMapping(value="/admin/changeInfo")
	public  ModelAndView adminChangeInfo(@RequestParam(value="username")String username){
		System.out.println("username:"+username);
		ModelAndView mav = new ModelAndView();
        mav.setViewName("adminChangeInfo");
        Staff staff=staffService.getUserByUN(username);
        if(staff!=null){
        	mav.addObject("staff",staff);
        }else {
        	mav.setViewName("home");
		}
        return mav;
	}
	@RequestMapping(value="/removeUser")
	public ModelAndView removeUser(@RequestParam(value="username") String username){
		ModelAndView mav = new ModelAndView();
        mav.setViewName("adminUser");
        staffService.removeUserByUserName(username);
        return mav;
	}
	
}
