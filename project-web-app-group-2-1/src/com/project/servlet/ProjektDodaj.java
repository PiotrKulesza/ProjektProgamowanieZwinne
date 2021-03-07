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

@WebServlet("/ProjektDodaj")
public class ProjektDodaj extends HttpServlet{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ProjektDodaj() {
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
			Projekt projekt = new Projekt();
			projekt.setNazwa(request.getParameter("nazwa"));
			projekt.setOpis(request.getParameter("opis"));
			projekt.setDataczasUtworzenia(LocalDateTime.now());
			projekt.setDataOddania(LocalDate.parse(request.getParameter("dataOddania")));

			entityManager.getTransaction().begin();
			entityManager.persist(projekt);
			entityManager.getTransaction().commit();
			entityManager.close(); // zalecane umieszczenie metody close() w bloku finally
			request.setAttribute("projekt", projekt);
		}
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/projekt_dodaj.jsp"); 
		dispatcher.forward(request, response);
	}

}
