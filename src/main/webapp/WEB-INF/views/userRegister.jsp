<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

              <h5 class="text-center mt-4 mb-2 text-dark">Register</h5>
                  <form class="form-signin py-4"  method=post action="/user/register">
                    
                    <input class="form-styling" type="text" name="firstname" placeholder="First Name" required/>
                    <input class="form-styling" type="text" name="lastname" placeholder="Last Name" required />
                    <input class="form-styling" type="email" name="email" placeholder="Email"  required/>
                    <input class="form-styling" type="text" name="age" placeholder="Age"  required/>
                    <input class="form-styling" type="text" name="address" placeholder="Address"  required/>
                    <input class="form-styling" type="text" name="username" placeholder="User Name"  required/>
                    <input class="form-styling" type="password" name="password" placeholder="Password" required/>
                    <input type="submit" class="btn-custom-signin btn-info btn-rounded my-3"  value="Register"> 
                    <c:if test="${error!=null}">
                    <span class=" text-danger font-weight-bolder my-2"><c:out value="${error}"/></span>
		  	  		</c:if>
                    </form>

		</div>
		</div>
                    
   <jsp:include page="components/footer.jsp" />
	<jsp:include page="components/scripts.jsp" />
</body>
</html>