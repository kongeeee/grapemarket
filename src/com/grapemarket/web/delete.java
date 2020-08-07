package com.grapemarket.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grapemarket.model.dbDAO;
import com.grapemarket.model.postDTO;

/**
 * Servlet implementation class delete
 */
@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public delete() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String category = request.getParameter("category");
		String view_index = request.getParameter("view_index");
		int post_index = Integer.parseInt(view_index);
		
		postDTO post = new postDTO();
		dbDAO db = new dbDAO();
		db.DBconnect();
		
		post.setPost_index(post_index);
		
		db.post_delete(post);
		db.DBclose();
		
		String path = (String)request.getSession().getAttribute("prevPage");
		
		request.setAttribute("category", category);

		request.getRequestDispatcher(path).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
