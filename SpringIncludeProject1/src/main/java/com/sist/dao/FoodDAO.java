package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
@Repository
public class FoodDAO {
  @Autowired
  private FoodMapper mapper;
  
  /*@Select("SELECT cno,title,subject,poster "
		  +"FROM project_category")*/
   public List<CategoryVO> categoryListData()
   {
	   return mapper.categoryListData();
   }
   
   /*
    *  @Select("SELECT fno,cno,name,address,poster,tel,type "
		  +"FROM project_food "
		  +"WHERE cno=#{cno}")
   ;
    */
   public List<FoodVO> foodListData(int cno)
   {
	   return mapper.foodListData(cno);
   }
   //@Select("SELECT title,subject FROM project_category WHERE cno=#{cno}")
   public CategoryVO categoryInfoData(int cno)
   {
	   return mapper.categoryInfoData(cno);
   }
   
   //@Select("SELECT * FROM project_food WHERE fno=#{fno}")
   public FoodVO foodDetailData(int fno)
   {
	   return mapper.foodDetailData(fno);
   }
}
