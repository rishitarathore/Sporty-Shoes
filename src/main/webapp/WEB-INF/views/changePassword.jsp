<!DOCTYPE html>
<html lang="en">
<head>
<title>SportyShoes - Change Password</title>
<jsp:include page="components/links.jsp" />
</head>

<body class=" d-flex flex-column h-100">

	<jsp:include page="components/nav.jsp" />


	<div class="custom-container">
		<div class="frame">

			<h5 class="text-center mt-5 mb-2 text-dark">Change Password</h5>
			<form class="form-signin" method=post action="/admin/changePassAction" >
				<input class="form-styling" type="password" name="password1"
					placeholder="Enter New Password" /> <input class="form-styling"
					type="password" name="password2" placeholder="Confirm New Password" />
					<span class="text-danger font-weight-bolder my-2">${errorMessage}</span>
				<input type="submit" class="btn-custom-signin btn-info btn-rounded my-3" value="Submit">

			</form>
		</div>
	</div>

	<jsp:include page="components/footer.jsp" />
	<jsp:include page="components/scripts.jsp" />
</body>
</html>