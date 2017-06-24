package com.C1200.CollegeScoreLib.service;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.C1200.CollegeScoreLib.dao.impl.ProvinceBatchScoreDaoImpl;
import com.C1200.CollegeScoreLib.dao.impl.ProvinceDaoImpl;
import com.C1200.CollegeScoreLib.dao.impl.ScoreRankDaoImpl;
import com.C1200.CollegeScoreLib.dao.impl.TouDangXianDaoImpl;
import com.C1200.CollegeScoreLib.entity.Province;
import com.C1200.CollegeScoreLib.entity.ProvinceBatchScore;
import com.C1200.CollegeScoreLib.entity.ScoreRank;
import com.C1200.CollegeScoreLib.entity.TouDangXian;


public class ProvinceService {
	
	private ProvinceDaoImpl provinceDao = new ProvinceDaoImpl();
	private ProvinceBatchScoreDaoImpl provinceBatchScoreDao = new ProvinceBatchScoreDaoImpl();
	private ScoreRankDaoImpl scoreRankDao = new ScoreRankDaoImpl();
	private TouDangXianDaoImpl tdxDao = new TouDangXianDaoImpl();
	
	
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
	public long getProvinceBatchScoreSizeByAttrs(ProvinceBatchScore pbs)
	{
		long size = provinceBatchScoreDao.getProvinceBatchScoreSizeByAttrs(pbs);
		if(size>0)
			return size;
		return 0;
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
	public int getProvinceIdByProvinceName(String province_name){
		return provinceDao.getProvinceIdByProvinceName(province_name);
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
	
	//@代号：ytl 
	public List<ScoreRank> getScoreRankByAttrs(ScoreRank sr){
		List<ScoreRank> list = scoreRankDao.getScoreRankByAttrs(sr);
		if(list != null && list.size()>0)
		{
			return list;
		}
		return null;
	}
	
	//@代号：ljt 
	public List<TouDangXian> getTouDangXianByAttrs(TouDangXian tdx, int page, int size){
		List<TouDangXian> list = tdxDao.getTouDangXianByAttrs(tdx, page, size);
		if(list != null && list.size()>0)
		{
			return list;
		}
		return null;
	}
	
	//@代号：ljt
	public JSONObject getTouDangXianJSONObject(TouDangXian tdx){
		return tdxDao.getTouDangXianJSONObject(tdx);
	}
	
	//@代号：ljt 
	public long getTouDangXianSizeByAttrs(TouDangXian tdx)
	{
		long size = tdxDao.getTouDangXianSizeByAttrs(tdx);
		if(size>0)
			return size;
		return 0;
	}
	
	
}
