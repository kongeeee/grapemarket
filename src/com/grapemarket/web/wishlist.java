package com.grapemarket.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grapemarket.model.dbDAO;
import com.grapemarket.model.wishlistDTO;

@WebServlet("/wishlist")
public class wishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public wishlist() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ArrayList<wishlistDTO> wish_list = null;
		
		int member_index = 0;
		
		PrintWriter script = response.getWriter();
		
		dbDAO db = new dbDAO();
		db.DBconnect();
		
		try {
			member_index = (int) request.getSession().getAttribute("member_index");
			wish_list = db.wishlist(member_index,6,0);
			request.setAttribute("list", wish_list);
		}catch(Exception e) {
		}
		
		db.DBclose();
		
		request.getRequestDispatcher("/WEB-INF/views/wishlist.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
