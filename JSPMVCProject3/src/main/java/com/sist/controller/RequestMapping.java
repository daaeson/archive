package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)	// 저장 기간 => 프로그램 종료 시까지 유지
@Target(METHOD)	// 메소드 구분
/*
	@RequestMapping("a") ==> if 만약 사용자가 보내주는 값이 a라면 // index 값을 찾기
	public void display()
	{
	
	}
*/
public @interface RequestMapping {
	public String value();
}
