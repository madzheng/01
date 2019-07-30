package com.abc.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.mvc.pojo.User;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private Map<String,Object> db = new HashMap<>();

	//添加用户
	@RequestMapping("/add")
	@ResponseBody
	public Object add(@RequestBody User user) {
		//返回到的数据
		JSONObject result = new JSONObject();
		if(user!=null) {
			System.out.println("这是json格式的数据："+JSONObject.fromObject(user));
			System.out.println("这是json格式的toString数据："+JSONObject.fromObject(user).toString());
			//保存到数据库中
			db.put(user.getId(), user);
			result.put("msg", "用户信息添加成功！");
		} else {
			result.put("msg", "用户信息添加失败！");
		}
		return result;
	}
	
	//查询用户
	@RequestMapping("/get/{id}")
	@ResponseBody
	public Object get(@PathVariable String id) {
		JSONObject result = new JSONObject();
		//查询用户信息
		User user = (User) db.get(id);
		if(user==null) {
			result.put("msg", "用户不存在！");
		} else {
			result = JSONObject.fromObject(user);
		}
		return result;
	}
	
	//修改用户
	@RequestMapping("/modify/{id}")
	@ResponseBody
	public Object modify(@PathVariable String id, @RequestBody User user) {
		JSONObject result = new JSONObject();
		//首先查询用户信息
		User user0= (User) db.get(id);
		if(user0==null) {
			result.put("msg", "用户不存在，修改信息失败。");
		} else {
			db.remove(user0.getId());
			db.put(user.getId(), user);
			result.put("msg", "修改用户信息成功！");
		}
		return result;
	}
	
	//删除用户
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Object delete(@PathVariable String id) {
		JSONObject result = new JSONObject();
		//首先查询用户信息
		User user0= (User) db.get(id);
		if(user0==null) {
			result.put("msg", "用户不存在，删除失败。");
		} else {
			db.remove(user0.getId());
			result.put("msg", "删除用户信息成功！");
		}
		return result;
	}
}
