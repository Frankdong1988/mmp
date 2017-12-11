package com.frank.mmp.common.utils;

import static com.frank.mmp.common.utils.StringUtils.javaBoolean;
import static com.frank.mmp.common.utils.StringUtils.toCamelCase;
import static com.frank.mmp.common.utils.StringUtils.toUpperCaseFirst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
/**
 * 简易代码生成器
 * @author tongyi
 * @date Sep 28, 2013
 */
public class CodeGenerator {
	private final static String					DRIVER		= "com.mysql.jdbc.Driver";
	private final static String					URL			= "jdbc:mysql://10.10.0.9:3306/information_schema";//rm-bp1460o9ak1f18vrzo.mysql.rds.aliyuncs.com rm-bp1vxqlotd8bqf601o.mysql.rds.aliyuncs.com
	private final static String					USERNAME	= "root";//xjzx_java_read  xjzx_test_root01
	private final static String					PASSWORD	= "123456";//%A*IRqg@  51xjzx#Com
	private final static String					QUERY_SQL	= "SELECT `COLUMN_NAME`, `DATA_TYPE`, `COLUMN_TYPE`, `EXTRA`,`COLUMN_COMMENT` FROM `COLUMNS` WHERE `TABLE_SCHEMA` = ? AND `TABLE_NAME` = ? ORDER BY `ORDINAL_POSITION` ASC";
	private final static Map<String, String>	JAVA_TYPE	= new HashMap<String, String>();

	static {
		JAVA_TYPE.put("int", "int");
		JAVA_TYPE.put("tinyint", "int");
		JAVA_TYPE.put("tinyint1", "boolean");
		JAVA_TYPE.put("smallint", "int");
		JAVA_TYPE.put("mediumint", "int");
		JAVA_TYPE.put("bigint", "long");
		JAVA_TYPE.put("varchar", "string");
		JAVA_TYPE.put("char", "string");
		JAVA_TYPE.put("decimal", "decimal");
		JAVA_TYPE.put("datetime", "date");
		JAVA_TYPE.put("timestamp", "long");
		JAVA_TYPE.put("float", "float");
		JAVA_TYPE.put("date", "date");
		JAVA_TYPE.put("year", "int");
		JAVA_TYPE.put("enum", "string");
		JAVA_TYPE.put("text", "string");
		JAVA_TYPE.put("mediumtext", "string");
	}
	public static void main(String[] args) {
		String tableSchema = "xjzx_tools";
		// String tableName = "tb_order";
		String tableName = "phone_operator_file";

		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			// ibatis Sqlmap file
			doGeneratorIbatisSqlmap(conn, tableSchema, tableName);

			// 实体字段
			doGeneratorEntityField(conn, tableSchema, tableName);

			// Webx form
			// doGeneratorForm(conn, tableSchema, tableName);

			// Action field
			// doGeneratorActionField(conn, tableSchema, tableName);

			// service field
			// doGeneratorServiceField(conn, tableSchema, tableName);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void doGeneratorServiceField(Connection connection, String tableSchema, String tableName) throws SQLException {
		String ns = tableName.substring(3);

		PreparedStatement preparedStmt = connection.prepareStatement(QUERY_SQL);
		preparedStmt.setString(1, tableSchema);
		preparedStmt.setString(2, tableName);

		ResultSet resultSet = preparedStmt.executeQuery();
		while (resultSet.next()) {
			String column = resultSet.getString("COLUMN_NAME");
			String jdbcType = resultSet.getString("DATA_TYPE");
			String columnType = resultSet.getString("COLUMN_TYPE");

			String columnType1 = javaBoolean(columnType) ? "tinyint1" : jdbcType;
			String javaType = JAVA_TYPE.get(columnType1);
			if ("string".equalsIgnoreCase(javaType) || "date".equalsIgnoreCase(javaType)) {
				javaType = toUpperCaseFirst(javaType);
			}

			System.out.println(ns + ".set" + toUpperCaseFirst(toCamelCase(column)) + "(" + toCamelCase(column) + ");");

		}

	}

