<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sporty Shoes - Register</title>
	<jsp:include page="components/links.jsp" />
</head>
<body class=" d-flex flex-column h-100">
	<jsp:include page="components/nav.jsp" />


      <div class="custom-container">
          <div class="frame">

              <h5 class="text-center mt-4 mb-3 text-dark">Edit User</h5>
                  <form class="form-signin py-4"  method=post action="/user/editProfileAction">
            		<input type=hidden name=user_id value="${user.id}">
            		
                    <input class="form-styling" type="text" name="firstname" placeholder="First Name" value="${user.firstName}" required/>
                    <input class="form-styling" type="text" name="lastname" placeholder="Last Name" value="${user.lastName}" required />
                    <input class="form-styling" type="email" name="email" placeholder="Email" value="${user.email}" required/>
                    <input class="form-styling" type="text" name="age" placeholder="Age" value="${user.age}" required/>
                    <input class="form-styling" type="text" name="address" placeholder="Address" value="${user.address}"  required/>
                    <input class="form-styling" type="text" name="username" placeholder="User Name" value="${user.login.username}" required/>
                    <input type="submit" class="btn-custom-signin btn-info btn-rounded my-3"  value="Confirm Changes"> 
                    </form>

		</div>
		</div>
                    
   <jsp:include page="components/footer.jsp" />
	<jsp:include page="components/scripts.jsp" />
</body>
</html>