package com.grapemarket.web;


import java.io.IOException;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.Statement;

import java.util.Arrays;



import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import com.grapemarket.model.dbDAO;
import com.grapemarket.model.memberDTO;

@WebServlet("/LogInOk")

public class LogInOk extends HttpServlet {

	private static final long serialVersionUID = 1L;


	private String userID, pswd;

       
    public LogInOk() {

        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    
    userID = request.getParameter("userID");
    pswd   = request.getParameter("pswd");
    
    dbDAO db = new dbDAO();
    db.DBconnect();
    
    memberDTO mem = new memberDTO();
    
    mem.setUserID(userID);
    mem.setPswd(pswd);
    
    
    
    request.getRequestDispatcher("/").forward(request, response);
    
	}
    
	
}
    
  
    