package com.C1200.CollegeScoreLib.controller;

import com.C1200.CollegeScoreLib.entity.*;
import com.C1200.CollegeScoreLib.service.CollegeService;
import com.C1200.CollegeScoreLib.service.MajorService;
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
	private MajorService ms = new MajorService();
	
	@GET
	@Path("/getColleges")
	@Produces(MediaType.APPLICATION_JSON)
	public List<School> getColleges(){
		List<School> Clist= cs.getAllCollege();
		return Clist;
	}
	
	//该API调用方式如下：
	//http://localhost:8090/CollegeScoreLibrary/api/scoreLibrary/getProvinceBatchScore?province=湖南&year=2012	
	@GET
	@Path("/getProvinceBatchScore")
	@Produces(MediaType.APPLICATION_JSON)			//@代号：ljt 
	public JSONObject getProvinceBatchScore(@QueryParam("province") String province_name,
			@QueryParam("year") String year, @QueryParam("WL") String WL, @QueryParam("batch") String batch, 
			@QueryParam("page") int page, @QueryParam("size") int size) throws Exception{
		int province_id = 0;
		long list_size = 0;
		List<ProvinceBatchScore> list = null;
		List<Province> allprovince = null;
		JSONArray jsonarray = new JSONArray();
		JSONObject json = new JSONObject();
		JSONObject ret_json = new JSONObject();
		
		ProvinceBatchScore pbs = new ProvinceBatchScore();
		pbs.setYear(year);
		pbs.setWl(WL);
		pbs.setBatch(batch);
		
		//当省份作为查询参数时
		if(province_name!=null && !province_name.equals("")){
			province_id = ps.getProvinceIdByProvinceName(province_name);
			pbs.setProvince_id(province_id);
		}
		else{
			allprovince = ps.getAllProvince();
		}
		
		list = ps.getProvinceBatchScoreByAttrs(pbs,page,size);
		list_size = ps.getProvinceBatchScoreSizeByAttrs(pbs);
		String province_name_put = "";
		for (int i = 0; i < list.size(); i++) {
			json = ps.getProvinceBatchScoreJSONObject(list.get(i));
			//当省份不作为查询参数时，需获取每一个数据中province_id对应的province_name
			if(province_name==null || province_name.equals("")){
				province_id = list.get(i).getProvince_id();
				province_name_put = ps.getProvinceNameByIDFromList(province_id, allprovince);
				json.append("province_name", province_name_put);
			}
			jsonarray.put(json);
		}
		ret_json.append("total", list_size);
		ret_json.append("data", jsonarray);
		return ret_json;
		
	}
	
	@GET
	@Path("/getCollegeScore")
	@Produces(MediaType.APPLICATION_JSON)			//@代号：ljt
	public JSONObject getCollegeScore(@QueryParam("school") String school_name, 
            @QueryParam("province") String province_name, @QueryParam("year") String year,
            @QueryParam("WL") String WL, @QueryParam("batch") String batch,@QueryParam("page") int page, 
            @QueryParam("size") int size) throws Exception{
		int province_id = 0;
		int school_id = 0;
		long list_size = 0;
		List<CollegeAdmissionScore> list = null;
		List<Province> allprovince = null;
    	List<School> allschool = null;
		JSONArray jsonarray = new JSONArray();
		JSONObject json = new JSONObject();
		JSONObject ret_json = new JSONObject();
		
		CollegeAdmissionScore cas = new CollegeAdmissionScore();
		cas.setYear(year);
		cas.setWl(WL);
		cas.setBatch(batch);
		
		if(province_name!=null && !province_name.equals("")){
			province_id = ps.getProvinceIdByProvinceName(province_name);
			cas.setAdmission_province_id(province_id);
		}
		else{
			allprovince = ps.getAllProvince();
		}
    	if(school_name!=null && !school_name.equals("")){
    		school_id = cs.getCollegeIdByName(school_name);
    		cas.setSchool_id(school_id);
    	}
    	else{
    		allschool = cs.getAllCollege();
    	}
		
		String school_name_put="";
		String province_name_put="";
		list_size = cs.getCollegeAdmissionScoreSizeByAttrs(cas);
		list = cs.getCollegeAdmissionScoreByAttrs(cas, page, size);
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				json = cs.getCollegeAdmissionScoreJSONObject(list.get(i));
    			if(school_name==null || school_name.equals("")){
	    			school_id = list.get(i).getSchool_id();
	    			school_name_put = cs.getSchoolNameByIDFromList(school_id, allschool);
					json.append("school_name", school_name_put);
    			}
    			if(province_name==null || province_name.equals("")){
    				province_id = list.get(i).getAdmission_province_id();
    				province_name_put = ps.getProvinceNameByIDFromList(province_id, allprovince);
    				json.append("province_name", province_name_put);
    			}
				jsonarray.put(json);
			}
		}	
		ret_json.append("total", list_size);
		ret_json.append("data", jsonarray);
		return ret_json;
	}
	
	
	
    @GET
    @Path("/getTouDangXian")
    @Produces(MediaType.APPLICATION_JSON)       //@代号：ljt
    public JSONObject getTouDangXian(@QueryParam("school") String school_name, @QueryParam("major") String major_name,
            @QueryParam("province") String province_name, @QueryParam("year") String year,
            @QueryParam("WL") String WL, @QueryParam("batch") String batch,@QueryParam("page") int page, 
            @QueryParam("size") int size) throws Exception{
    	int province_id = 0;
    	int school_id = 0;
    	int major_id = 0;
    	long list_size = 0;
    	List<TouDangXian> list = null;
    	List<Province> allprovince = null;
    	List<School> allschool = null;
    	TouDangXian tdx = new TouDangXian();
    	JSONArray jsonarray = new JSONArray();
		JSONObject json = new JSONObject();
		JSONObject ret_json = new JSONObject();
		
    	tdx.setYear(year);
    	tdx.setWl(WL);
    	tdx.setBatch(batch);
    	
    	major_name = "";  //暂时不做专业的投档线查询

    	if(province_name!=null && !province_name.equals("")){
    		province_id = ps.getProvinceIdByProvinceName(province_name);
    		tdx.setProvince_id(province_id);
    	}
    	else{
    		allprovince = ps.getAllProvince();
    	}
    	if(school_name!=null && !school_name.equals("")){
    		school_id = cs.getCollegeIdByName(school_name);
    		tdx.setSchool_id(school_id);
    	}
    	else{
    		allschool = cs.getAllCollege();
    	}
		if(major_name!=null && !major_name.equals("")){
			major_id = ms.getMajorIdByName(major_name);
			if(major_id>0){
				tdx.setMajor_id(major_id);
			}
			else{
				return null;
			}
		}
    	
		String school_name_put="";
		String province_name_put="";
    	list_size = ps.getTouDangXianSizeByAttrs(tdx);
		list = ps.getTouDangXianByAttrs(tdx, page, size);
		if(list!=null){
    		for (int i = 0; i < list.size(); i++) {
    			json = ps.getTouDangXianJSONObject(list.get(i));
    			if(school_name==null || school_name.equals("")){
	    			school_id = list.get(i).getSchool_id();
	    			school_name_put = cs.getSchoolNameByIDFromList(school_id, allschool);
					json.append("school_name", school_name_put);
    			}
    			if(province_name==null || province_name.equals("")){
    				province_id = list.get(i).getProvince_id();
    				province_name_put = ps.getProvinceNameByIDFromList(province_id, allprovince);
    				json.append("province_name", province_name_put);
    			}
    			jsonarray.put(json);
			}
		}
		ret_json.append("total", list_size);
		ret_json.append("data", jsonarray);
    	return ret_json;
    }
	
	//该API调用方式如下：
	//http://localhost:8080/CollegeScoreLibrary/api/scoreLibrary/getScoreRank?province=湖南&year=2012&WL=W
	@GET
	@Path("/getScoreRank")
	@Produces(MediaType.APPLICATION_JSON)			//@代号：ytl
	public List<ScoreRank> getScoreRank(@QueryParam("province") String province_name,
			@QueryParam("year") String year, @QueryParam("WL") String WL){
		int province_id = ps.getProvinceIdByProvinceName(province_name);
		ScoreRank sr = new ScoreRank();
		sr.setProvince_id(province_id);
		sr.setYear(year);
		sr.setWl(WL);
		List<ScoreRank> list = ps.getScoreRankByAttrs(sr);
		return list;
	}

    //该API调用方式如下：
    //http://localhost:8080/CollegeScoreLibrary/api/scoreLibrary/getMajorAdmissionScore?school=2&major=1&province=4&year=2012&WL=W&batch=本科一批
    @GET
    @Path("/getMajorAdmissionScore")
    @Produces(MediaType.APPLICATION_JSON)			//@代号：ytl
    public List<MajorAdmissionScore> getMajorAdmitScore(@QueryParam("school") String schoolName, @QueryParam("major") String majorName,
                                        @QueryParam("province") String province_name, @QueryParam("year") String year,
                                        @QueryParam("WL") String WL, @QueryParam("batch") String batch){
        int provinceId = ps.getProvinceIdByProvinceName(province_name);
        int schoolId = cs.getCollegeIdByName(schoolName);
        int majorId = ms.getMajorIdByName(majorName);
        MajorAdmissionScore mas = new MajorAdmissionScore();
        mas.setSchool_id(schoolId);
        mas.setMajor_id(majorId);
        mas.setYear(year);
        mas.setAdmission_province_id(provinceId);
        mas.setWl(WL);
        mas.setBatch(batch);
        List<MajorAdmissionScore> list = ms.getMajorAdmissionScoreByAttrs(mas);
        return list;
    }
	
}
