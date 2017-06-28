package com.C1200.CollegeScoreLib.controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.C1200.CollegeScoreLib.entity.User;
import com.C1200.CollegeScoreLib.service.UserService;


@Path("/user")
public class UserController {
	
	private UserService us = new UserService();
	
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public String userRegister(@FormParam("username") String username,@FormParam("password") String password,
			@FormParam("ARclass") String ARclass, @FormParam("WL") String wl,@FormParam("score") String score){
		
		System.out.println(username);
		User user = new User();
		user.setUsername(username);
		user.setUserpassword(password);
		user.setArclass(ARclass);
		user.setWl(wl);
		user.setScore(score);
		try {
			us.userRegister(user);
			return "OK";
		} catch (Exception e) {
			e.printStackTrace();
			return "BAD";
		}
		

	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@FormParam("username") String username,@FormParam("password") String password) throws Exception{

		if(us.userLogin(username, password)){
			return "OK";
		}
		
		return "BAD";
		
	}
	

}
