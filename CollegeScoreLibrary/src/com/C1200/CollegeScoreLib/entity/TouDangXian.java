package com.C1200.CollegeScoreLib.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
* @author jiangtao
* @purpose 实体类：投档线 （与数据库tb_TouDangXian表各字段名一一对应）
*/
@XmlRootElement		//该脚注用于注册rest API
public class TouDangXian implements BaseEntity{


	
	private int id=0;
	private int province_id=0;
	private int school_id=0;			//学校ID
	private int major_id=0;				//专业ID，当该值不为0时，表示该投档线为专业分的投档线
	private String year;
	private String wl;					//取值仅为 L 或  W ，分别代表  理科 和 文科
	private String batch;
	private String tdx_score;			//投档分
	private String chinese;
	private String math;
	private String english;
	private String admission_num;		//录取人数
	
	public TouDangXian(){}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProvince_id() {
		return province_id;
	}

	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}

	public int getSchool_id() {
		return school_id;
	}

	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}

	public int getMajor_id() {
		return major_id;
	}

	public void setMajor_id(int major_id) {
		this.major_id = major_id;
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

	public String getTdx_score() {
		return tdx_score;
	}

	public void setTdx_score(String tdx_score) {
		this.tdx_score = tdx_score;
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	public String getMath() {
		return math;
	}

	public void setMath(String math) {
		this.math = math;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getAdmission_num() {
		return admission_num;
	}

	public void setAdmission_num(String admission_num) {
		this.admission_num = admission_num;
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
