package sise3;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class siseA {
	
	static Map<String,String> cookies =null;
	public static String denglu(String username,String password) throws Exception {
		Connection.Response connection = Jsoup.connect("http://class.sise.com.cn:7001/sise/login.jsp").execute();
		try {connection = Jsoup.connect("http://class.sise.com.cn:7001/sise/login.jsp").execute();} 
		catch (Exception e) {System.out.println("Ä£ÄâµÇÂ½Ê§°Ü");}
		
		 Document loginjsp=Jsoup.parse(connection.body());

		 Elements element = loginjsp.getElementsByTag("form");
			
			String dataKey = element.get(0).childNode(1).attr("name");
		
			String data = element.get(0).childNode(1).attr("value");

			Map<String,String> xinxi = new HashMap<String,String>();
			xinxi.put("username", username);
			xinxi.put("password", password);
			xinxi.put(dataKey, data);
			
			Connection conn = Jsoup.connect("http://class.sise.com.cn:7001/sise/login_check.jsp");	
			Response response = conn.ignoreContentType(true).method(Method.POST).data(xinxi).execute();
			
			if(response.body().indexOf("index.jsp")<0){
				System.out.println("Ç×°®µÄ£¬µÇÂ½Ê§°Ü");
				return "error";
			}
			cookies = response.cookies();
			

			Document main_jsp = Jsoup.connect("http://class.sise.com.cn:7001/sise/module/student_states/student_select_class/main.jsp").cookies(cookies).post();
			main_jsp.charset(Charset.forName("UTF-8"));
			//System.out.println(main_jsp);
	
			Map<String,String> link = new HashMap<String,String>();
			link.put("infolink", wz.getinfolink(main_jsp));
			link.put("classlink", wz.getclasslink(main_jsp));
			link.put("examlink", wz.getexamlink(main_jsp));
			
			getcontent.getinfo(link.get("infolink").toString());
			getcontent.getscore(link.get("infolink").toString());
			getcontent.getclass(link.get("classlink").toString());
			getcontent.getexam(link.get("examlink").toString());	
			return "";
	}
	public static void main(String[] args) throws Exception {
		siseA.denglu("1440121203","a");	
		Print.kb();
		Print.TestTime();
		Print.information();
		Print.cj();
		
	}

}
