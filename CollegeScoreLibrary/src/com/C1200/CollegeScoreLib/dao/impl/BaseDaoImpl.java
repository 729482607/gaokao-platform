package com.C1200.CollegeScoreLib.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.C1200.CollegeScoreLib.dao.BaseDao;
import com.C1200.CollegeScoreLib.entity.BaseEntity;
import com.C1200.CollegeScoreLib.utils.JDBCUtils;


/**
 * 可能存在bug
 * @author 
 *
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T>{
	
	private Class<T> clazz;
	private String primaryKey;
	private Boolean increment;
	
	String tableName="";
	static String tablePredix = "tb_";
	public BaseDaoImpl()
	{
		try {
			ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
			clazz=(Class<T>) type.getActualTypeArguments()[0];
			T t=clazz.newInstance();
			Method m=clazz.getDeclaredMethod("getPrimaryKey");
			primaryKey=(String) m.invoke(t);
			increment=(Boolean) clazz.getDeclaredMethod("isPK_Increment").invoke(t);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void saveEntry(T t) throws Exception {
		String simpleName=clazz.getSimpleName();
		StringBuilder sb=new StringBuilder();
		//insert into User (id,username,password,phone,cellphone,address,email)  values (?,?,?,?,?,?,?)
		sb.append("insert into ");
		tableName = getTableName(t, simpleName, tableName);
		sb.append(tableName);
		sb.append(" (");
		String attr="";
		Field[] fields=clazz.getDeclaredFields();
		List<Object> params=new ArrayList<Object>();
		String lastsql=" values (";
		for (Field field : fields) {
			String name=field.getName();
			if(name.trim().startsWith("_"))
			{
				continue;
			}
			if(name.trim().equals(primaryKey)&&increment)    //SQL SERVER insert语句自增主键不用管，也不能用0作假参。MYSQL可用0.
			{
				continue;
			}
			String methodname="";
			if (field.getType() == Boolean.TYPE)
			{
				methodname="is"+name.toUpperCase().substring(0, 1)+name.substring(1);
			}
			else
				methodname="get"+name.toUpperCase().substring(0, 1)+name.substring(1);
			attr+=name+",";
			Method m=clazz.getMethod(methodname);
			params.add(m.invoke(t));
			lastsql+="?,";
			
		}
		attr=attr.substring(0, attr.lastIndexOf(','));
		lastsql=lastsql.substring(0, lastsql.lastIndexOf(','));
		sb.append(attr).append(')').append(lastsql).append(')');
//		System.out.println("saveSql:"+sb.toString());
		excSql(sb.toString(),params.toArray());
	}

	private String getTableName(T t, String simpleName, String tableName) {
		if(t instanceof BaseEntity)
		{
			tableName=((BaseEntity)t).getTableName();
			if(tableName==null||tableName.trim().equals(""))
			{
				tableName=simpleName;
			}
		}
		return "["+tableName+"]";
	}

	@Override
	public void updateEntry(T t) throws Exception{
		
		String simpleName=clazz.getSimpleName();
		StringBuilder sb=new StringBuilder();
		String attr="";
		Field[] fields=clazz.getDeclaredFields();
		
		String keyValue="";
		List<Object> params=new ArrayList<Object>();
		
		sb.append("update ");
		tableName = getTableName(t, simpleName, tableName);
		sb.append(tableName);
		sb.append(" set ");
		//update User set  username=? , password=? , phone=? , cellphone=? , address=? , email=?  where id=?
		for (Field field : fields) {
			
			String name=field.getName();
			if(name.trim().startsWith("_"))
			{
				continue;
			}
			String methodname="";
			if (field.getType() == Boolean.TYPE)
			{
				methodname="is"+name.toUpperCase().substring(0, 1)+name.substring(1);
			}
			else
				methodname="get"+name.toUpperCase().substring(0, 1)+name.substring(1);
			if(name.trim().equals(primaryKey))
			{
				keyValue=clazz.getMethod(methodname).invoke(t).toString();
				continue;
			}
			
			attr+=(" "+name+"=? ,");
			Method m=clazz.getMethod(methodname);
			params.add(m.invoke(t));
		}
		attr=attr.substring(0, attr.lastIndexOf(","));
		attr+=" where "+primaryKey+"=?";
		sb.append(attr);
		params.add(keyValue);
		//System.out.println("updateSql:"+sb.toString());
		excSql(sb.toString(),params.toArray());
		
	}

	@Override
	public void deleteEntry(Serializable id) throws Exception {
		String simplename=clazz.getSimpleName();
		StringBuilder sb=new StringBuilder();
		sb.append("delete from ");
		sb.append(tablePredix+simplename);
		sb.append(" where ");
		sb.append(primaryKey+"=?");
		
		//System.out.println(sb.toString());
		
		excSql(sb.toString(), id);
		
	}

	@Override
	public T getEntry(Serializable id) throws Exception {
		String simpleName=clazz.getSimpleName();
		String sql="select * from "+(tablePredix+simpleName)+" where "+primaryKey+"=?";
		return getEntry(sql,id);
	}

	@Override
	public List<T> getAllEntrys() throws Exception {
		String simpleName=clazz.getSimpleName();
		String sql="select * from "+(tablePredix+simpleName);
		return getEntrys(sql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getEntrys(String sql, Object... objs) throws Exception {
		QueryRunner qr=new QueryRunner();
		return (List<T>) qr.query(JDBCUtils.getConnection(),sql, new BeanListHandler(clazz),
				objs);
		
	}
	
//	public List<T> getEntrys2(String sql, Object... objs) throws Exception {
//		QueryRunner qr=new QueryRunner();
//		return (List<T>) qr.query(JDBCUtils.getConnection(),sql, new BeanListHandler(clazz),
//				objs);
//		
//	}

	@SuppressWarnings("unchecked")
	@Override
	public T getEntry(String sql, Object... objs) throws Exception {
		QueryRunner qr=new QueryRunner();
		return (T) qr.query(JDBCUtils.getConnection(),sql, new BeanHandler(clazz), objs);
	}

	@Override
	public void excSql(String sql, Object... objs) throws Exception {
		QueryRunner qr=new QueryRunner();
		qr.update(JDBCUtils.getConnection(),sql,objs);
	}

	@Override
	public Object excSql_retValue(String sql,ResultSetHandler sh, Object... objs) throws Exception {
		QueryRunner qr=new QueryRunner();
		return qr.query(JDBCUtils.getConnection(),sql,sh,objs);
	}

	@Override
	public void deleteEntry(String sql, Object... objs) throws Exception {
		QueryRunner qr=new QueryRunner();
		qr.query(JDBCUtils.getConnection(),sql, new BeanHandler(clazz), objs);
	}
	
	
	//@代号：ljt 
	@Override
	public String getSQLqueryString(T t) throws Exception {
		
		String QueryString="";
//		String attr="";
//		String attr_value="";
		Field[] fields=clazz.getDeclaredFields();
		for (Field field : fields) {
			String name=field.getName();
			if(name.trim().startsWith("_"))
			{
				continue;
			}
			if(name.trim().equals(primaryKey)&&increment)    //SQL SERVER insert语句自增主键不用管，也不能用0作假参。MYSQL可用0.
			{
				continue;
			}
			String methodname="";
			if (field.getType() == Boolean.TYPE)
			{
				methodname="is"+name.toUpperCase().substring(0, 1)+name.substring(1);
			}
			else
				methodname="get"+name.toUpperCase().substring(0, 1)+name.substring(1);
			Method m=clazz.getMethod(methodname);
			if(field.getType() == Integer.TYPE){
				int int_value = (Integer)m.invoke(t);
				if (int_value!=0)
				{
					QueryString += name+"="+String.valueOf(int_value)+" and ";
//					attr += name+"=? and ";
//					attr_value += String.valueOf(int_value)+",";
				}
			}
			else{
				String str_value =  (String)m.invoke(t);
				if(str_value != null && !str_value.equals("")){
					QueryString += name+"='"+str_value+"' and ";
//					attr += name+"=? and ";
//					attr_value += str_value+",";
				}
			}
		}
		if(QueryString.indexOf('a')>0){
			QueryString = QueryString.substring(0, QueryString.lastIndexOf('a')-1);
		}
//		attr = attr.substring(0, attr.lastIndexOf('a')-1);
//		attr_value = attr_value.substring(0, attr_value.lastIndexOf(','));
//		QueryString = attr+"--"+attr_value;
		return QueryString;
	}


}
