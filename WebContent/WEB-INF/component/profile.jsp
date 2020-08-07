<%@ page contentType="text/html; charset=utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${list_count eq 0}">
	<div align="center">대화하는 상대가 없습니다.</div>
</c:if>

<c:forEach items="${profile}" var="profile" begin="0" end="${list_count}">
	<div class="profile" onclick="ajaxtest2(this);">
		<input type="hidden" value="${profile.member_index}">
	    <div class="face"><i class="fa fa-user fa-2x"></i></div>
	    <div>
	        <div class="info">
	            <div>${profile.sender}</div>
	            <div>${profile.date_time}</div>
	        </div>
	        <div class="message">${profile.content}</div>
	    </div>
	</div>
</c:forEach>