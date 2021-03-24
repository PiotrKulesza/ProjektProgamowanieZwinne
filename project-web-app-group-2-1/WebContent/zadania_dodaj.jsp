<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Proszę podać dane zadania</h1>

<form action="ZadaniaDodaj" method="POST" id="zadanieform">
	<input type="hidden" name="szukajNazwaLubOpis" value="<%= request.getParameter("szukajNazwaLubOpis") %>"/>
	<input type="hidden" name="page" value="<%= request.getParameter("page") %>"/> 
	<input type="hidden" name="ilosc" value="<%= request.getParameter("ilosc") %>"/> 
	<input type="hidden" name="projektId" value="<%= request.getParameter("projektId") %>"/> 
	<table>
		<tr>
		<th>Nazwa</th> <th><input type="text" name="nazwa" /> </th>
		</tr>
		<tr>
		<th>Opis</th> <th><textarea rows="4" cols="50" name="opis" form="zadanieform"> </textarea></th>
		</tr>
	</table>
	<input name="btn_zapisz" value="Zapisz" type="submit">
</form>

</body>
</html>