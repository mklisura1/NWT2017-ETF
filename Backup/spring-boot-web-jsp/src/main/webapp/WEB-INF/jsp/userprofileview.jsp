<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>NWT | User profile</title>

<!-- Import pocetnih skripti iz fajla -->
<%@ include file="page_tiles/start_skripts.jsp"%>


<!-- Magnific Popup core CSS file -->
<link rel="stylesheet" href="${cp}/resources/plugins/magnific-popup/magnific-popup.css">

<style type="text/css">
	.image-link {
  cursor: -webkit-zoom-in;
  cursor: -moz-zoom-in;
  cursor: zoom-in;
}


/* This block of CSS adds opacity transition to background */
.mfp-with-zoom .mfp-container,
.mfp-with-zoom.mfp-bg {
	opacity: 0;
	-webkit-backface-visibility: hidden;
	-webkit-transition: all 0.3s ease-out; 
	-moz-transition: all 0.3s ease-out; 
	-o-transition: all 0.3s ease-out; 
	transition: all 0.3s ease-out;
}

.mfp-with-zoom.mfp-ready .mfp-container {
		opacity: 1;
}
.mfp-with-zoom.mfp-ready.mfp-bg {
		opacity: 0.8;
}

.mfp-with-zoom.mfp-removing .mfp-container, 
.mfp-with-zoom.mfp-removing.mfp-bg {
	opacity: 0;
}



/* padding-bottom and top for image */
.mfp-no-margins img.mfp-img {
	padding: 0;
}
/* position of shadow behind the image */
.mfp-no-margins .mfp-figure:after {
	top: 0;
	bottom: 0;
}
/* padding for main container */
.mfp-no-margins .mfp-container {
	padding: 0;
}



/* aligns caption to center */
.mfp-title {
  text-align: center;
  padding: 6px 0;
}
.image-source-link {
  color: #DDD;
}

</style>

</head>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Import main-header iz fajla -->
		<%@ include file="page_tiles/main_header.jsp"%>
		
		<!-- Import main-header iz fajla -->
		<jsp:include page="page_tiles/main_sidebar.jsp">
			<jsp:param value="active" name="paramProfileActive"/>
		</jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>My profile</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Profile</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-3">

						<!-- Profile Image -->
						<div class="box box-primary">
							<div class="box-body box-profile">
								<img class="profile-user-img img-responsive img-circle"
									src="${cp}/images/user2-128x128.jpg"
									alt="User profile picture">
								<h3 class="profile-username text-center">${user.first_name} ${user.last_name}</h3>

								<ul class="list-group list-group-unbordered">
									<li class="list-group-item"><b>Username</b> <a
										class="pull-right">${user.username}</a></li>
									<li class="list-group-item"><b>Roles</b> <a
										class="pull-right">All User Roles</a></li>
								</ul>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->

						<!-- About Me Box -->
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">About Me</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<strong><i class="fa fa-calendar-check-o margin-r-5"></i>Birth date</strong>
								<p class="text-muted"><fmt:formatDate type="date" pattern="dd.MM.yyyy" value="${user.birth_date}" /></p>

								<hr>

								<strong><i class="fa fa-map-marker margin-r-5"></i>
									Location</strong>
								<p class="text-muted">${user.address}</p>

								<hr>

								<strong><i class="fa fa-phone margin-r-5"></i>
									JMBG</strong>
								<p>${user.jmbg}</p>
								
								<hr>
								
								<strong><i class="fa fa-map-marker margin-r-5"></i>
									User type</strong>
								<p class="text-muted">NESTO2</p>

								<hr>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
					<div class="col-md-9">
						<div class="nav-tabs-custom">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#photo" data-toggle="tab">Photos</a></li>
								<li><a href="#settings" data-toggle="tab">Settings</a></li>
							</ul>
							<div class="tab-content">
								<div class="active tab-pane" id="photo">
									<!-- Post -->
									<div class="post">
										<div class="user-block">
											<img class="img-circle img-bordered-sm"
												src="${cp}/images/user2-128x128.jpg" alt="user image">
											<span class="username"> <a href="#">${user.first_name} ${user.last_name}</a> 
													<a href="#" class="pull-right btn-box-tool"><i
													class="fa fa-times"></i></a>
											</span> <span class="description">Shared publicly - 7:30 PM
												today</span>
										</div>
										<!-- /.user-block -->
										<p>Ovo je moj prvi post.</p>
										<ul class="list-inline">
											<li><a href="#" class="link-black text-sm"><i
													class="fa fa-share margin-r-5"></i> Share</a></li>
											<li><a href="#" class="link-black text-sm"><i
													class="fa fa-thumbs-o-up margin-r-5"></i> Like</a></li>
											<li class="pull-right"><a href="#"
												class="link-black text-sm"><i
													class="fa fa-comments-o margin-r-5"></i> Comments (5)</a></li>
										</ul>

										<input class="form-control input-sm" type="text"
											placeholder="Type a comment">
									</div>
									<!-- /.post -->

									<!-- Post -->
									<div class="post clearfix">
										<div class="user-block">
											<img class="img-circle img-bordered-sm"
												src="${cp}/images/user2-128x128.jpg" alt="user image">
											<span class="username"> <a href="#">${user.first_name} ${user.last_name}</a> <a
												href="#" class="pull-right btn-box-tool"><i
													class="fa fa-times"></i></a>
											</span> </span> <span class="description">Shared publicly - 7:30 PM
												today</span>
										</div>
										<!-- /.user-block -->
										<p>Ovo je moj drugi post.</p>

										<form class="form-horizontal">
											<div class="form-group margin-bottom-none">
												<div class="col-sm-9">
													<input class="form-control input-sm" placeholder="Response">
												</div>
												<div class="col-sm-3">
													<button class="btn btn-danger pull-right btn-block btn-sm">Send</button>
												</div>
											</div>
										</form>
									</div>
									<!-- /.post -->

									<!-- Post -->
									<div class="post">
										<div class="user-block">
											<img class="img-circle img-bordered-sm"
												src="${cp}/images/user2-128x128.jpg" alt="user image">
											<span class="username"> <a href="#">Adam Jones</a> <a
												href="#" class="pull-right btn-box-tool"><i
													class="fa fa-times"></i></a>
											</span> <span class="description">Posted 5 photos - 5 days
												ago</span>
										</div>
										<!-- /.user-block -->
									</div>
									<!-- /.post -->
								</div>
								<!-- /.tab-pane -->
								
								<!-- /.tab-pane -->
							</div>
							<!-- /.tab-content -->
						</div>
						<!-- /.nav-tabs-custom -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<!-- Import footera iz fajla -->
		<%@ include file="page_tiles/footer.jsp"%>
	</div>
	
	<!-- Import krajnjih skripti iz fajla -->
	<%@ include file="page_tiles/end_skripts.jsp"%>
	
	<!-- Magnific Popup core JS file -->
	<script src="${cp}/resources/plugins/magnific-popup/jquery.magnific-popup.js"></script>
</body>
</html>
