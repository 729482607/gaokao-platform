package com.C1200.CollegeScoreLib.service;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.C1200.CollegeScoreLib.dao.impl.CollegeAdmissionScoreDaoImpl;
import com.C1200.CollegeScoreLib.dao.impl.SchoolDaoImpl;
import com.C1200.CollegeScoreLib.entity.CollegeAdmissionScore;
import com.C1200.CollegeScoreLib.entity.Province;
import com.C1200.CollegeScoreLib.entity.School;
import com.C1200.CollegeScoreLib.entity.TouDangXian;

public class CollegeService {
	private SchoolDaoImpl schoolDaoImpl = new SchoolDaoImpl();
	private CollegeAdmissionScoreDaoImpl casDao = new CollegeAdmissionScoreDaoImpl();
	
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
	
	
	//@代号：ljt 
	public List<CollegeAdmissionScore> getCollegeAdmissionScoreByAttrs(CollegeAdmissionScore collegeAdmissionScore, int page, int size){
		List<CollegeAdmissionScore> list = casDao.getCollegeAdmissionScoreByAttrs(collegeAdmissionScore, page, size);
		if(list != null && list.size()>0)
		{
			return list;
		}
		return null;
	}
	
	
	//@代号：ljt 
	public long getCollegeAdmissionScoreSizeByAttrs(CollegeAdmissionScore collegeAdmissionScore){
		long size = casDao.getCollegeAdmissionScoreSizeByAttrs(collegeAdmissionScore);
		if(size>0)
			return size;
		return 0;	
	}
	
	//@代号：ljt
	public JSONObject getCollegeAdmissionScoreJSONObject(CollegeAdmissionScore cas){
		return casDao.getCollegeAdmissionScoreJSONObject(cas);
	}

	//@代号：ytl
	public List<CollegeAdmissionScore> getCollegeAdmissionScoreByScoreRange(CollegeAdmissionScore cas, String[] scoreRange){
		List<CollegeAdmissionScore> list = casDao.getCollegeAdmissionScoreByScoreRange(cas, scoreRange);
		if(list != null){
			return list;
		}

		return null;
	}
}
