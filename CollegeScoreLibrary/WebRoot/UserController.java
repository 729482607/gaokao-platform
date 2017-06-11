package com.bctc.ims.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;




import com.bctc.ims.pojo.OvertimeRequisition;
import com.bctc.ims.pojo.User;
import com.bctc.ims.service.impl.OvertimeRequisitionServiceImpl;
import com.bctc.ims.service.impl.UserServiceImpl;

@Path("/user")
public class UserController {
	public List<User> list=null;
	private UserServiceImpl usi = new UserServiceImpl();
	private OvertimeRequisitionServiceImpl orsi = new OvertimeRequisitionServiceImpl();
	/**
	 * user路径下默认显示用户列表
	 * @return
	 */
	@GET
	//@Path("/getUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers(){
		if(list==null){
			list=usi.getAllUsers();
		}
		
		return list;
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public String register(@FormParam("username") String username,@FormParam("password") String password){
		String authority = "0";
		User user = usi.getUserByName(username);
		if(user!=null){
			return "[\"用户已注册！\"]";
		}
		else if(usi.addUser(username, password, authority))
			 return "[\"SUCCESS\"]";
		return "[\"ERROR\"]";
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("username") String username,@FormParam("password") String password){
		User user = usi.getUserByName(username);
		JSONObject json = new JSONObject();
		//String result = "";
		try {
			if(user!=null){
				String pw = user.getPassword();
				if(pw.equals(password)){
					String authority = user.getAuthority();
					int user_id = user.getUser_id();
					json.put("user_id", user_id);
					json.put("name", username);
					json.put("status", "SUCCESS");
					json.put("authority", authority);
					//result += "SUCCESS-"+authority; 
					
				}
				else
					json.put("status", "password ERROR!");
					//result += "password ERROR";
			}
			else
				json.put("status", "User NOT found");
				//result += "User NOT found";
		} catch (Exception e) {
			return "[\""+"Server ERROR"+"\"]";
		}
		//return "[\""+result+"\"]";
		return json.toString();
	}
	
//	@GET
//	@Path("/login")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String loginGET(@QueryParam("username") String username,@QueryParam("password") String password){
//		User user = usi.getUserByName(username);
//		if(user!=null){
//			String pw = user.getPassword();
//			if(pw.equals(password)){
//				return "login success";
//			}
//			else return "password ERROR!";
//		}
//		return "User NOT found";
//	}
	
	@POST
	@Path("/applyOT")
	@Produces(MediaType.APPLICATION_JSON)
	public String applyOT(@FormParam("user_id") String user_id,@FormParam("reason") String reason,@FormParam("date") 
	String date,@FormParam("time") String time,@FormParam("isS8") String isS8){
		String res = orsi.addOTrequisition(Integer.parseInt(user_id), reason, date, time, isS8);
		if(res.equals("SUCCESS")) return "[\""+res+"\"]";
		return "[\""+res+"\"]";
	}
	
	@GET
	@Path("/deleteApplyOT")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteApplyOT(@QueryParam("OTR_id") String OTR_id){
		boolean flag = orsi.deleteOTrequisition(Integer.parseInt(OTR_id));
		if(flag) return "[\"SUCCESS\"]";
		return "[\"ERROR\"]";
	}
	
	@GET
	@Path("/getDptmtApplyOT/{FMTdate}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllApplyOT(@PathParam("FMTdate") String FMTdate){ 
		List<JSONObject> json_list= new ArrayList<JSONObject>();
		List<OvertimeRequisition> OTR_list = orsi.getAllOTrequisitionsByFMTdate(FMTdate);
		int user_id = -1;
		String name = "";
		User u = null;
		try {
			for (int i = 0; i < OTR_list.size(); i++) {
				JSONObject json = new JSONObject();
				OvertimeRequisition otr = OTR_list.get(i);
				user_id = otr.getUser_id();
				u = usi.getUserByUserId(user_id);
				if(u!=null){
					name = u.getName();
					json.put("name", name);
				}
				else  json.put("name", user_id);
				json.put("reason",otr.getReason());
				json.put("date", otr.getDate());
				json.put("time", otr.getTime());
				json.put("id", otr.getId());
				json.put("isS8", otr.getIsS8());
				json_list.add(json);
			}
			
		} catch (JSONException e) {
			return null;
		}
		return json_list.toString();
	}
	
	@GET
	@Path("getApplyOT/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OvertimeRequisition> getApplyOTbyId(@PathParam("user_id") String user_id){
		int uid = Integer.parseInt(user_id);
		List<OvertimeRequisition> OTR_list = new ArrayList<OvertimeRequisition>();
		Date d1 = new Date();
//		Calendar ca = new GregorianCalendar();
//		ca.setTime(d1);
//		ca.add(ca.DATE, -4);
//		d1 = ca.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = sdf.format(d1);
		int day  = d1.getDay();
		OvertimeRequisition otr = orsi.getOTrequisitionByUserIdAndDate(uid,date1);
		if(otr==null) return null;
		else{
			OTR_list.add(otr);
			if(day==5){
				Calendar ca = new GregorianCalendar();
				ca.setTime(d1);
				ca.add(ca.DATE, 1);
				String date2 = sdf.format(ca.getTime());
				otr = orsi.getOTrequisitionByUserIdAndDate(uid,date2);
				if(otr!=null)
					OTR_list.add(otr);
				ca.add(ca.DATE, 1);
				String date3 = sdf.format(ca.getTime());
				otr = orsi.getOTrequisitionByUserIdAndDate(uid,date3);
				if(otr!=null)
					OTR_list.add(otr);
			}
		}
			
		return OTR_list;
	}
	
	
	@GET
	@Path("getAllApplyOT/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OvertimeRequisition> getAllApplyOTbyId(@PathParam("user_id") String user_id){
		int uid = Integer.parseInt(user_id);
		List<OvertimeRequisition> OTR_list = new ArrayList<OvertimeRequisition>();
		OTR_list = orsi.getAllOTrequisitionByUserId(uid);		
		return OTR_list;
	}
}
