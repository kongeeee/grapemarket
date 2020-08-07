function validate() {

	var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
	var phone_regExp = /^\d{3}-\d{3,4}-\d{4}$/;
	var nick_reg = /^[\w가-힣]{2,20}$/;
	
	var userID = document.getElementById("userID");
	var pswd = document.getElementById("pswd");
	var phone = document.getElementById("phone");
	var nickname = document.getElementById("nickname");
	
	if(!check(re,userID,"아이디는 4~12자의 영문 대소문자와 숫자로만 입력")) {
	    return false;
	}
	if(!check(re,pswd,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {
	    return false;
	}
	if(!check(phone_regExp, phone, "올바른 휴대폰 번호를 입력해 주세요.")) {
		return false;
	}
	if(!check(nick_reg,nickname,"닉네임은 2~20자의 영어나 숫자,한글을 입력해야 합니다.")) {
		return false;
	}
}
	
function check(re, what, message) {
    if(re.test(what.value)) {
        return true;
    }
    alert(message);
    what.value = "";
    what.focus();
}