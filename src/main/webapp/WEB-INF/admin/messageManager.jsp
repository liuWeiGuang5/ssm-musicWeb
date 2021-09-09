<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta content="" name="description" />
<meta content="webthemez" name="author" />

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />

<title>评论管理</title>
<jsp:include page="/part/manager.css.jsp"></jsp:include>
</head>
<body>
	<div id="wrapper">
		<!--头部：页面标题栏-->
		<jsp:include page="/part/manager.header.jsp"></jsp:include>
		<!--导航栏：页面菜单栏-->
		<jsp:include page="/part/manager.menu.jsp"></jsp:include>
		<!--表格-->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<!-- start dataTables -->
						<!-- 表格格式：面板中嵌套表格 -->
						<div class="panel panel-default">
							<!-- 面板头放：页面标题，刷新按钮，添加按钮 -->
							<div class="panel-heading">
								<font size="4">评论管理</font> <a href="#"> <span
									class="glyphicon glyphicon-repeat"></span>
								</a>

								<%--条件查询--%>
								<form class="form-inline" action="<%=basePath %>message/toMessageManager" method="post" style="float:right;margin-left: 10px">
									<div class="form-group">
										<label for="exampleInputName2"><font size="2">视频名</font></label>
										<input type="text" name="videoname" value="${videoname}" class="form-control" id="exampleInputName2">
									</div>
									<div class="form-group">
										<label for="exampleInputEmail2"><font size="2">关键字</font></label>
										<input type="text" name="messagecomment" value="${messagecomment}" class="form-control" id="exampleInputEmail2">
									</div>
									<button type="submit" class="btn btn-default">搜索</button>
									<button type="button" class="btn btn-default" onclick="doClear()">清空</button>
								</form>

								<span style="float: right">

									<button type="button" class="btn btn-default btn-sm" onclick="expExl()">
										<span class="glyphicon glyphicon-plane"></span> 导出
									</button>

								</span>



							</div>
							<!-- 面板体放：表格内容，按照dataTable格式来构造-->
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover dataTables-example"
										id="dataTables-example">
										<thead>
											<tr>
												<th style="width:5%">评论编号</th>
												<th style="width:15%">评论信息</th>
												<th style="width:13%">评论时间</th>
												<th style="width:15%">视频名称</th>
												<th style="width:10%">发表用户</th>
												<th style="width:5%">删除</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty info.list }">
												<c:forEach var="m" items="${info.list}">

													<tr>
														<td>${m.messageid }</td>
														<td>${m.messagecomment }</td>
														<td>
														<fmt:formatDate value="${m.messagetime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>		   </td>
														<td>${m.video.videoname}</td>
														<td>${m.user.userName }</td>
														<td>
															<a href="javascript:doRemove(${m.messageid},'${m.user.userName }')" style="color:rgb(212,106,64)">
																<span class="glyphicon glyphicon-remove"></span>
															</a>
														</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
									<table class="table table-bordered table-hover definewidth m10" >
									  	<tr><th colspan="5">  <div class="inline pull-right page">
									          <a href='<%=basePath%>message/toMessageManager?pageNO=1&messagecomment=${messagecomment}' >第一页</a>
									          <c:if test="${info.pageNum!=1}">
									             <a href='<%=basePath%>message/toMessageManager?pageNO=${info.prePage}&messagecomment=${messagecomment}'>上一页</a>
									          </c:if>
									          <c:if test="${info.pageNum==1}">
									            <a href='<%=basePath%>message/toMessageManager?pageNO=1&messagecomment=${messagecomment}'>上一页</a>
									          </c:if>
									          
									          <c:if test="${info.pageNum==info.pages}">
									             <a href='<%=basePath%>message/toMessageManager?pageNO=${info.pages}&messagecomment=${messagecomment}' >下一页</a>
									          </c:if> 
									          <c:if test="${info.pageNum!=info.pages}">
									             <a href='<%=basePath%>message/toMessageManager?pageNO=${info.nextPage}&messagecomment=${messagecomment}' >下一页</a>
									          </c:if>  
									          <a href='<%=basePath%>message/toMessageManager?pageNO=${info.pages}&messagecomment=${messagecomment}' >最后一页</a>
											  &nbsp;&nbsp;&nbsp;共<span class='current'>${info.total}</span>条记录<span class='current'> ${info.pageNum}/${info.pages} </span>页
											  </div>
											 </th></tr>
									  </table>
									
									
								</div>
							</div>
						</div>
						<!--End dataTables -->

							<!-- /.modal-dialog -->
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/part/manager.js.jsp"></jsp:include>
	<script src="assets/js/dataTables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/echarts.min.js"></script>
    <script src="assets/js/dataTables/dataTables.bootstrap.js"></script>
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
    </script>
	<script type="text/javascript">
		//导出数据
		function expExl(){
			window.location.href="<%=basePath%>message/expExl";
		}

		function doClear(){
			$("#exampleInputName2").val("")
			$("#exampleInputEmail2").val("")
		}


		function doRemove(mid,name){
			if(confirm("您确定删除"+name+"的这条评论吗？")){
				$.post("<%=basePath %>message/delById",{'messageid':mid},function(data){
					if(data=="yes"){
						alert("删除成功");
						window.location.reload();
					}else{
						alert("删除失败");
					}
				});
			}
		}
		$(".glyphicon-repeat").click(function(){
			window.location.reload();
		});
	</script>
	
</body>
</html>