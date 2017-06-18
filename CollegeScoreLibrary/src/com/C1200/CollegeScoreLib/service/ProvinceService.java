package com.C1200.CollegeScoreLib.service;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.C1200.CollegeScoreLib.dao.impl.ProvinceBatchScoreDaoImpl;
import com.C1200.CollegeScoreLib.dao.impl.ProvinceDaoImpl;
import com.C1200.CollegeScoreLib.entity.Province;
import com.C1200.CollegeScoreLib.entity.ProvinceBatchScore;


public class ProvinceService {
	
	private ProvinceDaoImpl provinceDao = new ProvinceDaoImpl();
	private ProvinceBatchScoreDaoImpl provinceBatchScoreDao = new ProvinceBatchScoreDaoImpl();
	
	
	
	public List<ProvinceBatchScore> getAllProvinceBatchScore()		//@代号：ljt 
	{
		List<ProvinceBatchScore> pllist = provinceBatchScoreDao.getAllProvinceBatchScore();
		if(pllist != null && pllist.size()>0)
		{
			return pllist;
		}
		return null;
	}

	//@代号：ljt 
	public List<ProvinceBatchScore> getProvinceBatchScoreByAttrs(ProvinceBatchScore pbs, int page, int size)
	{
		List<ProvinceBatchScore> pllist = provinceBatchScoreDao.getProvinceBatchScoreByAttrs(pbs,page,size);
		if(pllist != null && pllist.size()>0)
		{
			return pllist;
		}
		return null;
	}
	
	
	//@代号：ljt 
	public List<Province> getAllProvince()
	{
		List<Province> pllist = provinceDao.getAllProvince();
		if(pllist != null && pllist.size()>0)
		{
			return pllist;
		}
		return null;
	}
	
	
	//@代号：ljt
	public JSONObject getProvinceBatchScoreJSONObject(ProvinceBatchScore pbs){
		return provinceBatchScoreDao.getProvinceBatchScoreJSONObject(pbs);
	}
	
	
	//@代号：ljt 
	public int getProvinceIdByProvinceNmae(String province_name){
		return provinceDao.getProvinceIdByProvinceNmae(province_name);
	}
	
	//@代号：ljt
	public String getProvinceNameByProvinceId(int province_id){
		return provinceDao.getProvinceNameByProvinceId(province_id);
	}
	
	//@代号：ljt 
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
