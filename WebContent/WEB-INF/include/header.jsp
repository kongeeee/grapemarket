<%@ page contentType="text/html; charset=utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="javax.servlet.http.Cookie" %>
<%	
	if (session.getAttribute("nickname") == null) {
		if (request.getHeader("Cookie") != null) {
			
			Cookie[] cookies = request.getCookies();
			int index = 0;
			boolean is_login_keep = false;
			for (; index < cookies.length; index++) {
				if (cookies[index].getName().equals("keep_login")) {
					is_login_keep = true;
					break;
				}
			}
			if(is_login_keep) {
				out.print("<script>location.href='/login?keep_login=' + escape('"+cookies[index].getValue()+"');</script>");
			}
		}
	}
%>
<jsp:include page="./prevPage.jsp"/>
<header>
    <div class="top">
        <h1><a href="/">포도마켓</a></h1>
        <form class="input_box" method="GET" action="/list">
            <i class="fa fa-search"></i>
            <input name="search" type="search" placeholder="제목을 검색해 주세요" value="${param.search}">
            <select id="category" name="category">
            	<option ${ category == "deal_board" ? "selected" : "" } value="deal_board">거래게시판</option>
            	<option ${ category == "free_board" ? "selected" : "" } value="free_board">자유게시판</option>
            	<option ${ category == "question_board" ? "selected" : "" } value="question_board">질문게시판</option>
            	<option ${ category == "report_board" ? "selected" : "" } value="report_board">신고게시판</option>
            </select>
        </form>
        <ul>
        	<c:if test="${!empty sessionScope.nickname }">
            	<li><a href="#chat" onclick="popup('chat'); ajaxtest();">채팅</a></li>
            	<li><a href="#mypage" onclick="popup('mypage')">마이페이지</a></li>
            	<li><a href="#logout" onclick="location.href = '/logout'; ">로그아웃</a></li>
            </c:if>
            <c:if test="${empty sessionScope.nickname }">
	            <li><a href="#signup" onclick="popup('signup')">회원가입</a></li>
	            <li><a href="#login" onclick="popup('login')">로그인</a></li>
            </c:if>
        </ul>
    </div>
    <div class="banner">
        <img src="/img/banner.png" alt="포도마켓을 설명하는 배너">
    </div>
    <c:set var="url" value="/WEB-INF/views/wishlist.jsp" />
    <ul class="nav">
        <li><a href="/list?category=deal_board" class="rounded_box ${category == 'deal_board' ? 'activate' : '' }">거래게시판</a></li>
        <li><a href="/list?category=free_board" class="rounded_box ${category == 'free_board' ? 'activate' : '' }">자유게시판</a></li>
        <li><a href="/list?category=question_board" class="rounded_box ${category == 'question_board' ? 'activate' : '' }">질문게시판</a></li>
        <li><a href="/list?category=report_board" class="rounded_box ${category == 'report_board' ? 'activate' : '' }">신고게시판</a></li>
        <li><a href="/wishlist" class="rounded_box ${ pageContext.request.requestURI == url ? 'activate' : '' }">찜목록</a></li>
    </ul>
</header>