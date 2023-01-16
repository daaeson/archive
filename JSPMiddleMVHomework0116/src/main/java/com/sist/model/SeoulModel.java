package com.sist.model;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;
import com.sist.vo.*;

public class SeoulModel {
	public void locationListData(HttpServletRequest request, HttpServletResponse response)
	{
		// DAO 연결
		SeoulDAO dao = new SeoulDAO();
		ArrayList<SeoulVO> list=dao.locationListData();
		request.setAttribute("list", list);
	}
	
}
