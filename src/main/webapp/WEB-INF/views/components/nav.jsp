<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ page isELIgnored="false"%> --%>


<nav class="navbar navbar-expand-lg navbar-light" id="custom-nav">
	<a href="/" id="custom-navbar-brand" class="navbar-brand ">SportyShoes</a>
	<button type="button" class="navbar-toggler" data-toggle="collapse"
		data-target="#navbarCollapse">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="mx-5">
		<form class=" d-flex input-group w-auto">
			<input type="search" class="form-control" placeholder="Search All"
				aria-label="Search" />
			<button class="btn btn-info" type="button">Search</button>
		</form>
	</div>

	<ul id="custom-anchor-nav" class="navbar-nav ml-auto">

		<c:if test="${admin_id != null }">

			<li class="nav-item "><a href="/admin/dashboard"
				class="nav-link pr-4 ">Dashboard</a></li>

			<li class="nav-item dropdown"><a class="nav-link" href="#"
				id="navbarDropdown" role="button" data-mdb-toggle="dropdown"
				aria-expanded="false"> Hello, ${admin_username} <i
					id="custom-icon-dropdown" class="mx-1 fas fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item" href="/admin/changePassword">Change Password</a></li>
					<li><a class="dropdown-item" href="/logout">Logout</a></li>
				</ul></li>

		</c:if>
		
	 	<c:if test="${user_id != null }">

			<li class="nav-item "><a href="/user/dashboard" class="nav-link pr-4 ">Dashboard</a></li>
			<li class="nav-item dropdown"><a class="nav-link" href="#"
				id="navbarDropdown" role="button" data-mdb-toggle="dropdown"
				aria-expanded="false"> Hello, ${user_username} <i
					id="custom-icon-dropdown" class="mx-1 fas fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
				<!-- 	<li><a class="dropdown-item" href="#">Change Password</a></li> -->
					<li><a class="dropdown-item" href="/logout">Logout</a></li>
				</ul></li>
			 
		</c:if>
	
		
		<c:if test ="${user_id == null && admin_id == null}"> 
				
			<li class="nav-item "><a href="/register" class="nav-link pr-4 ">User Register</a></li>	

			<li class="nav-item dropdown"><a class="nav-link " href="#"
				id="navbarDropdown" role="button" data-mdb-toggle="dropdown"
				aria-expanded="false"> Login <i id="custom-icon-dropdown"
					class="mx-2 fas fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item" href="/user">User</a></li>
					<li><a class="dropdown-item" href="/admin">Admin</a></li>

				</ul></li>



		</c:if>
	</ul>
</nav>


<c:if test="${user_id != null }">
     <div id="sub-nav" class="container-fluid d-flex justify-content-end ">
            
            <a  href="/user/editprofile">Edit Profile </a>
            <a  href="/user/yourOrders">Your Order </a>
            <a href="/cart">Cart </a>
            </div>
 </c:if>           
            
  
