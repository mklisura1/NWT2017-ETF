<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>NWT | Add new bank account</title>

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
			<jsp:param value="active" name="paramBankAccountAddActive"/>
		</jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Add new bank account</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li>Bank account</li>
					<li class="active">Add new bank account</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-4">

						<!-- Profile Image -->
						<div class="box box-primary">
							<div class="box-body ">
								<form:form class="form-horizonata" role ="form" method="POST" action="${cp}/users/add/bankaccount" modelAttribute="bankAccount">
								
									<spring:bind path="bank_account_name">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="bank_account_name">Bank Account</form:label>
			                    			<form:input type="text" path="bank_account_name" placeholder="eg. Name"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="bank_account_name" cssStyle="color: #ff0000;"/>
											</div>
						                </div>
									</spring:bind>
									
									<spring:bind path="bank_account_number">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="bank_account_number">Bank account number</form:label>
			                    			<form:input type="text" path="bank_account_number" placeholder="eg. 121"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="bank_account_number" cssStyle="color: #ff0000;"/>
											</div>
						                </div>
									</spring:bind>
									
									<spring:bind path="credit_amount">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="credit_amount">Initial credit</form:label>
			                    			<form:input type="text" path="credit_amount" placeholder="eg. 121"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="credit_amount" cssStyle="color: #ff0000;"/>
											</div>
						                </div>
									</spring:bind>
									
									<div class="form-group">
										<div class="col-sm-offset-4 col-sm-10">
											<button type="submit" class="btn btn-success">Save</button>
										
											<button type="submit" class="btn btn-warning">Cancel</button>
										</div>
										<br>
									</div>

								</form:form>
								
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
