package com.frank.mmp.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author 耶律齐
* @version 创建时间：2017年11月8日 下午4:52:22
* 文件操作工具类
*/
public class FileUtil {
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);
	/**
	 * 文件下载到客户端工具类
	 * @param filePath 文件路径
	 * @param res 
	 * @param req 
	 * @throws IOException 
	 */
	public static void exportFileToClient(String filePath,HttpServletRequest req,HttpServletResponse res) throws IOException{
		if(null == filePath || "".equals(filePath.trim()) || null == res){
			throw new RuntimeException("文件导出参数为空");
		}
		File file = new File(filePath);
		if(!file.exists()){
			throw new FileNotFoundException("文件不存在");
		}
		log.info("导出下载文件路径："+filePath);
		String userAgent = req.getHeader("USER-AGENT").toLowerCase();
		String fileName = file.getName();
		if (userAgent.contains("firefox")) {// 火狐浏览器
			fileName = new String(fileName.getBytes(), "ISO8859-1");
		} else {
			fileName = URLEncoder.encode(fileName, "UTF-8");// 其他浏览器
		}
		// 设置文件MIME类型为Excel  
        res.setContentType(req.getHeader("content-type"));
        // 设置Content-Disposition  
//        res.setHeader("Content-Disposition", "attachment;filename="+fileName);
        res.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");
        // 读取文件
  		InputStream in = null;
  		OutputStream out = null;
  		try{
  			in = new FileInputStream(filePath);
  			out = res.getOutputStream();
  			// 写文件
  			int b;
  			while((b=in.read()) != -1)
  			{
  				out.write(b);
  			}
  		}catch(Exception e){
  			log.error("文件下载异常：",e);
  		} finally{
  			if(null != in){
  				in.close();
  			}
  			if(null != out){
  				out.close();
  			}
  		}
	}
	
	/**
	 * 删除指定位置文件
	 * @param path 文件路径
	 * @throws FileNotFoundException 
	 */
	public static void deleteFile(String path) throws FileNotFoundException{
		File file = new File(path);
		if(!file.isFile()){
			throw new FileNotFoundException("文件不存在");
		}
		file.delete();
	}
	
	public static void main(String[] args) {
		String path = "C:\\Develop\\eclipse\\files\\phoneSplit\\1\\";
		try {
			deleteFile(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
