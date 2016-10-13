package sise3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getcontent {
	
		static Map<String,String> infoMap = new HashMap<String,String>();
		static Map<String,String> scoreMap = new HashMap<String,String>();	
		static String [][] clasStrings = new String [9][8]; 
		
		
		
		
		
		
		static String [][] examStrings = new String [10][8]; 
		
	
		public static void getinfo(String infolink){
		
			Document course_jsp=getpage(infolink);
			Elements stuinfo = course_jsp.select("#form1 > table.table1 > tbody > tr > td > table > tbody > tr > td ");
			
			for (int i = 0; i < stuinfo.size(); i=i+2) {
		        String studentinfo = stuinfo.get(i).text();
					      if(!(studentinfo ==null || studentinfo.isEmpty())){
					    	  studentinfo=studentinfo.replaceAll(" ：", "");
					    	  infoMap.put(studentinfo, stuinfo.get(i+1).text());   
					      }
					}
			
		};
	
				public static String getscore(String infolink){
				
					Document score_jsp=getpage(infolink);
	
					Elements stuscore = score_jsp.select("#form1 > table:nth-child(6) > tbody > tr");
					
					//获取必修课程分数
					for (int i = 0; i < stuscore.size(); i=i+1) {
						//如果已得学分的表格不为空
						if (!(stuscore.get(i).select(" td:nth-child(10)").text().toString().length()==0)) {
						    	  scoreMap.put(stuscore.get(i).select(" td:nth-child(3)").text(), 
						    			  	   stuscore.get(i).select(" td:nth-child(9)").text());
						}
					}
					
				  //获取选修课程分数
					 stuscore = score_jsp.select("#form1 > table:nth-child(10) > tbody > tr");
						for (int i = 0; i < stuscore.size(); i=i+1) {
							//如果已得学分的表格不为空
							if (!(stuscore.get(i).select(" td:nth-child(9)").text().toString().length()==0)) {
							    	  scoreMap.put(stuscore.get(i).select(" td:nth-child(2)").text(), 
							    			  	   stuscore.get(i).select(" td:nth-child(8)").text());
							      
							}
						}
					return null;
				};
		
				
		//返回课程表（默认返回当前学期课表）
		public static void  getclass(String classlink){
			
			Document class_jsp=getpage(classlink);
			Elements stuclass = class_jsp.select("body > form > table:nth-child(5) > tbody > tr:nth-child(1)");
			//赋值星期 字符串到数组
			for (int i = 1; i <=7; i++) {
				clasStrings[0][i]=stuclass.select("td").get(i).text();
				
			}
			
			//写入课程详情
			for (int i = 2; i <=9; i++) {
				String selecString="body > form > table:nth-child(5) > tbody > tr:nth-child("+ i +")";
				stuclass = class_jsp.select(selecString);
					for (int j = 0; j <stuclass.select("td").size(); j++) {
						//严格判断表格内容是否存在意义，即该td是否存在真正的课程
						if (!(stuclass.select("td").get(j).text().toString().length()==0||(stuclass.select("td").get(j).toString().indexOf("nbsp")>=0))) {
							selecString="td:nth-child("+ (j+1) +")";
							clasStrings[i-1][j]=stuclass.select(selecString).text();
						}else {
							clasStrings[i-1][j]="-1";
						}		
					}		
			}
		
		};
		
		//返回考试安排表
		public static void getexam(String examlink){
			
			Document exam_jsp=getpage(examlink);
			
			Elements stuexam = exam_jsp.select("#form1 > div > table.table");
			 
			//赋值备注信息（表头）到数组
			for (int i = 0; i <=7; i++) {
				examStrings[0][i]=stuexam.select("th").get(i).text();
				
			}
			
			stuexam=stuexam.select("tbody > tr");
			//写入考试安排表
			for (int i = 1; i <=stuexam.size(); i++) {
			
					for (int j = 0; j <8; j++) {
							examStrings[i][j]=stuexam.get(i-1).select("td").get(j).text().toString();
								
					}		
			}
			
		};
		
		//获取每个分页面，并返回Document对象
		public static Document getpage(String examlink){
			Document pageDocument;
			try {
				pageDocument = Jsoup.connect(examlink).cookies(siseA.cookies).post();
				pageDocument.charset(Charset.forName("UTF-8"));
				//System.out.println(pageDocument);
				return pageDocument;
			} catch (IOException e) {
				//抛出异常，说明404错误
				e.printStackTrace();
				return null;
			}
		}
}
