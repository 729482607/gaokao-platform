package com.C1200.CollegeScoreLib.controller;

import com.C1200.CollegeScoreLib.entity.School;
import com.C1200.CollegeScoreLib.entity.TouDangXian;
import com.C1200.CollegeScoreLib.service.CollegeService;
import com.C1200.CollegeScoreLib.service.ProvinceService;
import com.C1200.CollegeScoreLib.service.RecommendService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.util.List;

/**
 * Created by yang on 17/6/22.
 */

@Path("/recommend")
public class RecommendController {
    private RecommendService rs = new RecommendService();
    private ProvinceService ps = new ProvinceService();
    private CollegeService cs = new CollegeService();

    //http://localhost:8080/CollegeScoreLibrary/api/recommend/getRecommendSchool?province=湖南&year=2016&WL=L&score=550
    @GET
    @Path("/getRecommendSchool")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getRecommendSchool(@QueryParam("score") String score, @QueryParam("year") String year,
    		@QueryParam("compare_year") String compare_year,@QueryParam("province") String provinceName, 
    		@QueryParam("WL") String wl) throws Exception{
    	JSONObject ret_json = new JSONObject();
    	if(score==null || score.equals("")){
			ret_json.put("error", "请输入正确的分数");
			return ret_json;
    	}
    	
        String tdx_score = "";
        String rank = "";
        int school_id = 0;
        String riskType = "";
        String school_name = "";
        String admission_num = "";
        List<School> allSchool = cs.getAllCollege();
		JSONArray jsonarray = new JSONArray();
        
    	//暂时指定为上下浮动5分
        int offset = 7;
        int province_id = ps.getProvinceIdByProvinceName(provinceName);
        //List<School> list = rs.getRecommendSchool(province_id, year, wl, score, offset);
        String my_rank = rs.getRankByScore(province_id, year, wl, score);
        String compare_year_score = rs.getScoreByRank(province_id, compare_year, wl, my_rank);
        List<TouDangXian> list_tdx = rs.getCollegeListWithTDX(province_id, compare_year, wl, compare_year_score, offset);
        if(list_tdx != null && list_tdx.size() > 0){
            for (int i = 0; i < list_tdx.size(); i++) {
            	JSONObject json = new JSONObject();
            	tdx_score = list_tdx.get(i).getTdx_score();
            	rank = list_tdx.get(i).getRankInProvince();
            	school_id = list_tdx.get(i).getSchool_id();
            	school_name = cs.getSchoolNameByIDFromList(school_id, allSchool);
            	admission_num = list_tdx.get(i).getAdmission_num();
            	riskType = rs.getRiskType(Integer.parseInt(compare_year_score), Integer.parseInt(tdx_score));
            	json.put("school_name", school_name);
            	json.put("tdx_score", tdx_score);
            	json.put("rank", rank);
            	json.put("riskType", riskType);
            	json.put("my_rank", my_rank);
            	json.put("admission_num", admission_num);
            	jsonarray.put(json);
			}
            ret_json.append("total", String.valueOf(list_tdx.size()));
            ret_json.append("data", jsonarray);  
        }
        else{
    		ret_json.put("error", "请输入正确的分数");
    		return ret_json;

        }

        return ret_json;
    }
}
