<%@ page contentType="text/html; charset=utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/loginCheck.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="ko">
<head>
    <jsp:include page="../include/head.jsp">
        <jsp:param name="filename" value="wishlist"/>
        <jsp:param name="title" value="포도마켓 - 찜목록"/>
    </jsp:include>
</head>
<body>
	<jsp:include page="../include/popup.jsp"/>
    <div class="container">
        <jsp:include page="../include/header.jsp"/>
        
        <div class="content" style="cursor:pointer;">
            <div class="preview">
                <c:forEach items="${list}" var="index" begin="0" end="5">
                <a class="post">
                	<i class="fa fa-2x fa-remove close" onclick="
                	if(confirm('삭제하시겠습니까?')){
                	location.href='/wishlist_delete?view_index=${index.post_index}';}
                	"></i>
                    <img src="${ empty index.img ? '/img/noimage.gif' : index.img }" onclick="location.href='/view?category=${index.category}&view_index=${index.post_index}';" alt="">
                    <div class="title" onclick="location.href='/view?category=${index.category}&view_index=${index.post_index}';">${index.title}</div>
                </a>
                </c:forEach>
            </div>
            <ul class="pagination">
                <li><a href=""><i class="fa fa-chevron-left"></i></a></li>
                <li><a href="">1</a></li>
                <li class="active"><a href="">2</a></li>
                <li><a href="">3</a></li>
                <li><a href="">4</a></li>
                <li><a href="">5</a></li>
                <li><a href=""><i class="fa fa-chevron-right"></i></a></li>
            </ul>
        </div>
        <footer>

        </footer>
    </div>
</body>
</html>