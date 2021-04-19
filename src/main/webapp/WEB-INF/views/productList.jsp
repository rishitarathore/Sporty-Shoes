<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SportyShoes - Products</title>
<jsp:include page="components/links.jsp" />
</head>

<body class=" d-flex flex-column h-100">

	<jsp:include page="components/nav.jsp" />


	<div class="dashboard container w-75 pb-5 px-5  ">

		<br>
		<h4 class="text-center mt-4 mb-4 ">Product List</h4>
		<br>
		
		<div class=" mx-auto d-flex justify-content-center text-success">${successeditmsg}</div>				
		<div class=" mx-auto d-flex justify-content-center text-primary">${successdeletemsg}</div>
		<hr>
		
		<c:if test="${list.size() <= 0 }">	
				
			<div class="row pt-2 my-0">
				
				<div class="col-auto ml-auto">				
					<div id="btn"
						class="text-center btn btn-link text-danger">
						<a class="text-danger"
							href="/admin/productPanel">Back</a>
					</div>
				</div>
			</div>

		<p class="alert alert-warning p-2 my-3 text-center" >No Product</p>

		</c:if>
      	<c:if test="${list.size() > 0 }">	
      	
		
		
		
		
		
			<div class="container d-flex justify-content-end">
			<div id="btn" class="text-center btn btn-link text-danger">
				<a class="text-danger" href="/admin/productPanel">Back</a>
			</div>
		</div>
		<table class="table">

			<tr class="table-info">
				<th scope="col">#</th>
				<th scope="col">Name</th>
				<th scope="col">Price</th>
				<th scope="col">Details</th>
				<th scope="col">Category</th>
				<th style="width: 15%" scope="col">Action</th>
			</tr>

			<tbody>
				<c:forEach items="${list}" var="item">
					<tr>
						<th>${item.id}</th>
						<td>${item.name}</td>
						<td>${item.price}</td>
						<td>${item.details}</td>
						<td>${item.category.name}</td>
						<td><a class="text-primary"
							href="/admin/editProduct?id=${item.id}">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
							class="text-danger" href="/admin/deleteProduct?id=${item.id}">Delete</a>
						</td>
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