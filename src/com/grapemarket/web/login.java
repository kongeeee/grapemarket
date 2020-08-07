package com.grapemarket.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grapemarket.model.dbDAO;
import com.grapemarket.model.memberDTO;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean is_login_keep = false;
		Cookie[] cookies = request.getCookies();
		int index = 0;
		String userid = request.getParameter("keep_login");
		System.out.println(userid);
		if (request.getHeader("Cookie") != null) {
			for (; index < cookies.length; index++) {
				if (cookies[index].getName().equals("keep_login")) {
					is_login_keep = true;
					break;
				}
			}
		}
		
		if (request.getParameter("keep_login") == null) {
			request.getRequestDispatcher("/WEB-INF/views/PageNotFound.jsp").forward(request, response);
		} else if (is_login_keep && userid.equals(cookies[index].getValue())){
			
			dbDAO db = new dbDAO();
			db.DBconnect();
			memberDTO mem = db.id_search(userid);
			
			request.getSession().setAttribute("userID", mem.getUserID());
			request.getSession().setAttribute("nickname", mem.getNickname());
			request.getSession().setAttribute("phone", mem.getPhone());
			request.getSession().setAttribute("member_index", mem.getMember_index());
			request.getSession().setAttribute("admin", mem.getIsadmin());
			response.sendRedirect("/");
		} else {
			request.getRequestDispatcher("/WEB-INF/views/PageNotFound.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userID");
		String pswd = request.getParameter("pswd");
		
		dbDAO db = new dbDAO();
		db.DBconnect();
		memberDTO mem = db.login(id, pswd);
		if(mem != null) {
			// 로그인 성공
			request.getSession().setAttribute("userID", mem.getUserID());
			request.getSession().setAttribute("nickname", mem.getNickname());
			request.getSession().setAttribute("phone", mem.getPhone());
			request.getSession().setAttribute("member_index", mem.getMember_index());
			request.getSession().setAttribute("admin", mem.getIsadmin());
			System.out.println(request.getParameter("keep_login") != null);
			if (request.getParameter("keep_login") != null) {
				Cookie keep_login = new Cookie("keep_login", mem.getUserID());
				keep_login.setMaxAge(60*60*24);
				response.addCookie(keep_login);
			}
			response.sendRedirect((String)request.getSession().getAttribute("nowPage"));
		} else {
			// 로그인 실패
			response.setContentType("text/html;charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
			response.getWriter().append("<script>alert('아이디 또는 비밀번호가 틀립니다'); location.href = '"+(String)request.getSession().getAttribute("nowPage")+"';</script>");
		}
		
		db.DBclose();
	}

}
