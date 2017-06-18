package com.C1200.CollegeScoreLib.test;

import com.C1200.CollegeScoreLib.dao.impl.ProvinceBatchScoreDaoImpl;
import com.C1200.CollegeScoreLib.entity.ProvinceBatchScore;

public class Function_test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProvinceBatchScoreDaoImpl pbsdi = new ProvinceBatchScoreDaoImpl();	
		ProvinceBatchScore pbs = new ProvinceBatchScore();
		pbs.setProvince_id(4);
		pbs.setYear("2015");
		pbs.setWl("L");
		//System.out.println(pbs.getScore());
		try {
			System.out.println(pbsdi.getSQLqueryString(pbs));
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}

}
