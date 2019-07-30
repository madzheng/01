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

	//����û�
	@RequestMapping("/add")
	@ResponseBody
	public Object add(@RequestBody User user) {
		//���ص�������
		JSONObject result = new JSONObject();
		if(user!=null) {
			System.out.println("����json��ʽ�����ݣ�"+JSONObject.fromObject(user));
			System.out.println("����json��ʽ��toString���ݣ�"+JSONObject.fromObject(user).toString());
			//���浽���ݿ���
			db.put(user.getId(), user);
			result.put("msg", "�û���Ϣ��ӳɹ���");
		} else {
			result.put("msg", "�û���Ϣ���ʧ�ܣ�");
		}
		return result;
	}
	
	//��ѯ�û�
	@RequestMapping("/get/{id}")
	@ResponseBody
	public Object get(@PathVariable String id) {
		JSONObject result = new JSONObject();
		//��ѯ�û���Ϣ
		User user = (User) db.get(id);
		if(user==null) {
			result.put("msg", "�û������ڣ�");
		} else {
			result = JSONObject.fromObject(user);
		}
		return result;
	}
	
	//�޸��û�
	@RequestMapping("/modify/{id}")
	@ResponseBody
	public Object modify(@PathVariable String id, @RequestBody User user) {
		JSONObject result = new JSONObject();
		//���Ȳ�ѯ�û���Ϣ
		User user0= (User) db.get(id);
		if(user0==null) {
			result.put("msg", "�û������ڣ��޸���Ϣʧ�ܡ�");
		} else {
			db.remove(user0.getId());
			db.put(user.getId(), user);
			result.put("msg", "�޸��û���Ϣ�ɹ���");
		}
		return result;
	}
	
	//ɾ���û�
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Object delete(@PathVariable String id) {
		JSONObject result = new JSONObject();
		//���Ȳ�ѯ�û���Ϣ
		User user0= (User) db.get(id);
		if(user0==null) {
			result.put("msg", "�û������ڣ�ɾ��ʧ�ܡ�");
		} else {
			db.remove(user0.getId());
			result.put("msg", "ɾ���û���Ϣ�ɹ���");
		}
		return result;
	}
}
