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
					    	  studentinfo=studentinfo.replaceAll(" ��", "");
					    	  infoMap.put(studentinfo, stuinfo.get(i+1).text());   
					      }
					}
			
		};
	
				public static String getscore(String infolink){
				
					Document score_jsp=getpage(infolink);
	
					Elements stuscore = score_jsp.select("#form1 > table:nth-child(6) > tbody > tr");
					
					//��ȡ���޿γ̷���
					for (int i = 0; i < stuscore.size(); i=i+1) {
						//����ѵ�ѧ�ֵı��Ϊ��
						if (!(stuscore.get(i).select(" td:nth-child(10)").text().toString().length()==0)) {
						    	  scoreMap.put(stuscore.get(i).select(" td:nth-child(3)").text(), 
						    			  	   stuscore.get(i).select(" td:nth-child(9)").text());
						}
					}
					
				  //��ȡѡ�޿γ̷���
					 stuscore = score_jsp.select("#form1 > table:nth-child(10) > tbody > tr");
						for (int i = 0; i < stuscore.size(); i=i+1) {
							//����ѵ�ѧ�ֵı��Ϊ��
							if (!(stuscore.get(i).select(" td:nth-child(9)").text().toString().length()==0)) {
							    	  scoreMap.put(stuscore.get(i).select(" td:nth-child(2)").text(), 
							    			  	   stuscore.get(i).select(" td:nth-child(8)").text());
							      
							}
						}
					return null;
				};
		
				
		//���ؿγ̱�Ĭ�Ϸ��ص�ǰѧ�ڿα�
		public static void  getclass(String classlink){
			
			Document class_jsp=getpage(classlink);
			Elements stuclass = class_jsp.select("body > form > table:nth-child(5) > tbody > tr:nth-child(1)");
			//��ֵ���� �ַ���������
			for (int i = 1; i <=7; i++) {
				clasStrings[0][i]=stuclass.select("td").get(i).text();
				
			}
			
			//д��γ�����
			for (int i = 2; i <=9; i++) {
				String selecString="body > form > table:nth-child(5) > tbody > tr:nth-child("+ i +")";
				stuclass = class_jsp.select(selecString);
					for (int j = 0; j <stuclass.select("td").size(); j++) {
						//�ϸ��жϱ�������Ƿ�������壬����td�Ƿ���������Ŀγ�
						if (!(stuclass.select("td").get(j).text().toString().length()==0||(stuclass.select("td").get(j).toString().indexOf("nbsp")>=0))) {
							selecString="td:nth-child("+ (j+1) +")";
							clasStrings[i-1][j]=stuclass.select(selecString).text();
						}else {
							clasStrings[i-1][j]="-1";
						}		
					}		
			}
		
		};
		
		//���ؿ��԰��ű�
		public static void getexam(String examlink){
			
			Document exam_jsp=getpage(examlink);
			
			Elements stuexam = exam_jsp.select("#form1 > div > table.table");
			 
			//��ֵ��ע��Ϣ����ͷ��������
			for (int i = 0; i <=7; i++) {
				examStrings[0][i]=stuexam.select("th").get(i).text();
				
			}
			
			stuexam=stuexam.select("tbody > tr");
			//д�뿼�԰��ű�
			for (int i = 1; i <=stuexam.size(); i++) {
			
					for (int j = 0; j <8; j++) {
							examStrings[i][j]=stuexam.get(i-1).select("td").get(j).text().toString();
								
					}		
			}
			
		};
		
		//��ȡÿ����ҳ�棬������Document����
		public static Document getpage(String examlink){
			Document pageDocument;
			try {
				pageDocument = Jsoup.connect(examlink).cookies(siseA.cookies).post();
				pageDocument.charset(Charset.forName("UTF-8"));
				//System.out.println(pageDocument);
				return pageDocument;
			} catch (IOException e) {
				//�׳��쳣��˵��404����
				e.printStackTrace();
				return null;
			}
		}
}
