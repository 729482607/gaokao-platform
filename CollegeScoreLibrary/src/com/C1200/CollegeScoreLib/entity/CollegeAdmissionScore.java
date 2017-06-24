package com.C1200.CollegeScoreLib.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
* @author jiangtao
* @purpose 实体类：高校录取线 （与数据库tb_CollegeAdmissionScore表各字段名一一对应）
*/

@XmlRootElement		//该脚注用于注册rest API
public class CollegeAdmissionScore implements BaseEntity{

	private int id=0;
	private int school_id=0;
	private int admission_province_id=0;
	private String year;
	private String wl;
	private String batch;
	private String average_score;
	private String highest_score;
	private String lowest_score;
	private String admission_num;
	private String tdx_score;
	private String skx_score;
	private String rankInProvince;
	
	public CollegeAdmissionScore(){}
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getSchool_id() {
		return school_id;
	}



	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}



	public int getAdmission_province_id() {
		return admission_province_id;
	}



	public void setAdmission_province_id(int admission_province_id) {
		this.admission_province_id = admission_province_id;
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public String getWl() {
		return wl;
	}



	public void setWl(String wl) {
		this.wl = wl;
	}



	public String getBatch() {
		return batch;
	}



	public void setBatch(String batch) {
		this.batch = batch;
	}



	public String getAverage_score() {
		return average_score;
	}



	public void setAverage_score(String average_score) {
		this.average_score = average_score;
	}



	public String getHighest_score() {
		return highest_score;
	}



	public void setHighest_score(String highest_score) {
		this.highest_score = highest_score;
	}



	public String getLowest_score() {
		return lowest_score;
	}



	public void setLowest_score(String lowest_score) {
		this.lowest_score = lowest_score;
	}



	public String getAdmission_num() {
		return admission_num;
	}



	public void setAdmission_num(String admission_num) {
		this.admission_num = admission_num;
	}



	public String getTdx_score() {
		return tdx_score;
	}



	public void setTdx_score(String tdx_score) {
		this.tdx_score = tdx_score;
	}



	public String getSkx_score() {
		return skx_score;
	}



	public void setSkx_score(String skx_score) {
		this.skx_score = skx_score;
	}



	public String getRankInProvince() {
		return rankInProvince;
	}



	public void setRankInProvince(String rankInProvince) {
		this.rankInProvince = rankInProvince;
	}



	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isPK_Increment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

}
