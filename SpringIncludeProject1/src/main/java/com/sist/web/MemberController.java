package com.sist.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.vo.*;
import com.sist.dao.*;
@Controller
public class MemberController {
    @Autowired
	private MemberDAO dao;
    
    @Autowired
    private BCryptPasswordEncoder encoder;
    
    @GetMapping("member/join.do")
    public String member_join(Model model)
    {
    	model.addAttribute("main_jsp", "../member/join.jsp");
    	return "main/main";
    }
    
    @PostMapping("member/join_ok.do")
    public String member_join_ok(MemberVO vo,Model model)
    {
    	String en=encoder.encode(vo.getPwd()); // 암호화 
    	vo.setPwd(en);
    	
    	dao.memberInsert(vo);
    	return "redirect:../main/main.do";
    }
    
    @PostMapping("member/login_ok.do")
    @ResponseBody  //화면을 변경하는 것이 아니라 => 원하는 데이터 전송이 가능 ==> @RestController
    public String member_login_ok(MemberVO vo,HttpSession session)// session => 매개변수로 받을 수 있다 
    {
    	String result="";
    	int count=dao.memberIdCheck(vo.getId());
    	if(count==0)
    	{
    		result="NOID";
    	}
    	else
    	{
    		MemberVO pvo=dao.memberPwdCheck(vo.getId());
    		if(encoder.matches(vo.getPwd(), pvo.getPwd()))// 암호화된 비밀번호 비교(복호화)
    		{
    			session.setAttribute("id", pvo.getId());
    			session.setAttribute("name", pvo.getName());
    			result="OK";
    		}
    		else
    		{
    			result="NOPWD";
    		}
    	}
    	return result;
    }
    @PostMapping("member/logout.do")
    public String member_logout(HttpSession session)
    {
    	session.invalidate();
    	return "redirect:../main/main.do";
    }
}
