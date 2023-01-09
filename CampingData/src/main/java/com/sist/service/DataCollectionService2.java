package com.sist.service;
import com.sist.dao.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class DataCollectionService2 {

	public static void main(String[] args) {
		DataCollectionService2 ds = new DataCollectionService2();
//		ds.itemCategoryGetData();
//		ds.itemDetailData();
	}
	public void itemCategoryGetData()
	   {
	      ItemDAO dao=new ItemDAO();
	      try
	      {
	         //사이트 연결 => HTML태그 읽기
	         Document doc=Jsoup.connect("https://campinglist.co.kr").get();
	         //System.out.println(doc.toString());
	         //Elements name=doc.select("div.catenabber a"); // 부모 카테고리
	         Elements name=doc.select("div.catenabber a");
	         Elements link=doc.select("div.catenabber a");
//	         System.out.println(name.toString());
	         
	         for(int i=1;i<name.size();i++)
	         {
	            System.out.println(i);
	            System.out.println(name.get(i).text());
	            System.out.println(link.get(i).attr("href"));
	            System.out.println("===============================");
	            CategoryVO vo=new CategoryVO();
	            vo.setName(name.get(i).text());
	            vo.setLink(link.get(i).attr("href"));
	            dao.itemCategoryInsert(vo);
	         }
	      }catch(Exception ex) { ex.printStackTrace(); }
	            
	   }
	public void itemDetailData()
	{
		ItemDAO dao = new ItemDAO();
		try
		{
			ArrayList<CategoryVO> list=dao.itemCategoryInfoData();
			for(CategoryVO vo:list)
			{
//				System.out.println(vo.getIcno()+" "+vo.getName()+" "+vo.getLink());
			
				ItemVO ivo = new ItemVO();
				ivo.setIcno(vo.getIcno());
				System.out.println(vo.getIcno()+" "+vo.getName());
				Document doc=Jsoup.connect(vo.getLink()).get();
				Elements link = doc.select("ul.prdList li.item div.description a");
				for(int k=0; k<link.size(); k++)
				{
//					System.out.println(link.get(k).attr("href"));
					Document doc2=Jsoup.connect("https://campinglist.co.kr"+link.get(k).attr("href")).get();
					// 1. 이미지
					Elements image=doc2.select("");
				}
				
			}

			
			
		}catch(Exception ex) {}
	}
}










