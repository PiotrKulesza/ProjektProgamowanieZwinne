<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<h1>Proszę podać dane projektu</h1>

	<form action="StudentDodaj" method="POST" id="projektform">
		<table>
			<tr>
			<th>Imię:</th> <th><input type="text" name="imie" /> </th>
			</tr>
			<tr>
			<th>Nazwisko:</th> <th><input type="text" name="nazwisko" /> </th>
			</tr>
			<tr>
			<th>Numer indeksu:</th> <th><input type="text" name="nrIndeksu" /> </th>
			</tr>
			<tr>
			<th>Email:</th> <th><input type="email" name="email" /> </th>
			</tr>
			<tr>
			<th>Typ studiów:</th><th> <select id="stacjonarny" name="stacjonarny">
				<option value="-1">Wybierz....</option>
			    <option value="1">Stacjonarne</option>
			    <option value="0">Niestacjonarne</option>
	  		</select></th>
			</tr>
			</table>
		<input name="btn_zapisz" value="Zapisz" type="submit">
	</form>


	ID dodanego studenta: ${student.studentId} <br/>
	Imię dodanego studenta: ${student.imie} <br/>
	Nazwisko dodanego studneta: ${student.nazwisko} <br/>
	Numer indeksu dodanego studneta: ${student.nrIndeksu} <br/>
	Email dodanego studneta: ${student.email} <br/>
	<c:if test="${student.stacjonarny==false }">
		Typ studiów dodanego studneta: Niestacjonarny <br/>
	</c:if>
	<c:if test="${student.stacjonarny==true }">
		Typ studiów dodanego studneta: Stacjonarny <br/>
	</c:if>

</body>
</html>