package com.C1200.CollegeScoreLib.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Province implements BaseEntity{
	private int province_id;
	private String province_name;
	private String locate_area;
	
	public Province(){}
	
	public Province(int province_id, String province_name, String locate_area){
		this.province_id = province_id;
		this.province_name = province_name;
		this.locate_area = locate_area;
	}
	
	public int getProvince_id() {
		return province_id;
	}

	public void setProvince_id(int province_id) {
		this.province_id = province_id;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getLocate_area() {
		return locate_area;
	}

	public void setLocate_area(String locate_area) {
		this.locate_area = locate_area;
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
