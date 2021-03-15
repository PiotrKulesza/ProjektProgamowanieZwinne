package com.project.servlet;

import java.io.IOException;
import java.time.LocalDate;

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

@WebServlet("/ProjektEdytuj")
public class ProjektEdytuj extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ProjektEdytuj() {
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
		
		EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
		Projekt projekt = new Projekt();
		projekt.setProjektId(Integer.parseInt(request.getParameter("projektId")));
		projekt.setNazwa(request.getParameter("nazwa"));
		projekt.setOpis(request.getParameter("opis"));
		projekt.setDataOddania(LocalDate.parse(request.getParameter("dataOddania")));
		entityManager.getTransaction().begin();
		int zmianaTF = entityManager.createQuery("UPDATE Projekt p SET p.nazwa ='"
				+projekt.getNazwa()
				+"', p.opis='"
				+projekt.getOpis()
				+"', p.dataOddania='"
				+projekt.getDataOddania()
				+"' WHERE p.projektId='"+projekt.getProjektId()+"'")
				.executeUpdate();
		System.out.println(zmianaTF);
		entityManager.getTransaction().commit();
		entityManager.close();
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/projekt_lista.jsp");
		dispatcher.forward(request, response);
	}

}
