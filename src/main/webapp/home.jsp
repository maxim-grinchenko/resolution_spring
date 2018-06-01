<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>
</head>
<body>
	<div align="center">
		<h1>User List</h1>
		
		<table border="1">

			<th>Name</th>
			<th>Last Name</th>
			<th>Address</th>
			<th>Phone</th>

			<c:forEach var="user" items="${listUser}">
				<tr>

					<td>${user.name}</td>
					<td>${user.lastName}</td>
					<td>${user.email}</td>
					<td>${user.phone}</td>
					<td><a href="editUser?id=${user.id}">Edit</a>
						<a href="deleteUser?id=${user.id}">Delete</a></td>

				</tr>
			</c:forEach>
		</table>
		<p>
			New User Register <a href="newUser">register</a>
		</p>
	</div>
</body>
</html>