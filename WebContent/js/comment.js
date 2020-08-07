function writeComment() {
	var httpRequest = new XMLHttpRequest();
	
	if (!httpRequest) {
		alert("댓글 작성 오류");
		return false;
	}
	
	httpRequest.onreadystatechange = function() {
		if (httpRequest.readyState === XMLHttpRequest.DONE) {
			if (httpRequest.status === 200) {
				document.getElementsByClassName("user_list")[0].innerHTML = httpRequest.responseText;
			} else {
				alert("request에 뭔가 문제가 있어요.");
			}
		}
	}
	httpRequest.open("POST", "/messenger");
	httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	httpRequest.send("uidx=" + uidx); //
//	}
}