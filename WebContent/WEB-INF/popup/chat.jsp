<%@ page contentType="text/html; charset=utf8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="dim chat">
    <div class="popup_container">
    <i class="fa fa-2x fa-remove close" onclick="popup_close(this);"></i>
        <div class="chat_wrapper">
            <div class="user_list">
                <%--<c:forEach var="index" begin="1" end="5">
                <div class="profile">
                    <div class="face"><i class="fa fa-user fa-2x"></i></div>
                    <div>
                        <div class="info">
                            <div>문문</div>
                            <div>오후 3:41</div>
                        </div>
                        <div class="message">테스트 입력글 입니다.</div>
                    </div>
                </div>
                </c:forEach>--%>
            </div>
            <div class="chat_">
                <div class="info2">
                    <i class="fa fa-comment fa-1x"></i>
                    <div><span>대화를 시작해 보세요</span></div>
                </div>
               
                <div class="view">
<%--                     <c:forEach var="index" begin="1" end="5"> --%>
<%--                     <div class="${index % 2 == 0 ? "myself" : "your"}"> --%>
<!--                         <div class="message">것은 영락과 부패 뿐이다 낙원을 장식하는 천자만흥</div> -->
<!--                         <div class="time">20/05/14 오후 3:41</div> -->
<!--                     </div> -->
<%--                     </c:forEach> --%>
                </div>
                 <form onsubmit="ajaxtest();">
                    <textarea id="chat_message_box" placeholder="내용을 입력해 주세요" name="" id=""  rows="3"></textarea>
                    <button onclick="sendMessage();"><i class="fa fa-paper-plane fa-2x"></i></button>
                    <input id="uidx2" type="hidden">
                </form>
            </div>
        </div>
    </div>
</div>