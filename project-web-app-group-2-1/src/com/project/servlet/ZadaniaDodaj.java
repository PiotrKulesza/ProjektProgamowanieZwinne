package com.project.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
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

@WebServlet("/ZadaniaDodaj")
public class ZadaniaDodaj extends HttpServlet {

	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
    * @see HttpServlet#HttpServlet()
    */
   public ZadaniaDodaj() {
       super();
       // TODO Auto-generated constructor stub
   }
   
   
   /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
   	doPost(request, response);
	}

   /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//processRequest(request, response);
		if(request.getParameter("btn_zapisz") != null) {
			EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
			
			
			Student student = new Student();
			student.setEmail(request.getParameter("email"));
			student.setImie(request.getParameter("imie"));
			student.setNazwisko(request.getParameter("nazwisko"));
			student.setNrIndeksu(request.getParameter("nrIndeksu"));
			student.setStacjonarny(Integer.parseInt(request.getParameter("stacjonarny"))==1);

			entityManager.getTransaction().begin();
			entityManager.persist(student);
			entityManager.getTransaction().commit();
			entityManager.close(); // zalecane umieszczenie metody close() w bloku finally
			request.setAttribute("student", student);
		}
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/dodaj_student.jsp"); 
		dispatcher.forward(request, response);
	}
   
   
}




