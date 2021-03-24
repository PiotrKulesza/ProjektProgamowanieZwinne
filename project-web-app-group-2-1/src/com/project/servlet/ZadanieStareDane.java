package com.project.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.model.Zadanie;
import com.project.util.HibernateUtil;

@WebServlet("/ZadanieStareDane")
public class ZadanieStareDane  extends HttpServlet {

	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
    * @see HttpServlet#HttpServlet()
    */
   public ZadanieStareDane() {
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
			

			
		int page = 0;
   		if(request.getParameter("page")!=null)
   		page = Integer.parseInt(request.getParameter("page"));
   		int amountOfItems = Integer.parseInt(request.getParameter("ilosc"));
   		String szukajNazwaLubOpis = request.getParameter("szukajNazwaLubOpis");
   		int projektId = Integer.parseInt(request.getParameter("projektId"));
   		int zadanieId = Integer.parseInt(request.getParameter("zadanieId"));
   		
   		
   		Zadanie zadanie = entityManager.find(Zadanie.class, zadanieId);
   		
		
		request.setAttribute("page", page);
    	request.setAttribute("amountOfItems", amountOfItems);
    	request.setAttribute("ilosc", amountOfItems);
    	request.setAttribute("szukajNazwaLubOpis", szukajNazwaLubOpis);
    	request.setAttribute("projektId", projektId);
    	request.setAttribute("zadanie", zadanie);
    	
    	ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/zadanie_edytuj.jsp");
		dispatcher.forward(request, response);
	}
   
   
}
