package com.grapemarket.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grapemarket.model.dbDAO;
import com.grapemarket.model.memberDTO;

/**
 * Servlet implementation class mypage
 */
@WebServlet("/mypage")
public class mypage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mypage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/PageNotFound.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
		dbDAO db = new dbDAO();
		db.DBconnect();
		
		String nickname  = request.getParameter("nickname");
		String pswd      = request.getParameter("pswd");
		int member_index = (int)request.getSession().getAttribute("member_index");
		String information = "";
		
		if (!nickname.equals("")) {
			if (db.nickname_search(nickname) != null) {
				response.getWriter().append("<script>alert('이미있는 닉네임 입니다.'); location.href = '" + (String)request.getSession().getAttribute("nowPage") +"';</script>");
				return;
			} else {
				memberDTO mem = new memberDTO();
				mem.setNickname(nickname);
				mem.setMember_index(member_index);
				db.memnick_update(mem);
				information += "닉네임";
				request.getSession().setAttribute("nickname", nickname);
			}
		}
		if (!pswd.equals("")) {
			memberDTO mem = new memberDTO();
			mem.setPswd(pswd);
			mem.setMember_index(member_index);
			db.mempswd_update(mem);
			information += " 패스워드";
		}
		information += " 변경이 완료되었습니다";
		response.getWriter().append("<script>alert('"+information+"'); location.href = '" + (String)request.getSession().getAttribute("nowPage") + "';</script>");
	}

}
