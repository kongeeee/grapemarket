package com.grapemarket.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grapemarket.model.commentDTO;
import com.grapemarket.model.dbDAO;
import com.grapemarket.model.memberDTO;
import com.grapemarket.model.postDTO;

/**
 * Servlet implementation class view
 */
@WebServlet("/view")
public class view extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public view() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		dbDAO db = new dbDAO();
		postDTO post = null;
		commentDTO comment = null;
		String category = request.getParameter("category");
		int post_index = 0;
		
		try {
			post_index = Integer.parseInt(request.getParameter("view_index"));
			if(post_index < 0) throw new Exception("view.jsp에서 음수값이 입력되었습니다.");
		}catch(Exception e) {
			 request.getRequestDispatcher("/WEB-INF/views/pageNotFound.jsp").forward(request, response);
			 System.out.println(e.getMessage());
			 return;
		}
		
		db.DBconnect();
		
		post = db.getpost(post_index);									// 게시글 불러오기
		
		if(post == null) {
			request.getRequestDispatcher("/WEB-INF/views/pageNotFound.jsp").forward(request, response);
			System.out.println("해당 인덱스에 게시물이 없습니다.");
			return;
		}
		
		db.views_up(post_index);										// 조회수
		
		ArrayList<commentDTO> comment_list = db.comment(post_index);	// 댓글 불러오기

		db.DBclose();
		
		request.setAttribute("post", post);								// 게시글
		request.setAttribute("comment", comment_list);					// 댓글
		request.setAttribute("category", category);
		
		request.getRequestDispatcher("/WEB-INF/views/view.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
