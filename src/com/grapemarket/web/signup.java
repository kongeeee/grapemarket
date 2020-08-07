package com.grapemarket.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grapemarket.model.dbDAO;
import com.grapemarket.model.memberDTO;


@WebServlet("/signup")

public class signup extends HttpServlet {

	private static final long serialVersionUID = 1L;

//  private Connection con;
//	private Statement stmt;
//	ResultSet rs = null;
//	PreparedStatement pstm = null;
	
	int result = 0;
	
	private String userID, pswd, phone, nickname;

	public signup() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/pageNotFound.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// html파일에서 값을 가져온다.
		userID = request.getParameter("userID");
		pswd = request.getParameter("pswd");
		phone = request.getParameter("phone");
		nickname = request.getParameter("nickname");
	
		System.out.println(userID);
		System.out.println(pswd);
		System.out.println(phone);
		System.out.println(nickname);
		dbDAO db =new dbDAO();
		db.DBconnect();
		
		memberDTO mem = new memberDTO();
		
		mem.setNickname(nickname);
		mem.setPhone(phone);
		mem.setPswd(pswd);
		mem.setUserID(userID);
		
		// 잘 입력되었을 경우
		db.mem_insert(mem);
		
		db.DBclose();
		
//		request.getRequestDispatcher("joinResult.html").forward(request, response);
		
		
//		String query = "insert into member values('" + userID + "', '" + pswd + "', '" + phone + "', '" + nickname + "')";
		
//		String url="jdbc:postgresql://192.168.0.208/grapemarket";
//		String user = "grappy";
//		String password = "grappy";
		
		/*try {
			Class.forName("org.postgresql.Driver"); // JDBC 드라이버 로딩
			//System.out.println("jdbc Driver connect success");
			
			con = DriverManager.getConnection("org.postgresql.Driver","grappy","grappy"); //데이터베이스 연결
			stmt = con.createStatement(); //statement 객체 생성
			rs = stmt.executeQuery("SELECT VERSION()");
			
			if(rs.next()) {
			//System.out.println(rs.getString(1));
		}}catch(ClassNotFoundException e) {
			System.out.println("where is JDBC Driver?");
			e.printStackTrace();
		}catch(SQLException ex) {
			System.out.println("Connection failed");
			ex.printStackTrace();
			return;
		}*/
	}
}