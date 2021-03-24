package com.project.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.model.Projekt;
import com.project.model.Student;
import com.project.util.HibernateUtil;

@WebServlet("/StudenciPobierz")
public class StudenciPobierz extends HttpServlet{
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
    * @see HttpServlet#HttpServlet()
    */
   public StudenciPobierz() {
       super();
       // TODO Auto-generated constructor stub
   }

   /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
	   
	   int page = 0;
		if(request.getParameter("page")!=null)
		page = Integer.parseInt(request.getParameter("page"));
		int amountOfItems = Integer.parseInt(request.getParameter("ilosc"));
		int projektId = Integer.parseInt(request.getParameter("projektId"));
		String szukajNazwaLubOpis = request.getParameter("szukajNazwaLubOpis");
		
		EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
		TypedQuery<Projekt> query = entityManager
				.createQuery("SELECT p FROM Projekt p WHERE p.projektId = "+projektId, Projekt.class);
		Projekt projekt = query.getSingleResult();
		projekt.setZadania(projekt.getZadania().stream().sorted((o1,o2) -> o1.getKolejnosc().compareTo(o2.getKolejnosc())).collect(Collectors.toList()));
		List<Student> students = entityManager.createQuery("Select s from Student s").getResultList();
		List<Student> listaDodanychStudentow = new ArrayList<>(projekt.getStudenci());
		
		for (Student student:listaDodanychStudentow ) {
			students.remove(student);
		}
		System.out.println(students.size());
		if (projekt!= null) {
			request.setAttribute("page", page);
	    	request.setAttribute("amountOfItems", amountOfItems);
	    	request.setAttribute("ilosc", amountOfItems);
	    	request.setAttribute("szukajNazwaLubOpis", szukajNazwaLubOpis);
	    	request.setAttribute("projektId",projektId);
	    	request.setAttribute("students", students);
	    	request.setAttribute("listaDodanychStudentow", listaDodanychStudentow);
	    	
	    	ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/student_dodaj_projekt.jsp");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("page", page);
	    	request.setAttribute("amountOfItems", amountOfItems);
	    	request.setAttribute("ilosc", amountOfItems);
	    	request.setAttribute("szukajNazwaLubOpis", szukajNazwaLubOpis);
	    	request.setAttribute("btn_szukaj", "btn_szukaj");
	    	ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/ProjektPobierz");
			dispatcher.forward(request, response);
		}
   	
	}

   /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//processRequest(request, response);
	}

}