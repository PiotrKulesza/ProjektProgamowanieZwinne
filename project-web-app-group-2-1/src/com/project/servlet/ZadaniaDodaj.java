package com.project.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.project.model.Zadanie;
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
		
		EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
			
		Zadanie zadanie = new Zadanie();
		zadanie.setDataczasDodania(LocalDateTime.now());
		zadanie.setNazwa(request.getParameter("nazwa"));
		zadanie.setOpis(request.getParameter("opis"));
			
		int page = 0;
   		if(request.getParameter("page")!=null)
   		page = Integer.parseInt(request.getParameter("page"));
   		int amountOfItems = Integer.parseInt(request.getParameter("ilosc"));
   		String szukajNazwaLubOpis = request.getParameter("szukajNazwaLubOpis");
   		int projektId = Integer.parseInt(request.getParameter("projektId"));
   		
   		

   		TypedQuery<Projekt> query = entityManager
				.createQuery("SELECT p FROM Projekt p WHERE p.projektId = "+projektId, Projekt.class);
		Projekt projekt = query.getSingleResult();
		if(projekt.getZadania()!=null && !projekt.getZadania().isEmpty()) 
			zadanie.setKolejnosc(projekt.getZadania().size()+1);
		else zadanie.setKolejnosc(1);
   		
		zadanie.setProjekt(projekt);
		
		entityManager.getTransaction().begin();
		entityManager.persist(zadanie);
		entityManager.getTransaction().commit();
		
		entityManager.close(); // zalecane umieszczenie metody close() w bloku finally
		
		request.setAttribute("page", page);
    	request.setAttribute("amountOfItems", amountOfItems);
    	request.setAttribute("ilosc", amountOfItems);
    	request.setAttribute("szukajNazwaLubOpis", szukajNazwaLubOpis);
    	request.setAttribute("projekt", projekt);
    	
    	ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/ZadaniaPobierz");
		dispatcher.forward(request, response);
	}
   
   
}




