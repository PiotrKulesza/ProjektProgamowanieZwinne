<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.project.model.Projekt"%>
<%@page import="com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
</head>

<body>
	<h1>Lista projektów</h1>
	<a href="/project-web-app-group-2-1/projekt_dodaj.jsp">Dodaj projekt</a>
	<form action="ProjektPobierz" method="GET">
		<input type="text" name="szukajNazwaLubOpis" /> <input name="btn_szukaj" value="Szukaj" type="submit"> 
	</form>
	<form>
		Rozmiar strony: <select id="ilosc" name="ilosc">
		    <option value="5">5</option>
		    <option value="6">6</option>
		    <option value="7">7</option>
		    <option value="8">8</option>
		    <option value="9">9</option>
		    <option value="10">10</option>
  		</select>
	</form>
	<%
		int pageNumber = 0;
		if(request.getParameter("page")!=null)
			pageNumber=Integer.parseInt(request.getParameter("page"));
		
	%>
	<%if(pageNumber>0){%>><a href="/project-web-app-group-2-1/projekt_lista.jsp?page=<%=pageNumber-1%>">Poprzednia strona</a>
	<%} %>
	<a href="/project-web-app-group-2-1/projekt_lista.jsp?page=<%=pageNumber+1%>">Następna strona</a>
	<table>
		<tr>
			<th>LP.</th>
			<th>ID</th>
			<th>Nazwa</th>
			<th>Opis</th>
			<th>Data utworzenia</th>
			<th>Data oddania</th>
		</tr>
	
	<% 
		ArrayList<Projekt> projekty = new ArrayList<Projekt>();
		projekty=(ArrayList<Projekt>) request.getAttribute("projekty");
		if(projekty!=null){
		for(int i=0+(5*pageNumber);i<5*(pageNumber+1);i++) {	
	%>	
		
		<tr>
			<th><%=i+1%></th>
			<td><%=projekty.get(i).getProjektId()%></td>
			<td><%=projekty.get(i).getNazwa()%></td>
			<td><%=projekty.get(i).getOpis()%></td>
			<td><%=projekty.get(i).getDataczasUtworzenia()%></td>
			<td><%=projekty.get(i).getDataOddania()%></td>
		</tr>
		
	<%}}else{ %>
	<tr>
			<th scope=6>Dane są puste</th>
			
	</tr>
	<%} %>
	</table>
</body>
</html>