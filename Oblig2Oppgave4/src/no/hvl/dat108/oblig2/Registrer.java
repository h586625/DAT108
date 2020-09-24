package no.hvl.dat108.oblig2;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registrer")
public class Registrer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		String fornavn = request.getParameter("fornavn");
		String etternavn = request.getParameter("etternavn");

		String pattern = "dd MMMMM yyyy HH:mm";
		SimpleDateFormat simpleDateFormat =
				new SimpleDateFormat(pattern, new Locale("nb", "NO"));
		String dato = simpleDateFormat.format(new Date());

		PrintWriter out = response.getWriter();

		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title>Kvittering</title>");
		out.println("</head>");
		out.println("<body>");

		if (fornavn == null || etternavn == null || fornavn == "" || etternavn == "") {
			out.println("Hei! Du har ikke fylt ut registreringsskjemaet.");
		} else {
			out.println("Hei, " + fornavn + " " + etternavn);
			out.println("<p>Din registrering er mottatt " + dato + "</p>");
		}

		out.println("</body>");
		out.println("</html>");
	}

}
