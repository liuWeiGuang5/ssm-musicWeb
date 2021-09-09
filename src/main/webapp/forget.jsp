<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>忘记密码</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="<%=basePath %>js/jPlayer/jplayer.flat.css" type="text/css" />
  <link rel="stylesheet" href="<%=basePath %>css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="<%=basePath %>css/animate.css" type="text/css" />
  <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="<%=basePath %>css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="<%=basePath %>css/font.css" type="text/css" />
  <link rel="stylesheet" href="<%=basePath %>css/app.css" type="text/css" />  
    <!--[if lt IE 9]>
    <script src="js/ie/html5shiv.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/excanvas.js"></script>
  <![endif]-->
</head>
<body class="bg-info dker">
  <section id="content" class="m-t-lg wrapper-md animated fadeInDown">
    <div class="container aside-xl">
      <a class="navbar-brand block" href="signup.html"><span class="h1 font-bold">忘记密码</span></a>
      <section class="m-b-lg">
        <header class="wrapper text-center">
          <strong>请输入个人信息</strong>
        </header>

          <div class="form-group">
            <input type="text"  placeholder="用户名 4~16位" class="form-control rounded input-lg text-center no-border" id="userName" name="userName">
          </div>
          <div class="form-group">
            <input type="text" placeholder="邮件" class="form-control rounded input-lg text-center no-border" id="email" name="email">
          </div>
          <div class="form-group">
            <input type="text" placeholder="电话" class="form-control rounded input-lg text-center no-border"
                   id="phone" name="phone">
          </div>

          <div class="form-group">
            <input type="text" placeholder="地址" class="form-control rounded input-lg text-center no-border"
                   id="address" name="address">
          </div>


          <button id="oldbtn" style="display: block" class="btn btn-lg btn-warning lt b-white b-2x btn-block btn-rounded"   onclick="submitTest()"><i class="icon-arrow-right pull-right"></i><span class="m-r-n-lg">确认</span></button>
        <form method="post" id="forget" action="<%=basePath%>user/updatePwd">
          <div class="form-group" id="newpw" style="display:none">
            <input type="text" id="uname" name="uname" style="display:none">
            <input type="text" placeholder="请输入新密码" class="form-control rounded input-lg text-center no-border"
                   id="newpassword" name="password">
          </div>
        <button id="b1" type="submit" value="yy" style="display: none" class="btn btn-lg btn-warning lt b-white b-2x btn-block btn-rounded">提交</button>
          <div class="line line-dashed"></div>
        </form>
      </section>
    </div>
  </section>
  <!-- footer -->
  <footer id="footer">
    <div class="text-center padder clearfix">
      <p>
        <small>Guide Music team music welcome come to you regedister<br>&copy; 2020-02</small>
      </p>
    </div>
  </footer>
  <!-- / footer -->
  <script src="<%=basePath %>js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="<%=basePath %>js/bootstrap.js"></script>
  <!-- App -->
  <script src="<%=basePath %>js/app.js"></script>  
  <script src="<%=basePath %>js/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="<%=basePath %>js/app.plugin.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/jPlayer/jquery.jplayer.min.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/jPlayer/add-on/jplayer.playlist.min.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/jPlayer/demo.js"></script>
  <script type="text/javascript">
  	function submitTest(){
  	  var username = $("#userName").val()
      var email = $("#email").val()
      var phone = $("#phone").val()
      var address = $("#address").val()
  	  $.ajax({
        url:"<%=basePath%>user/forget",
        data:{"userName":username,"email":email,"phone":phone,"address":address},
        type:"get",
        success:function (data){
          if (data=="yes"){
            alert("信息确认成功,请重置密码")
            $("#newpw").css("display","block")
            $("#b1").css("display","block")
            $("#oldbtn").css("display","none")
            $("#uname").val($("#userName").val())
           /* $("#oldbtn").attr("action","")
            $("#forget").submit()*/
          }else {
            alert("输入的信息有误或者账号被禁用")
          }
        }
      })
    }

 </script>
</body>
</html>