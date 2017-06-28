package com.C1200.CollegeScoreLib.dao.impl;

import com.C1200.CollegeScoreLib.dao.BaseDao;
import com.C1200.CollegeScoreLib.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements BaseDao<User>{
	
	public void addUser(User user) throws Exception{
		super.saveEntry(user);
	}
	
	public User getUserByUNandP(User user) throws Exception{
		if(user==null || user.getUsername()==null || user.getUserpassword()==null) return null;
		String sql = "select * from tb_User";
		String SQLqueryString = super.getSQLqueryString(user);
		if(!SQLqueryString.equals("") && SQLqueryString!=null){
			sql+=" where "+SQLqueryString; 
			User ret_user = super.getEntrys(sql).get(0);			
			return ret_user;
		}
		return null;	
	}
	

}
