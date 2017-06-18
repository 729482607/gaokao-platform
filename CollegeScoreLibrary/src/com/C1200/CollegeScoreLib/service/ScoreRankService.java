package com.C1200.CollegeScoreLib.service;

import java.util.List;

import com.C1200.CollegeScoreLib.dao.impl.ScoreRankDaoImpl;
import com.C1200.CollegeScoreLib.entity.ProvinceBatchScore;
import com.C1200.CollegeScoreLib.entity.ScoreRank;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ScoreRankService {
	private ScoreRankDaoImpl scoreRankDao = new ScoreRankDaoImpl();
	//@代号：ytl 
	public List<ScoreRank> getScoreRankByAttrs(ScoreRank sr){
		List<ScoreRank> list = scoreRankDao.getScoreRankByAttrs(sr);
		if(list != null && list.size()>0)
		{
			return list;
		}
		return null;
	}
}
