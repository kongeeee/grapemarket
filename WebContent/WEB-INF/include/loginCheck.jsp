<%@ page contentType="text/html; charset=utf8" %>

<% 	if (session.getAttribute("nickname") == null) { %>
		<script>
			alert("로그인이 필요한 서비스 입니다.");
			location.href = "/";
		</script>
<%	} %>