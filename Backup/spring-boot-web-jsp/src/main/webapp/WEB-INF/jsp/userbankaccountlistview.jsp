<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<!DOCTYPE html>
<html>
<head>
<title>NWT | Show my bank accounts</title>

<!-- Import pocetnih skripti iz fajla -->
<%@ include file="page_tiles/start_skripts.jsp"%>

</head>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Import main-header iz fajla -->
		<%@ include file="page_tiles/main_header.jsp"%>
		
		<!-- Import main-header iz fajla -->
		<jsp:include page="page_tiles/main_sidebar.jsp">
			<jsp:param value="active" name="paramBankAccountActive"/>
			<jsp:param value="active" name="paramBankAccountListActive"/>
		</jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Show all bank accounts</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li>Bank account</li>
					<li class="active">Show my bank accounts</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">

						<!-- Profile Image -->
						<div class="box box-primary">
							<div class="box-body ">
								<div id="example1_wrapper"
									class="dataTables_wrapper form-inline dt-bootstrap">
									<div class="row">
										<div class="col-sm-12">
											<table class="table table-bordered table-striped dataTable">
												<thead>
													<tr>
														<th>Bank account name</th>
														<th>Type</th>
														<th>Credit amount</th>
														<th>Bank account number</th>
														<th style="width: 165px;">Action</th>
														
													</tr>
												</thead>
												<tbody>
													<c:forEach var="bankAccounts" items="${bankAccounts}">
														<tr>															
															<td>${bankAccounts.bank_account_name}</td>
															<td>${bankAccounts.bankAccountType.bank_account_type_name}</td>
															<td>${bankAccounts.credit_amount}</td>
															<td>${bankAccounts.bank_account_number}</td>
															<td><a class="btn btn-danger" href="${cp}/users/delete/bankaccount/${bankAccounts.bank_account_id}">Delete</a>
															</td>
														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>
									</div>

								</div>
								
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
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

</body>
</html>
