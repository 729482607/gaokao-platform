package com.C1200.CollegeScoreLib.service;

import java.util.List;

import com.C1200.CollegeScoreLib.dao.impl.ProvinceBatchScoreDaoImpl;
import com.C1200.CollegeScoreLib.dao.impl.ProvinceDaoImpl;
import com.C1200.CollegeScoreLib.entity.Province;
import com.C1200.CollegeScoreLib.entity.ProvinceBatchScore;


public class ProvinceService {
	
	private ProvinceDaoImpl provinceDao = new ProvinceDaoImpl();
	private ProvinceBatchScoreDaoImpl provinceBatchScoreDao = new ProvinceBatchScoreDaoImpl();
	
	public List<ProvinceBatchScore> getAllProvinceBatchScore()
	{
		List<ProvinceBatchScore> pllist = provinceBatchScoreDao.getAllProvinceBatchScore();
		if(pllist != null && pllist.size()>0)
		{
			return pllist;
		}
		return null;
	}
	
	public List<ProvinceBatchScore> getProvinceBatchScoreByProvinceAndYear(String province_name, String year)
	{
		int province_id = getProvinceIDByName(province_name);
		List<ProvinceBatchScore> pllist = provinceBatchScoreDao.getProvinceBatchScoreByProvinceAndYear(province_id, year);
		if(pllist != null && pllist.size()>0)
		{
			return pllist;
		}
		return null;
	}
	
	public List<Province> getAllProvince()
	{
		List<Province> pllist = provinceDao.getAllProvince();
		if(pllist != null && pllist.size()>0)
		{
			return pllist;
		}
		return null;
	}
	
	public int getProvinceIDByName(String province_name){
		return provinceDao.getIdByProvince(province_name);
	}
	
	public String getProvinceNameByID(int province_id){
		
		return null;
	}
	
	public String getProvinceNameByIDFromList(int province_id, List<Province> allprovince){
		for (int i = 0; i < allprovince.size(); i++) {
			Province province = allprovince.get(i);
			if(province.getProvince_id()==province_id){
				return province.getProvince_name();
			}
		}
		
		return null;
	}
	
	
}
