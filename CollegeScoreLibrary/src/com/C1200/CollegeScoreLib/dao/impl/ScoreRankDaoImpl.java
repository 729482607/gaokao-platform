package com.C1200.CollegeScoreLib.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.C1200.CollegeScoreLib.dao.BaseDao;
import com.C1200.CollegeScoreLib.entity.ScoreRank;


public class ScoreRankDaoImpl extends BaseDaoImpl<ScoreRank> implements BaseDao<ScoreRank>{
	public List<ScoreRank> getScoreRankByAttrs(ScoreRank sRank){
		List<ScoreRank> list = new ArrayList<ScoreRank>();	
		String sql = "select * from tb_ScoreRank where ";
		try {
			sql += super.getSQLqueryString(sRank);    //getSQLqueryString()根据类实例的各属性的值是否为有效值，来拼接sql查询语句
			list = super.getEntrys(sql);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//@代号：ljt 
	public JSONObject getScoreRankJSONObject(ScoreRank sr){
		try {
			return super.getJSONObject(sr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//@代号：ljt 
	public long getScoreRankSizeByAttrs(ScoreRank sr)
	{
		long size=0;	
		String sql = "select count(*) from tb_ScoreRank";
		String SQLqueryString = "";
		try {
			SQLqueryString = super.getSQLqueryString(sr); 
			if(!SQLqueryString.equals("") && SQLqueryString!=null){
				sql+=" where "+SQLqueryString;    //getSQLqueryString()根据类实例的各属性的值是否为有效值，来拼接sql查询语句
			}
			size = excSql_retSize(sql);

	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}
	

}
