package com.C1200.CollegeScoreLib.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.C1200.CollegeScoreLib.dao.BaseDao;
//import com.C1200.CollegeScoreLib.entity.ProvinceBatchScore;
import com.C1200.CollegeScoreLib.entity.School;


/**
* @author jiangtao
* @purpose 实体类对应的数据库操作类：学校 
*/
public class SchoolDaoImpl extends BaseDaoImpl<School> implements BaseDao<School>{

	
	//获取数据库tb_school表中所有的数据
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
	
	//根据学校名获取学校ID
	public int getSchoolIdBySchoolName(String school_name)   //@代号：ljt 
	{
		if(school_name == null){
			return 0;
		}
		int id = 0;
		try {
			School school = getEntry("select * from tb_School where school_name = ?", school_name);
			if(school != null){
                id = school.getSchool_id();
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	
	//根据学校ID获取学校名
	public String getSchoolNameBySchoolId(int school_id)   //@代号：ljt 
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
