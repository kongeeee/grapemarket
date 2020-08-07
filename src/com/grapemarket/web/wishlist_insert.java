package com.grapemarket.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grapemarket.model.dbDAO;
import com.grapemarket.model.memberDTO;
import com.grapemarket.model.postDTO;

@WebServlet("/wishlist_insert")
public class wishlist_insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public wishlist_insert() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		memberDTO mem = new memberDTO();
		postDTO post = new postDTO();
		PrintWriter script = response.getWriter();
		
		int member_index = 0;
		try {
			member_index = (int) request.getSession().getAttribute("member_index");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			script.println("<script>alert('로그인이 필요한 서비스 입니다.');");
			script.println("location.href='"+(String)request.getSession().getAttribute("prevPage")+"';</script>");
			return;
		}
		
		String view_index = request.getParameter("view_index");
		
		dbDAO db = new dbDAO();
		db.DBconnect();
		int post_index = 0;
		
		if(view_index != null) {
			post_index = Integer.parseInt(view_index);
			mem.setMember_index(member_index);
			post.setPost_index(post_index);
		}
		
		System.out.println("view_index :" + view_index);
		System.out.println("member_index : " + member_index);
		
		String path = "/wishlist";
		
		int wish = 1;
		wish = db.wishlist(member_index, post_index);
		if(wish >= 1) {
			script.println("<script>alert('이미 등록되어있습니다');location.href='"+(String)request.getSession().getAttribute("nowPage")+"';</script>");
		}else {
			db.wish_insert(mem, post);
			request.getRequestDispatcher(path).forward(request, response);
		}

		db.DBclose();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
