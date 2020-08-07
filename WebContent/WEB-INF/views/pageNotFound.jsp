<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>없는 페이지!</title>
<style>
	html, body {
		height: 100%;
		margin: 0;
		padding: 0;
	}
	.container {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		height: 100%;
	}
	h1 {
		color: purple;
	}
	button {
		border-radius: 0.5rem;
		background: #9392b4;
		color: white;
		padding: 0.5rem 1rem;
		box-shadow: 0 1px 3px 1px #ddd;
		border: none;
	}
	button:hover {
		box-shadow: 0 2px 4px 2px #ddd;
		background: #a4a3c5;
	}
</style>
</head>
<body>
	<div class="container">
		<h1>페이지를 찾을 수 없습니다!</h1>
		<a href="/main"><button>메인으로 돌아가기</button></a>
	</div>
</body>
</html>