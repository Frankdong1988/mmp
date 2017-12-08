package com.frank.mmp.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * excel操作类
 * 
 * @author LIUTQ
 * 
 */
public class ExcelUtil {
	private static String UserPath = System.getProperty("user.dir");
	private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);

	public static void setFileDownloadHeader(HttpServletRequest request,
			HttpServletResponse response, String fileName) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		String finalFileName = fileName;
		try {
			if (userAgent.contains("firefox")) {// 火狐浏览器
				finalFileName = new String(fileName.getBytes(), "ISO8859-1");
			} else {
				finalFileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
			}
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ finalFileName + "\"");// 这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开
		} catch (Exception e) {
			log.error("setFileDownloadHeader error", e);
		}
	}

	/**
	 * 多sheet导出
	 * @param workbook
	 * @param mainTitle
	 * @param titles
	 * @param contents
	 * @param sheetNum
	 * @param totalSheet
	 */
	public final static void buildExcel(SXSSFWorkbook wb, String mainTitle,
			String[] titles, List<Object[]> contents, int sheetNum,
			int totalSheet, OutputStream os) {
		try {
			/** **********创建工作表************ */
			Sheet sheet = wb.createSheet("Sheet" + sheetNum);

			/** ************设置单元格字体************** */
			Font font = wb.createFont();
//			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示

			/** ************以下设置三种单元格样式************ */
			CellStyle cellStyle = wb.createCellStyle();
//			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellStyle.setFont(font);
			Row row = sheet.createRow(0);
			for (short i = 0; i < titles.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(titles[i]);
			}
			/** ***************以下是EXCEL正文数据********************* */
			for (int i = 0; i < contents.size(); i++) {// row
				Object[] rowContent = contents.get(i);
				Row row2 = sheet.createRow(i + 1);
				for (int j = 0; j < titles.length; j++) { // cell
					Cell cell = row2.createCell(j);
					cell.setCellValue(String.valueOf(rowContent[j]));
				}
			}
			if (totalSheet == sheetNum) {
				wb.write(os);
				os.close();
			}
		} catch (Exception e) {
			log.error("buildExcel error,", e);
		}
	}
	
	
	
	/**
	 * 读取excel文件数据(只支持Excel文件)
	 * @param filePath 文件路径
	 * @param delFile true为读取完后删除文件
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static List<String[]> readExcelDate(String filePath,boolean delFile) {
		if(null == filePath || "".equals(filePath.trim())){
			throw new RuntimeException("文件路径为空");
		}
		File file = new File(filePath);
		if(!file.exists()){
			throw new RuntimeException("文件不存在");
		}
		if(!filePath.endsWith(".xlsx") && !filePath.endsWith(".xls")){
			throw new RuntimeException("文件格式不符合要求");
		}
		Workbook book = null;
		try {
			Long start = System.currentTimeMillis();
			if(delFile){
				file.deleteOnExit();
			}
			//sheet的数量
			int sheetNum = 0;
			//hello.xls为要读取的excel文件名
			if(filePath.endsWith(".xlsx")){
				book = new XSSFWorkbook(new FileInputStream(filePath));
				/*sheetNum = book.getNumberOfSheets();
        		int rown = book.getSheetAt(0).getLastRowNum();
        		if(rown > 80000 || sheetNum > 1){
        			book = new SXSSFWorkbook((XSSFWorkbook)book, 1000);
        		}*/
			} else if(filePath.endsWith(".xls")){
				book = new HSSFWorkbook(new FileInputStream(filePath));
			}
			sheetNum = book.getNumberOfSheets();
			//获取所有的sheet
			List<String[]> lists = new ArrayList<>();
			for(int k=0; k<sheetNum; k++){
				Sheet sheet = book.getSheetAt(k);
				if(null == sheet || null == sheet.getRow(0)){
					continue;
				}
				final int columnNum = sheet.getRow(0).getLastCellNum();
				final int rowNum = sheet.getLastRowNum();
				for(int j=0; j<=rowNum; j++){
					Row row = sheet.getRow(j);
					if(null == row){
						continue;
					}
					String[] list = new String[columnNum];
					for(int i=0; i<columnNum; i++){
						Cell cell = row.getCell(i);
						if(null == cell){
							continue;
						}
//            			log.info("ExcelUtil 正在读取文件："+cell.getStringCellValue());
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						list[i] = cell.getStringCellValue().trim();
						row.removeCell(cell);
					}
					lists.add(list);
					sheet.removeRow(row);
				}
			}
			Long end = System.currentTimeMillis();
			log.info("读取excel文件数据总耗时："+(end-start));
			log.info("返回数据长度："+lists.size());
			return lists;
		} catch (Exception e)  {
			log.error("读取excel文件数据异常：",e);
			return null;
		} 
	}
	
	/**
	 * 读取excel文件数据
	 * @param filePath 文件路径
	 * @return
	 */
	public static List<String[]> readExcelDate(String filePath){
		return readExcelDate(filePath,false);
	}
	
	/**
	 * 将参数放入sheet
	 * @param sheet
	 * @param list
	 * @param title
	 */
	private static void creatSheetData(Sheet sheet,List<String[]> list,String[] title,int cellValueType){
		Row rowT = sheet.createRow(0);
		for(int i=0; i<title.length; i++){
			Cell cell = rowT.createCell(i);
			cell.setCellType(cellValueType);
			cell.setCellValue(title[i]);
		}
		int j = 0;
		while(!list.isEmpty()){
			String[] strs = list.remove(0);
			if(null == strs || strs.length <= 0){
				continue;
			}
			Row row = sheet.createRow(++j);
			for(int i=0; i<title.length; i++){
				String val = strs[i];
				Cell cell = row.createCell(i);
				if(null == val || "".equals(val.trim())){
					cell.setCellType(cellValueType);
					cell.setCellValue("");
					continue;
				}
				cell.setCellType(cellValueType);
				cell.setCellValue(val);
			}
		}
	}
	
	/**
	 * 生成Excel文件
	 * @param savePath 保存路径
	 * @param title excel标题
	 * @param dataList 数据
	 * @param excelVersion excel版本{1:高版本(2007及以上版本)，0:低版本(2007以下版本)}
	 * @return 保存路径
	 */
	public static String creatExcelFile(String savePath,String[] title,List<String[]> dataList,Integer excelVersion){
		if(null == dataList || dataList.isEmpty()){
			throw new RuntimeException("Excel 数据不能为空");
		}
		if(null == title || title.length == 0){
			throw new RuntimeException("Excel 标题不能为空");
		}
		if(null == savePath || "".equals(savePath.trim())){
			throw new RuntimeException("保存路径不能为空");
		}
		log.info("ExcelUtil 开始创建文件，长度："+dataList.size());
		Workbook book = null;
		Sheet sheet = null;
		File file = null;
		String fileName = null;
		Long start = System.currentTimeMillis();
		try{
			file = new File(savePath);
			if(savePath.contains(".")){
				fileName = file.getName().split("\\.")[0];
				savePath = file.getParent()+"/";
			} else {
				fileName = System.currentTimeMillis()+"";
			}
		}catch(Exception e){
			log.error("保存路径不合法："+savePath+" ",e);
			throw new RuntimeException("保存路径不合法");
		}
		int cellValueType = 0;
		CellStyle cellStyle = null;
		String houzhui = ".xlsx";
		List<List<String[]>> lists = new ArrayList<>();
		try{
			if(null != excelVersion && 0 == excelVersion){
				int maxLine = 65536;
				if(dataList.size()>maxLine){
					//如果行数量超出单个sheet限制，要生成多个sheet
					while(!dataList.isEmpty()){
						List<String[]> datas = new ArrayList<>();
						int length = dataList.size()>maxLine?maxLine:dataList.size();
						for(int i=0; i<length; i++){
							datas.add(dataList.remove(0));
						}
						lists.add(datas);
					}
				} else {
					lists.add(dataList);
				}
				book = new HSSFWorkbook();
				cellValueType = HSSFCell.CELL_TYPE_STRING;
				cellStyle = book.createCellStyle();
				cellStyle.setFillBackgroundColor(HSSFColor.TEAL.index);
				houzhui = ".xls";
			} else {
				int maxLine = 500000;
				if(dataList.size()>maxLine){
					//如果行数量超出单个sheet限制，要生成多个sheet
					while(!dataList.isEmpty()){
						List<String[]> datas = new ArrayList<>();
						int length = dataList.size()>maxLine?maxLine:dataList.size();
						for(int i=0; i<length; i++){
							datas.add(dataList.remove(0));
						}
						lists.add(datas);
					}
				} else {
					lists.add(dataList);
				}
				book = new XSSFWorkbook();
				if(lists.size()>1 || lists.get(0).size() > 90000){
					book = new SXSSFWorkbook((XSSFWorkbook)book, 1000);
				}
				cellValueType = XSSFCell.CELL_TYPE_STRING; 
				cellStyle = book.createCellStyle();
				cellStyle.setFillForegroundColor(IndexedColors.TEAL.index);
			}
			log.info("生产Excel文件sheet的数量："+lists.size());
			for(List<String[]> list:lists){
				sheet = book.createSheet();
				creatSheetData(sheet, list, title, cellValueType);
			}
			savePath = renameFileExists(savePath+fileName+houzhui);
			File file2 = new File(savePath);
			String path2 = file2.getParent();
			File file3 = new File(path2);
			if(!file3.exists()){
				file3.mkdirs();
			}
			FileOutputStream fileOut = new FileOutputStream(savePath);
	        book.write(fileOut);  
	        fileOut.close();
            Long end = System.currentTimeMillis();
            log.info("生成excel文件总耗时："+(end-start));
		}catch(Exception e){
			log.error("生产excel文件异常：",e);
			throw new RuntimeException("生产excel文件异常："+e); 
		}
		return savePath;
	}
	
	/**
	 * 生产Excel文件
	 * @param savePath 保存路径
	 * @param title excel标题
	 * @param dataList 数据
	 * @return 保存路径
	 */
	public static String creatExcelFile(String savePath,String[] title,List<String[]> dataList){
		return creatExcelFile(savePath, title, dataList, null);
	}
	
	/**
	 * 如果文件在指定如今下存在 即重命名文件
	 * @param filePath
	 * @return
	 */
	private static String renameFileExists(String filePath){
		File file2 = new File(filePath);
		String name = file2.getName().split("\\.")[0];
		String houzhui = file2.getName().split("\\.")[1];
		String path = file2.getParent();
		int i = 0;
		while(file2.exists()){
			name = name+"("+i+")";
			file2 = new File(path+"/"+name+"."+houzhui);
			if(file2.exists()){
				name = name.substring(0, name.length()-3);
			}
			i++;
		}
		return path+"/"+name+"."+houzhui;
	}
	
	
	public static void main(String[] args) {

	}
	
}
