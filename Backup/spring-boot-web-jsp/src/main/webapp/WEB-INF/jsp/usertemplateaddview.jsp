<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>NWT | Add new template</title>

<!-- Import pocetnih skripti iz fajla -->
<%@ include file="page_tiles/start_skripts.jsp"%>

</head>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Import main-header iz fajla -->
		<%@ include file="page_tiles/main_header.jsp"%>
		
		<!-- Import main-header iz fajla -->
		<jsp:include page="page_tiles/main_sidebar.jsp">
			<jsp:param value="active" name="paramTemplateActive"/>
			<jsp:param value="active" name="paramTemplateAddActive"/>
		</jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Add new template</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li>Template</li>
					<li class="active">Add new template</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-4">

						<!-- Profile Image -->
						<div class="box box-primary">
							<div class="box-body ">
								<form:form class="form-horizonata" role ="form" method="POST" action="${cp}/users/add/template" modelAttribute="template">

									<spring:bind path="sender_name">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="sender_name">Sender name</form:label>
			                    			<form:input type="text" path="sender_name" placeholder="eg. Name"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="sender_name" cssStyle="color: #ff0000;"/>
											</div>
						                </div>
									</spring:bind>
									
									<spring:bind path="receiver_name">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="receiver_name">Receiver name</form:label>
			                    			<form:input type="text" path="receiver_name" placeholder="eg. Name"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="receiver_name" cssStyle="color: #ff0000;"/>
											</div>
						                </div>
									</spring:bind>
									
									<spring:bind path="sender_bank_acc_number">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="sender_bank_acc_number">Sender bank account number</form:label>
			                    			<form:input type="text" path="sender_bank_acc_number" placeholder="eg. 12312312312"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="sender_bank_acc_number" cssStyle="color: #ff0000;"/>
											</div>
						                </div>
									</spring:bind>
									
									<spring:bind path="receiver_bank_acc_number">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="receiver_bank_acc_number">Receiver bank account number</form:label>
			                    			<form:input type="text" path="receiver_bank_acc_number" placeholder="eg. 123123123"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="receiver_bank_acc_number" cssStyle="color: #ff0000;"/>
											</div>
						                </div>
									</spring:bind>
									
									<spring:bind path="payment_purpose">
				        				<div class="form-group ${status.error ? 'has-error' : ''}">
						                    <form:label path="payment_purpose">Payment purpose</form:label>
			                    			<form:input type="text" path="payment_purpose" placeholder="eg. For something"  class="form-control"/>
			                    			<div class="has-error">
												<form:errors path="payment_purpose" cssStyle="color: #ff0000;"/>
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
