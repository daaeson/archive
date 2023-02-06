package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD) //메소드찾기 (구분)
// 주의점 : 같은 이름을 가진 어노테이션이 있는 경우 에러 발생 
/*
 *   public class A ==> 오류 
 *   {
 *      @RequestMapping("a.do")
 *      public void disp()
 *      {
 *      }
 *      
 *      @RequestMapping("a.do")
 *      public void disp1()
 *      {
 *      }
 *   }
 */
public @interface RequestMapping {
   public String value();
} 
