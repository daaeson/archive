package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
public interface FoodMapper {
   @Select("SELECT cno,title,subject,poster "
		  +"FROM project_category")
   public List<CategoryVO> categoryListData();
   
   @Select("SELECT fno,cno,name,address,poster,tel,type,score "
		  +"FROM project_food "
		  +"WHERE cno=#{cno}")
   public List<FoodVO> foodListData(int cno);
   
   @Select("SELECT title,subject FROM project_category WHERE cno=#{cno}")
   public CategoryVO categoryInfoData(int cno);
   
   @Select("SELECT * FROM project_food WHERE fno=#{fno}")
   public FoodVO foodDetailData(int fno);
}
