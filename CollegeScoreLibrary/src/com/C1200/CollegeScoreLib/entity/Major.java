package com.C1200.CollegeScoreLib.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
* @author jiangtao
* @purpose 实体类：专业 （与数据库tb_Major表各字段名一一对应）
*/
@XmlRootElement		//该脚注用于注册rest API
public class Major implements BaseEntity{

	private int major_id=0;
	private String major_name;
	private String major_category;
	
	public Major(){}
	
	public Major(int major_id,String major_name,String major_category){
		this.major_id = major_id;
		this.major_name = major_name;
		this.major_category = major_category;
	}
	

	public int getMajor_id() {
		return major_id;
	}

	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	public String getMajor_category() {
		return major_category;
	}

	public void setMajor_category(String major_category) {
		this.major_category = major_category;
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
