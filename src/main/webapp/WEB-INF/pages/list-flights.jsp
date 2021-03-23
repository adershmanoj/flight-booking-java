<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quote</title>
<link rel='stylesheet' type='text/css' href="resources/css/style.css">

</head>
<body>
	<div class='flight-table'>
		<table>
			<tr>
				<th>ID</th>
				<th>Flight Number</th>
				<th>Departure</th>
				<th>Arrival</th>
				<th>ETD</th>
				<th>ETA</th>
				<th>Airline</th>
				<th>Fare</th>
				<!-- rest of you columns -->
			</tr>
			<c:forEach items="${flights}" var="flight">
				<tr>
					<td>${flight.getId()}</td>
					<td>${flight.getFlightNumber()}</td>
					<td>${flight.getDeparture()}</td>
					<td>${flight.getArrival()}</td>
					<td>${flight.getEtd()}</td>
					<td>${flight.getEta()}</td>
					<td>${flight.getAirline()}</td>
					<td>${flight.getFare()}</td>
					<td><a href="booking?id=${flight.getId()}">Book</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>