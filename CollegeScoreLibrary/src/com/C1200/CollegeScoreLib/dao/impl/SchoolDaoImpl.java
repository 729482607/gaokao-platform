package com.C1200.CollegeScoreLib.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.C1200.CollegeScoreLib.dao.BaseDao;
//import com.C1200.CollegeScoreLib.entity.ProvinceBatchScore;
import com.C1200.CollegeScoreLib.entity.School;

public class SchoolDaoImpl extends BaseDaoImpl<School> implements BaseDao<School>{

	public List<School> getAllSchool()   //@代号：ljt 
	{
		List<School> list = new ArrayList<School>();
		try {
			list = super.getEntrys("select * from tb_School");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getIdBySchool(String school_name)   //@代号：ljt 
	{
		int id = -1;
		try {
			School school = getEntry("select * from tb_School where school_name = ?", school_name);
			id = school.getSchool_id();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public String getSchoolNameById(int school_id)   //@代号：ljt 
	{
		String s_name = "";
		try {
			School school = getEntry("select * from tb_School where school_id = ?", school_id);
			s_name = school.getSchool_name();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s_name;
	}
}
