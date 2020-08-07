<%@ page contentType="text/html; charset=utf8" %>

<div class="dim login">
    <div class="popup_container">
        <i class="fa fa-2x fa-remove close" onclick="popup_close(this)"></i>
        <h1>포도마켓</h1>   
        <h2>로그인</h2>
        <form class="login_form" id="login_form" name="login_form" method="post" action="/login">
            <div class="input_box">
                <i class="fa fa-2x fa-user i"></i>
                <input type="text" name="userID" data-length="20" placeholder="아이디">
            </div>
            <div class="input_box">
                <i class="fa fa-2x fa-lock i"></i>
                <input type="password" name="pswd" data-length="20" placeholder="비밀번호">    
            </div>
            <input type="submit" class="input_button" id="SingUp" name="SignUp" value="로그인">   
        
	        <div class="login_check">
	            <input id="idcheck" type="checkbox" class="item" name="keep_login" value="true">
	            <label for="idcheck">로그인 유지</label>
	            <!--<div class="pswd_check"><a href="#">비밀번호 찾기</a></div>-->
	        </div>
        </form>
    </div>
</div>