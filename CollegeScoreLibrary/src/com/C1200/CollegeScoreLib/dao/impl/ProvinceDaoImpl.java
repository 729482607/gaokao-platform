package com.C1200.CollegeScoreLib.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.C1200.CollegeScoreLib.dao.BaseDao;
import com.C1200.CollegeScoreLib.entity.Province;


/**
* @author jiangtao
* @purpose 实体类对应的数据库操作类：省 
*/
public class ProvinceDaoImpl extends BaseDaoImpl<Province> implements BaseDao<Province>{
	
	
	//获取数据库tb_province表中所有的数据
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
	
	
	//根据省名称获取省ID
	public int getProvinceIdByProvinceNmae(String province_name)   //@代号：ljt 
	{
		int id = 0;
		try {
			Province province = getEntry("select * from tb_Province where province_name = ?", province_name);
			id = province.getProvince_id();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	
	//根据省ID获取省名称
	public String getProvinceNameByProvinceId(int province_id)   //@代号：ljt 
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
