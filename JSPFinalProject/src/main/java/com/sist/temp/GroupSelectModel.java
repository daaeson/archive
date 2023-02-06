package com.sist.temp;
import java.util.*;
public class GroupSelectModel {
   public static void main(String[] args) {
	   GroupSelectModel m=new GroupSelectModel();
	   m.group_choice();
   }
   public List<Integer> group_choice()
   {
	   List<Integer> list=new ArrayList<Integer>();
	   int[] com=new int[13];
	   int[] a=new int[4];
	   for(int i=0;i<4;i++)
	   {
		   int sel=(int)(Math.random()*13);
		   com[sel]=1;
	   }
	   boolean bCheck=false;
	   int sel=0;
	   for(int i=0;i<4;i++)
	   {
		   bCheck=true;
		   while(bCheck)
		   {
		       sel=(int)(Math.random()*13);
		       bCheck=false;
		       if(com[sel]!=0)
		       {
		    	   bCheck=true;
		    	   break;
		       }
		   }
		   com[sel]=2;
	   }
	   bCheck=false;
	   sel=0;
	   for(int i=0;i<4;i++)
	   {
		   bCheck=true;
		   while(bCheck)
		   {
		       sel=(int)(Math.random()*13);
		       bCheck=false;
		       if(com[sel]!=0)
		       {
		    	   bCheck=true;
		    	   break;
		       }
		   }
		   com[sel]=3;
	   }
	   for(int i=0;i<13;i++)
	   {
		   System.out.print(com[i]+" ");
	   }
	   return list;
   }
}
