package cn.edu.sjtu.se.dslab.AutoJobMan.web.controller;

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

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.Staff;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.WorkSite;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.secret.EncryptorService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IStaffService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IWorkSiteService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.ClassParse;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.Message;

@Controller
public class LoginController {
	@Resource(name = "staffService")
	IStaffService staffService;
	
	@Resource(name = "workSiteService")
	IWorkSiteService workSiteService;

	@RequestMapping(value = {"/login","/","/logout"})
	public ModelAndView show() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	

	@RequestMapping(value = "/login/submit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logIn(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {

		boolean checkin = false;
		boolean admin = false;
		Map<String, Object> modelMap=new HashMap<String,Object>();
		
		EncryptorService sec = new EncryptorService();
		password = sec.encrypt(password);
		//System.out.print("username="+username+"password="+password+"\n");
		Staff staff = staffService.checklogin(username, password);
		if (staff != null  && staff.getAuth() == 1) {
			checkin = true;
			modelMap.put("username", staff.getRealname());
			if (staff.getAuth() == 1) {
				admin = true;
			}
			modelMap.put("admin", admin);
		}
		modelMap.put("checkin", checkin);
		return modelMap;
	}

	@RequestMapping(value = "/login/service", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginByClient(String username, String password) {
		Map<String, String> result = new HashMap<String, String>();
		System.out.println(username + " login");
		System.out.println(password + " password");
		Staff staff = staffService.checklogin(username, password);
		if (staff != null) {
			System.out.println(username + " login successfully.");
			ClassParse parse = new ClassParse();
			String staffStr = parse.staff2String(staff);
			result.put("status", Message.SUCCESS);
			result.put("staff", staffStr);
			List<WorkSite> sites = workSiteService.getAll();
			for (WorkSite workSite : sites) {
				workSite.setJoinfos(null);
			}
			String sitesStr = parse.Worksites2String(sites);
			result.put("sites", sitesStr);
		}else {
			result.put("status", Message.ERROR);
		}
		return result;
	}

}
