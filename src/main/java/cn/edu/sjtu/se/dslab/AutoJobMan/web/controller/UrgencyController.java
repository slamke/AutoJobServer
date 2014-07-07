package cn.edu.sjtu.se.dslab.AutoJobMan.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.DeclarationInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.PhotoInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.Staff;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IDeclarationInfoService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IPhotoInfoService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IStaffService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.ClassParse;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.Const;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.Message;

@Controller
@RequestMapping(value = "/urgency")
public class UrgencyController {

	@Resource(name = "staffService")
	IStaffService staffService;

	@Resource(name = "photoInfoService")
	IPhotoInfoService photoInfoService;

	@Resource(name = "declarationInfoService")
	IDeclarationInfoService declarationInfoService;

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public ModelAndView retrieveUrgencyDetail(@PathVariable(value = "id") int id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("urgencyDetail");
		DeclarationInfo info = declarationInfoService.getById(id);
		mav.addObject("urgencyInfo", info);
		return mav;
	}

	@RequestMapping(value = "/list/done", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDoneList() {
		Map<String, Object> modelMap=new HashMap<String, Object>();
		List<DeclarationInfo> declarationInfos=declarationInfoService.getDone();
		int declSize=0;
		if(declarationInfos!=null){
			declSize=declarationInfos.size();
		}
		modelMap.put("declsize", declSize);
		return modelMap;
	}
	
	@RequestMapping(value = "/list/done/page", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDoneListPage(@RequestParam(value="page")int page,@RequestParam(value="num")int num) {
		List<DeclarationInfo> declarationInfos=declarationInfoService.getDonePage(page,num);
		Map<String, Object> modelMap=new HashMap<String,Object>(2);
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		if (declarationInfos != null) {
			modelMap.put("success", "success");
			for (DeclarationInfo decl : declarationInfos) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("staffName", decl.getStaff().getRealname());
				map.put("title", decl.getTitle());
				map.put("id", "" + decl.getId());
				data.add(map);
			}
			modelMap.put("declartioninfos", data);
		}
		return modelMap;
	}

	@RequestMapping(value = "/list/undone", method = RequestMethod.GET)
	public ModelAndView getUnDoneList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("urgencyList");
		List<DeclarationInfo> declarationInfos=declarationInfoService.getUnDone();
		int declsize=0;
		if(declarationInfos!=null){
			declsize=declarationInfos.size();
		}
		mav.addObject("declsize", declsize);
		return mav;
	}
	
