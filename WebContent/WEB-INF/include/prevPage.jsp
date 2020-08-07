<%@ page contentType="text/html; charset=utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
	// prevPageCode 이전페이지를 세션에 저장하는 로직
	String nowURL   = request.getAttribute("javax.servlet.forward.request_uri") + "?" + request.getQueryString();
	String nowPage  = (String)session.getAttribute("nowPage");
	String prevPage = (String)session.getAttribute("prevPage");
	if (prevPage == null) session.setAttribute("prevPage", "/");
	
	if (nowPage == null) {
		session.setAttribute("nowPage", nowURL);
	} else {
		if (!nowPage.equals(nowURL)) {
			session.setAttribute("prevPage", nowPage);
			session.setAttribute("nowPage", nowURL);
		}
	}
	// prevPageCode
%>