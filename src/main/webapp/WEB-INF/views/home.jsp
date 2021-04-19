<!DOCTYPE html>
<html lang="en">
<head>
<title>SportyShoes - Home</title>
<jsp:include page="components/links.jsp" />
</head>

<body class=" d-flex flex-column h-100">

	<jsp:include page="components/nav.jsp" />

	<div class="container px-0  pt-2">
		<div class="row px-5 py-0 d-flex align-items-center ">
			<div class="col-lg-6 mt-5 p-0">
				<h2 class=" welcome py-2 ">Welcome to SportyShoes.com</h2>
				<button type="button"
					class="btn btn-lg  btn-outline-dark btn-rounded"><a href="/shopNow" id="custom-a-show-now">Shop Now!</a></button>
			</div>
			<div class="col-lg-6 mt-5 p-0">

				<!--Carousel Wrapper-->
				<div id="carousel-example-1z" class="carousel slide carousel-fade"
					data-ride="carousel">
					<!--Slides-->
					<div class="carousel-inner" role="listbox">
						<!--First slide-->
						<div class="carousel-item active">
							<img class="d-block w-100" src="images/5png.png"
								alt="First slide">
						</div>
						<!--/First slide-->
						<!--Second slide-->
						<div class="carousel-item">
							<img class="d-block w-100" src="images/4png.png"
								alt="Second slide">
						</div>
						<!--/Second slide-->
						<!--Third slide-->
						<div class="carousel-item">
							<img class="d-block w-100" src="images/7png.png"
								alt="Third slide">
						</div>
						<!--/Third slide-->
						<div class="carousel-item">
							<img class="d-block w-100" src="images/1png.png"
								alt="Fourth slide">
						</div>

					</div>
					<!--/.Slides-->
				</div>

				<!--/.Carousel Wrapper-->

			</div>
		</div>
	</div>





	<jsp:include page="components/footer.jsp" />
	<jsp:include page="components/scripts.jsp" />
</body>
</html>