	@RequestMapping(value = "/list/undone/page", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUnDoneListPage(@RequestParam(value="page")int page,@RequestParam(value="num")int num) {
		Map<String, Object> modelmap=new HashMap<String,Object>(2);
		List<DeclarationInfo> declarationInfos=declarationInfoService.getUnDonePage(page,num);
		List<Map<String, String>> data=new ArrayList<Map<String,String>>();
		
		if(declarationInfos!=null){
			for(DeclarationInfo decl:declarationInfos){
				Map<String, String> map=new HashMap<String,String>();
				map.put("staffName", decl.getStaff().getRealname());
				map.put("title", decl.getTitle());
				map.put("id", ""+decl.getId());
				data.add(map);
			}
		}
		modelmap.put("declartioninfos", data);
		return modelmap;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView saveUrgencyResult(@RequestParam(value="id")int id,@RequestParam(value="result")String result) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("urgencyDetail");
		DeclarationInfo declarationInfo=declarationInfoService.getById(id);
		declarationInfo.setResult(result);
		declarationInfo.setState(true);
		declarationInfoService.update(declarationInfo);
		mv.addObject("success", "保存成功");
		return mv;
	}

	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	@ResponseBody
	public String uploadUrgencyInfo(HttpServletRequest request, String content,
			String username, String password, MultipartFile file) {
		Staff staff = staffService.checklogin(username, password);
		if (staff != null) {

			System.out.println(staff.getId() + " start update");
			ClassParse classParse = new ClassParse();
			DeclarationInfo dInfo = classParse.string2UrgencyInfo(content);
			DeclarationInfo infoDeclarationInfo = declarationInfoService
					.getById(dInfo.getId());
			if (infoDeclarationInfo.isState()) {
				return Message.SUCCESS;
			}
			PhotoInfo photoInfo = dInfo.getPhoto();
			if (photoInfo != null && file != null) {
				try {
					String folder = request.getSession().getServletContext()
							.getRealPath("/");
					folder = folder.replace("\\", "/");
					String fileName = Const.FOLDER_UPLOAD;
					Calendar cal = Calendar.getInstance();
					cal.setTime(photoInfo.getTime());
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int month = cal.get(Calendar.MONTH) + 1;
					int year = cal.get(Calendar.YEAR);
					fileName += "/" + year + "/" + month + "/" + day + "";
					if (photoInfo.getPath() != null) {
						String temp[] = photoInfo.getPath().split("/");
						int length = temp.length;
						String name = temp[length - 1];
						name = staff.getId() + "_" + name;
						getFolder(folder + fileName);
						File imageFile = new File(folder, fileName + "/" + name);
						if (!imageFile.exists()) {
							if (file != null && !file.isEmpty()) {
								FileUtils.copyInputStreamToFile(
										file.getInputStream(), imageFile);
							}
						}
						photoInfo.setPath(fileName + "/" + name);
						System.out.println("fileName:" + fileName);
						System.out.println("folder:" + folder);
					}
					photoInfo.setInfo(null);
					photoInfo.setStaff(staff);
					photoInfoService.savePhotoInfo(photoInfo);
					dInfo.setPhoto(photoInfo);
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			dInfo.setStaff(staff);
			declarationInfoService.update(dInfo);
			return Message.SUCCESS;
		} else {
			System.out.println("************");
		}
		return Message.SUCCESS;
	}

	@RequestMapping(value = { "/result" }, method = RequestMethod.POST)
	@ResponseBody
	public String getUrgencyInfoResult(HttpServletRequest request, String list,
			String username, String password) {
		Staff staff = staffService.checklogin(username, password);
		if (staff != null) {
			System.out.println(staff.getId() + " start check");
			ClassParse classParse = new ClassParse();
			List<Integer> lists = classParse.string2IntegerList(list);
			Map<Integer, String> resultMap = new HashMap<Integer, String>();
			if (list != null) {
				for (Integer integer : lists) {
					System.out.println("************" + integer);
					DeclarationInfo info = declarationInfoService
							.getById(integer);
					if (!info.isCancel() && info.isState()) {
						System.out.println("************___" + integer);
						resultMap.put(integer, info.getResult());
					}
				}
			}
			return classParse.object2String(resultMap);
		} else {
			System.out.println("************");
		}
		return Message.ERROR;
	}

	@RequestMapping(value = { "/cancel" }, method = RequestMethod.POST)
	@ResponseBody
	public String cancelUrgencyInfo(HttpServletRequest request, String list,
			String username, String password) {
		Staff staff = staffService.checklogin(username, password);
		if (staff != null) {
			System.out.println(staff.getId() + " start cancel");
			ClassParse classParse = new ClassParse();
			List<Integer> lists = classParse.string2IntegerList(list);
			List<Integer> cancelList = new ArrayList<Integer>();
			if (list != null) {
				for (Integer integer : lists) {
					System.out.println("************" + integer);
					DeclarationInfo info = declarationInfoService
							.getById(integer);
					if (!info.isState()) {
						System.out.println("************___" + integer);
						info.setCancel(true);
						declarationInfoService.update(info);
						cancelList.add(integer);
					}
				}
			}
			return classParse.object2String(cancelList);
		} else {
			System.out.println("************");
		}
		return Message.ERROR;
	}

	protected File getFolder(String targetFolderName) throws IOException {
		File targetFile = new File(targetFolderName);
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}
		if (!targetFile.exists()) {
			targetFile.mkdir();
		}
		return targetFile;
	}
}
