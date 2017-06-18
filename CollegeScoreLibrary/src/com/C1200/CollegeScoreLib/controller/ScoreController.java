package com.C1200.CollegeScoreLib.controller;

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
	
	
	//http://localhost:8090/CollegeScoreLibrary/api/scoreLibrary/getProvinceBatchScore?province=湖南&year=2012	
	@GET
	@Path("/getProvinceBatchScore")
	@Produces(MediaType.APPLICATION_JSON)			//@代号：ljt 
	public List<ProvinceBatchScore> getProvinceBatchScore(@QueryParam("province") String province_name,
			@QueryParam("year") String year, @QueryParam("WL") String WL, @QueryParam("batch") String batch, 
			@QueryParam("page") int page, @QueryParam("size") int size){
		int province_id = ps.getProvinceIdByProvinceNmae(province_name);
		ProvinceBatchScore pbs = new ProvinceBatchScore();
		pbs.setProvince_id(province_id);
		pbs.setYear(year);
		pbs.setWl(WL);
		pbs.setBatch(batch);
		System.out.println(page);
		List<ProvinceBatchScore> PBSlist = ps.getProvinceBatchScoreByAttrs(pbs,page,size);
		return PBSlist;
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
