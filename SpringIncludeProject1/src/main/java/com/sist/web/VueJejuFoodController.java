package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@RestController
public class VueJejuFoodController {
	@Autowired
	private JejuDAO dao;
	
	@RequestMapping(value="jeju/jeju_food_list_vue.do", produces="text/plain;charset=UTF-8")
	public String jeju_food_list(String page)
	{
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		Map map = new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", curpage*20);
		List<JejuFoodVO> list = dao.jejuFoodListData(map);
		int totalpage = dao.jejuTotalPage();
		JSONArray arr = new JSONArray();
		int i=0;
		for(JejuFoodVO vo:list) {
			JSONObject obj = new JSONObject();
			obj.put("no", vo.getNo());
			obj.put("title", vo.getTitle());
			obj.put("poster", vo.getPoster());
			if(i==0) {
				obj.put("curpage", curpage);
				obj.put("totalpage", totalpage);
			}
			arr.add(obj);
			i++;
		}
		return arr.toJSONString();
	}
	
	@RequestMapping(value="jeju/jeju_food_detail_vue.do", produces="text/plain;charset=UTF-8")
	public String jeju_food_detail(int no)
	{
		JejuFoodVO vo = dao.jejuDetailData(no);
		JSONObject obj = new JSONObject();
		obj.put("no",vo.getNo());
		obj.put("title",vo.getTitle());
		obj.put("poster", vo.getPoster());
		obj.put("score",vo.getScore());
		obj.put("addr",vo.getAddr());
		obj.put("addr2",vo.getAddr2());
		obj.put("tel",vo.getTel());
		obj.put("type",vo.getType());
		obj.put("parking", vo.getParking());
		obj.put("time",vo.getTime());
		obj.put("menu", vo.getMenu());
		return obj.toJSONString();
	}
	
}
