package com.C1200.CollegeScoreLib.service;

import java.util.List;

import com.C1200.CollegeScoreLib.dao.impl.SchoolDaoImpl;
import com.C1200.CollegeScoreLib.entity.School;

public class CollegeService {
	private SchoolDaoImpl collegeDaoImpl = new SchoolDaoImpl();
	
	public List<School> getAllCollege()
	{
		List<School> Clist = collegeDaoImpl.getAllSchool();
		if(Clist != null && Clist.size()>0)
		{
			return Clist;
		}
		return null;
	}
}
