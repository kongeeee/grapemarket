<%@ page contentType="text/html; charset=utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <jsp:include page="../include/head.jsp">
        <jsp:param name="filename" value="view"/>
        <jsp:param name="title" value="포도마켓 - 게시물"/>
    </jsp:include>
    <script>
    	function delete_confirm(){
    		var post_delete = confirm("삭제하시겠습니까?");
    		if(post_delete){
    			location.href='/delete?category=${category}&view_index=${post.post_index}';
    			}
    	}
    </script>
    <script>
    	function wishlist(){
    		var wishlist = confirm("위시리스트에 저장하시겠습니까?");
    		if(wishlist) location.href='/wishlist_insert?view_index=${post.post_index}';
    		else location.href='/view?category=${category}&view_index=${post.post_index}';
    	}
    </script>
</head>
<body>
	<jsp:include page="../include/popup.jsp"/>
    <div class="container">
        <jsp:include page="../include/header.jsp"/>
        <div class="content">
            <table class="post">
                <tr>
                    <td><h3>제목</h3></td>
                    <td>${post.title}</td>
                </tr>
                <tr>
                    <td>닉네임</td>
                    <td>
                   		<span class="dropdown">
						    <span>${post.nickname}</span>
							<div class="dropdown_content">
								<a href="#">회원정보보기</a><br/>
								<a href="#">채팅하기</a>
						  	</div>
						</span>
                    </td>
                </tr>
                <c:choose>
	                <c:when test="${post.isphone eq 1}">
	                <tr>
	                    <td>전화번호</td>
	                    <td>${post.phone}</td>
	                </tr>
	                </c:when>
	                <c:otherwise>
	                </c:otherwise>
                </c:choose>
                <tr>
                    <td>가격</td>
                    <td>
                    	<c:choose>
                    		<c:when test="${empty post.price}">0</c:when>
                    		<c:otherwise>${post.price}</c:otherwise>
                    	</c:choose>원</td>
                </tr>
                <tr>
                	<c:choose>
                	<c:when test="${not empty post.img}">
                	<td colspan="2"><img style="width:95%" src="${post.img}" alt=""></td>
                	</c:when>
                	<c:otherwise>
                	</c:otherwise>
                	</c:choose>
                </tr>
                <tr>
                    <td colspan="2">${post.content}</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="button" class="input_button" value="뒤로가기" onclick="location.href='${prevPage}'">
                        <c:if test="${sessionScope.member_index eq post.member_index}">
	                        <input type="button" class="input_button" value="글삭제" onclick="delete_confirm();">
	                        <input type="button" class="input_button" value="글수정" onclick="location.href='/write?category=${category}&view_index=${post.post_index}';">
                        </c:if>
                        <i style="float:right; margin: 0 1rem; cursor:pointer;" class="fa fa-shopping-basket fa-2x" onclick="wishlist()"></i>
                    </td>
                </tr>
            </table>
            <div class="comment">
                <form class="comment_form" name="comment_form" action="/comment" method="post">
                    <textarea placeholder="댓글을 입력해 보세요" rows="4" name="content" id=""></textarea>
                    <input type="submit" class="input_button" value="작성">
                    <input type="hidden" name="view_index" value="${ param.view_index }"/>
                </form>
                <c:forEach items="${comment}" var="comment">
	                <div class="comment_box">
	                    <div class="content">${comment.content}</div>
	                    <div>
	                        <div class="writer">${comment.nickname}</div>
	                        <div class="date">${comment.date_created}</div>
	                        <c:if test="${ comment.nickname == sessionScope.nickname }">
	                        <form method="GET" action="/comment">
		                        <input class="font_button" type="submit" value="삭제">
	                  			<input type="hidden" name="member_index" value="${ comment.member_index }"/>
	                  			<input type="hidden" name="comment_index" value="${ comment.comment_index }"/>
	                        </form>
	                        </c:if>
	                    </div>
	                </div>
                </c:forEach>
            </div>
        </div>

        <footer>
            Copyright (c) 2020 TEAM_D All Right Reserved.
        </footer>
    </div>
</body>
</html>