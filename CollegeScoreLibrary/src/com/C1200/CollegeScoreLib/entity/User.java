package com.C1200.CollegeScoreLib.entity;



public class User implements BaseEntity{

	private int id=0;
	private String username;
	private String userpassword;
	private String arclass;
	private String wl;
	private String score;
	private String admission_school;
	
	public User(){}
	
		
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}






	public String getUserpassword() {
		return userpassword;
	}


	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}


	public String getArclass() {
		return arclass;
	}



	public void setArclass(String arclass) {
		this.arclass = arclass;
	}



	public String getWl() {
		return wl;
	}



	public void setWl(String wl) {
		this.wl = wl;
	}



	public String getScore() {
		return score;
	}



	public void setScore(String score) {
		this.score = score;
	}

	

	public String getAdmission_school() {
		return admission_school;
	}



	public void setAdmission_school(String admission_school) {
		this.admission_school = admission_school;
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
