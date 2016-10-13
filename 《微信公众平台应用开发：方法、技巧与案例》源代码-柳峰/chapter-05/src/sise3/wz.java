package sise3;

import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class wz {
	

	public static String getinfolink(Document main_jsp){
		Elements stuclass=main_jsp.select("body > table > tbody > tr:nth-child(1) > td:nth-child(1) > table > tbody > tr > td");
		String stu=stuclass.attr("onclick");

		Pattern p=Pattern.compile("'../../../../../?"); 
		String[] str=p.split(stu);

		String reallink="http://class.sise.com.cn:7001/" + str[1];

		reallink=reallink.substring(0,reallink.length()-1);
		return reallink;	
			
	};
	
	public static String getclasslink(Document main_jsp){
		Elements stuclass=main_jsp.select("body > table > tbody > tr:nth-child(1) > td:nth-child(2) > table > tbody > tr > td");
		String stu=stuclass.attr("onclick");

		Pattern p=Pattern.compile("'/?"); 
		String[] str=p.split(stu);
		String reallink="http://class.sise.com.cn:7001/" + str[1];
		return reallink;
	};
	

	public static String getexamlink(Document main_jsp){
		Elements stuclass=main_jsp.select("body > table > tbody > tr:nth-child(1) > td:nth-child(3) > table > tbody > tr > td");
		String stu=stuclass.attr("onclick");

		Pattern p=Pattern.compile("'../../../../../?"); 
		String[] str=p.split(stu);
		String reallink="http://class.sise.com.cn:7001/" + str[1];
		reallink=reallink.substring(0,reallink.length()-1);
		return reallink;
	};
}
