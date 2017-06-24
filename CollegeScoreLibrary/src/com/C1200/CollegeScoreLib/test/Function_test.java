package com.C1200.CollegeScoreLib.test;

import java.util.List;

import com.C1200.CollegeScoreLib.dao.impl.ProvinceBatchScoreDaoImpl;
import com.C1200.CollegeScoreLib.entity.CollegeAdmissionScore;
import com.C1200.CollegeScoreLib.entity.ProvinceBatchScore;
import com.C1200.CollegeScoreLib.service.CollegeService;
import com.C1200.CollegeScoreLib.service.ProvinceService;

public class Function_test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProvinceBatchScoreDaoImpl pbsdi = new ProvinceBatchScoreDaoImpl();	
		ProvinceBatchScore pbs = new ProvinceBatchScore();
		ProvinceService ps = new ProvinceService();
		CollegeService cs = new CollegeService();
		
		CollegeAdmissionScore cas = new CollegeAdmissionScore();
		cas.setAdmission_province_id(4);
		pbs.setProvince_id(4);
		
		try {
			System.out.println(cs.getCollegeAdmissionScoreSizeByAttrs(cas));
			System.out.println(ps.getProvinceBatchScoreSizeByAttrs(pbs));
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		//List<ProvinceBatchScore> PBSlist = ps.getProvinceBatchScoreByAttrs(pbs,0,0);
		
	}

}
