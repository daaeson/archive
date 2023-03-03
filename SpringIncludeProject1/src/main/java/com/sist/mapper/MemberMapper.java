package com.sist.mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface MemberMapper {
  @Insert("INSERT INTO spring_member VALUES(#{id},#{pwd},#{name})")
  public void memberInsert(MemberVO vo);
  
  // 로그인 처리 
  @Select("SELECT COUNT(*) FROM spring_member "
		 +"WHERE id=#{id}")
  public int memberIdCheck(String id);
  
  @Select("SELECT id,pwd,name FROM spring_member "
		 +"WHERE id=#{id}")
  public MemberVO memberPwdCheck(String id);
}
