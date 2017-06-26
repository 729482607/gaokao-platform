package com.C1200.CollegeScoreLib.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.C1200.CollegeScoreLib.dao.BaseDao;
import com.C1200.CollegeScoreLib.entity.CollegeAdmissionScore;


public class CollegeAdmissionScoreDaoImpl extends BaseDaoImpl<CollegeAdmissionScore> implements BaseDao<CollegeAdmissionScore>{
    
	private long itemSize=0;
	//@代号：ljt 
	public List<CollegeAdmissionScore> getCollegeAdmissionScoreByAttrs(CollegeAdmissionScore collegeAdmissionScore, int page, int size){

        List<CollegeAdmissionScore> list = new ArrayList<CollegeAdmissionScore>();
        String sql = "select * from tb_CollegeAdmissionScore";
        int pass_count = size*(page-1);
        String SQLqueryString = "";
        
        try {
        	SQLqueryString = super.getSQLqueryString(collegeAdmissionScore);
        	if(!SQLqueryString.equals("") && SQLqueryString!=null){
        		sql+=" where "+SQLqueryString;
				if(page>0 && size>0){
					sql+=" limit "+String.valueOf(pass_count)+","+String.valueOf(size);
				}
	            list = super.getEntrys(sql);
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
	
	//@代号：ljt 
	public long getCollegeAdmissionScoreSizeByAttrs(CollegeAdmissionScore collegeAdmissionScore){

		long size=0;	
        String sql = "select count(*) from tb_CollegeAdmissionScore";
        String SQLqueryString = "";
        try {
        	SQLqueryString = super.getSQLqueryString(collegeAdmissionScore);
        	if(!SQLqueryString.equals("") && SQLqueryString!=null){
        		sql+=" where "+SQLqueryString;
        	}
			size = excSql_retSize(sql);
			this.itemSize = size;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
	
	
	//@代号：ljt 
	public JSONObject getCollegeAdmissionScoreJSONObject(CollegeAdmissionScore cas){
		try {
			return super.getJSONObject(cas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<CollegeAdmissionScore> getCollegeAdmissionScoreByScoreRange(CollegeAdmissionScore cas, String[] rang){
        List<CollegeAdmissionScore> list = new ArrayList<CollegeAdmissionScore>();
	    String sql = "select * from tb_CollegeAdmissionScore where ";
	    if(Integer.valueOf(rang[0]) > Integer.valueOf(rang[1])){
	        String temp = rang[0];
	        rang[0] = rang[1];
	        rang[1] = temp;
        }
        try {
            sql += super.getSQLqueryString(cas);
            sql += "and TDX_score BETWEEN " + rang[0] + " and " + rang[1];
            System.out.println(sql);
            list = super.getEntrys(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
	
	
	
	public long getItemSize() {
		return itemSize;
	}

	public void setItemSize(long itemSize) {
		this.itemSize = itemSize;
	}
}
