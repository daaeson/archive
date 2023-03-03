package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.DataBoardVO;
import com.sist.vo.NoticeVO;
public interface FooterMapper {
   @Select("SELECT no,name,subject,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,rownum "
		  +"FROM (SELECT no,name,subject,regdate "
		  +"FROM project_notice ORDER BY no DESC) "
		  +"WHERE rownum<=5")
   public List<NoticeVO> noticeDataTop5();
   
   @Select("SELECT no,name,subject,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,rownum "
			  +"FROM (SELECT no,name,subject,regdate "
			  +"FROM spring_databoard ORDER BY no DESC) "
			  +"WHERE rownum<=5")
   public List<DataBoardVO> databoardDataTop5();
}
