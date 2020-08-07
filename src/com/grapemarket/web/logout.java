package com.grapemarket.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class logout
 */
@WebServlet("/logout")
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String prevPage = (String)request.getSession().getAttribute("nowPage");
		request.getSession().invalidate();
		
		Cookie[] cookies = request.getCookies();
		for (int index = 0; index < cookies.length; index++) {
			cookies[index].setMaxAge(0);
			response.addCookie(cookies[index]);
		}
		
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		response.getWriter().append("<script>alert('로그아웃하였습니다.'); location.href = '" + prevPage + "';</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
