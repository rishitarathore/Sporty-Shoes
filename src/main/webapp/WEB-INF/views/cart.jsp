<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SportyShoes - Cart</title>
<jsp:include page="components/links.jsp" />
</head>


<body class=" d-flex flex-column h-100">

	<jsp:include page="components/nav.jsp" />

	<div class="dashboard w-50 mx-auto pb-5 px-5  ">

		<br>
		<h4 class="text-center mt-4 mb-4 ">Cart</h4>
		<br>

		<c:if test="${cartItems.size() <= 0}">
			<hr>
			<p class="alert alert-warning p-2 my-5 text-center">Cart is
				Empty</p>
			<a class="float-end" href="/user/dashboard">Continue shopping</a>
		</c:if>

		<c:if test="${cartItems.size() > 0}">

			<p class="text-warning">Order Total: Rs ${totalValue}</p>
			<div class="row pt-3">
				<div class="col-auto mr-auto">
					<a class="stretched-link btn btn-info " href="/user/dashboard">Add
						more to Cart</a>
				</div>
				<div class="col-auto ">
					<a class="stretched-link btn btn-warning " href="/cart/checkout">Proceed
						To Buy </a>
				</div>

			</div>

			<hr>

			<table class="table">

				<tr class="table-info">

					<th>Product</th>
					<th>Qty</th>
					<th>Price</th>
					<th></th>
				</tr>

				<tbody>
					<c:forEach items="${cartItems}" var="item">
						<tr>
							<td>${item.name}</td>
							<td>${item.qty}</td>
							<td>${item.price}</td>
							<td><a class="text-primary"
								href="/cart/deleteItem?id=${item.productId}">Remove from
									Cart</a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</c:if>
	</div>

	<jsp:include page="components/footer.jsp" />
	<jsp:include page="components/scripts.jsp" />
</body>
</html>