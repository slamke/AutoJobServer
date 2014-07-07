package cn.edu.sjtu.se.dslab.AutoJobMan.web.secret;

import cn.edu.sjtu.se.dslab.AutoJobMan.web.util.Message;

public class EncryptorService {
	
	public String encrypt(String text){
		try {
			if (text == null) {
				return Message.ERROR;
			}else {
				MD5 md5 = new MD5();
				String s = md5.getMD5ofStr(text);
				return s;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Message.ERROR;
	}
}
