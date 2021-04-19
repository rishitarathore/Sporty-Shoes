<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

<title>Sporty Shoes - Product Form</title>
<jsp:include page="components/links.jsp" />

</head>

<body class=" d-flex flex-column h-100">

	<jsp:include page="components/nav.jsp" />


	<div class="dashboard  pb-5  ">

		<br>
		<h4 class="text-center mt-4 mb-4 ">Manage Products</h4>
		<br>

		<div class="container d-flex justify-content-center">

			<div class="row w-50" id="custom-anchor">
				<div class="col btn mx-3 btn-info btn-rounded ">
					<a class="stretched-link" href="/admin/editProduct?id=0">Add
						New Product </a>
				</div>
				<div class="col btn mx-3 btn-info btn-rounded">
					<a class="stretched-link" href="/admin/productList">Products</a>
				</div>
			</div>

		</div>




		<div class="container d-flex justify-content-center mt-5">

			<form method=post action="/admin/saveProduct">
				<h5 class="text-center mb-4 ">${titlemsg}</h5>
				<input type=hidden name=id value="${product.id}"> <input
					class="form-control mb-2" type="text" placeholder="Product Name"
					name="name" value="${product.name}"> <input
					class="form-control mb-2" type="text" placeholder="Product Price"
					name="price" value="${product.price}"> <input
					class="form-control mb-2" type="text" placeholder="Product Sizes"
					name="details" value="${product.details}"> <select
					class="browser-default custom-select mb-2" name="categoryId"
					required>
					<option value="" disabled selected>Select Category</option>
					<c:forEach items="${categoryList}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
				</select> <input class="btn btn-success my-2" type="submit" value="Submit">
				<div
					class=" mx-auto d-flex justify-content-center text-success mt-4">${successmsg}</div>

			</form>
		</div>
	</div>



	<jsp:include page="components/footer.jsp" />
	<jsp:include page="components/scripts.jsp" />
</body>
</html>