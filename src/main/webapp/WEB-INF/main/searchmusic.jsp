<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title>Musik | Web Application</title>
<meta name="description"
	content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
	<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="<%=basePath %>js/jPlayer/jplayer.flat.css"
	type="text/css" />
<link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="css/animate.css" type="text/css" />
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css" />
<link rel="stylesheet" href="css/simple-line-icons.css" type="text/css" />
<link rel="stylesheet" href="css/font.css" type="text/css" />
<link rel="stylesheet" href="css/app.css" type="text/css" />
	<link rel="stylesheet" href="<%=basePath%>css/backimg.css" type="text/css" />
<!--[if lt IE 9]>
    <script src="js/ie/html5shiv.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/excanvas.js"></script>
  <![endif]-->
</head>
<body class="backimg">
	<section class="vbox">
		<header
			class="bg-white-only header header-md navbar navbar-fixed-top-xs">
			<div class="navbar-header aside bg-info dk nav-xs">
				<a class="btn btn-link visible-xs"
					data-toggle="class:nav-off-screen,open" data-target="#nav,html">
					<i class="icon-list"></i>
				</a> <a href="<%=basePath%>user/toIndex" class="navbar-brand text-lt"> <i
					class="icon-earphones"></i> <img src="images/logo.png" alt="."
					class="hide"> <span class="hidden-nav-xs m-l-sm">Musik</span>
				</a> <a class="btn btn-link visible-xs" data-toggle="dropdown"
					data-target=".user"> <i class="icon-settings"></i>
				</a>
			</div>
			<ul class="nav navbar-nav hidden-xs">
				<li><a href="#nav,.navbar-header"
					data-toggle="class:nav-xs,nav-xs" class="text-muted"> <i
						class="fa fa-indent text"></i> <i class="fa fa-dedent text-active"></i>
				</a></li>
			</ul>
			<form
				class="navbar-form navbar-left input-s-lg m-t m-l-n-xs hidden-xs"
				role="<%=basePath%>music/searchMusic">
				 <div class="form-group">
					<div class="input-group">
						<span class="input-group-btn">
							<button  type="submit" href="http://localhost:8080/ylTeamMusic/video.html"
								 class="btn btn-sm bg-white btn-icon rounded" >
								<i class="fa fa-search"></i>
							</button>
						</span> <input type="text" value="${searchName}"
							class="form-control input-sm no-border rounded"
							placeholder="请输入歌曲名称">
					</div>
				</div> 
				
				
				
			</form>
				<div class="navbar-right ">
				<c:if test="${not empty user}">
					<ul class="nav navbar-nav m-n hidden-xs nav-user user">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle bg clear" data-toggle="dropdown"> 
								<span class="thumb-sm avatar pull-right m-t-n-sm m-b-n-sm m-l-sm"></span>
								<span class="glyphicon glyphicon-user"></span>&emsp;${user.userName} <b class="caret"></b>
							</a>

							<ul class="dropdown-menu animated fadeInRight">
								<li><a href="Logout.html"><span class="glyphicon glyphicon-log-out"></span>&nbsp;退出登录</a></li>
							</ul>
					    </li>
					</ul>
				</c:if>
				<c:if test="${empty user}">
					<p></p>
					<a href="<%=basePath%>login.jsp"><span class="glyphicon glyphicon-log-in"></span>&nbsp;登录</a>
					<span>&emsp;</span>
					<a href="<%=basePath%>signup.jsp" ><span class="glyphicon glyphicon-new-window"></span>&nbsp;免费注册</a>
				</c:if>
				<span>&emsp;</span>
					<a target="_blank" href="<%=basePath%>user/toAdminLogin" ><span class="glyphicon glyphicon-new-window"></span>&nbsp;管理员登录</a>
			</div>
		</header>
		<section>
			<section class="hbox stretch">
				<!-- .aside -->
				<aside class="bg-dark dk nav-xs aside hidden-print" id="nav">
					<section class="vbox">
						<section class="w-f-md scrollable">
							<div class="slim-scroll" data-height="auto"
								data-disable-fade-out="true" data-distance="0" data-size="10px"
								data-railOpacity="0.2">



								<!-- nav -->
								<nav class="nav-primary hidden-xs">
									<ul class="nav bg clearfix">
										<li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
											Discover</li>
										<li><a href="<%=basePath%>music/getComendMusics"> <i class="icon-disc icon  "></i>
												<span class="font-bold">推荐</span>
										</a></li>
										<li><a href="<%=basePath%>music/getTypes"> <i
												class="icon-music-tone-alt icon  "></i> <span
												class="font-bold">流派</span>
										</a></li>

										<li><a href="<%=basePath%>video/show" data-target="#content"
											data-el="#bjax-el" data-replace="true"> <i
												class="icon-social-youtube icon   "></i> <span
												class="font-bold">视频</span>
										</a></li>
										<li class="m-b hidden-nav-xs"></li>
									</ul>
									
								<!-- / nav -->
							</div>
						</section>

						<footer class="footer hidden-xs no-padder text-center-nav-xs">
							<div class="bg hidden-xs ">
								<div class="dropdown dropup wrapper-sm clearfix">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										<span class="thumb-sm avatar pull-left m-l-xs"> <img
											src="images/a3.png" class="dker" alt="..."> <i
											class="on b-black"></i>
									</span> <span class="hidden-nav-xs clear"> <span
											class="block m-l"> <strong class="font-bold text-lt">John.Smith</strong>
												<b class="caret"></b>
										</span> <span class="text-muted text-xs block m-l">Art
												Director</span>
									</span>
									</a>
									<ul class="dropdown-menu animated fadeInRight aside text-left">
										<li><span class="arrow bottom hidden-nav-xs"></span> <a
											href="#">Settings</a></li>
										<li><a href="profile.html">Profile</a></li>
										<li><a href="#"> <span
												class="badge bg-danger pull-right">3</span> Notifications
										</a></li>
										<li><a href="docs.html">Help</a></li>
										<li class="divider"></li>
										<li><a href="modal.lockme.html" data-toggle="ajaxModal">Logout</a>
										</li>
									</ul>
								</div>
							</div>
						</footer>
					</section>
				</aside>
				<!-- /.aside -->
				<section id="content">
					<section class="vbox">
						<section class="w-f-md" id="bjax-target">
							<section class="hbox stretch">
								<!-- / side content -->



								<!-- 音乐列表 -->
								<section>
									<section class="vbox">
										<section class="scrollable padder-lg">
											<div class="row row-sm"  >

												<c:if test="${not empty info }">
													<c:set var="muindex" value="1"></c:set>
													<c:forEach var="pm" items="${info}" varStatus="st">
														<div class="col-xs-6 col-sm-4 col-md-3 col-lg-2" >
															<div class="item">
																<div class="pos-rlt">
																	<div class="item-overlay opacity r r-2x bg-black">
																		<div class="center text-center m-t-n  " >
																			  <a href="#" data-toggle="class"  class="RondomSongs jp-play-me"  st="${st.index }">
												                                 <i class="icon-control-play i-2x text"></i>  
												                                 <i class="icon-control-pause i-2x text-active"></i> 
												                              </a>
																		</div>
																	</div>
																	<a href="track-detail.html"><img
																		src="/musicfile/${pm.musicphotourl }" alt="" width="100" height="260px"
																		class="r r-2x img-full"></a>
																</div>
																<div class="padder-v">
																	<a href="track-detail.html" data-bjax
																		data-target="#bjax-target" data-el="#bjax-el"
																		data-replace="true" class="text-ellipsis">${pm.musicname }</a>
																 <a href="track-detail.html" data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis text-xs text-muted">${pm.singer.singername}</a> 
																</div>
															</div>
														</div>
													</c:forEach>
												</c:if>
											</div>


											<!--   底部页数列表 -->
											<%--<ul class="pagination pagination">
												<c:if test="${info.pageNum-1<1 }">
													<li><a><i class="fa fa-chevron-left"></i></a></li>
												</c:if>
												
												<c:if test="${info.pageNum-1>=1 }">
													<li><a
														href="<%=basePath%>music/getComendMusics?pageNO=${info.prePage }"><i
															class="fa fa-chevron-left"></i></a></li>
												</c:if>

												<c:if test="${not empty info.list }">
													<c:forEach begin="1" end="${info.pages }" step="1"
														var="i">
														<c:if test="${i==info.pageNum}">
															<li class="active"><a>${i }</a></li>
														</c:if>
														<c:if test="${i!=info.pageNum}">
															<li><a
																href="<%=basePath%>music/getComendMusics?pageNO=${i}">${i}</a></li>
														</c:if>
													</c:forEach>
												</c:if>

												<c:if test="${info.pageNum+1<=info.pages }">
													<li><a
														href="<%=basePath%>music/getComendMusics?pageNO=${info.pageNum+1 }"><i
															class="fa fa-chevron-right"></i></a></li>
												</c:if>
												<c:if test="${info.pageNum+1>info.pages }">
													<li><a><i class="fa fa-chevron-right"></i></a></li>
												</c:if>--%>
											</ul>
										</section>
									</section>
								</section>



							</section>
						</section>
						<footer class="footer bg-info dker">
							<div id="jp_container_N">
								<div class="jp-type-playlist">
									<div id="jplayer_N" class="jp-jplayer hide"></div>
									<div class="jp-gui">
										<div class="jp-video-play hide">
											<a class="jp-video-play-icon">play</a>
										</div>
										<div class="jp-interface">
											<div class="jp-controls">
												<div>
													<a class="jp-previous"><i
														class="icon-control-rewind i-lg"></i></a>
												</div>
												<div>
													<a class="jp-play"><i class="icon-control-play i-2x"></i></a>
													<a class="jp-pause hid"><i
														class="icon-control-pause i-2x"></i></a>
												</div>
												<div>
													<a class="jp-next"><i class="icon-control-forward i-lg"></i></a>
												</div>
												<div class="hide">
													<a class="jp-stop"><i class="fa fa-stop"></i></a>
												</div>
												<div>
													<a class="" data-toggle="dropdown" data-target="#playlist"><i
														class="icon-list"></i></a>
												</div>
												<div class="jp-progress hidden-xs">
													<div class="jp-seek-bar dk">
														<div class="jp-play-bar bg-info"></div>
														<div class="jp-title text-lt">
															<ul>
																<li></li>
															</ul>
														</div>
													</div>
												</div>
												<div
													class="hidden-xs hidden-sm jp-current-time text-xs text-muted"></div>
												<div
													class="hidden-xs hidden-sm jp-duration text-xs text-muted"></div>
												<div class="hidden-xs hidden-sm">
													<a class="jp-mute" title="mute"><i
														class="icon-volume-2"></i></a> <a class="jp-unmute hid"
														title="unmute"><i class="icon-volume-off"></i></a>
												</div>
												<div class="hidden-xs hidden-sm jp-volume">
													<div class="jp-volume-bar dk">
														<div class="jp-volume-bar-value lter"></div>
													</div>
												</div>
												<div>
													<a class="jp-shuffle" title="shuffle"> <i
														class="icon-shuffle text-muted"></i></a> <a
														class="jp-shuffle-off hid" title="shuffle off"><i
														class="icon-shuffle text-lt"></i></a>
												</div>
												<div>
													<a class="jp-repeat" title="repeat"><i
														class="icon-loop text-muted"></i></a> <a
														class="jp-repeat-off hid" title="repeat off"><i
														class="icon-loop text-lt"></i></a>
												</div>
												<div class="hide">
													<a class="jp-full-screen" title="full screen"><i
														class="fa fa-expand"></i></a> <a class="jp-restore-screen"
														title="restore screen"><i
														class="fa fa-compress text-lt"></i></a>
												</div>
											</div>
										</div>
									</div>
									<div class="jp-playlist dropup" id="playlist">
										<ul class="dropdown-menu aside-xl dker">
											<!-- The method Playlist.displayPlaylist() uses this unordered list -->
											<li class="list-group-item"></li>
										</ul>
									</div>
									<div class="jp-no-solution hide">
										<span>Update Required</span> To play the media you will need
										to either update your browser to a recent version or update
										your <a href="http://get.adobe.com/flashplayer/"
											target="_blank">Flash plugin</a>.
									</div>
								</div>
							</div>
						</footer>
					</section>
					<a href="#" class="hide nav-off-screen-block"
						data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>
				</section>
			</section>
		</section>
	</section>
	<script src="<%=basePath %>js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="<%=basePath %>js/bootstrap.js"></script>
	<!-- App -->
	<script src="<%=basePath %>js/app.js"></script>
	<script src="<%=basePath %>js/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="<%=basePath %>js/app.plugin.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jPlayer/jquery.jplayer.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath %>js/jPlayer/add-on/jplayer.playlist.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jPlayer/demo.js"></script>

	<script type="text/javascript">
	  var myPlaylist; 
	  $(".RondomSongs").click(function(){
		  var r =$(this).attr("class");
		  if(r.indexOf('active')>=0){  
		  }else{
			  myPlaylist.remove();
			  var newSongs = ${mlist};
			  if(newSongs.length>0){
				 for(var i=0;i<newSongs.length;i++){
					 myPlaylist.add({
						 title:newSongs[i].musicname,
					     artist:newSongs[i].singer.singername,
					     mp3:"/musicfile/"+newSongs[i].songurl,
					     poster:"/musicfile/"+newSongs[i].musicphotourl
					 });
				 }
			  }
		  }
		  opration();
	  });
	  $(document).ready(function(){
		  getlist();
		  opration();
	   });
	   function getlist(){
		      myPlaylist = new jPlayerPlaylist({
			    jPlayer: "#jplayer_N",
			    cssSelectorAncestor: "#jp_container_N"
			  },${mlist},{
			    playlistOptions: {
			      enableRemoveControls: true,
			      autoPlay: false
			    },
			    swfPath: "js/jPlayer",
			    supplied: "webmv, ogv, m4v, oga, mp3",
			    smoothPlayBar: true,
			    keyEnabled: true,
			    audioFullScreen: false
			  });
  	    }
	   function opration(){
		   $(document).on($.jPlayer.event.pause, myPlaylist.cssSelector.jPlayer,  function(){
				  $('.musicbar').removeClass('animate');
			    $('.jp-play-me').removeClass('active');
			    $('.jp-play-me').parent('li').removeClass('active');
			  });
		
			  $(document).on($.jPlayer.event.play, myPlaylist.cssSelector.jPlayer,  function(){
			    $('.musicbar').addClass('animate');
			  });
		
			 $(document).on('click', '.jp-play-me', function(e){
			    e && e.preventDefault();
			    var $this = $(e.target);
			    if (!$this.is('a')) $this = $this.closest('a');
			    $('.jp-play-me').not($this).removeClass('active');
			    $('.jp-play-me').parent('li').not($this.parent('li')).removeClass('active');		    
			    $('.jp-play-me').not($this).find(".item-overlay").removeClass('active');
			    if( !$this.hasClass('active') ){
			      myPlaylist.pause();
			    }else{
			    	var a = $(this).attr("st");
			    	myPlaylist.play(a);
			    }
			  });
	   }
  </script>
</body>
</html>