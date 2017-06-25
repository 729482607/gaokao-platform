package com.C1200.CollegeScoreLib.controller;

import com.C1200.CollegeScoreLib.entity.School;
import com.C1200.CollegeScoreLib.service.CollegeService;
import com.C1200.CollegeScoreLib.service.ProvinceService;
import com.C1200.CollegeScoreLib.service.RecommendService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by yang on 17/6/22.
 */

@Path("/recommend")
public class RecommendController {
    private RecommendService rs = new RecommendService();
    private ProvinceService ps = new ProvinceService();

    //http://localhost:8080/CollegeScoreLibrary/api/recommend/getRecommendSchool?province=湖南&year=2016&WL=L&score=550
    @GET
    @Path("/getRecommendSchool")
    @Produces(MediaType.APPLICATION_JSON)
    public List<School> getRecommendSchool(@QueryParam("score") String score, @QueryParam("year") String year,
                                           @QueryParam("province") String provinceName, @QueryParam("WL") String WL){
        //暂时指定为上下浮动3分
        int offset = 3;
        int provinceId = ps.getProvinceIdByProvinceName(provinceName);
        List<School> list = rs.getRecommendSchool(provinceId, year, WL, score, offset);
        if(list != null && list.size() > 0){
            return list;
        }

        return null;
    }
}
