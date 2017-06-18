package com.C1200.CollegeScoreLib.entity;

import javax.xml.bind.annotation.XmlRootElement;


/**
* @author jiangtao
* @purpose 实体类：省批次线 （与数据库tb_ProvinceBatchScore表各字段名一一对应）
*/
@XmlRootElement      //该脚注用于注册rest API
public class ProvinceBatchScore implements BaseEntity{

	private int id=0;
	private String year;
	private int province_id=0;		//省的ID，非省名
	private String wl;			//取值仅为 L 或  W ，分别代表  理科 和 文科
	private String batch;     //批次
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

	

	public int getProvince_id() {
		return province_id;
	}


	public void setProvince_id(int province_id) {
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