	private static void doGeneratorActionField(Connection connection, String tableSchema, String tableName) throws SQLException {
		PreparedStatement preparedStmt = connection.prepareStatement(QUERY_SQL);
		preparedStmt.setString(1, tableSchema);
		preparedStmt.setString(2, tableName);

		String functionParam = "";

		ResultSet resultSet = preparedStmt.executeQuery();
		while (resultSet.next()) {
			String column = resultSet.getString("COLUMN_NAME");
			String jdbcType = resultSet.getString("DATA_TYPE");
			String columnType = resultSet.getString("COLUMN_TYPE");

			String columnType1 = javaBoolean(columnType) ? "tinyint1" : jdbcType;
			String javaType = JAVA_TYPE.get(columnType1);
			if ("string".equalsIgnoreCase(javaType) || "date".equalsIgnoreCase(javaType)) {
				javaType = toUpperCaseFirst(javaType);
			}

			System.out.println(javaType + " " + toCamelCase(column) + " = formGroup.getField(\"" + toCamelCase(column) + "\").get" + toUpperCaseFirst(javaType)
					+ "Value();");
			functionParam += (StringUtils.isEmpty(functionParam)) ? (javaType + " " + toCamelCase(column)) : (", " + javaType + " " + toCamelCase(column));
		}

		System.out.println();
		System.out.println(functionParam);
	}

	private static void doGeneratorForm(Connection connection, String tableSchema, String tableName) throws SQLException {
		PreparedStatement preparedStmt = connection.prepareStatement(QUERY_SQL);
		preparedStmt.setString(1, tableSchema);
		preparedStmt.setString(2, tableName);

		ResultSet resultSet = preparedStmt.executeQuery();
		while (resultSet.next()) {
			String column = resultSet.getString("COLUMN_NAME");

			System.out.println("<field name=\"" + toCamelCase(column) + "\" />");

		}
	}

	private static void doGeneratorEntityField(Connection connection, String tableSchema, String tableName) throws SQLException {
		PreparedStatement preparedStmt = connection.prepareStatement(QUERY_SQL);
		preparedStmt.setString(1, tableSchema);
		preparedStmt.setString(2, tableName);

		ResultSet resultSet = preparedStmt.executeQuery();
		while (resultSet.next()) {
			String column = resultSet.getString("COLUMN_NAME");
			String jdbcType = resultSet.getString("DATA_TYPE");
			String columnType = resultSet.getString("COLUMN_TYPE");
			String columnComment = resultSet.getString("COLUMN_COMMENT"); 

			String columnType1 = javaBoolean(columnType) ? "tinyint1" : jdbcType;
			String javaType = JAVA_TYPE.get(columnType1);
			if ("string".equalsIgnoreCase(javaType) || "date".equalsIgnoreCase(javaType)) {
				javaType = toUpperCaseFirst(javaType);
			} else if ("decimal".equals(javaType)) {
				javaType = "BigDecimal";
			}
			System.out.println("/** "+columnComment+" **/");
			System.out.println("private " + javaType + " " + toCamelCase(column) + ";");

		}
	}

