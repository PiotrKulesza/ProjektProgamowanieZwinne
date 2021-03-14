package com.project.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.project.util.HibernateUtil;

@WebServlet("/ProjektPobierz")
public class ProjektPobierz extends HttpServlet{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ProjektPobierz() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
    	if(request.getParameter("btn_szukaj") != null) {
    		int page = 0;
    		if(request.getParameter("page")!=null)
    		page = Integer.parseInt(request.getParameter("page"));
    		int amountOfItems = Integer.parseInt(request.getParameter("ilosc"));
    		
    		String szukajNazwaLubOpis = request.getParameter("szukajNazwaLubOpis");
    		
    		
	    	EntityManager entityManager = HibernateUtil.getInstance().createEntityManager();
	    	TypedQuery<Projekt> query = entityManager
	    			.createQuery("SELECT p FROM Projekt p", Projekt.class);
	    	List<Projekt> projekty = new ArrayList<>();
	    	projekty = query.getResultList();
	    	List<Projekt> elementsToSend = projekty
	    			.stream()
	    			.filter(x -> x.getNazwa().contains(szukajNazwaLubOpis) || x.getOpis().contains(szukajNazwaLubOpis))
	    			.skip(amountOfItems*page)
	    			.limit(amountOfItems)
	    			.collect(Collectors.toList());
	    	
	    	request.setAttribute("projekty", elementsToSend);
	    	if(page>0)request.setAttribute("previousPage", page-1);
	    	else request.setAttribute("previousPage", 0);
	    	request.setAttribute("nextPage", page+1);
	    	request.setAttribute("page", page);
	    	request.setAttribute("amountOfItems", amountOfItems);
    	}
    	ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/projekt_lista.jsp");
		dispatcher.forward(request, response);
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
