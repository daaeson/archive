package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
/*
 *    1. 오라클 연결 ==> DAO
 *    ------------------------------------ MyBatis
 *    2. 브라우저 전송 ==> Controller
 *    ------------------------------------ DispatcherServlet
 */
@Repository
public class SeoulDAO {
   @Autowired
   private SeoulMapper mapper;
   
   /*
    *   @Select("SELECT no,title,poster,num "
		 +"FROM (SELECT no,title,poster,rownum as num "
		 +"FROM (SELECT /*+ INDEX_ASC(seoul_location sl_no_pk)no,title,poster "
		 +"FROM seoul_location)) "
		 +"WHERE num BETWEEN #{start} AND #{end}")
         public List<SeoulLocationVO> seoulLocationListData(Map map);
    */
   public List<SeoulLocationVO> seoulLocationListData(Map map)
   {
	   return mapper.seoulLocationListData(map);
   }
   
   //@Select("SELECT CEIL(COUNT(*)/20.0) FROM seoul_location")
   public int seoulTotalPage()
   {
	  return mapper.seoulTotalPage(); 
   }
}
