package no.hvl.dat108;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//web.xml?
//@WebServlet(name = "Servlet Boilerplate", urlPatterns = "/paamelding")
@WebServlet("/paamelding")
public class ServletBoilerplate extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	@EJB
//	private SomethingDAO somethingDAO;

//	private String kode;
//	@Override
//	public void init() throws ServletException {
//		kode = getServletConfig().getInitParameter("kode");
//	}



	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// ===== 1. REQUEST PARAMETERS and PARSING (if necessary) =====
		String somethingId = request.getParameter("something_id");
		int parsedId = Integer.parseInt(somethingId);

		// ===== 2. ERROR HANDLING (if necessary) =====
		// 1. with a redirect to another page?
		if (!Validator.isValidSomething(somethingId)) {
			// må skrives ut i JSP filen: ${aarsak==ugyldigRequest ? : }
			response.sendRedirect("feilmelding?aarsak=ugyldigRequest");
			return;
		}
		// 2. alternately with a forward instead of a redirect
		if (request.getParameter("feilmelding") != null) {
			request.setAttribute("feilmelding", "-");
		}

		// ===== 3. DAO (if necessary) =====
//		Something something = somethingDAO.findSomething(parsedId);
		// Set e.g. request or session attributes below, so we can fetch the
		// data dynamically in our JSP. E.g.:
//		request.setAttribute("something", something);
		// or
//      sesjon.setAttribute("something", something);

		// ===== 4. JSP =====
//		request.getRequestDispatcher("WEB-INF/jsp/<navn>.jsp").forward(request, response);

	} //doGet()



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// ===== 1. REQUEST PARAMETERS =====
		String somethingIdAsString = request.getParameter("something");
		int somethingAsId = Integer.parseInt(somethingIdAsString);

		// ===== 2. FIND? =====
//		Element element = elementDAO.find(somethingAsId);
//		if (element != null) {
//			Bean bean = new Bean(rating, tekst, anmelder, film);
//			element.add(bean);
//		}
		
		// ===== 3. CREATE, UPDATE etc.? =====
//		Bean bean = new Bean(rating, tekst, anmelder, film);

//		if (sjekk om objektet allerede eksisterer og ugyldig input osv.?) {
			// e.g. store something to the database
//			beanDAO.add(bean);

			// sessions?
//			HttpSession sesjon = AppUtils.newSession(request, 60);
//			sesjon.setAttribute("bean", bean);
			// Fjerner eventuelt objekt knyttet til sesjonen
//			request.getSession().removeAttribute("skjema");
			// PRG
//			response.sendRedirect("some-page");
			// no session?
//			response.sendRedirect("some-page?bean_id=somethingAsId");
//		} else {
//			response.sendRedirect("some-other-page");
//		}
	} //doPost()
}
