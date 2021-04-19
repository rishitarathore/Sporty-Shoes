<!DOCTYPE html>
<html lang="en">
<head>
<title>SportyShoes - Category Panel</title>
<jsp:include page="components/links.jsp" />
</head>
<body class=" d-flex flex-column h-100">

	<jsp:include page="components/nav.jsp" />



	<div class="dashboard  pb-5  ">

		<br>
		<h4 class="text-center mt-4 mb-4 ">Manage Categories</h4>
		<br>

		<div class="container d-flex justify-content-center">

			<div class="row w-50" id="custom-anchor">
				<div class="col btn mx-3 btn-info btn-rounded">
					<a class="stretched-link" href="/admin/editCategory?id=0">Add New Category </a>
				</div>
				<div class="col  btn mx-3 btn-info btn-rounded">
					<a class="stretched-link" href="/admin/categoryList">Categories</a>
				</div>
			</div>

		</div>

	</div>

	<jsp:include page="components/footer.jsp" />
	<jsp:include page="components/scripts.jsp" />
</body>
</html>