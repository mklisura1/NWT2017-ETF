<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>NWT | Add new payment</title>

<!-- Import pocetnih skripti iz fajla -->
<%@ include file="page_tiles/start_skripts.jsp"%>

</head>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Import main-header iz fajla -->
		<%@ include file="page_tiles/main_header.jsp"%>
		
		<!-- Import main-header iz fajla -->
		<jsp:include page="page_tiles/main_sidebar.jsp">
			<jsp:param value="active" name="paramPaymentActive"/>
		</jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Add new payment</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li>Payment</li>
					<li class="active">Add new payment</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-4">

						<!-- Profile Image -->
						<div class="box box-primary">
							<div class="box-body ">
								<form:form class="form-horizonata" role ="form" method="POST" action="${cp}/users/add/payment" modelAttribute="payment">
								
									<spring:bind path="amount">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="amount">Amount</form:label>
			                    			<form:input type="text" path="amount" placeholder="eg. 122"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="amount" cssStyle="color: #ff0000;"/>
											</div>
						                </div>
									</spring:bind>
									
									<spring:bind path="senderName">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="senderName">Sender name</form:label>
			                    			<form:input type="text" path="senderName" placeholder="eg. Jane"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="senderName" cssStyle="color: #ff0000;"/>
											</div>
						                </div>
									</spring:bind>
									
									<spring:bind path="senderBankAccNumber">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="senderBankAccNumber">Sender bank account</form:label>
			                    			<form:input type="text" path="senderBankAccNumber" placeholder="eg. 122221"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="senderBankAccNumber" cssStyle="color: #ff0000;"/>
											</div>
						                </div>
									</spring:bind>
									
									<spring:bind path="receiverName">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="receiverName">Receiver name</form:label>
			                    			<form:input type="text" path="receiverName" placeholder="eg. Jane"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="receiverName" cssStyle="color: #ff0000;"/>
											</div>
						                </div>
									</spring:bind>
									
									<spring:bind path="receiverBankAccNumber">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="receiverBankAccNumber">Receiver bank account</form:label>
			                    			<form:input type="text" path="receiverBankAccNumber" placeholder="eg. 122221"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="receiverBankAccNumber" cssStyle="color: #ff0000;"/>
											</div>
						                </div>
									</spring:bind>
									
									<spring:bind path="purpose">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="purpose">Purpose</form:label>
			                    			<form:input type="text" path="purpose" placeholder="eg. 121"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="purpose" cssStyle="color: #ff0000;"/>
											</div>
						                </div>
									</spring:bind>
									
									<spring:bind path="typeDescription">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="typeDescription">Description</form:label>
			                    			<form:input type="text" path="typeDescription" placeholder="eg. Blah"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="typeDescription" cssStyle="color: #ff0000;"/>
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
