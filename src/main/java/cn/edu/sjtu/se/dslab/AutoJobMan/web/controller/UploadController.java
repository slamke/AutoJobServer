package cn.edu.sjtu.se.dslab.AutoJobMan.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.DeclarationInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.PhotoInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.Staff;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IDeclarationInfoService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IJobInfoService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IPhotoInfoService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.service.IStaffService;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.ClassParse;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.Const;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.Message;


@Controller
@RequestMapping(value = "/upload")
public class UploadController {
	@Resource(name="staffService")
	IStaffService staffService;
	
	@Resource(name="jobInfoService")
	IJobInfoService jobInfoService;
	
	@Resource(name="photoInfoService")
	IPhotoInfoService photoInfoService;
	
	@Resource(name="declarationInfoService")
	IDeclarationInfoService declarationInfoService;

	
	@RequestMapping(value = { "/{job_id}/image" }, method = RequestMethod.POST)
	@ResponseBody
	public String getAllProductToJson(HttpServletRequest request,
			String content, @PathVariable(value = "job_id") int job_id, String username, String password,
			MultipartFile file) {
		
		//��֤�û�������
		Staff staff=staffService.checklogin(username, password);
		if(staff!=null){
			JobInfo jobinfo=jobInfoService.queryJobInfoById(job_id);
			if(jobinfo!=null){
				try {
					ClassParse classParse=new ClassParse();
					PhotoInfo persist=classParse.string2PhotoInfo(content);
					if (persist != null) {
						String folder = request.getSession()
								.getServletContext().getRealPath("/");
						folder = folder.replace("\\", "/");
						String fileName = Const.FOLDER_UPLOAD;
						Calendar cal = Calendar.getInstance();
						cal.setTime(persist.getTime());
						int day = cal.get(Calendar.DAY_OF_MONTH);
						int month = cal.get(Calendar.MONTH) + 1;
						int year = cal.get(Calendar.YEAR);
						fileName += "/" + year + "/" + month + "/" + day
								+ "";
						if (persist.getPath() != null) {
							String temp[] = persist.getPath().split("/");
							int length = temp.length;
							String name = temp[length - 1];
							String fileType = name.substring(name
									.lastIndexOf("."));

							getFolder(folder + fileName);
							File imageFile = new File(folder, fileName
									+ "/" + name);
							if (imageFile.exists()) {
								String newName = ""
										+ System.currentTimeMillis() 
										+ fileType;
								fileName = fileName + "/" + newName;
							} else {
								fileName = fileName + "/" + name;
								imageFile = new File(folder, fileName);
							}
							persist.setPath(fileName);
							System.out.println("fileName:" + fileName);
							System.out.println("folder:" + folder);
							if (file != null && !file.isEmpty()) {
								FileUtils.copyInputStreamToFile(
										file.getInputStream(), imageFile);
							}
						}
						if (persist.getInfo() == null) {
							persist.setInfo(jobinfo);
						}
						photoInfoService.savePhotoInfo(persist);
						return Message.SUCCESS;
					}// persist
				} catch (UnsupportedEncodingException e) {
					// TODO: handle exception
					e.printStackTrace();
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return Message.ERROR;
	}
	@RequestMapping(value = { "/job" }, method = RequestMethod.POST)
	@ResponseBody
	public String uploadJobInfo(String content, String username, String password) {
		//���� ��֤�û�������
		Staff staff=staffService.checklogin(username, password);
		if(staff!=null){
			System.out.println(staff.getId()+" start insert");
			ClassParse classParse=new ClassParse();
			JobInfo jobInfo=classParse.string2JobInfo(content);
			jobInfo.setStaff(staff);
			jobInfoService.saveJobInfo(jobInfo);
			//System.out.println(jobInfo.getLocation()+" end insert");
			String key = ""+jobInfo.getId();
			return key;
		}else {
			System.out.println("************");
		}
		//����ΪJObinfo����
		//������ݿ�
		//��������    ����ʱ������-1
		return "-1";
	}
	@RequestMapping(value = { "/urgency" }, method = RequestMethod.POST)
	@ResponseBody
	public String uploadUrgencyInfo(HttpServletRequest request,String content, String username, String password,MultipartFile file) {
		Staff staff=staffService.checklogin(username, password);
		if(staff!=null){
			System.out.println(staff.getId()+" start insert");
			ClassParse classParse=new ClassParse();
			DeclarationInfo dInfo=classParse.string2UrgencyInfo(content);
			PhotoInfo photoInfo = dInfo.getPhoto();
			if (photoInfo != null) {
				try {
				String folder = request.getSession()
						.getServletContext().getRealPath("/");
				folder = folder.replace("\\", "/");
				String fileName = Const.FOLDER_UPLOAD;
				Calendar cal = Calendar.getInstance();
				cal.setTime(photoInfo.getTime());
				int day = cal.get(Calendar.DAY_OF_MONTH);
				int month = cal.get(Calendar.MONTH) + 1;
				int year = cal.get(Calendar.YEAR);
				fileName += "/" + year + "/" + month + "/" + day
						+ "";
				if (photoInfo.getPath() != null) {
					String temp[] = photoInfo.getPath().split("/");
					int length = temp.length;
					String name = temp[length - 1];
					String fileType = name.substring(name
							.lastIndexOf("."));
					getFolder(folder + fileName);
					File imageFile = new File(folder, fileName
							+ "/" + name);
					if (imageFile.exists()) {
						String newName = ""
								+ System.currentTimeMillis() 
								+ fileType;
						fileName = fileName + "/" + newName;
					} else {
						fileName = fileName + "/" + name;
						imageFile = new File(folder, fileName);
					}
					photoInfo.setPath(fileName);
					System.out.println("fileName:" + fileName);
					System.out.println("folder:" + folder);
					if (file != null && !file.isEmpty()) {
						FileUtils.copyInputStreamToFile(
								file.getInputStream(), imageFile);
					}
				}
				photoInfo.setInfo(null);
				photoInfo.setStaff(staff);
				photoInfoService.savePhotoInfo(photoInfo);
				dInfo.setPhoto(photoInfo);
				}catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			dInfo.setStaff(staff);
			declarationInfoService.save(dInfo);
			//System.out.println(jobInfo.getLocation()+" end insert");
			String key = ""+dInfo.getId();
			return key;
		}else {
			System.out.println("************");
		}
		return "-1";
	}

	@RequestMapping(value = { "/job/update" }, method = RequestMethod.POST)
	@ResponseBody
	public String updateJobInfo(String content, String username, String password) {
		//���� ��֤�û�������
		Staff staff=staffService.checklogin(username, password);
		if(staff!=null){
			System.out.println(staff.getId()+" start update");
			ClassParse classParse=new ClassParse();
			JobInfo jobInfo=classParse.string2JobInfo(content);
			jobInfo.setStaff(staff);
			jobInfoService.updateJobInfo(jobInfo);
			//System.out.println(jobInfo.getLocation()+" "+jobInfo.getId()+" end update");
			return Message.SUCCESS;
		}else {
			System.out.println("************");
		}
		//����ΪJObinfo����
		//������ݿ�
		//��������    ����ʱ������-1
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
