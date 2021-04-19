<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SportyShoes - User List</title>
<jsp:include page="components/links.jsp" />

</head>

<body class=" d-flex flex-column h-100">

	<jsp:include page="components/nav.jsp" />

	<div class="dashboard  pb-5 px-5  ">

		<br>
		<h4 class="text-center mt-4 mb-4 ">User List</h4>
		<br>
		<hr>
		<c:if test="${list.size() <= 0 }">	
				
			<div class="row pt-2 my-0">
				
				<div class="col-auto ml-auto">				
					<div id="btn"
						class="text-center btn btn-link text-danger">
						<a class="text-danger"
							href="/admin/dashboard">Back</a>
					</div>
				</div>
			</div>

		<p class="alert alert-warning p-2 my-3 text-center" >No Users</p>

		</c:if>
      	<c:if test="${list.size() > 0 }">
		
		
		
		<div class="container d-flex justify-content-end">
			<input id="btn" class="text-center hidden-print btn btn-link"
				type="button" value="Print" onclick="window.print()" />
			<div id="btn"
				class="text-center hidden-print btn btn-link text-danger">
				<a class="text-danger"
					href="/admin/dashboard">Back</a>
			</div>
		</div>
		<table class="table">

			<tr class="table-info">
				<th scope="col">#</th>
				<th scope="col">First Name</th>
				<th scope="col">Last Name</th>
				<th scope="col">Email</th>
				<th scope="col">User name</th>
			</tr>
			<tbody>
					<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.id }</td>
					<td>${item.firstName}</td>
					<td>${item.firstName}</td>
					<td>${item.email}</td>
					<td>${item.login.username}</td>
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