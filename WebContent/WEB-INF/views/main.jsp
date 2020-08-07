<%@ page contentType="text/html; charset=utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <jsp:include page="../include/head.jsp">
        <jsp:param name="filename" value="main"/>
        <jsp:param name="title" value="포도마켓"/>
    </jsp:include>
</head>
<body>
	<jsp:include page="../include/popup.jsp"/>
    <div class="container">
        <jsp:include page="../include/header.jsp"/>
        <div class="content">
            <h4>미리보기</h4>
            <div class="preview" style="cursor: pointer;">
         	<c:forEach items="${list}" var="index" begin="0" end="2">
                <a class="post" onclick="location.href='/view?category=${index.category}&view_index=${index.post_index}';">
					<c:choose>
					<c:when test="${empty index.img}">
						<img  src="/img/noimage.gif" alt="">
					</c:when>
					<c:otherwise>
						<img src="${index.img}" alt="">
					</c:otherwise>
					</c:choose>
                    <div class="title">${index.title}</div>
                </a>
            </c:forEach>
            </div>
            <h4>핫한 게시물</h4>
            <div class="preview" style="cursor: pointer;">
   				<c:forEach items="${view}" var="index" begin="0" end="2">
	                <a class="post" style="grid-row: span 2" onclick="location.href='/view?category=${index.category}&view_index=${index.post_index}';">
	<!--                     <img src="https://t1.daumcdn.net/liveboard/happypet/226c5d22a96348978d1f392a509984ba.JPG" alt="">
							 <div class="title">고양이 입니닷</div> -->
						<img src="${empty index.img && index.img eq '' ? "/img/noimage.gif" : index.img }" alt="">
	                    <div class="title">${index.title}</div>
	                </a>
                </c:forEach>
            </div>
        </div>

        <footer>
            Copyright (c) 2020 TEAM_D All Right Reserved.
        </footer>
    </div>
</body>
</html>