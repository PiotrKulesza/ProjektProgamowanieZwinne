package com.project.servlet;

import java.io.IOException;
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
import com.project.model.Zadanie;
import com.project.util.HibernateUtil;

@WebServlet("/ZadanieZmianaKolejnosci")
public class ZadanieZmianaKolejnosci extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ZadanieZmianaKolejnosci() {
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
		int page = 0;
		if(request.getParameter("page")!=null)
		page = Integer.parseInt(request.getParameter("page"));
		int amountOfItems = Integer.parseInt(request.getParameter("ilosc"));
		int projektId = Integer.parseInt(request.getParameter("projektId"));
		int zadanieId = Integer.parseInt(request.getParameter("zadanieId"));
		String szukajNazwaLubOpis = request.getParameter("szukajNazwaLubOpis");
		String kolejnosc = request.getParameter("kolejnosc");
		
		
		EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
		TypedQuery<Projekt> query = entityManager
				.createQuery("SELECT p FROM Projekt p WHERE p.projektId = "+projektId, Projekt.class);
		Projekt projekt = query.getSingleResult();
		projekt.setZadania(projekt.getZadania().stream().sorted((o1,o2) -> o1.getKolejnosc().compareTo(o2.getKolejnosc())).collect(Collectors.toList()));
		
		Zadanie zadanie1 = null; 
		Zadanie zadanie2 = null; 
		
		if(kolejnosc.equals("kolejnoscUp")) {
			
			Zadanie zadanieTMP = projekt.getZadania().stream().filter(z->z.getZadanieId()==zadanieId).findFirst().get();
			int zadanieIdUp = projekt.getZadania().stream().filter(z->z.getKolejnosc()==zadanieTMP.getKolejnosc()-1).findFirst().get().getZadanieId();
			zadanie1 = entityManager.find(Zadanie.class, zadanieId);
			entityManager.getTransaction().begin();
			zadanie1.setKolejnosc(zadanie1.getKolejnosc()-1);
			entityManager.getTransaction().commit();
			zadanie2 = entityManager.find(Zadanie.class, zadanieIdUp);
			entityManager.getTransaction().begin();
			zadanie2.setKolejnosc(zadanie2.getKolejnosc()+1);
			entityManager.getTransaction().commit();
		}else {
			Zadanie zadanieTMP = projekt.getZadania().stream().filter(z->z.getZadanieId()==zadanieId).findFirst().get();
			int zadanieIdDOWN = projekt.getZadania().stream().filter(z->z.getKolejnosc()==zadanieTMP.getKolejnosc()+1).findFirst().get().getZadanieId();
			zadanie1 = entityManager.find(Zadanie.class, zadanieId);
			entityManager.getTransaction().begin();
			zadanie1.setKolejnosc(zadanie1.getKolejnosc()+1);
			entityManager.getTransaction().commit();
			zadanie2 = entityManager.find(Zadanie.class, zadanieIdDOWN);
			entityManager.getTransaction().begin();
			zadanie2.setKolejnosc(zadanie2.getKolejnosc()-1);
			entityManager.getTransaction().commit();
		}
			
		entityManager.close();
		request.setAttribute("page", page);
    	request.setAttribute("amountOfItems", amountOfItems);
    	request.setAttribute("ilosc", amountOfItems);
    	request.setAttribute("projektId", projektId);
    	request.setAttribute("szukajNazwaLubOpis", szukajNazwaLubOpis);
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/ZadaniaPobierz"); 
		dispatcher.forward(request, response);
	}

}


