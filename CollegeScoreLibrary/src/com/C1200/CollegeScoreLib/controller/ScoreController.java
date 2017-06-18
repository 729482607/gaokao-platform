package com.C1200.CollegeScoreLib.controller;

import com.C1200.CollegeScoreLib.entity.Province;
import com.C1200.CollegeScoreLib.entity.School;
import com.C1200.CollegeScoreLib.entity.ProvinceBatchScore;
import com.C1200.CollegeScoreLib.service.CollegeService;
import com.C1200.CollegeScoreLib.service.ProvinceService;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;




@Path("/scoreLibrary")
public class ScoreController {
	
	private CollegeService cs = new CollegeService();
	private ProvinceService ps = new ProvinceService();
	
	@GET
	@Path("/getColleges")
	@Produces(MediaType.APPLICATION_JSON)
	public List<School> getColleges(){
		//List<College> Clist= cs.getAllCollege();
		List<School> Clist= new ArrayList<School>();
		Clist.add(new School(0,"is 0"));
		Clist.add(new School(1,"is 1"));
		Clist.add(new School(2,"is 2"));
		return Clist;
	}
	
	//该API调用方式如下：
	//http://localhost:8090/CollegeScoreLibrary/api/scoreLibrary/getProvinceBatchScore?province=湖南&year=2012	
	@GET
	@Path("/getProvinceBatchScore")
	@Produces(MediaType.APPLICATION_JSON)			//@代号：ljt 
	public JSONArray getProvinceBatchScore(@QueryParam("province") String province_name,
			@QueryParam("year") String year, @QueryParam("wl") String WL, @QueryParam("batch") String batch, 
			@QueryParam("page") int page, @QueryParam("size") int size) throws Exception{
		int province_id = 0;
		List<ProvinceBatchScore> PBSlist = null;
		JSONArray ret_jsonarray = new JSONArray();
		JSONObject json = new JSONObject();
		
		ProvinceBatchScore pbs = new ProvinceBatchScore();
		pbs.setYear(year);
		pbs.setWl(WL);
		pbs.setBatch(batch);
		
		//当省份作为查询参数时
		if(province_name!=null && !province_name.equals("")){
			province_id = ps.getProvinceIdByProvinceNmae(province_name);
			pbs.setProvince_id(province_id);
			PBSlist = ps.getProvinceBatchScoreByAttrs(pbs,page,size);
			for (int i = 0; i < PBSlist.size(); i++) {
				json = ps.getProvinceBatchScoreJSONObject(PBSlist.get(i));
				ret_jsonarray.put(json);
			}
			return ret_jsonarray;
		}
		
		//当省份不作为查询参数时，需获取每一个数据中province_id对应的province_name
		else{
			pbs.setProvince_id(province_id);
			PBSlist = ps.getProvinceBatchScoreByAttrs(pbs,page,size);
			System.out.println(PBSlist.size());
			List<Province> allprovince = ps.getAllProvince();
			for (int i = 0; i < PBSlist.size(); i++) {
				province_id = PBSlist.get(i).getProvince_id();
				province_name = ps.getProvinceNameByIDFromList(province_id, allprovince);
				json = ps.getProvinceBatchScoreJSONObject(PBSlist.get(i));
				json.append("province_name", province_name);
				ret_jsonarray.put(json);
			}
			
			return ret_jsonarray;
		}
		
	}
	
	
	@GET
	@Path("/getJSONtest")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONArray APItest(){
		List<String> list = new ArrayList<String>();
		for (int i = 1; i < 6; i++) {
			list.add("test_string_"+String.valueOf(i));
		}
		JSONArray json = new JSONArray(list);
		return json;
		
	}
	
	@GET
	@Path("/getStringTest")
	@Produces(MediaType.APPLICATION_JSON)
	public String APItest2(){
		return "API test!";
		
	}
	
}
