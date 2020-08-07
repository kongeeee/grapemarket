<%@ page contentType="test/html; charset=utf8" %>

<div class="dim signup">
    <div class="popup_container">
        <i class="fa fa-2x fa-remove close" onclick="popup_close(this)"></i>
        <h1>포도마켓</h1>   
        <h2>회원가입</h2>
        <form class="signup_form" onsubmit="return validate();" id="signup_form" name="signup_form" method="post" action="/signup">
            <div class="input_box">
                <i class="fa fa-2x fa-user i"></i>
                <input type="text" id="userID" name="userID" data-length="20" placeholder="아이디" >
            </div>
            <div class="input_box">
                <i class="fa fa-2x fa-lock i"></i>
                <input type="password" id="pswd" name="pswd" data-length="20" placeholder="비밀번호">    
            </div>
            <div class="input_box">
                <i class="fa fa-2x fa-phone i"></i>
                <input type="text" id="phone" name="phone" data-length="20" placeholder="휴대폰 번호(-포함)">   
            </div>
            <div class="input_box">
                <i class="fa fa-2x fa-smile-o i"></i>
                <input type="text" id="nickname" name="nickname" data-length="20" placeholder="닉네임">    
            </div>
            <input type="submit" class="input_button" id="signup" name="signup" value="회원가입"/>
        </form>
    </div>
</div>