package com.C1200.CollegeScoreLib.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.C1200.CollegeScoreLib.dao.BaseDao;
import com.C1200.CollegeScoreLib.entity.TouDangXian;

public class TouDangXianDaoImpl extends BaseDaoImpl<TouDangXian> implements BaseDao<TouDangXian>{
	
	private long itemSize=0;
	
	//获取数据库tb_TouDangXian表中所有的数据
	public List<TouDangXian> getAllTouDangXian()   //@代号：ljt 
	{
		List<TouDangXian> list = new ArrayList<TouDangXian>();
		try {
			list = super.getAllEntrys();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	//@代号：ljt 
	public List<TouDangXian> getTouDangXianByAttrs(TouDangXian tdx, int page, int size){
		List<TouDangXian> list = new ArrayList<TouDangXian>();	
		int pass_count = size*(page-1);
		String sql = "select * from tb_TouDangXian";
		String SQLqueryString = "";
		try {
			SQLqueryString = super.getSQLqueryString(tdx); 
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
	public JSONObject getTouDangXianJSONObject(TouDangXian tdx){
		try {
			return super.getJSONObject(tdx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//@代号：ljt 
	public long getTouDangXianSizeByAttrs(TouDangXian tdx)
	{
		long size=0;	
		String sql = "select count(*) from tb_TouDangXian";
		String SQLqueryString = "";
		try {
			SQLqueryString = super.getSQLqueryString(tdx); 
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
	
	
	public long getItemSize() {
		return itemSize;
	}

	public void setItemSize(long itemSize) {
		this.itemSize = itemSize;
	}
}
