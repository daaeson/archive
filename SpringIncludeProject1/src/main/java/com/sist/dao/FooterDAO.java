package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.DataBoardVO;
import com.sist.vo.NoticeVO;
@Repository
public class FooterDAO {
  @Autowired
  private FooterMapper mapper;
  
  /*@Select("SELECT no,name,subject,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,rownum "
		  +"FROM (SELECT no,name,subject,regdate "
		  +"FROM project_notice ORDER BY no DESC) "
		  +"WHERE rownum<=5")*/
   public List<NoticeVO> noticeDataTop5()
   {
	   return mapper.noticeDataTop5();
   }
   
   /*@Select("SELECT no,name,subject,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,rownum "
			  +"FROM (SELECT no,name,subject,regdate "
			  +"FROM spring_databoard ORDER BY no DESC) "
			  +"WHERE rownum<=5")*/
   public List<DataBoardVO> databoardDataTop5()
   {
	   return mapper.databoardDataTop5();
   }
}
