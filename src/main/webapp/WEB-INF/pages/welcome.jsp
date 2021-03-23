<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Services</title>
<link rel='stylesheet' type='text/css' href="resources/css/style.css">

</head>
<body>
	<div class='welcome-container'>
		<h1>Welcome ${customer.getFirstName()}</h1>
		<div class="form">
			<h4 class="error">${message}</h4>
			<form method="post" action="search" modelAttribute="search">
				<input placeholder="Departure City Code" pattern="[A-Za-z\s]{3}" type="text" name="departure" required /> 
				<input placeholder="Destination City Code" pattern="[A-Za-z\s]{3}"type="text" name="destination" required /> 
				<input placeholder="Date in dd/mm/yyyy" type="date" name="date" required /> 
				<input placeholder="Seats" type="number" name="seats" min="1" max="5" required /> 
				<input type="submit" value="Search flights">
				<p class="message">Have a booking? <a id="register-switch" href="view">View bookings</a></p>	
				<p class="message">Want to cancel booking? <a id="register-switch" href="cancel">Cancel booking</a></p>	
				<p class="message">Want to change profile? <a id="register-switch" href="update">Update profile</a></p>	
				
			</form>
		</div>
	</div>
</body>
</html>