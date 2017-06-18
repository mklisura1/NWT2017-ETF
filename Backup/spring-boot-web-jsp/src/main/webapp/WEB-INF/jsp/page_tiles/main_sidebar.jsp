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
				<img src="${cp}/images/user2-160x160.jpg" class="img-circle"
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
			
			<li class=" treeview ${param.paramBankAccountActive}">
			
				<a href="#"><i class="fa fa-users"></i> <span>Bank Accounts</span> <i class="fa fa-angle-left pull-right"></i> </a>
				
				<ul class="treeview-menu">
					<li class="${param.paramBankAccountAddActive}">
						<a href="${cp}/users/add/bankaccount"> <i class="fa fa-edit"></i> <span>Add new bank account</span></a>
					</li>
					<li class="${param.paramBankAccountListActive}">
						<a href="${cp}/users/list/bankaccount"> <i class="fa fa-th"></i> <span>My bank accounts</span></a>
					</li>
				</ul>
			</li>
			
			<li class=" treeview ${param.paramTransactionActive}">
			
				<a href="#"><i class="fa fa-users"></i> <span>Transactions</span> <i class="fa fa-angle-left pull-right"></i> </a>
				
				<ul class="treeview-menu">
					<li class="${param.paramTransactionAddActive}">
						<a href="${cp}/users/add/transaction"> <i class="fa fa-th"></i> <span>Add new transaction</span></a>
					</li>
					<li class="${param.paramTransactionListActive}">
						<a href="${cp}/users/list/transaction"> <i class="fa fa-th"></i> <span>My transactions</span></a>
					</li>
				</ul>
			</li>
			
			<li class="${param.paramPaymentActive}">
				<a href="${cp}/users/add/payment"> <i class="fa fa-th"></i> <span>Payments</span></a>
			</li>
			
			<li class=" treeview ${param.paramTemplateActive}">
			
				<a href="#"><i class="fa fa-users"></i> <span>Templates</span> <i class="fa fa-angle-left pull-right"></i> </a>
				
				<ul class="treeview-menu">
					<li class="${param.paramTemplateAddActive}">
						<a href="${cp}/users/add/template"> <i class="fa fa-th"></i> <span>Add new template</span></a>
					</li>
					<li class="${param.paramTemplateListActive}">
						<a href="${cp}/users/list/template"> <i class="fa fa-th"></i> <span>List of templates</span></a>
					</li>
				</ul>
			</li>
			
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>