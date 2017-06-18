package com.C1200.CollegeScoreLib.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.C1200.CollegeScoreLib.dao.BaseDao;
import com.C1200.CollegeScoreLib.entity.ProvinceBatchScore;

public class ProvinceBatchScoreDaoImpl extends BaseDaoImpl<ProvinceBatchScore> implements BaseDao<ProvinceBatchScore>{

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
	
	//@代号：ljt 
	public List<ProvinceBatchScore> getProvinceBatchScoreByAttrs(ProvinceBatchScore pbs, int page, int size){
		List<ProvinceBatchScore> list = new ArrayList<ProvinceBatchScore>();	
		int pass_count = size*(page-1);
		String sql = "select * from tb_ProvinceBatchScore where ";
		try {
			sql+=super.getSQLqueryString(pbs);
			if(page>0 && size>0){
				sql+=" limit "+String.valueOf(pass_count)+","+String.valueOf(size);
			}
			list = super.getEntrys(sql);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	//@代号：ljt    
	//这是一个示例，实际不需要了
//	public List<ProvinceBatchScore> getProvinceBatchScoreByProvinceAndYear(int province_id, String year){
//		List<ProvinceBatchScore> list = new ArrayList<ProvinceBatchScore>();
//		try {
//			list = super.getEntrys("select * from tb_ProvinceBatchScore where province_id=? and year = ?",province_id,year);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
		
}
