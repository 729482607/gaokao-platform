package com.C1200.CollegeScoreLib.service;

import com.C1200.CollegeScoreLib.dao.impl.CollegeAdmissionScoreDaoImpl;
import com.C1200.CollegeScoreLib.dao.impl.SchoolDaoImpl;
import com.C1200.CollegeScoreLib.dao.impl.ScoreRankDaoImpl;
import com.C1200.CollegeScoreLib.dao.impl.TouDangXianDaoImpl;
import com.C1200.CollegeScoreLib.entity.CollegeAdmissionScore;
import com.C1200.CollegeScoreLib.entity.School;
import com.C1200.CollegeScoreLib.entity.ScoreRank;
import com.C1200.CollegeScoreLib.entity.TouDangXian;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang on 17/6/22.
 */
public class RecommendService {
    private ScoreRankDaoImpl scoreRankDao = new ScoreRankDaoImpl();
    private CollegeAdmissionScoreDaoImpl collegeAdmissionScoreDao = new CollegeAdmissionScoreDaoImpl();
    private SchoolDaoImpl schoolDao = new SchoolDaoImpl();
    private TouDangXianDaoImpl tdxDao = new TouDangXianDaoImpl();

    public List<School> getRecommendSchool(int provinceId, String year, String WL, String score, int offset){
        List<School> schools = new ArrayList<School>();
        String[] rankRange = getRankRange(provinceId, year, WL, score, offset);
        String lastYear = (Integer.valueOf(year) - 1) + "";
        String[] lastYearScoreRange = getScoreRange(provinceId, lastYear, WL, rankRange);
        CollegeAdmissionScore cas = new CollegeAdmissionScore();
        cas.setAdmission_province_id(provinceId);
        cas.setYear(lastYear);
        cas.setWl(WL);
        List<CollegeAdmissionScore> list = collegeAdmissionScoreDao.getCollegeAdmissionScoreByScoreRange(cas, lastYearScoreRange);
        if(list != null){
            for(CollegeAdmissionScore c : list){
                School school = new School();
                school.setSchool_id(c.getSchool_id());
                schools.addAll(schoolDao.getSchoolByAttrs(school));
            }
        }

        return schools;
    }

    public String[] getRankRange(int provinceId, String year, String WL, String score, int offset){
        String[] range = new String[2];
        int scoreValue = Integer.valueOf(score);
        range[0] = getRankByScore(provinceId, year, WL, (scoreValue - offset) + "");
        range[1] = getRankByScore(provinceId, year, WL, (scoreValue + offset) + "");
        return range;
    }

    public String getRankByScore(int provinceId, String year, String WL, String score){
        ScoreRank scoreRank = new ScoreRank();
        scoreRank.setProvince_id(provinceId);
        scoreRank.setYear(year);
        scoreRank.setWl(WL);
        scoreRank.setExtrapointType("1");
        scoreRank.setScore(score);
        List<ScoreRank> list = scoreRankDao.getScoreRankByAttrs(scoreRank);

        if(list != null && list.size() > 0){
        	String numInScore = list.get(0).getNumInScore();
            return String.valueOf(Integer.parseInt(list.get(0).getAccumulateNum())-Integer.parseInt(numInScore)/3*2);
        }

        return null;
    }

    public String[] getScoreRange(int provinceId, String year, String WL, String[] rank){
        String[] scoreRange = new String[2];
        scoreRange[0] = getScoreByRank(provinceId, year, WL, rank[0]);
        scoreRange[1] = getScoreByRank(provinceId, year, WL, rank[1]);
        return scoreRange;
    }
    
    //此方法效率有待改进
    public String getScoreByRank(int provinceId, String year, String WL, String rank){
        ScoreRank scoreRank = new ScoreRank();
        scoreRank.setProvince_id(provinceId);
        scoreRank.setYear(year);
        scoreRank.setWl(WL);
        List<ScoreRank> list =  scoreRankDao.getScoreRankByAttrs(scoreRank);
        if(list != null){
            for(int i = 0; i < list.size()-1; i ++){
                int rank1 = Integer.valueOf(list.get(i).getAccumulateNum());
                int rank2 = Integer.valueOf(list.get(i + 1).getAccumulateNum());
                int r = Integer.valueOf(rank);
                if(rank1 <= r && r <= rank2){
                	if((r-rank1)>=(rank2-r))
                		return list.get(i + 1).getScore();
                	else
                		return list.get(i).getScore();
                }
            }
        }
        return null;
    }
    
    public List<TouDangXian> getCollegeListWithTDX(int province_id, String year, String wl, String score, int offset){
    	List<TouDangXian> list = null;
    	TouDangXian tdx = new TouDangXian();
    	
    	tdx.setProvince_id(province_id);
    	tdx.setYear(year);
    	tdx.setWl(wl);
    	list = tdxDao.getTouDangXianScopeByScore(tdx, score, offset);
    	if(list != null){
    		return list;
    	}
    	return null;
    	
    }
    
    public String getRiskType(int lastYear_stu_score, int tdx_score){
    	String riskType = "";
    	if(tdx_score>lastYear_stu_score+5){
    		riskType = "赌博型";
    	}
    	if(tdx_score>lastYear_stu_score+2 && tdx_score<=lastYear_stu_score+5){
    		riskType = "大冲刺";
    	}
    	if(tdx_score>lastYear_stu_score && tdx_score<=lastYear_stu_score+2){
    		riskType = "小冲刺";
    	}
    	if(tdx_score>lastYear_stu_score-3 && tdx_score<=lastYear_stu_score){
    		riskType = "稳定型";
    	}
    	if(tdx_score>lastYear_stu_score-6 && tdx_score<=lastYear_stu_score-3){
    		riskType = "稳妥型";
    	}
    	if(tdx_score<=lastYear_stu_score-6){
    		riskType = "保底型";
    	}
    	
    	return riskType;
    }
}
