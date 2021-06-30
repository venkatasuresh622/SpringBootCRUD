<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.zensar.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css2.css">
<title>Update Product</title>
</head>
<body>
<h2>Update Product</h2>
<form action="updateProduct" method="post">
<% Product product = (Product)request.getAttribute("product"); %>
<table>
	<tr>
		<td>Product Id:</td>
		<td><input type="text" name="productId" value="<%=product.getProductId() %>" /></td>
	</tr>
	<tr>
		<td>Product Name</td>
		<td><input type="text" name="productName" value="<%=product.getProductName() %>"/></td>
	</tr>
		<tr>
		<td>Price:</td>
		<td><input type="text" name="price" value="<%=product.getPrice() %>"/></td>
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