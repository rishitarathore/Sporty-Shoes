<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SportyShoes - User Orders</title>
<jsp:include page="components/links.jsp" />
</head>

<body class=" d-flex flex-column h-100">

	<jsp:include page="components/nav.jsp" />

	<div class="dashboard w-75 mx-auto pb-5 px-5  ">

		<br>
		<h4 class="text-center mt-4 mb-4 ">Orders</h4>
		<br>
		<p class="text-warning">Total Orders : ${totalAmount}</p>
		<hr>
		<c:if test="${list.size() <= 0 }">

		<div class="container d-flex justify-content-end">
				<div id="btn" class="text-center btn btn-link text-danger">
					<a class="text-danger" href="/user/dashboard">Back</a>
				</div>
			</div>

			<p class="alert alert-warning p-2 my-3 text-center">No Order</p>

		</c:if>
		<c:if test="${list.size() > 0 }">

			<div class="container d-flex justify-content-end">
				<div id="btn" class="text-center btn btn-link text-danger">
					<a class="text-danger" href="/user/dashboard">Back</a>
				</div>
			</div>


			<table class="table">

				<tr class="table-info">
					<th style="width: 15%;">Order ID</th>
					<th style="width: 20%;">Date</th>
					<th>Items</th>
					<th style="width: 20%;">Total</th>



				</tr>

				<tbody>
					<c:forEach items="${list}" var="item">
						<tr>
							<td>${item.id}</td>
							<td>${item.date}</td>
							<td>${mapItems.get(item.id)}</td>
							<td>${item.grossTotal}</td>

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