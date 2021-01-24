package oppg1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;

@WebServlet("/handleliste")
public class Handleliste extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Liste liste = new Liste();
	private Cookie cookie;

	public void htmlhead(String tittel, PrintWriter out) {
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("<title>" + tittel + "</title>");
		out.println("</head>");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesjon = request.getSession(false);

		if (sesjon == null) {
			response.sendRedirect("innlogging?requiresLogin");
		} else {
			response.setContentType("text/html; charset=ISO-8859-1");

			PrintWriter out = response.getWriter();
			htmlhead("Handleliste", out);
			out.println("<body>");
			out.println("<h2>Min handleliste</h2>");
			out.println("<form action=\"" + "handleliste" + "\" method=\"post\">");
			out.println("<input type=\"text\" name=\"vare\" />");
			out.println("<input type=\"submit\" value=\"Legg til\" />");
			out.println("<p />");
			out.println("</form>");

			for (String item : liste.getItems()) {
				out.println("<form action=\"" + "handleliste" + "\" method=\"post\">");
				out.println("<input type=\"hidden\" id=\"itemid\" name=\"varenavn\" value=" + item + ">");
				out.println(item);
				out.println("<input type=\"submit\" value=\"Slett\" />");
				out.println("</br></br>");
				out.println("</form>");
			}
			out.println("<img src=\"https://conceptdraw.com/a2240c3/p39/preview/640/pict--shopping/supermarket-transport-map-vector-stencils-library\">");
			out.println("</body>");
			out.println("</html>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// sjekker om det finnes cookie og legger til om ikke
		if (cookie == null) {
			cookie = new Cookie("innlogget", "");
			cookie.setMaxAge(12960000); // 3600 * 3600
			response.addCookie(cookie);
		}

		HttpSession sesjon = request.getSession(false);

		if (sesjon == null) {
			response.sendRedirect("innlogging?requiresLogin");
		} else {
			String vare = request.getParameter("vare");
			String varenavn = request.getParameter("varenavn");
			// hvis varenavn ikke eksisterer og det er gyldig
			if (varenavn == null && !(vare.isBlank() || liste.finnes(vare))) {
				vare = StringEscapeUtils.escapeHtml4(vare);
				liste.addItem(new String(vare));
				// varenavn eksisterer fra før av
			} else if (varenavn != null) {
				varenavn = StringEscapeUtils.escapeHtml4(varenavn);
				if (liste.finnes(varenavn)) {
					liste.deleteItem(varenavn);
				}
			}

			response.sendRedirect("handleliste");
		}
	}
}
