<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

<title>SportyShoes - User Dashboard</title>
<jsp:include page="components/links.jsp" />

</head>
<body class=" d-flex flex-column h-100">

	<jsp:include page="components/nav.jsp" />

	<div class="container bg-trasparent my-4 p-3"
		style="position: relative;">
		<div
			class="row row-cols-1 row-cols-xs-2 row-cols-sm-2 row-cols-lg-4 g-3">


			<c:if test="${categoryname == null}">
			<c:forEach items="${productList}" var="item">


				<div class="col">
					<div class="card pl-4 pr-3 py-2">
						<div class="div1 row py-4 px-2">
							<div class="col-3"></div>
							<div class="col-6 d-flex justify-content-center">
								<img src="/images/2png.png" height="" width="130%" alt="">
							</div>
							<div class="col-3 d-flex flex-column pl-4">
								<i class="fa fa-heart fa-lg mt-4" aria-hidden="true"></i>

							</div>
						</div>
						<div class="py-2">
							<h5>${item.name}</h5>
							<p>Size ${item.details}</p>
							<div class="d-flex">
								<h5 class="align-self-center">Rs. ${item.price}</h5>
								<button class="buy d-flex ml-auto  px-4 py-1 border-0">
								<a class="buy" href="/cart/addItem?id=${item.id}">Add to Cart</a>
								</button>
							</div>
						</div>

					</div>
				</div>

			</c:forEach>
						</c:if>      -



		</div>
	</div>
	<jsp:include page="components/footer.jsp" />
	<jsp:include page="components/scripts.jsp" />
</body>
</html>