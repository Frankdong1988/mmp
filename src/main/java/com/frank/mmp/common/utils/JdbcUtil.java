package com.frank.mmp.common.utils;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 * 数据库连接工具
 */
public class JdbcUtil {
	private static Logger log = LoggerFactory.getLogger(JdbcUtil.class);
	private static Connection con;
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
				log.error("获取数据库连接异常：",e);
				throw new RuntimeException(e);
			} 
		}
		return con;
	}
	
	/**
	 * 执行查询SQL 
	 * @param sql select语句
	 * @param t 需要封装数据的bean实例
	 * @return
	 */
	public static <T> List<T> select(String sql,T t){
		if(null == sql || "".equals(sql.trim())){
			throw new RuntimeException("sql 语句为空异常");
		}
		Class tc = t.getClass();
		Method[] mt = tc.getMethods();
		Statement sta = null;
		ResultSet rs = null;
		try {
            //加载驱动程序
            Class.forName(driver);
            con = getCon();
            //2.创建statement类对象，用来执行SQL语句！！
            sta = con.createStatement();
            //3.ResultSet类，用来存放获取的结果集！！
            rs = sta.executeQuery(sql);
            ResultSetMetaData rsm = rs.getMetaData();
            int columNum = rsm.getColumnCount();
            List<T> list = new ArrayList<>();
            while(rs.next()){
            	T tt = (T) tc.newInstance();
            	for(int i=1; i<=columNum; i++){
            		String columName = rsm.getColumnName(i);
            		Object obj = rs.getObject(columName);
            		if(null == obj){
            			continue;
            		}
            		for(int j = 0; j<mt.length; j++){
            			String mtName = mt[j].getName();
            			if(null != mtName && mtName.startsWith("set") && formatColumName(columName).equals(StringUtil.charToLowerCase(mtName.trim().replace("set", ""),0))){
            				Class type = mt[j].getParameterTypes()[0];
            				String typeStr = type.getName();
            				if(typeStr.equals("java.lang.String")){
            					mt[j].invoke(tt, rs.getString(columName));
			                } else if(typeStr.equals("java.util.Date")){
			                	mt[j].invoke(tt, rs.getDate(columName));
			                } else if(typeStr.equals("java.lang.Integer")){
			                	mt[j].invoke(tt, rs.getInt(columName));
			                } else if(typeStr.equals("java.lang.Boolean")){
			                	mt[j].invoke(tt, rs.getBoolean(columName));
			                } else if(typeStr.equals("java.lang.Byte")){
			                	mt[j].invoke(tt, rs.getByte(columName));
			                } else if(typeStr.equals("java.lang.Double")){
			                	mt[j].invoke(tt, rs.getDouble(columName));
			                } else if(typeStr.equals("java.lang.Float")){
			                	mt[j].invoke(tt, rs.getFloat(columName));
			                } else if(typeStr.equals("java.lang.Long")){
			                	mt[j].invoke(tt, rs.getLong(columName));
			                } else if(typeStr.equals("java.lang.Number")){
			                	mt[j].invoke(tt, rs.getInt(columName));
			                } else if(typeStr.equals("java.lang.Short")){
			                	mt[j].invoke(tt, rs.getShort(columName));
			                }
            				break;
            			}
            		}
            	}
            	list.add(tt);
            }
            return list;
        } catch (Exception e) {
            log.error("jdbc连接数据库查询异常：",e);
            return null;
        } finally{
        	try {
        		if(null != rs){
        			rs.close();
        		}
        		if(null != sta){
        			sta.close();
        		}
        		if(null != con){
        			con.close();
        		}
			} catch (Exception e) {
				log.error("jdbc连接关闭异常：",e);
			}
        }
	}
	
	/**
	 * 更新操作
	 * @param sql update或者insert语句
	 * @return 被修改的数据行数
	 */
	public static Integer update(String sql){
		if(null == sql || "".equals(sql.trim())){
			throw new RuntimeException("sql 语句为空异常");
		}
		Statement sta = null;
		ResultSet rs = null;
		try {
            //加载驱动程序
            Class.forName(driver);
            con = getCon();
            // 1.getConnection()方法，连接MySQL数据库
            if(!con.isClosed())
            //2.创建statement类对象，用来执行SQL语句！！
            sta = con.createStatement();
            //3.ResultSet类，用来存放获取的结果集！！
            return sta.executeUpdate(sql);
		} catch (Exception e){
			log.error("jdbc连接数据库 更新操作 异常：",e);
			return null;
		}
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
//		String sql = "SELECT * FROM sys_menu";
//		MenuBean bean = new MenuBean();
//		 List<MenuBean> list = select(sql,bean);
//		System.out.println("返回值："+JSONArray.fromObject(list).toString());
		System.out.println("+++++");
	}
	
}
