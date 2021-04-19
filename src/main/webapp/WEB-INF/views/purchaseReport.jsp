<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>SportyShoes - Purchase Report</title>
<jsp:include page="components/links.jsp" />
</head>

<body class=" d-flex flex-column h-100">

	<jsp:include page="components/nav.jsp" />


	<div class="dashboard  pb-5 px-5  ">

		<br>
		<h4 class="text-center mt-4 mb-4 ">Purchase Report</h4>
		<br>


		<div class="container w-100 ">
			<div class="row  justify-content-md-center">
				<form action="/admin/purchaseReport" class="d-flex">
					<div class="col-3"></div>
					<div class="col-3">
						<input class="browser-default custom-select" type="date"
							name="dateFilter">
					</div>

					<div class="col-3">
						<select class="browser-default custom-select mb-2"
							name="categoryId">
							<option value="" disabled selected>Select Category</option>
							<c:forEach items="${categoryList}" var="item">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
						</select>
					</div>

					<!-- Button -->
					<div class="col-3">
						<button class="btn btn-info btn-md center-block mx-2"
							type="submit">Filter</button>
					</div>
				</form>
			</div>
		</div>

		<hr>
		<c:if test="${list.size() <= 0 }">

			<div class="row pt-2 my-0">

				<div class="col-auto ml-auto">
					<div id="btn" class="text-center btn btn-link text-danger">
						<a class="text-danger" href="/admin/dashboard">Back</a>
					</div>
				</div>
			</div>

			<p class="alert alert-warning p-2 my-3 text-center">No Orders</p>

		</c:if>
		<c:if test="${list.size() > 0 }">


			<div class="row pt-2 my-0">
				<div class="col-auto mr-auto">
					<p class="fs-5 alert-secondary">Total ${list.size()}
						Orders : Rs. ${totalAmount }</p>
				</div>
				<div class="col-auto">
					<input id="btn" class="text-center hidden-print btn btn-link"
						type="button" value="Print" onclick="window.print()" />
					<div id="btn" class="text-center  btn btn-link text-danger">
						<a class="text-danger" href="/admin/dashboard">Back</a>
					</div>

				</div>
			</div>

			<div>
				<table class="table">
					<tr class="table-info">
						<th style="width: 10%;">Order ID</th>
						<th style="width: 20%;">Users</th>
						<th style="width: 15%;">Date</th>
						<th>Items</th>
						<th style="width: 18%;">Order Total</th>
					</tr>

					<tbody>
						<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.id}</td>
								<td>${mapUsers.get(item.id)}</td>
								<td>${item.date}</td>
								<td>${mapItems.get(item.id)}</td>
								<th>${item.grossTotal}</th>

							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</c:if>

	</div>



	<jsp:include page="components/footer.jsp" />
	<jsp:include page="components/scripts.jsp" />
</body>
</html>