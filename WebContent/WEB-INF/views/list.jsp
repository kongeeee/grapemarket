<%@ page contentType="text/html; charset=utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <jsp:include page="../include/head.jsp">
        <jsp:param name="filename" value="list"/>
        <jsp:param name="title" value="포도마켓 - 목록"/>
    </jsp:include>
</head>
<body>
	<jsp:include page="../include/popup.jsp"/>
    <div class="container">
        <jsp:include page="../include/header.jsp"/>
        <div class='content'>
            <table class="list">
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>작성일</th>
                    <th>조회수</th>
                </tr>
                <c:choose>
                	<c:when test="${not empty post_list}">
		                <c:forEach items="${post_list}" var="index" varStatus="status">
		                <tr style="cursor: pointer;" onclick="location.href = '/view?category=${category}&view_index=${index.post_index}';">
		                    <td>${status.count}</td>
		                    <td>${index.title}</td>
		                    <td>${index.nickname}</td>
		                    <td>${index.date_created}</td>
		                    <td>${index.views}</td>
		                </tr>
		                </c:forEach>
	                </c:when>
	                <c:otherwise>
	                	<td colspan="5">글을 입력해 주세요</td>
	                </c:otherwise>
                </c:choose>
            </table>
            <input class="input_button" type="button" value="글쓰기" onclick="location.href='/write?category=${category}';">
            <ul class="pagination">
                <li class="${prev_page[1]}"><a href="${prev_page[0]}"><i class="fa fa-chevron-left"></i></a></li>
                <c:forEach var="index" begin="${first_page}" end="${last_page}">
                	<li class="${page == index ? "activate" : ""}"><a href="${link}${index}">${index}</a></li>
                </c:forEach>
                <li class="${next_page[1]}"><a href="${next_page[0]}"><i class="fa fa-chevron-right"></i></a></li>
            </ul>
        </div>

        <footer>
            Copyright (c) 2020 TEAM_D All Right Reserved.
        </footer>
    </div>
</body>
</html>