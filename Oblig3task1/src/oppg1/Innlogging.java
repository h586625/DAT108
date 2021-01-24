package oppg1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Innlogging", urlPatterns = "/innlogging")
public class Innlogging extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String correctPassword;
	int intervall;

	@Override
	public void init() {
		correctPassword = getServletConfig().getInitParameter("password");
		intervall = Integer.parseInt(getServletContext().getInitParameter("sek"));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean requiresLoginRedirect = request.getParameter("requiresLogin") != null;
		boolean invalidPasswordRedirect = request.getParameter("invalidPassword") != null;

		response.setContentType("text/html; charset=ISO-8859-1");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>Login</title>");
		out.println("</head>");
		out.println("<body>");

		if (invalidPasswordRedirect) {
			out.println("    <p>Feil passord. Vennligst pr�v igjen.</p>");
		} else if (requiresLoginRedirect) {
			out.println("    <p>Du har v�rt inaktiv for lenge. Vennligst logg inn igjen.</p>");
		} else {
			out.println("    <p>Skriv inn passord: </p>");
		}

		out.println("<form action=\"" + "innlogging" + "\" method=\"post\">");
		out.println("    <p><input type=\"password\" name=\"password\" /></p>");
		out.println("    <p><input type=\"submit\" value=\"Logg inn\" /></p>");
		out.println("</form>");

		out.println("</body>");
		out.println("</html>");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String p = request.getParameter("password");
		if (p == null || !p.equals(correctPassword)) {
			response.sendRedirect("innlogging?invalidPassword");
		} else {
			nySesjon(request);
			response.sendRedirect("handleliste");
		}
	}

	public void nySesjon(HttpServletRequest request) {
		// F�rste vi gj�r er � gi ny ID.
		// Henter den han har (om han har) ved � skrive false
		HttpSession sesjon = request.getSession(false);

		if (sesjon != null) {
			sesjon.invalidate(); // Gj�re gammel sesjon ugyldig
		}
		sesjon = request.getSession(true); // Generere ny
		sesjon.setMaxInactiveInterval(intervall); // Sekunder
	}
}
