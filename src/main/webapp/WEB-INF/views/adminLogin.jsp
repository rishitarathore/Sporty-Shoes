<!DOCTYPE html>
<html lang="en">
<head>
<title>SportyShoes - Admin Login</title>
<jsp:include page="components/links.jsp" />
</head>
<body class=" d-flex flex-column h-100">

	<jsp:include page="components/nav.jsp" />


	<div class="custom-container">
		<div class="frame">

			<h5 class="text-center mt-5 mb-4 text-dark">Admin Login</h5>
			<form class="form-signin" action="/admin/login" method="post">
				<input class="form-styling" type="text" name="username"
					placeholder="Username" /> <input class="form-styling"
					type="password" name="password" placeholder="Password" /> <span
					class="text-danger font-weight-bolder my-2">${errorMessage}</span>
				<input type="submit"
					class="btn-custom-signin btn-info btn-rounded my-3" value="Sign In">
				<!-- <div class="f-pass">
					<a href="/admin/edit">Forgot your password?</a>
				</div> -->
			</form>
		</div>
	</div>





	<jsp:include page="components/footer.jsp" />
	<jsp:include page="components/scripts.jsp" />
</body>
</html>