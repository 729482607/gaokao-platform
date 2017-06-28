package com.C1200.CollegeScoreLib.service;

import com.C1200.CollegeScoreLib.dao.impl.UserDaoImpl;
import com.C1200.CollegeScoreLib.entity.User;

public class UserService {
	private UserDaoImpl udi = new UserDaoImpl();
	
	public void userRegister(User user) throws Exception{
		udi.addUser(user);
	}
	
	public boolean userLogin(String username, String password) throws Exception{
		User user = new User();
		user.setUsername(username);
		user.setUserpassword(password);
		User ret_u = udi.getUserByUNandP(user);
		if (ret_u!=null){
			return true;
		}
		return false;
	}
}
