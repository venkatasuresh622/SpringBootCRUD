<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css2.css">
<title>Add Product</title>
</head>
<body>
<h2>Add Product</h2>
<form action="addProduct" method="post">
<table>
	<tr>
		<td>Product Id:</td>
		<td><input type="text" name="productId" /></td>
	</tr>
	<tr>
		<td>Product Name</td>
		<td><input type="text" name="productName"/></td>
	</tr>
		<tr>
		<td>Price:</td>
		<td><input type="text" name="price"/></td>
	</tr>
	</tr>
		<tr>
		<td>&nbsp;</td>
		<td><input type="submit"/></td>
	</tr>
</table>
</form>
</body>
</html>