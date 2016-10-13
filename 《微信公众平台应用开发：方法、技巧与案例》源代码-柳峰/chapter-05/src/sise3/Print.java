package sise3;

import java.util.Map;

public class Print {
	public static void TestTime(){
		int a=0;
		   for(String arr : getcontent.examStrings[1]){
					System.out.print(getcontent.examStrings[0][a]+":  ");
					System.out.println(arr);
					a++;
				}   
		   System.out.println();
		   int b=0;
		   for(String arr : getcontent.examStrings[2]){
					System.out.print(getcontent.examStrings[0][b]+":  ");
					System.out.println(arr);
					b++;
				} 
		   System.out.println();
		   int c=0;
		   for(String arr : getcontent.examStrings[3]){
					System.out.print(getcontent.examStrings[0][c]+":  ");
					System.out.println(arr);
					c++;
				} 
		   System.out.println();
		   int d=0;
		   for(String arr : getcontent.examStrings[4]){
					System.out.print(getcontent.examStrings[0][d]+":  ");
					System.out.println(arr);
					d++;
				} 
		   System.out.println();
		   int e=0;
		   for(String arr : getcontent.examStrings[5]){
					System.out.print(getcontent.examStrings[0][e]+":  ");
					System.out.println(arr);
					e++;
				} 
		   System.out.println();
		   int f=0;
		   for(String arr : getcontent.examStrings[6]){
					System.out.print(getcontent.examStrings[0][f]+":  ");
					System.out.println(arr);
					f++;
				} 
	}
	
	public static void information(){
		System.out.println("学生个人信息：");
		for(Map.Entry<String, String> entry : getcontent.infoMap.entrySet()){
			    System.out.println(entry.getKey()+" : "+entry.getValue());
			}
	}
	
	public static void cj(){
		System.out.println("\n学生成绩：");
		System.out.println("办公软件"+'\t'+getcontent.scoreMap.get("办公软件"));
		System.out.println("中国近代史纲要"+'\t'+getcontent.scoreMap.get("中国近代史纲要"));
		System.out.println("大学英语 I"+'\t'+getcontent.scoreMap.get("大学英语 I"));
		System.out.println("C++语言程序设计I"+'\t'+getcontent.scoreMap.get("C++语言程序设计I"));
		System.out.println("大学生心身健康教育 I"+'\t'+getcontent.scoreMap.get("大学生心身健康教育 I"));
		System.out.println("军事教育"+'\t'+getcontent.scoreMap.get("军事教育"));
		System.out.println("计算机科学导论"+'\t'+getcontent.scoreMap.get("计算机科学导论"));
		System.out.println("网页设计"+'\t'+getcontent.scoreMap.get("网页设计"));
		System.out.println("思想道德修养与法律基础"+'\t'+getcontent.scoreMap.get("思想道德修养与法律基础"));
		System.out.println("离散数学"+'\t'+getcontent.scoreMap.get("离散数学"));
	 	
	}
	
	
	public static void kb() {
		//测试输出	
		
		
		System.out.println("\n学生课程表：");
		//其实可以根据数组第一个索引知道判断第几节
		System.out.print("星期三第二节课："+ "时间是："+ getcontent.clasStrings[2][0]);
		System.out.println(" \t" + getcontent.clasStrings[2][3]);
		System.out.print("星期二第一节课："+ "时间是："+ getcontent.clasStrings[1][0]);
		System.out.println(" \t" + getcontent.clasStrings[1][2]);
		System.out.print("星期五第一节课："+ "时间是："+ getcontent.clasStrings[1][0]);
		System.out.println(" \t" + getcontent.clasStrings[1][5]);

	}
}
	
