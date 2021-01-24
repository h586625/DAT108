<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registrering av betaling</title>
</head>
<body>
	<h2>Deltagere som ikke har betalt</h2>
	
	<table class="ikke-betalt">
		<tr>
			<th>Navn</th>
			<th>Mobil</th>
			<th>Betaling</th>
		</tr>
		
		<c:forEach items="${deltagere}" var="d">
			<c:choose>
				<c:when test="${deltager.betalt eq false}">
					<tr>
						<td><c:out value="${d.navn}" /></td>
						<td><c:out value="${d.mobil}" /></td>
						<td>
							<form method="post">
								<input type="hidden" name="deltager_id" value="${d.mobil}" />
								<button type="submit">Registrer betaling</button>
							</form>
						</td>
					</tr>
				</c:when>
			</c:choose>
		</c:forEach>
	</table>
	
	<h2>Deltagere som har betalt</h2>
	
	<table class="betalt">
		<tr>
			<th>Navn</th>
			<th>Mobil</th>
			<th>Betaling</th>
		</tr>
		
		<tr>
			<th>Navn</th>
			<th>Mobil</th>
			<th>Betaling</th>
		</tr>
		
		<c:forEach items="${deltagere}" var="d">
			<c:choose>
				<c:when test="${deltager.betalt eq true}">
					<tr>
						<td><c:out value="${d.navn}" /></td>
						<td><c:out value="${d.mobil}" /></td>
						<td>Betaling registrert</td>
					</tr>
				</c:when>
			</c:choose>
		</c:forEach>
	</table>
	
	<form method="get" action="loggut">
		<button type="submit">Logg ut</button>
	</form>
</body>
</html>
