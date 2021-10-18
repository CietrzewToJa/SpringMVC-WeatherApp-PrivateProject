<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="URLToReachResourcesFolder/css/my-style-sheet.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Anton&display=swap"
	rel="stylesheet">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<title>Check the weather</title>
</head>
<body>


	<h1>check the weather</h1>

	<div class="container-fluid">
		<form:form action="show-weather" method="POST" modelAttribute="weather"
			class="row justify-content-center">

			<div class="col-6 col-md-3 text-end search-field">
				<form:input path="city" class="form-control" placeholder="Enter city here"></form:input>
			</div>
			<div class="col-auto go-field">
				<button type="submit" class="btn btn-success play-button right-field">
					<i class="material-icons d-flex justify-content-center">play_arrow</i>
				</button>
			</div>

		</form:form>
	</div>

</body>
</html>