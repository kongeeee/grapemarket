package com.grapemarket.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grapemarket.model.dbDAO;
import com.grapemarket.model.postDTO;

@WebServlet("/list")
public class list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public list() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		dbDAO db = new dbDAO();
		db.DBconnect();	
		ArrayList<postDTO> post_list = null;
		
		String search = request.getParameter("search");
		if (search == null) search = "";
		String category = request.getParameter("category");
		String link = "/list?category="+category+"&search="+search+"&page=";
		int page = 1;
		int first_page = 1;
		int last_page = 1;
		int posts_per_page = 10;
		int page_size = 5;
		int total_count = db.post_countX(category, search);
		last_page = total_count / posts_per_page;
		if (total_count % posts_per_page != 0) last_page++;
		int max_page = last_page;
		try { page = Integer.parseInt(request.getParameter("page")); } catch (Exception e) {}
		if (page > last_page) page = last_page;
		if (page < 1) page = 1;
		while (page > page_size + first_page - 1) first_page += page_size;
		if(last_page > first_page + page_size - 1) last_page = first_page + page_size - 1;
		
		post_list = db.title_search(search, category, posts_per_page, (page-1)*posts_per_page);
		
		ArrayList<String> prev_page = new ArrayList<String>();
		prev_page.add(page==1?"#":link+String.valueOf(page-1));
		prev_page.add(page==1?"deactivate":"");
		ArrayList<String> next_page = new ArrayList<String>();
		next_page.add(page==max_page?"#":link+String.valueOf(page+1));
		next_page.add(page==max_page?"deactivate":"");
		
		request.setAttribute("link", link);
		request.setAttribute("prev_page", prev_page);
		request.setAttribute("next_page", next_page);
		request.setAttribute("page", page);
		request.setAttribute("first_page", first_page);
		request.setAttribute("last_page", last_page);
		request.setAttribute("category", category);
		request.setAttribute("post_list", post_list);


		
		db.DBclose();	

		
		
		
		request.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}