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
import com.project.util.HibernateUtil;

@WebServlet("/ProjektUsun")
public class ProjektUsun extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ProjektUsun() {
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
		
		int page = 0;
		if(request.getParameter("page")!=null)
		page = Integer.parseInt(request.getParameter("page"));
		int amountOfItems = Integer.parseInt(request.getParameter("ilosc"));
		int projektId = Integer.parseInt(request.getParameter("projektId"));
		String szukajNazwaLubOpis = request.getParameter("szukajNazwaLubOpis");
		
		
		EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
		Projekt projekt = entityManager.find(Projekt.class, projektId);
			entityManager.getTransaction().begin();
			entityManager.remove(projekt);
			entityManager.getTransaction().commit();
		
		
		request.setAttribute("page", page);
    	request.setAttribute("amountOfItems", amountOfItems);
    	request.setAttribute("ilosc", amountOfItems);
    	request.setAttribute("szukajNazwaLubOpis", szukajNazwaLubOpis);
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/ProjektPobierz"); 
		dispatcher.forward(request, response);
		
		
	}

}
