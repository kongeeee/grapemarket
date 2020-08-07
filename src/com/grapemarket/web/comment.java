package com.grapemarket.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grapemarket.model.dbDAO;

import com.grapemarket.model.dbDAO;

@WebServlet("/comment")
public class comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public comment() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		
        if (request.getParameter("member_index") == null) {
			request.getRequestDispatcher("/WEB-INF/views/PageNotFound.jsp").forward(request, response);
		} else {
			int member_index = Integer.parseInt(request.getParameter("member_index"));
			int memidx = (int)request.getSession().getAttribute("member_index");
			int comment_index = Integer.parseInt(request.getParameter("comment_index"));
			System.out.println(member_index);
			System.out.println(memidx);
			if (member_index == memidx) {
				dbDAO db = new dbDAO();
				db.DBconnect();
				db.comment_delete(comment_index);
				response.getWriter().append("<script>alert('댓글이 삭제되었습니다.'); location.href = '" + (String)request.getSession().getAttribute("nowPage") + "';</script>");
			}else {
				response.getWriter().append("<script>alert('댓글을 삭제할 수 없었습니다.'); location.href = '" + (String)request.getSession().getAttribute("nowPage") + "';</script>");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dbDAO db = new dbDAO();
		db.DBconnect();
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		
        System.out.println(request.getParameter("view_index"));
		if (request.getParameter("content").equals("")) {
			response.getWriter().append("<script>alert('댓글을 입력해 주세요.'); location.href = '" + (String)request.getSession().getAttribute("nowPage") + "';</script>");
		} else {
			db.comment_insert((int)request.getSession().getAttribute("member_index"), Integer.parseInt(request.getParameter("view_index")), request.getParameter("content"));
			response.getWriter().append("<script>alert('댓글이 작성되었습니다.'); location.href = '" + (String)request.getSession().getAttribute("nowPage") + "';</script>");
		}
		db.DBclose();
	}

}
