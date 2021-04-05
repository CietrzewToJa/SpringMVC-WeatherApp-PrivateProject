<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Wybierz miejscowość</title>
</head>
<body>
	<h1 align="center">Witaj w aplikacji pogodowej!</h1>
	<hr>
	<br/>
	
	<div align="center">
		<form:form action="show-weather" method="get" modelAttribute="requestInfo">
		
			<label>Wybierz miasto: </label>
			<form:select path="cityName">
				<form:option value="Zakopane" label="Zakopane"></form:option>
				<form:option value="Kraków" label="Kraków"></form:option>
				<form:option value="Warszawa" label="Warszawa"></form:option>
				<form:option value="Gdańsk" label="Gdańsk"></form:option>			
			</form:select>
			
			<input type="submit" value="Pokaż pogodę!">
			
		</form:form>	
	</div>
	
</body>
</html>