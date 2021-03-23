<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bookings</title>
<link rel='stylesheet' type='text/css' href="resources/css/style.css">

</head>
<body>
	<div class='flight-table'>
		<form method="post" action="updateProfile" modelAttribute="customer">
			<label>Email</label>
			<input type="email" name="email" value="${customer.getEmail()}"required />
			<label>First Name</label>
			<input type="text" name="firstName" value="${customer.getFirstName()}"required />
			<label>Last Name</label>
			<input type="text" name="lastName" value="${customer.getLastName()}"required />
			<label>Mobile</label>
			<input type="tel" name="mobile" value="${customer.getMobile()}"required />
			<label>Password</label>
			<input type="password" name="password" value="${customer.getPassword()}" required />
			<input type="submit" value="Update" />
		</form>
	</div>
</body>
</html>