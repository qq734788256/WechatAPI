package com.wx.base.util;

import java.util.HashMap;
import java.util.Map;

public class ImageAdaptiveUtil {

	/**
	 * 图片适配方法
	 * @param url
	 * @param imageWidth
	 * @param imageHeight
	 * @param watermark
	 * @return
	 */
	public static String getImagePath(String url,int imageWidth,int imageHeight,int watermark){
		String path="";
		if(url!=null&&url.length()>0){
			/* 处理图片斜杠问题 */
			if("/".equals(url.substring(0,1))){
				url=url.substring(1,url.length());
			}
			path="http://thumb.kting.cn/t1/"+imageWidth+"/"+imageHeight+"/"+url;
		}else{
			path="";
		}
		return path;
	}
	public static String getImagePath_3_5(String url,int imageWidth,int imageHeight,int watermark){
		String path="";
		if(url!=null&&url.length()>0){
			/* 处理图片斜杠问题 */
			if("/".equals(url.substring(0,1))){
				url=url.substring(1,url.length());
			}
			path="http://thumb.kting.cn/b1/"+imageWidth+"/"+imageHeight+"/"+url;
		}else{
			path="";
		}
		return path;
	}
	/**
	 * 图片适配方法
	 * @param url
	 * @param imageWidth
	 * @param imageHeight
	 * @param watermark
	 * @return
	 */
	public static String getImagePath_4_0(String url,int imageWidth,int imageHeight,int watermark){
		String path="";
		if(url!=null&&url.length()>0){
			/* 处理图片斜杠问题 */
			if("/".equals(url.substring(0,1))){
				url=url.substring(1,url.length());
			}
			path="http://thumb.kting.cn/a1/"+imageWidth+"/"+imageHeight+"/"+url;
		}else{
			path="";
		}
		return path;
	}
	/**
	 * 头像图片适配方法
	 * @param url
	 * @param imageWidth
	 * @param imageHeight
	 * @param watermark
	 * @return
	 */
	public static String getAvatarPath(String url,int imageWidth,int imageHeight){
		String path="";
		if(url!=null&&url.length()>0){
			path=url.replace("http://me.kting.cn/uploads/userface/","http://thumb.kting.cn/t1/"+imageWidth+"/"+imageHeight+"/data/uploads/");
			path=path.replace("http://api.kting.cn/Public/","http://thumb.kting.cn/t1/"+imageWidth+"/"+imageHeight+"/data/");
		}
		return path;
	}
	/**
	 * 图片适配方法data类型
	 * @param url
	 * @param imageWidth
	 * @param imageHeight
	 * @param watermark
	 * @return
	 */
	public static String getPicPathData_4_2(String url,int imageWidth,int imageHeight){
		String path="";
		if(url!=null&&url.length()>0){
			path=url.replace("http://me.kting.cn/uploads/userface/","http://thumb.kting.cn/t1/"+imageWidth+"/"+imageHeight+"/data/uploads/");
			path=path.replace("http://api.kting.cn/Public/","http://thumb.kting.cn/t1/"+imageWidth+"/"+imageHeight+"/data/");
		}
		return path;
	}
	/**
	 * 图片适配方法kt_file类型
	 * @param url
	 * @param imageWidth
	 * @param imageHeight
	 * @param watermark
	 * @return
	 */
	public static String getPicPathKtF_4_2(String url,int imageWidth,int imageHeight){
		String path="";
		if(url!=null&&url.length()>0){
			path=url.replace("http://me.kting.cn/uploads/userface/","http://thumb.kting.cn/t1/"+imageWidth+"/"+imageHeight+"/kt_file/uploads/");
			path=path.replace("http://api.kting.cn/Public/","http://thumb.kting.cn/t1/"+imageWidth+"/"+imageHeight+"/data/");
		}
		return path;
	}
	/**
	 * 头像图片适配方法
	 * @param url
	 * @param imageWidth
	 * @param imageHeight
	 * @param watermark
	 * @return
	 */
	public static String getAvatarPathKt(String url,int imageWidth,int imageHeight){
		String path="";
		if(url!=null&&url.length()>0){
			path="http://thumb.kting.cn/t1/"+imageWidth+"/"+imageHeight+"/"+url;
			path=path.replace("http://api.kting.cn/Public/","http://thumb.kting.cn/t1/"+imageWidth+"/"+imageHeight+"/data/");
		}
		return path;
	}
	/**
	 * 更改图片文件名
	 * @param fileName
	 * @return
	 */
	public static Map<String,String> updateImageName(String fileName){
		Map<String,String> newFileMap=new HashMap<String, String>();
		String currentTime=String.valueOf(System.currentTimeMillis()/1000);
		if (fileName.indexOf(".") != -1) { 
			newFileMap.put("avatar","mobile_"+currentTime+fileName.substring(fileName.lastIndexOf(".")));
			newFileMap.put("avatar_small","mobile_"+currentTime+"_50"+fileName.substring(fileName.lastIndexOf(".")));
        } 
		return newFileMap;
	}
	/**
	 * 语音地址更改
	 * @author wumengjia
	 * @return
	 */
	public static String getVoicePath(String url){
		String path="";
		/**
		 * http://audio.kting.cn/data/voice/20150716/mobile_1437011408.amr 
    	   uploads/voice/20150716/mobile_1437011408.amr
		 */
		if(url!=null&&url.length()>0){
			path=url.replace("uploads","data");
			path="http://audio.kting.cn/"+path;
		}
		return path;
	}
}
