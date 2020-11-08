package no.hvl.dat108;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/innlogging")
public class InnloggingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DeltagerDAOMemory deltagerDAO;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("feilmelding") != null) {
			request.setAttribute("feilmelding", "-");
		} else if (request.getParameter("unauthorized") != null) {
			request.setAttribute("unauthorized", "-");
		} else if (request.getParameter("finnesAllerede") != null) {
			request.setAttribute("finnesAllerede", "-");
		}

		request.getRequestDispatcher("WEB-INF/jsp/logginn.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mobil = request.getParameter("mobil");
		String passord = request.getParameter("passord");

		Deltager deltager = deltagerDAO.hentDeltager(mobil);

		if (deltager != null) {
			String salt = deltager.getPassord().getPwd_salt();
			String passordhash = deltager.getPassord().getPwd_hash();
			boolean valid = Passord.validerMedSalt(passord, salt, passordhash);

			if (valid) {
				HttpSession sesjon = AppUtils.newSession(request, 900);
				sesjon.setAttribute("deltager", deltager);
				response.sendRedirect("deltagerliste");
			} else {
				response.sendRedirect("innlogging?feilmelding");
			}
		} else {
			response.sendRedirect("innlogging?feilmelding");
		}

	}

}
