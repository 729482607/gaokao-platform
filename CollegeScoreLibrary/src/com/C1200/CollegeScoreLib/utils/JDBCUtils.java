package com.C1200.CollegeScoreLib.utils;

import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 *
 * @param <T>
 */
public class JDBCUtils {
	
	
	private static  ComboPooledDataSource datasource=null;
	private static ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>();
	static 
	{
		datasource=new ComboPooledDataSource();
	}
	
	public static ComboPooledDataSource getDataSource()
	{
		return datasource;
	}
	
	public static Connection getConnection() 
	{
		try {
			Connection conn=null;
			if(threadLocal.get()==null)
			{
				conn=datasource.getConnection();
				threadLocal.set(conn);
			}else {
				conn=threadLocal.get();
			}
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
	}
	

	public static void StartTransaction()
	{
		try {
			Connection conn=getConnection();
			if(conn!=null)
			{
				conn.setAutoCommit(false);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
	}
	
	public static void commit()
	{
		try {
			Connection conn=getConnection();
			if(conn==null)
				return ;
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	public static void rollback()
	{
		try {
			Connection conn=getConnection();
			if(conn==null)
				return ;
			conn.rollback();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public static void stopTransaction()
	{

		try {
			Connection conn=getConnection();
			if(conn==null)
				return ;
			conn.setAutoCommit(true);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public static void release()
	{
		try {
			Connection conn=threadLocal.get();
			if(conn!=null)
			{
				conn.close();
				threadLocal.remove();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}
