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
			list = super.getEntrys("select * from tb_ProvinceBatchScore");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProvinceBatchScore> getProvinceBatchScoreByProvinceAndYear(int province_id, String year){
		List<ProvinceBatchScore> list = new ArrayList<ProvinceBatchScore>();
		try {
			list = super.getEntrys("select * from tb_ProvinceBatchScore where province_id=? and year = ?",province_id,year);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
//	public String getProvinceBatchScoreByProvinceId(int province_id)   //@代号：ljt 
//	{
//		String s_name = "";
//		try {
//			ProvinceBatchScore provinceBatchScore = (ProvinceBatchScore)getEntry("select * from tb_ProvinceBatchScore where province_id = ?", province_id);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return s_name;
//	}	
	
}
