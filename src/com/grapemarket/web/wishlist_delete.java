package com.grapemarket.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grapemarket.model.dbDAO;
import com.grapemarket.model.wishlistDTO;

@WebServlet("/wishlist_delete")
public class wishlist_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public wishlist_delete() {super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		wishlistDTO wish = new wishlistDTO();
		dbDAO db = new dbDAO();
		
		int post_index = 0;

		String view_index = request.getParameter("view_index");
		try {
			post_index = Integer.parseInt(view_index);
		}catch(Exception e) {
			e.getMessage();
		}

		db.DBconnect();
		
		wish.setPost_index(post_index);
		db.wish_delete(wish);
		
		db.DBclose();
		
		String path = "/wishlist";

		response.sendRedirect(path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
