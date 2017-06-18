package com.C1200.CollegeScoreLib.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
* @author jiangtao
* @purpose 实体类：学校 （与数据库tb_school表各字段名一一对应）
*/
@XmlRootElement		//该脚注用于注册rest API
public class School implements BaseEntity{

	private int school_id;
	private String school_name;
	
	public School(){}
	
	public School(int school_id,String school_name)
	{
		this.school_id = school_id;
		this.school_name = school_name;
	}
	
	
	public int getSchool_id() {
		return school_id;
	}

	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
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
