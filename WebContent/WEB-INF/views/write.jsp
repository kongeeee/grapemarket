<%@ page contentType="text/html; charset=utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<jsp:include page="../include/loginCheck.jsp"/>
    <jsp:include page="../include/head.jsp">
        <jsp:param name="filename" value="write"/>
        <jsp:param name="title" value="포도마켓 - 글쓰기"/>
    </jsp:include>
    <script>
    	function button(){
    		var button = confirm('취소하시겠습니까?');
    			if(button){
    				location.href="/list?category=${category}";
    			}else{
    					
    			}
    	}
    </script>
</head>
<body>
    <div class="container">
        <jsp:include page="../include/header.jsp"/>
        <div class="content">
            <form id="write" name="write" enctype="multipart/formdata" method="post" action="/write">
           	<input type="hidden" name="is_modify" value="${empty view_index ? 0 : 1}"/>
           	<input type="hidden" name="view_index" value="${post_index}">
                <table class="post">
                    <tr>
                        <td><h4>제목</h4></td>
                        <td><input id="title" name="title" type="text" class="input_text" value="${modify.title}" placeholder="내용을 입력해 주세요"></td>
                        <td>
                            <select class="select" name="category" id="select">
                                <option ${category == "deal_board" ? "selected" : "" } value="deal_board">거래게시판</option>
                                <option ${category == "free_board" ? "selected" : "" } value="free_board">자유게시판</option>
                                <option ${category == "question_board" ? "selected" : "" } value="question_board">질문게시판</option>
                                <option ${category == "report_board" ? "selected" : "" } value="report_board">신고게시판</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>닉네임</td>
                        <td>
                            ${nickname} | ${phone}
                            <input id="public_phone" name="public_phone" type="checkbox" value="공개">
                            <label for="public_phone">휴대폰번호 공개</label>
                        </td>
                    </tr>
                    <tr>
                        <td>가격</td>
                        <td><input name="price" type="text" class="input_text" value="${modify.price}" placeholder="내용을 입력해 주세요">원</td>
                        <td><input id="file" name="file" type="file"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><textarea rows="15" name="content" id="content">${modify.content}</textarea></td>
                        <td>
                            <img id="img" name="img" src="" alt="">
<!--                             https://newsimg.hankookilbo.com/2019/04/29/201904291390027161_3.jpg -->
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" class="input_button" value="취소" onclick="button()">
                            <input type="submit" class="input_button" value="작성">
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <footer>
            Copyright (c) 2020 TEAM_D All Right Reserved.
        </footer>
    </div>
</body>
</html>