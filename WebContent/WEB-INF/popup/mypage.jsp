<%@ page contentType="test/html; charset=utf8" %>

<div class="dim mypage">
	<form class="popup_container" method="POST" action="/mypage">
		<i class="fa fa-2x fa-remove close" onclick="popup_close(this)"></i>
        <div class="user">
            <i class="fa fa-user fa-5x"></i>
        </div>
        <table>
            <tr>
                <td>아이디</td>
                <td>${sessionScope.userID}</td>
            </tr>
            <tr>
                <td>닉네임</td>
                <td>${sessionScope.nickname}</td>
            </tr>
            <tr>
                <td>비밀번호 변경</td>
                <td>
                    <input name="pswd" type="password" class="input_text" placeholder="변경할 비밀번호">
                </td>
            </tr>
            <tr>
                <td>닉네임 변경</td>
                <td>
                    <input name="nickname" type="text" class="input_text" placeholder="변경할 닉네임">
                    <!-- <input type="button" class="font_button" value="변경하기"> -->
                </td>
            </tr>
        </table>
        <!-- <div class="score">
            <div class="circle">
                <div class="up"></div>
                <div class="disc">좋아요^^</div>
            </div>

            <div class="circle">
                <div class="down"></div>
                <div class="disc">싫어요`-'</div>
            </div>
            <ul>
                <li><a href="">작성글 보러가기</a></li>
                <li><a href="">댓글 보러가기</a></li>
            </ul>
        </div> 
        <div class="test">
            <div>2건</div>
            <div>2건</div>
        </div> -->
        <input class="submit font_button" type="submit" value="저장하기">
        
    </form>
</div>