package com.C1200.CollegeScoreLib.test;

import java.util.List;

import com.C1200.CollegeScoreLib.dao.impl.ProvinceBatchScoreDaoImpl;
import com.C1200.CollegeScoreLib.entity.ProvinceBatchScore;
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
//		pbs.setProvince_id(4);
//		pbs.setYear("2015");
//		pbs.setWl("L");
		//System.out.println(pbs.getScore());
		try {
			System.out.println(pbsdi.getSQLqueryString(pbs));
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		List<ProvinceBatchScore> PBSlist = ps.getProvinceBatchScoreByAttrs(pbs,0,0);
		
	}

}
