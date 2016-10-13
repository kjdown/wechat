package org.liufeng.course.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ʾ����ʹ��JDBC����BAE�е�MySQL���ݿ�
 * 
 * @author liufeng
 * @date 2013-11-18
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1196941092414541883L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");

		PrintWriter out = response.getWriter();
		// ��ѯuser
		List<HashMap<String, Object>> userList = queryUser(request);
		// ����List����
		for (HashMap<String, Object> map : userList) {
			out.println(map.get("name") + " " + map.get("age"));
		}
		out.flush();
		out.close();
	}

	/**
	 * ��ѯBAE MySQL���ݿ���user�������
	 * 
	 * @param request
	 * @return
	 */
	private static List<HashMap<String, Object>> queryUser(HttpServletRequest request) {
		List<HashMap<String, Object>> userList = new ArrayList<HashMap<String, Object>>();

		// ��request����ͷ��ȡ��IP���˿ڡ��û���������
		String host = request.getHeader("BAE_ENV_ADDR_SQL_IP");
		String port = request.getHeader("BAE_ENV_ADDR_SQL_PORT");
		String username = request.getHeader("BAE_ENV_AK");
		String password = request.getHeader("BAE_ENV_SK");
		// ���ݿ�����
		String dbName = "FTGJUvPHrbXsLGsYpwlp";
		// JDBC URL
		String url = String.format("jdbc:mysql://%s:%s/%s", host, port, dbName);

		try {
			// ����MySQL����
			Class.forName("com.mysql.jdbc.Driver");
			// ��ȡ���ݿ�����
			Connection conn = DriverManager.getConnection(url, username, password);
			// �����ѯSQL���
			String sql = "select name,age from user";
			// ����PreparedStatement���󣨰����ѱ����SQL��䣩
			PreparedStatement ps = conn.prepareStatement(sql);
			// ִ�в�ѯ����ȡ�����
			ResultSet rs = ps.executeQuery();
			// ������ѯ�����
			while (rs.next()) {
				HashMap<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("name", rs.getString("name"));
				userMap.put("age", rs.getInt("age"));
				userList.add(userMap);
			}

			// �ر����ӣ��ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
}