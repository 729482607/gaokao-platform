package com.C1200.CollegeScoreLib.entity;

import javax.xml.bind.annotation.XmlRootElement;


/**
* @author jiangtao
* @purpose 实体类：省分段排名 （与数据库tb_ScoreRank表各字段名一一对应）
*/

@XmlRootElement		//该脚注用于注册rest API
public class ScoreRank implements BaseEntity{

	private int id=0;
	private int province_id=0;
	private String year;
	private String wl;					//取值仅为 L 或  W ，分别代表  理科 和 文科
	private String subsectionType;		//分段类型（5分段或1分段）
	private String extrapointType;		//加分类型（含全国加分，地方加分）   暂不考虑！
	private String score;
	private String numInScore;			//该分段的人数
	private String accumulateNum;		//累积排名
	
	
	public ScoreRank(){}
	
	public ScoreRank(int province_id,String year,String wl,String subsectionType,String extrapointType,
			String score, String numInScore, String accumulateNum){
		this.province_id = province_id;
		this.year = year;
		this.wl = wl;
		this.subsectionType = subsectionType;
		this.extrapointType = extrapointType;
		this.score = score;
		this.numInScore = numInScore;
		this.accumulateNum = accumulateNum;
	}
	
	
	
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

	public String getSubsectionType() {
		return subsectionType;
	}

	public void setSubsectionType(String subsectionType) {
		this.subsectionType = subsectionType;
	}

	public String getExtrapointType() {
		return extrapointType;
	}

	public void setExtrapointType(String extrapointType) {
		this.extrapointType = extrapointType;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getNumInScore() {
		return numInScore;
	}

	public void setNumInScore(String numInScore) {
		this.numInScore = numInScore;
	}

	public String getAccumulateNum() {
		return accumulateNum;
	}

	public void setAccumulateNum(String accumulateNum) {
		this.accumulateNum = accumulateNum;
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
