package com.sist.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.vo.*;
import com.sist.dao.*;
// DispatcherServlet ==>  연결하는 클래스 (@Controller,@RestController)  요청한 매개변수를 주입해준다 
@Aspect
@Component
public class IncludeAOP {
  @Autowired
  private FooterDAO dao;
  
  @After("execution(* com.sist.web.*Controller.*(..))")
  public void footerData()
  {
	  // 현재 사용중인 request객체를 얻어온다 : AOP,Intercepter , Task
	  HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	  List<NoticeVO> nList=dao.noticeDataTop5();
	  List<DataBoardVO> dList=dao.databoardDataTop5();
	  request.setAttribute("dList", dList);
	  request.setAttribute("nList", nList);
			  
  }
}
