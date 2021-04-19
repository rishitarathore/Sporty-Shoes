<!DOCTYPE html>
<html lang="en">
<head>
<title>SportyShoes - Admin Dashboard</title>
<jsp:include page="components/links.jsp" />
</head>
<body>

	<jsp:include page="components/nav.jsp" />

	<div class="dashboard  pb-5  ">

		<br>
		<h4 class="text-center mt-4 mb-4 ">Admin Dashboard</h4>
		<br>

		<div class="container  d-flex justify-content-center">

			<div class="row-12 w-25 " id="custom-anchor">
				<div class="col btn my-3 btn-info btn-rounded">
					<a class="stretched-link" href="/admin/categoryPanel">Manage
						Categories </a>
				</div>
				<div class="col  btn my-3 btn-info btn-rounded">
					<a class="stretched-link" href="/admin/productPanel">Manage
						Product</a>
				</div>
				<div class="col btn my-3 btn-info btn-rounded">
					<a class="stretched-link" href="/admin/userList">Users List</a>
				</div>
				<div class="col btn my-3 btn-info btn-rounded">
					<a class="stretched-link" href="/admin/purchaseReport">View
						Purchase Report</a>
				</div>
				<div class="col btn my-3 btn-info btn-rounded">
					<a class="stretched-link" href="/user/dashboard">View User
						Dashboard</a>
				</div>
			</div>

		</div>

	</div>
	<jsp:include page="components/footer.jsp" />
	<jsp:include page="components/scripts.jsp" />
</body>
</html>