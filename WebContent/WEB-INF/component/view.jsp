<%@ page contentType="text/html; charset=utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${view}" var="view" begin="0" end="${message_count}">
	<div class="${view.member_index == sessionScope.member_index ? "myself" : "your"}">
	    <div class="message">${view.content}</div>
	    <div class="time">${view.date_time }</div>
	</div>
</c:forEach>