package com.C1200.CollegeScoreLib.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProvinceBatchScore implements BaseEntity{

	private int id= 0;
	private String year;
	private int province_id;
	private String wl;
	private String batch;
	private String score;
	
	public ProvinceBatchScore(){}
	
	
	public ProvinceBatchScore(String year,int province_id,String wl,String batch,String score)
	{
		this.year = year;
		this.province_id = province_id;
		this.wl = wl;
		this.batch = batch;
		this.score = score;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	

	public int getprovince_id() {
		return province_id;
	}


	public void setprovince_id(int province_id) {
		this.province_id = province_id;
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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
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
