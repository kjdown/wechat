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
		System.out.println("ѧ��������Ϣ��");
		for(Map.Entry<String, String> entry : getcontent.infoMap.entrySet()){
			    System.out.println(entry.getKey()+" : "+entry.getValue());
			}
	}
	
	public static void cj(){
		System.out.println("\nѧ���ɼ���");
		System.out.println("�칫���"+'\t'+getcontent.scoreMap.get("�칫���"));
		System.out.println("�й�����ʷ��Ҫ"+'\t'+getcontent.scoreMap.get("�й�����ʷ��Ҫ"));
		System.out.println("��ѧӢ�� I"+'\t'+getcontent.scoreMap.get("��ѧӢ�� I"));
		System.out.println("C++���Գ������I"+'\t'+getcontent.scoreMap.get("C++���Գ������I"));
		System.out.println("��ѧ������������ I"+'\t'+getcontent.scoreMap.get("��ѧ������������ I"));
		System.out.println("���½���"+'\t'+getcontent.scoreMap.get("���½���"));
		System.out.println("�������ѧ����"+'\t'+getcontent.scoreMap.get("�������ѧ����"));
		System.out.println("��ҳ���"+'\t'+getcontent.scoreMap.get("��ҳ���"));
		System.out.println("˼����������뷨�ɻ���"+'\t'+getcontent.scoreMap.get("˼����������뷨�ɻ���"));
		System.out.println("��ɢ��ѧ"+'\t'+getcontent.scoreMap.get("��ɢ��ѧ"));
	 	
	}
	
	
	public static void kb() {
		//�������	
		
		
		System.out.println("\nѧ���γ̱�");
		//��ʵ���Ը��������һ������֪���жϵڼ���
		System.out.print("�������ڶ��ڿΣ�"+ "ʱ���ǣ�"+ getcontent.clasStrings[2][0]);
		System.out.println(" \t" + getcontent.clasStrings[2][3]);
		System.out.print("���ڶ���һ�ڿΣ�"+ "ʱ���ǣ�"+ getcontent.clasStrings[1][0]);
		System.out.println(" \t" + getcontent.clasStrings[1][2]);
		System.out.print("�������һ�ڿΣ�"+ "ʱ���ǣ�"+ getcontent.clasStrings[1][0]);
		System.out.println(" \t" + getcontent.clasStrings[1][5]);

	}
}
	
