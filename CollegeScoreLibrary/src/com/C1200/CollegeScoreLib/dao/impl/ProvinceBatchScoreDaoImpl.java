package com.C1200.CollegeScoreLib.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.codehaus.jettison.json.JSONObject;

import com.C1200.CollegeScoreLib.dao.BaseDao;
import com.C1200.CollegeScoreLib.entity.ProvinceBatchScore;


/**
* @author jiangtao
* @purpose 实体类对应的数据库操作类：省批次线 
*/
public class ProvinceBatchScoreDaoImpl extends BaseDaoImpl<ProvinceBatchScore> implements BaseDao<ProvinceBatchScore>{

	private int itemSize=0;

	//获取数据库tb_ProvinceBatchScore表中所有的数据
	public List<ProvinceBatchScore> getAllProvinceBatchScore()   //@代号：ljt 
	{
		List<ProvinceBatchScore> list = new ArrayList<ProvinceBatchScore>();
		try {
			list = super.getAllEntrys();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	/**
	* @funciton 根据不同的查询条件查询省批次线数据，查询参数一般有省，年份，文理科，批次          若包含分页查询，则参数还有  页数，每页个数
	* @param    pbs ProvinceBatchScore类实例，通过实例中的各属性的值来组合得到查询条件
	* @param	page 用于分页查询，表示查询第几页
	* @param	size 用于分页查询，表示每页的数目
	*/
	//@代号：ljt 
	public List<ProvinceBatchScore> getProvinceBatchScoreByAttrs(ProvinceBatchScore pbs, int page, int size){
		List<ProvinceBatchScore> list = new ArrayList<ProvinceBatchScore>();	
		int pass_count = size*(page-1);
		String sql = "select * from tb_ProvinceBatchScore";
		String SQLqueryString = "";
		try {
			SQLqueryString = super.getSQLqueryString(pbs); 
			if(!SQLqueryString.equals("") && SQLqueryString!=null){
				sql+=" where "+SQLqueryString;    //getSQLqueryString()根据类实例的各属性的值是否为有效值，来拼接sql查询语句
				if(page>0 && size>0){
					sql+=" limit "+String.valueOf(pass_count)+","+String.valueOf(size);
				}
			}
			list = super.getEntrys(sql);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	//@代号：ljt 
	public int getProvinceBatchScoreSizeByAttrs(ProvinceBatchScore pbs)
	{
		int size=0;	
		String sql = "select count(*) from tb_ProvinceBatchScore";
		String SQLqueryString = "";
		try {
			SQLqueryString = super.getSQLqueryString(pbs); 
			if(!SQLqueryString.equals("") && SQLqueryString!=null){
				sql+=" where "+SQLqueryString;    //getSQLqueryString()根据类实例的各属性的值是否为有效值，来拼接sql查询语句
			}
			size = excSql_retSize(sql);
			this.itemSize = size;
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}
	
	
	//@代号：ljt 
	public JSONObject getProvinceBatchScoreJSONObject(ProvinceBatchScore pbs){
		try {
			return super.getJSONObject(pbs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int getItemSize() {
		return itemSize;
	}

	public void setItemSize(int itemSize) {
		this.itemSize = itemSize;
	}
		
}
