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
import com.grapemarket.model.messengerDTO;

@WebServlet("/messenger")
public class messenger extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public messenger() { super(); }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/PageNotFound.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		dbDAO db = new dbDAO();
		db.DBconnect();
		System.out.println(request.getParameter("uidx"));
		
		if (request.getParameter("message") != null) {
			sendMessage(request.getParameter("uidx"), request.getParameter("uidx2"), request.getParameter("message"), db);
			return;
		}
		
		if (request.getParameter("uidx2") == null) {
			ArrayList<messengerDTO> msgr = db.messenger2(Integer.parseInt(request.getParameter("uidx")));
			System.out.println(msgr.size());
			System.out.println(msgr == null);
			
			request.setAttribute("profile", msgr);
			request.setAttribute("list_count", msgr.size());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/component/profile.jsp");
			dispatcher.forward(request, response);
		} else {
			System.out.println(request.getParameter("uidx2"));
			ArrayList<messengerDTO> msgr = db.messeger_cut(Integer.parseInt(request.getParameter("uidx2")), Integer.parseInt(request.getParameter("uidx")), 100, 0);
			System.out.println(msgr.size());
			request.setAttribute("view", msgr);
			request.setAttribute("message_count", msgr.size());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/component/view.jsp");
			dispatcher.forward(request, response);
		}
		
		db.DBclose();
	}
	
	private void sendMessage(String me, String you, String message, dbDAO db) {
		messengerDTO mes = new messengerDTO();
		System.out.println(me + you + message);
		mes.setMember_index(Integer.parseInt(me));
		mes.setMember_index2(Integer.parseInt(you));
		mes.setContent(message);
		
		db.mes_insert(mes);
		
		db.DBclose();
	}

}
