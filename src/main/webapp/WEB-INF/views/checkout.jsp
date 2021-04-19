<!DOCTYPE html>
<html lang="en">
<head>
<title>SportyShoes - Checkout</title>
<jsp:include page="components/links.jsp" />
</head>

<body class=" d-flex flex-column h-100">

	<jsp:include page="components/nav.jsp" />




	<div class="dashboard w-50 mx-auto pb-5 px-5  ">

		<br>
		<h4 class="text-center mt-4 mb-4 ">Checkout</h4>
		<br>

		<p class="w-75 mx-auto alert-success text-success">Order
			Total: ${totalValue}</p>


		<hr class="w-75 mx-auto">

		<div class="container w-75 justify-content-center">

			<form action="/cart/orderPlacedAction">
				<input type=hidden name=user_id value="${user.id}">
				<h6 class=" mx-auto py-2 ">Communication Details</h6>
				<input class="form-control  mb-3" type="text" placeholder="Email" value="${user.email}">
				<input class="form-control mb-3" type="text" placeholder="Address" value="${user.address}">


				<h6 class=" mx-auto py-2">Payment Method</h6>
				<div class="container">
					<!-- <p class="py-2">Payment Method:</p>     -->
					<div class="form-check pb-2">
						<input type="radio" class="form-check-input"
							id="materialUnchecked" name="materialExampleRadios"> <label
							class=" form-check-label" for="materialUnchecked">Debit
							Card</label>
					</div>


					<div class="form-check py-2">
						<input type="radio" class="form-check-input" id="materialChecked"
							name="materialExampleRadios" checked> <label
							class=" form-check-label" for="materialChecked">Credit
							Card</label>
					</div>

					<div class="form-check py-2">
						<input type="radio" class="form-check-input" id="materialChecked"
							name="materialExampleRadios" checked> <label
							class=" form-check-label" for="materialChecked">Pay on
							Delivery</label>
					</div>
				</div>

				<input type="submit" class="my-3 btn btn-success"
					value="Place Order" />
			</form>

		</div>
	</div>

	<jsp:include page="components/footer.jsp" />
	<jsp:include page="components/scripts.jsp" />
</body>
</html>