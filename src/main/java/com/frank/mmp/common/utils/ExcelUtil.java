package com.frank.mmp.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private static String temp = null;
	private static Workbook book = null;
	private static String outPath = null;
	private static final String[] PHONE_HEADER = {"13","14","15","17","18","19"};

	public static String creatFile(String savePath,List<String[]> list,String[] titles){
//		if(null == savePath || "".equals(savePath.trim())){
//			throw new RuntimeException("文件保存路径不能为空");
//		}
//		if(null == titles || titles.length <= 0){
//			throw new RuntimeException("文件行标题不能为空");
//		}
//		if(null == list || list.isEmpty()){
//			throw new RuntimeException("文件内容不能为空");
//		}
		File file = new File(savePath);
		if(file.isDirectory()){
			throw new RuntimeException("文件保存路径格式不正确，缺少文件名及后缀名");
		}
		String fileName = file.getName().split("\\.")[0];
		String path = file.getParent()+"\\";
		book = new XSSFWorkbook();
		Sheet sheet = book.createSheet();
		for(int i=0; i<titles.length; i++){
			sheet.createRow(0).createCell(i).setCellValue(titles[i]);
		}
		int k = 0;
		for(int i=0; i<list.size(); i++){
			if(null == list.get(i) || list.get(i).length<=0){
				continue;
			}
			for(int j=0; j<titles.length; j++){
				if(null == list.get(i)[j] || "".equals(list.get(i)[j].trim())){
					continue;
				}
				sheet.createRow(++k).createCell(j).setCellValue(list.get(i)[j]);
			}
		}
		list.removeAll(list);
		System.out.println("空了吗？"+list.isEmpty());
		String suffix = ".xls";
		FileOutputStream out = null;
		outPath = path+fileName+suffix;
		System.out.println("参数导入结束，剩余长度："+list.size());
		return null;
	}
	
	private static void outFile(Workbook book,String outPath){
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(outPath);
			book.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(null != out){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		String savePath = "E:\\temp\\测试号码.xls";
		List<String[]> lists = new ArrayList<>();
		String[] titles = {"号码"};
		for(int i=0; i<300000; i++){
			String[] list = new String[titles.length];
			list[0] = creatNumber();
			lists.add(list);
		}
		System.out.println("========号码创建完成=================");
		System.out.println("号码长度："+lists.size());
		creatFile(savePath, lists, titles);
		outFile(book, outPath);
		System.out.println("输出路径："+outPath);
	}
	
	private static String creatNumber(){
		temp = null;
		temp = (Math.random()+"").substring(2, 11);
		int index = (int)(Math.random()*6);
		temp = PHONE_HEADER[index]+temp;
		return temp;
	}
}
