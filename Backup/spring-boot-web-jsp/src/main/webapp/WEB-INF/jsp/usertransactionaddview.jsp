<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>NWT | Add new transaction</title>

<!-- Import pocetnih skripti iz fajla -->
<%@ include file="page_tiles/start_skripts.jsp"%>

</head>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Import main-header iz fajla -->
		<%@ include file="page_tiles/main_header.jsp"%>
		
		<!-- Import main-header iz fajla -->
		<jsp:include page="page_tiles/main_sidebar.jsp">
			<jsp:param value="active" name="paramTransactionActive"/>
			<jsp:param value="active" name="paramTransactionAddActive"/>
		</jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Add new transaction</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li>Transaction</li>
					<li class="active">Add new bank account</li>
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
														<th>Amount</th>
														<th>Sender name</th>
														<th>Sender bank account</th>
														<th>Receiver name</th>
														<th>Receiver bank account</th>
														<th>Payment purpose</th>
														<th>Payment type</th>
														<th>Payment status</th>
														<th>Payment description</th>
														<th style="width: 165px;">Action</th>
														
													</tr>
												</thead>
												<tbody>
													<c:forEach var="payments" items="${payments}">
														<tr>													
															<td>${payments.amount}</td>
															<td>${payments.senderName}</td>
															<td>${payments.senderBankAccNumber}</td>
															<td>${payments.receiverName}</td>
															<td>${payments.receiverBankAccNumber}</td>
															<td>${payments.purpose}</td>
															<td>${payments.type}</td>
															<td>${payments.status}</td>
															<td>${payments.typeDescription}</td>
															<td><a class="btn btn-success" href="${cp}/users/add/transaction/${payments.id}">Convert to transaction</a>
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
