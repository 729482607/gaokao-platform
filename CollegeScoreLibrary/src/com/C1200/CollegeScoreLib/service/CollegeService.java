package com.C1200.CollegeScoreLib.service;

import java.util.List;

import com.C1200.CollegeScoreLib.dao.impl.SchoolDaoImpl;
import com.C1200.CollegeScoreLib.entity.Province;
import com.C1200.CollegeScoreLib.entity.School;

public class CollegeService {
	private SchoolDaoImpl schoolDaoImpl = new SchoolDaoImpl();
	
	public List<School> getAllCollege()		//@代号：ljt 
	{
		List<School> Clist = schoolDaoImpl.getAllSchool();
		if(Clist != null && Clist.size()>0)
		{
			return Clist;
		}
		return null;
	}

    //@代号：ytl
	public int getCollegeIdByName(String name) {
		int id = schoolDaoImpl.getSchoolIdBySchoolName(name);
		return id;
	}
	
    //@代号：ljt
	public String getCollegeNameById(int school_id) {
		String name = schoolDaoImpl.getSchoolNameBySchoolId(school_id);
		return name;
	}
	
	
	//@代号：ljt 
	public String getSchoolNameByIDFromList(int school_id, List<School> allschool){
		for (int i = 0; i < allschool.size(); i++) {
			School school = allschool.get(i);
			if(school.getSchool_id()==school_id){
				return school.getSchool_name();
			}
		}
		return null;
	}
}
