package com.C1200.CollegeScoreLib.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.C1200.CollegeScoreLib.dao.BaseDao;
import com.C1200.CollegeScoreLib.entity.Province;

public class ProvinceDaoImpl extends BaseDaoImpl<Province> implements BaseDao<Province>{
	
	public List<Province> getAllProvince()   //@代号：ljt 
	{
		List<Province> list = new ArrayList<Province>();
		try {
			list = super.getEntrys("select * from tb_Province");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getProvinceIdByProvinceNmae(String province_name)   //@代号：ljt 
	{
		int id = -1;
		try {
			Province province = getEntry("select * from tb_Province where province_name = ?", province_name);
			id = province.getProvince_id();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public String getProvinceNameById(int province_id)   //@代号：ljt 
	{
		String s_name = "";
		try {
			Province province = getEntry("select * from tb_Province where province_id = ?", province_id);
			s_name = province.getProvince_name();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s_name;
	}
}