	private static void doGeneratorIbatisSqlmap(Connection connection, String tableSchema, String tableName) throws SQLException {
		String ns = tableName.substring(3);

		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		System.out.println("<!DOCTYPE sqlMap PUBLIC \"-//iBATIS.com//DTD SQL Map 2.0//EN\" " + "\"http://ibatis.apache.org/dtd/sql-map-2.dtd\">");
		System.out.println("<sqlMap namespace=\"" + toCamelCase(ns) + "\">");
		System.out.println();
		System.out.println("\t<typeAlias alias=\"" + ns + "\" type=\"vip.yile.dubaita.domain." + toUpperCaseFirst(toCamelCase(ns)) + "DO\" />");
		System.out.println("\t<typeAlias alias=\"" + ns + "_query\" type=\"vip.yile.dubaita.query." + toUpperCaseFirst(toCamelCase(ns)) + "Query\" />");

		System.out.println("\t<resultMap type=\"" + ns + "\" id=\"" + ns + "_result\">");

		PreparedStatement preparedStmt = connection.prepareStatement(QUERY_SQL);
		preparedStmt.setString(1, tableSchema);
		preparedStmt.setString(2, tableName);

		String columns = "";
		String insertColumns = "";
		String insertValues = "";
		String updateSets = "";

		ResultSet resultSet = preparedStmt.executeQuery();
		while (resultSet.next()) {
			String column = resultSet.getString("COLUMN_NAME");
			String jdbcType = resultSet.getString("DATA_TYPE");
			String columnType = resultSet.getString("COLUMN_TYPE");
			

			String columnType1 = javaBoolean(columnType) ? "tinyint1" : jdbcType;
			boolean autoIncrement = "auto_increment".equalsIgnoreCase(resultSet.getString("EXTRA"));

			System.out.println("\t\t<result property=\"" + toCamelCase(column) + "\" column=\"" + column + "\" javaType=\"" + JAVA_TYPE.get(columnType1)
					+ "\" jdbcType=\"" + ("int".equalsIgnoreCase(jdbcType) ? "INTEGER" : jdbcType.toUpperCase()) + "\" /> <!--" + columnType + "-->");

			columns += (StringUtils.isEmpty(columns)) ? "`" + column + "`" : ", `" + column + "`";
			if (!autoIncrement) {
				insertColumns += (StringUtils.isEmpty(insertColumns)) ? "`" + column + "`" : ", `" + column + "`";

				// 处理两个特殊的字段
				if ("gmt_create".equalsIgnoreCase(column) || "gmt_modified".equalsIgnoreCase(column)) {
					insertValues += (StringUtils.isEmpty(insertValues)) ? "now()" : ", now()";
				} else {
					insertValues += (StringUtils.isEmpty(insertValues)) ? "#" + toCamelCase(column) + "#" : ", #" + toCamelCase(column) + "#";
					updateSets += (StringUtils.isEmpty(updateSets)) ? "`" + column + "`=#" + toCamelCase(column) + "#"
							: ", `" + column + "`=#" + toCamelCase(column) + "#";
				}
			}
		}

		System.out.println("\t</resultMap>");

		System.out.println();
		System.out.println("\t<insert id=\"INSERT\" parameterClass=\"" + ns + "\">");
		System.out.println("\tINSERT INTO `" + tableName + "`(" + insertColumns + ") VALUES (" + insertValues + ");");
		System.out.println("\t\t<selectKey resultClass=\"int\" keyProperty=\"id\">" + "select last_insert_id() as id" + "</selectKey>");
		System.out.println("\t</insert>");

		System.out.println();
		System.out.println("\t<update id=\"UPDATE\" parameterClass=\"" + ns + "\">");
		System.out.println("\tUPDATE `" + tableName + "` SET " + updateSets + ", `gmt_modified`=now() WHERE `id`=#id#");
		System.out.println("\t</update>");

		System.out.println();
		System.out.println("\t<delete id=\"DELETE\" parameterClass=\"int\">");
		System.out.println("\tDELETE FROM `" + tableName + "` WHERE `id`=#id#");
		System.out.println("\t</delete>");

		System.out.println();
		System.out.println("\t<!-- ====== SELECT statement  ======= -->");
		System.out.println("\t<sql id=\"column_list\">");
		System.out.println("\t" + columns);
		System.out.println("\t</sql>");

		System.out.println();
		System.out.println("\t<sql id=\"limit_condition\">");
		System.out.println("\tORDER BY `gmt_create` DESC LIMIT #startPos#, #pageSize#");
		System.out.println("\t</sql>");

		System.out.println();
		System.out.println("\t<sql id=\"query_condition\">");
		System.out.println("\t\t<dynamic prepend=\"WHERE\">\r\n");
		System.out.println("\t\t</dynamic>");
		System.out.println("\t</sql>");

		System.out.println();
		System.out.println("\t<select id=\"FIND_BY_ID\" parameterClass=\"int\" resultMap=\"" + ns + "_result\">");
		System.out.println("\tSELECT");
		System.out.println("\t\t<include refid=\"column_list\" />");
		System.out.println("\t\tFROM `" + tableName + "` WHERE `id`= #id#");
		System.out.println("\t</select>");

		System.out.println();
		System.out.println("\t<select id=\"LIST_BY_QUERY\" parameterClass=\"" + ns + "_query\" resultMap=\"" + ns + "_result\">");
		System.out.println("\tSELECT");
		System.out.println("\t\t<include refid=\"column_list\" />");
		System.out.println("\t\tFROM `" + tableName + "`");
		System.out.println("\t\t\t<include refid=\"query_condition\" />");
		System.out.println("\t\t\t<include refid=\"limit_condition\" />");
		System.out.println("\t</select>");

		System.out.println();
		System.out.println("\t<select id=\"COUNT_BY_QUERY\" parameterClass=\"" + ns + "_query\" resultClass=\"int\">");
		System.out.println("\tSELECT COUNT(*) FROM `" + tableName + "`");
		System.out.println("\t\t<include refid=\"query_condition\" />");
		System.out.println("\t</select>");

		System.out.println();
		System.out.println("</sqlMap>");
	}

}
