package com.C1200.CollegeScoreLib.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
/**
 *
 * @param <T>
 */
public interface BaseDao<T> {
	
	public void saveEntry(T t) throws Exception;
	public void updateEntry(T t) throws Exception;
	public void deleteEntry(Serializable id) throws Exception;
	public void deleteEntry(String sql,Object... objs)  throws Exception;
	public T getEntry(Serializable id)  throws Exception;
	public List<T> getAllEntrys()  throws Exception;
	public List<T> getEntrys(String sql,Object... objs)throws Exception;
	public T getEntry(String sql,Object... objs)  throws Exception;
	public void excSql(String sql,Object... objs)throws Exception;
	public Object excSql_retValue(String sql,ResultSetHandler rh,Object... objs) throws Exception;

}
