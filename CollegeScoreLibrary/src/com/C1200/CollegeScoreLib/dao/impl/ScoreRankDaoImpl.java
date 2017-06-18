package com.C1200.CollegeScoreLib.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.C1200.CollegeScoreLib.dao.BaseDao;
import com.C1200.CollegeScoreLib.entity.ProvinceBatchScore;
import com.C1200.CollegeScoreLib.entity.ScoreRank;

public class ScoreRankDaoImpl extends BaseDaoImpl<ScoreRank> implements BaseDao<ScoreRank>{
	public List<ScoreRank> getScoreRankByAttrs(ScoreRank sRank){
		List<ScoreRank> list = new ArrayList<ScoreRank>();	
		String sql = "select * from tb_ScoreRank where ";
		try {
			sql += super.getSQLqueryString(sRank);    //getSQLqueryString()根据类实例的各属性的值是否为有效值，来拼接sql查询语句
			System.out.println(sql);
			list = super.getEntrys(sql);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
