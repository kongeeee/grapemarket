var delivery;
var profile;

function ajaxtest() {
	event.preventDefault();
	var httpRequest = new XMLHttpRequest();
	
	if (!httpRequest) {
		alert("XMLHTTP 인스턴스를 만들 수가 없어요 ㅠㅠ");
		return false;
	}
	
	httpRequest.onreadystatechange = function() {
		if (httpRequest.readyState === XMLHttpRequest.DONE) {
			if (httpRequest.status === 200) {
				document.getElementsByClassName("user_list")[0].innerHTML = httpRequest.responseText;
				var profile_list = document.getElementsByClassName("profile");
				for (var index = 0; index < profile_list.length; index++) {
					profile_list[index].style.height = '4rem';
				}
			} else {
				alert("목록 불러오기에 실패했습니다.");
			}
		}
	}
	httpRequest.open("POST", "/messenger");
	httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	httpRequest.send("uidx=" + uidx); //
	
}
function ajaxtest2(target) {
	clearInterval(delivery);
	profile = target;
	delivery = setInterval(function() {
		var httpRequest = new XMLHttpRequest();
		document.getElementsByClassName("info2")[0].children[1].children[0].innerText = profile.children[2].children[0].children[0].innerText + '님과 대화하기';
		if (!httpRequest) {
			alert("XMLHTTP 인스턴스를 만들 수가 없어요 ㅠㅠ");
			return false;
		}
		
		httpRequest.onreadystatechange = function() {
			if (httpRequest.readyState === XMLHttpRequest.DONE) {
				if (httpRequest.status === 200) {
					document.getElementsByClassName("view")[0].innerHTML = httpRequest.responseText;
					
					console.log(httpRequest.responseText);
					var view = document.getElementsByClassName("view")[0];
					view.scrollTop = view.scrollHeight;
				} else {
					alert("채팅 서버에 문제가 있습니다.");
				}
			}
		}
		httpRequest.open("POST", "/messenger");
		httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		httpRequest.send("uidx2=" + target.children[0].value + "&uidx=" + uidx);
		document.getElementById("uidx2").value = target.children[0].value;
	}, 1000);
}

function sendMessage() {
	var message = document.getElementById("chat_message_box").value;
	if (message == "") {
		return false;
	}
	document.getElementById("chat_message_box").value = "";
	
	var httpRequest = new XMLHttpRequest();
	if (!httpRequest) {
		alert("XMLHTTP 인스턴스를 만들 수가 없어요 ㅠㅠ");
		return false;
	}
	
	httpRequest.onreadystatechange = function() {
		if (httpRequest.readyState === XMLHttpRequest.DONE) {
			if (httpRequest.status === 200) {
				console.log("메세지를 보냈습니다.");
			} else {
				alert("채팅상대를 선택해주세요");
			}
		}
	}
	httpRequest.open("POST", "/messenger");
	httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	httpRequest.send("uidx2=" + document.getElementById("uidx2").value + "&uidx=" + uidx + "&message=" + message);
	
}