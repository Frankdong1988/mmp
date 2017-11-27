/**
 * 
 */
package com.frank.mmp.common.utils;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frank.mmp.system.bean.MenuBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 * 数据库连接工具
 */
public class JdbcUtil {
	private static Logger log = LoggerFactory.getLogger(JdbcUtil.class);
	private static Connection con;
	private static Statement sta;
	private static ResultSet rs;
	//驱动程序名
	private static String driver = "com.mysql.jdbc.Driver";
	//URL指向要访问的数据库名mydata
	private static String url = "jdbc:mysql://localhost:3306/user?characterEncoding=utf-8";
	//MySQL配置时的用户名
	private static String user = "root";
	//MySQL配置时的密码
	private static String password = "root";
	
	private static Connection getCon(){
		if(null == con){
			try {
				con = DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				log.error("异常：",e);
			} 
		}
		return con;
	}
	
	public static <T> List<T> select(String sql,T t){
		if(null == sql || "".equals(sql.trim())){
			throw new RuntimeException("sql 语句为空异常");
		}
		try {
            //加载驱动程序
            Class.forName(driver);
            con = getCon();
            // 1.getConnection()方法，连接MySQL数据库
            if(!con.isClosed())
                log.info("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            sta = con.createStatement();
            //3.ResultSet类，用来存放获取的结果集！！
            rs = sta.executeQuery(sql);
            ResultSetMetaData rsm = rs.getMetaData();
            int columNum = rsm.getColumnCount();
            List<Map<String, Object>> list = new ArrayList<>();
            while(rs.next()){
            	Map<String, Object> map = new HashMap<>();
            	for(int i=1; i<=columNum; i++){
            		String columName = rsm.getColumnName(i);
            		Object obj = rs.getObject(columName);
            		if(null == obj){
            			continue;
            		}
            		columName = formatColumName(columName);
            		map.put(columName, obj);
            	}
            	list.add(map);
            }
            setDataToObj(list,t);
        } catch (Exception e) {
            log.error("jdbc连接数据库查询异常：",e);
            return null;
        } finally{
        	try {
				rs.close();
				sta.close();
				con.close();
			} catch (Exception e) {
				log.error("jdbc连接关闭异常：",e);
			}
        }
		return null;
	}
	
	private static <T> List<T> setDataToObj(List<Map<String, Object>> list,T t){
		Class tc = t.getClass();
		Method[] mt = tc.getMethods();
		List<T> listT = new ArrayList<>(); 
		while(!list.isEmpty()){
			try {
				t = (T) tc.newInstance();
			} catch (Exception e) {
				log.error("实例化异常：",e);
			}
			Map<String, Object> map = list.remove(0);
			Set<String> keys = map.keySet();
			for(String key:keys){
				for(int i=0; i<mt.length; i++){
					String name = mt[i].getName();
					if(null != name && name.startsWith("set") && key.equals(StringUtil.charToLowerCase(name.trim().replace("set", ""),0))){
						@SuppressWarnings("unchecked")
						Class<T> type = (Class<T>) mt[i].getParameterTypes()[0];
						String typeStr = type.getName();
						try {
							if(typeStr.equals("java.lang.String")){
								mt[i].invoke(t, String.valueOf(map.get(key)));
			                } else if(typeStr.equals("java.util.Date")){
			                	mt[i].invoke(t, (Date)map.get(key));
			                } else if(typeStr.equals("java.lang.Integer")){
			                	mt[i].invoke(t, (Integer)map.get(key));
			                } else if(typeStr.equals("java.lang.Boolean")){
			                	mt[i].invoke(t, (Boolean)map.get(key));
			                } else if(typeStr.equals("java.lang.Byte")){
			                	mt[i].invoke(t, (Byte)map.get(key));
			                } else if(typeStr.equals("java.lang.Double")){
			                	mt[i].invoke(t, (Double)map.get(key));
			                } else if(typeStr.equals("java.lang.Float")){
			                	mt[i].invoke(t, (Float)map.get(key));
			                } else if(typeStr.equals("java.lang.Long")){
			                	mt[i].invoke(t, (Long)map.get(key));
			                } else if(typeStr.equals("java.lang.Number")){
			                	mt[i].invoke(t, (Number)map.get(key));
			                } else if(typeStr.equals("java.lang.Short")){
			                	mt[i].invoke(t, (Short)map.get(key));
			                }
						} catch (Exception e) {
							log.error("反射set方法赋值异常：",e);
						}
					}
				}
			}
			listT.add(t);
		}
		log.info("最终："+JSONArray.fromObject(listT).toString());
		return null;
	}
	
	/**
	 * 格式化列名
	 * @param columName
	 * @return
	 */
	private static String formatColumName(String columName){
		if(null == columName || "".equals(columName.trim())){
			return columName;
		}
		if(columName.contains("_")){
			String[] columArray = columName.split("_");
			columName = columArray[0];
			for(int i=1; i<columArray.length; i++){
				columName += StringUtil.charToUpperCase(columArray[i], 0);
			}
		}
		return columName;
	}
	
	public static void main(String[] args) {
		String sql = "SELECT * FROM sys_menu";
		MenuBean bean = new MenuBean();
		select(sql,bean);
	}
	
}
