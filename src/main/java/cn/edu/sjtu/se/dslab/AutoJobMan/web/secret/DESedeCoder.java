package cn.edu.sjtu.se.dslab.AutoJobMan.web.secret;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * DESede Coder<br/>
 * secret key length:	112/168 bit, default:	168 bit<br/>
 * mode:	ECB/CBC/PCBC/CTR/CTS/CFB/CFB8 to CFB128/OFB/OBF8 to OFB128<br/>
 * padding:	Nopadding/PKCS5Padding/ISO10126Padding/
 * @author Aub
 * 
 */
public class DESedeCoder {
	/*
	 * 基于口令的对称加密
	 */
	public String secretEncrypt(String text,String password) throws Exception {
		//实例化工具
		Cipher cipher2=Cipher.getInstance("PBEWithMD5AndDES");	
		//使用该工具将基于密码的形式生成Key
		SecretKey key2=SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(new PBEKeySpec(password.toCharArray()));
		PBEParameterSpec parameterspec=new PBEParameterSpec(new byte[]{1,2,3,4,5,6,7,8},1000);
		
		//初始化加密操作，同时传递加密的算法
		cipher2.init(Cipher.ENCRYPT_MODE,key2,parameterspec);
		
		 //将要加密的数据传递进去，返回加密后的数据
		byte [] results =cipher2.doFinal(text.getBytes());
		
		return new String(results);
	}
	
	/*
	 * 基于口令的对称解密
	 */
	public String secretDecrypt(String secret,String password) throws Exception{
		Cipher cipher2=Cipher.getInstance("PBEWithMD5AndDES");
		SecretKey key2=SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(new PBEKeySpec(password.toCharArray()));
		PBEParameterSpec parameterspec=new PBEParameterSpec(new byte[]{1,2,3,4,5,6,7,8},1000);
		cipher2.init(Cipher.DECRYPT_MODE,key2,parameterspec);
		
		byte [] src=secret.getBytes();
		byte [] result=cipher2.doFinal(src);
		return new String(result);
	}
	public static void main(String[] args) throws Exception {
		DESedeCoder deSedeCoder = new DESedeCoder();
		String s = deSedeCoder.secretEncrypt("root", "ROOTROOT");
		String o = deSedeCoder.secretDecrypt(s, "ROOTROOT");
		System.out.println(s);
		System.out.println(o);
	}
	
}