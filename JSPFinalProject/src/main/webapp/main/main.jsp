<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>맛집&여행</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="../js/jquery.min.js"></script> 
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="../shadow/css/shadowbox.css">
<script type="text/javascript" src="../shadow/js/shadowbox.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9965c727d3306713c47391be682e4be9&libraries=services"></script>
<link href="../css/layout.css" rel="stylesheet" type="text/css" media="all">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Hahmlet:wght@500&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<style type="text/css">
*{
    font-family: 'Hahmlet', serif;
}
/*  @media (min-width: 450px) {
  .main-card {
    width: 96%;
    max-width: 400px;
    height: calc(100% - 32px)!important;
    margin: 16px auto!important;
    border-radius: 8px!important;
    box-shadow: 0 10px 16px 0 rgba(0, 0, 0, 0.2),
      0 6px 20px 0 rgba(0, 0, 0, 0.19);
    max-height: 600px;
    right: 16px;
    bottom: 0;
    position: absolute;
  }
}
.main-card {
   background: white;
   color: white;
  width: 100%;
  height: 100%;
  margin: 0px;
  border-radius: 0px;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}
.line{
  height:1px;
  background-color: rebeccapurple;
  width:100%;
  opacity:0.2;
}
.main-title {
  background-color: rebeccapurple;
  font-size: large;
  font-weight: bold;
  padding: 16px;
  display: flex;
}
.main-title svg {
  height: 24px;
  margin: 0px 16px 0px 8px;
}
.main-title span {
  margin: auto 0px;
}
.chat-area {
  flex-grow: 1;
  overflow: auto;
  border-radius: 8px;
  padding: 16px;
  display: flex;
  flex-direction: column;
}
.input-message {
  padding: 8px 48px 8px 16px;
  flex-grow: 1;
  border: none;
}
.input-message:focus {
  outline: none;
}
.input-div {
  height: 48px;
  display: flex;
}

.input-send {
  background: transparent;
  width: 48px;
  height: 48px;
  right:0%;
  border: none;
  cursor: pointer;
}
.input-send:hover {
  background:lavender;
}
.input-send svg {
  fill: rebeccapurple;
  margin: 11px 8px;
}
.chat-message-div {
  display: flex;
}

.chat-message-sent {
  background-color: white;
  margin: 8px 16px 8px 64px;
  padding: 8px 16px;
  animation-name: fadeIn;
  animation-iteration-count: 1;
  animation-timing-function: ease-in;
  animation-duration: 100ms;
  color: black;
  border-radius: 8px 8px 2px 8px;
  background-color: lavender;
}

.chat-message-received {
  background-color: white;
  margin: 8px 64px 8px 16px;
  padding: 8px 16px;
  animation-name: fadeIn;
  animation-iteration-count: 1;
  animation-timing-function: ease-in;
  animation-duration: 100ms;
  color: black;
  border-radius: 8px 8px 8px 2px;
  background-color: lavender;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }

  to {
    opacity: 1;
  }
}
::-webkit-scrollbar {
  width: 10px;
}
::-webkit-scrollbar-track {
  background: #f1f1f1;
}

::-webkit-scrollbar-thumb {
  background: #888;
}

::-webkit-scrollbar-thumb:hover {
  background: #555;
}  */
</style>
<!-- <script type="text/javascript">
$(function(){
	$('div.main-card').toggleClass('active');
    var $win = $(window);
    var top = $(window).scrollTop(); // 현재 스크롤바의위치값을 반환합니다.
    
    var speed          = 1000;     // 따라다닐 속도 : "slow", "normal", or "fast" or numeric(단위:msec)
    var easing         = 'linear'; // 따라다니는 방법 기본 두가지 linear, swing
    var $layer         = $('div.main-card'); // 레이어셀렉팅
    var layerTopOffset = 0;   // 레이어 높이 상한선, 단위:px
    $layer.css('position', 'absolute');
   
    // 스크롤 바를 내린 상태에서 리프레시 했을 경우를 위해
    if (top > 0 )
      $win.scrollTop(layerTopOffset+top);
    else
      $win.scrollTop(0);
    //스크롤이벤트가 발생하면
    $(window).scroll(function(){
      var yPosition = $win.scrollTop()+80;
      if (yPosition< 0)
      {
        yPosition = $win.scrollTop()+80;
      }
      $layer.animate({"top":yPosition }, {duration:speed, easing:easing, queue:false});
    });
})
var running = false;
function send() {
  if (running == true) return;
  var msg = document.getElementById("message").value;
  if (msg == "") return;
  running = true;
  addMsg(msg);
  //DELEAY MESSAGE RESPOSE Echo
  window.setTimeout(addResponseMsg, 1000, msg);
}
function addMsg(msg) {
  var div = document.createElement("div");
  div.innerHTML =
    "<span style='flex-grow:1'></span><div class='chat-message-sent'>" +
    msg +
    "</div>";
  div.className = "chat-message-div";
  document.getElementById("message-box").appendChild(div);
  //SEND MESSAGE TO API
  document.getElementById("message").value = "";
  document.getElementById("message-box").scrollTop =document.getElementById("message-box").scrollHeight
}
function addResponseMsg(msg) {
  var div = document.createElement("div");
  div.innerHTML = "<div class='chat-message-received'>" + msg + "</div>";
  div.className = "chat-message-div";
  document.getElementById("message-box").appendChild(div);
  document.getElementById("message-box").scrollTop =document.getElementById("message-box").scrollHeight
  running = false;
}
document.getElementById("message").addEventListener("keyup", function (event) {
  if (event.keyCode === 13) {
    event.preventDefault();
    send();
  }
});
document.addEventListener('DOMContentLoaded', function() {
  window.setTimeout(addResponseMsg, 100, "Hi");
}, false);
</script> -->
</head>
<body id="top">
<jsp:include page="header.jsp"></jsp:include>
<!-- ######################################Header########################################################## --> 
<jsp:include page="${main_jsp }"></jsp:include> 
<!-- <div class="main-card">
  <div class="main-title">
    <svg viewBox="0 0 24 24">
    <path fill="currentColor" d="M12,2A2,2 0 0,1 14,4C14,4.74 13.6,5.39 13,5.73V7H14A7,7 0 0,1 21,14H22A1,1 0 0,1 23,15V18A1,1 0 0,1 22,19H21V20A2,2 0 0,1 19,22H5A2,2 0 0,1 3,20V19H2A1,1 0 0,1 1,18V15A1,1 0 0,1 2,14H3A7,7 0 0,1 10,7H11V5.73C10.4,5.39 10,4.74 10,4A2,2 0 0,1 12,2M7.5,13A2.5,2.5 0 0,0 5,15.5A2.5,2.5 0 0,0 7.5,18A2.5,2.5 0 0,0 10,15.5A2.5,2.5 0 0,0 7.5,13M16.5,13A2.5,2.5 0 0,0 14,15.5A2.5,2.5 0 0,0 16.5,18A2.5,2.5 0 0,0 19,15.5A2.5,2.5 0 0,0 16.5,13Z" />
    </svg><span>Chatbot</span></div>
  <div class="chat-area" id="message-box">
  </div>
  <div class="line"></div>
  <div class="input-div">
    <input class="input-message" name="message" 
           type="text" id="message" placeholder="Type your message ..." style="color:black"/>
    <button class="input-send" onclick="send()">
   <svg style="width:24px;height:24px">
    <path d="M2,21L23,12L2,3V10L17,12L2,14V21Z" />
   </svg>
    </button>
  </div>
 </div>  -->
<!-- ################################################################################################ -->
<jsp:include page="footer.jsp"></jsp:include>
<!-- ################################################################################################ -->

<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a> 
<!-- JAVASCRIPTS --> 
<script src="../js/jquery.min.js"></script> 
<!-- <script src="https://code.jquery.com/jquery-3.6.0.js"></script> -->
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script src="../js/jquery.backtotop.js"></script> 
<script src="../js/jquery.mobilemenu.js"></script> 
<script src="../js/jquery.flexslider-min.js"></script>

</body>
</html>