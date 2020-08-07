package com.grapemarket.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.grapemarket.model.dbDAO;
import com.grapemarket.model.memberDTO;
import com.grapemarket.model.postDTO;

@WebServlet("/write")
public class write extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public write() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String view_index = null;
		int post_index = 0;
		String category=request.getParameter("category");

		try {
			view_index = request.getParameter("view_index");
			
			if(view_index != null) 
			{
				post_index = Integer.parseInt(view_index);
				
				dbDAO db = new dbDAO();
				
				db.DBconnect();
				
				postDTO post = db.getpost(post_index);
	
				db.DBclose();
				
				request.setAttribute("modify", post);
				request.setAttribute("post_index", post_index);
			
		}}catch(Exception e) {
			e.getMessage();
		}
		
		request.setAttribute("category", category);
		request.setAttribute("view_index",view_index);

		request.getRequestDispatcher("/WEB-INF/views/write.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String is_modify = request.getParameter("is_modify");

		if(is_modify.equals("0")) write(request, response);
		else modify(request, response);

	}
	
	protected void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter script = response.getWriter();
		
		int isphone = 0;
		String public_phone = "공개안함";
		
		postDTO post = new postDTO();
		memberDTO mem = new memberDTO();
		
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		if(request.getParameter("public_phone") != null) {
			public_phone = request.getParameter("public_phone");
		}
		if(public_phone.equals("공개")) isphone = 1;
		else isphone = 0;
		String img = request.getParameter("file");
		int member_index = (int) request.getSession().getAttribute("member_index");
		
		dbDAO db = new dbDAO();
		
		db.DBconnect();
		
		post.setTitle(title);
		post.setContent(content);
		post.setCategory(category);
		post.setImg(img);
		post.setPrice(price);
		post.setIsphone(isphone);
		mem.setMember_index(member_index);
		
		db.post_insert(mem, post);
		
		db.DBclose();
		
		request.setAttribute("post", post);

		String path = "/list?category="+category ;
		
		script.println("<script>alert('작성되었습니다');"
				+ "location.href='"+path+"';"
				+ "</script>");
		script.close();
	}

	protected void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		postDTO post = new postDTO();
		PrintWriter script = response.getWriter();
		
		int isphone = 0;
		String public_phone = "공개안함";

		//글 수정하기
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		if(request.getParameter("public_phone") != null) {
			public_phone = request.getParameter("public_phone");
		}
		if(public_phone.equals("공개")) isphone = 1;
		else isphone = 0;
		String img = request.getParameter("img");
		int member_index = (int) request.getSession().getAttribute("member_index");
		int post_index = Integer.parseInt(request.getParameter("view_index"));
		String nickname = (String) request.getSession().getAttribute("nickname");
		String phone = (String) request.getSession().getAttribute("phone");
		
		dbDAO db = new dbDAO();
		db.DBconnect();
		
		post.setCategory(category);
		post.setTitle(title);
		post.setContent(content);
		post.setPrice(price);
		post.setImg(img);
		post.setPost_index(post_index);
		post.setIsphone(isphone);

		db.post_update(post);
		
		db.DBclose();
		
		request.setAttribute("post", post);
		
		String path = "/list?category="+category+"&view_index="+post_index ;
		
		script.println("<script>alert('수정되었습니다');"
				+ "location.href='"+path+"';"
				+ "</script>");
		script.close();
	}
}