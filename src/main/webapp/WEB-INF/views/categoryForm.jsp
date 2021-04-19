<!DOCTYPE html>
<html lang="en">
<head>
<title>SportyShoes - Category Form</title>

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
					<a class="stretched-link" href="/admin/editCategory?id=0">Add
						New Category </a>
				</div>


				<div class="col  btn mx-3 btn-info btn-rounded">
					<a class="stretched-link" href="/admin/categoryList">Categories</a>
				</div>
			</div>

		</div>
		<div class="container d-flex justify-content-center mt-5">

			<form method=post action="/admin/saveCategory">
				<h5 class="text-center mb-4 ">${titlemsg}</h5>
				<input type=hidden name=id value="${category.id}"> <input
					class="form-control mb-2" type="text" placeholder="Category Name"
					name=name value="${category.name}" required> <input
					class="btn btn-success my-2" type="submit" value="Submit">
				<div
					class=" mx-auto d-flex justify-content-center text-success mt-4">${successmsg}</div>

			</form>
		</div>
	</div>
	<jsp:include page="components/footer.jsp" />
	<jsp:include page="components/scripts.jsp" />
</body>
</html>