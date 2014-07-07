package cn.edu.sjtu.se.dslab.AutoJobMan.web.util;

import java.lang.reflect.Type;
import java.util.List;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.DeclarationInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.JobInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.PhotoInfo;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.Staff;
import cn.edu.sjtu.se.dslab.AutoJobMan.web.entity.WorkSite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ClassParse {

	private Gson gson;

	public ClassParse() {
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	}
	
	public JobInfo string2JobInfo(String content){
		try {
			Type type = new TypeToken<JobInfo>() {
			}.getType();
			if (content != null) {
				JobInfo record = gson.fromJson(content, type);
				return record;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public List<Integer> string2IntegerList(String content){
		try {
			Type type = new TypeToken<List<Integer>>() {
			}.getType();
			if (content != null) {
				List<Integer> record = gson.fromJson(content, type);
				return record;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public DeclarationInfo string2UrgencyInfo(String content){
		try {
			Type type = new TypeToken<DeclarationInfo>() {
			}.getType();
			if (content != null) {
				DeclarationInfo record = gson.fromJson(content, type);
				return record;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public String object2String(Object object) {
		try {
			if (object == null) {
				return null;
			}else {
				return gson.toJson(object);				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public PhotoInfo string2PhotoInfo(String content){
		try {
			Type type = new TypeToken<PhotoInfo>() {
			}.getType();
			if (content != null) {
				PhotoInfo record = gson.fromJson(content, type);
				return record;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public Staff string2Staff(String content) {
		try {
			Type type = new TypeToken<Staff>() {
			}.getType();
			if (content != null) {
				Staff record = gson.fromJson(content, type);
				return record;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}
	public String staff2String(Staff staff) {
		try {
			if (staff == null) {
				return null;
			}else {
				staff.setJobInfos(null);
				return gson.toJson(staff);				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
	public String Worksites2String(List<WorkSite> sites) {
		try {
			if (sites == null) {
				return null;
			}else {
				return gson.toJson(sites);				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
	public String jobList2String(List<JobInfo> infos) {
		try {
			if (infos == null) {
				return null;
			}else {
				return gson.toJson(infos);				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
}
