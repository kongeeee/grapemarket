package com.grapemarket.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grapemarket.model.dbDAO;
import com.grapemarket.model.postDTO;

@WebServlet("/main")
public class main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public main() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dbDAO db = new dbDAO();
		db.DBconnect();
		ArrayList<postDTO> list = db.post_main();
		
		ArrayList<postDTO> view = db.post_views();
		
		request.setAttribute("list", list);
		request.setAttribute("view", view);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/main.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
