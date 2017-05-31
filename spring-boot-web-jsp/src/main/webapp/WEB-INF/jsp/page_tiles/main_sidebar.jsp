<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="nwt.ebanking.model.User"%>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar" style="height: auto;">
	
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${cp}/resources/images/user2-160x160.jpg" class="img-circle"
					alt="User Image">
			</div>
			<div class="pull-left info">
				<p>Profile menu</p>
			</div>
		</div>
		
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
		
			<li class="header">MAIN NAVIGATION</li>
			<li class="${param.paramProfileActive}">
				<a href="${cp}/users/profile"> <i class="fa fa-th"></i> <span>Profile</span></a>
			</li>
			
			<li class=" treeview ${param.paramFriendsActive}">
			
				<a href="#"><i class="fa fa-users"></i> <span>Friends</span> <i class="fa fa-angle-left pull-right"></i> </a>
				
				<ul class="treeview-menu">
					<li class="${param.paramFriendsAddActive}">
						<a href="${cp}/users/find/friends"> <i class="fa fa-edit"></i> <span>Search friend</span></a>
					</li>
					<li class="${param.paramFriendsListActive}">
						<a href="${cp}/users/friends/list"> <i class="fa fa-th"></i> <span>My friends</span></a>
					</li>
				</ul>
			</li>
			
			<li class=" treeview ${param.paramGalleryActive}">
			
				<a href="#"><i class="fa fa-users"></i> <span>Gallery</span> <i class="fa fa-angle-left pull-right"></i> </a>
				
				<ul class="treeview-menu">
					<li class="${param.paramGalleryAddActive}">
						<a href="${cp}/gallery/add"> <i class="fa fa-th"></i> <span>Add new gallery</span></a>
					</li>
					<li class="${param.paramGalleryListActive}">
						<a href="${cp}/gallery/list"> <i class="fa fa-th"></i> <span>List of galleries</span></a>
					</li>
				</ul>
			</li>
			
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>