package no.hvl.dat108;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Admin LoggInn", urlPatterns = "/adminlogginn")
public class AdminLogginnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private DeltagerDAO deltagerDAO;

	private String adminpassord;
	@Override
	public void init() throws ServletException {
		adminpassord = getServletConfig().getInitParameter("adminpassord");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("uautorisert") != null) {
			request.setAttribute("feilmelding", "-");
		} else if (request.getParameter("feilmelding") != null) {
			request.setAttribute("uautorisert", "-");
		}

		if (isInnloggetSomAdmin(request)) {
			response.sendRedirect("betaling");
		} else {
			request.getRequestDispatcher("WEB-INF/jsp/adminlogginn.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String passord = request.getParameter("adminpassord");

		Deltager deltager = deltagerDAO.hentDeltager(passord);

		if (deltager != null) {
			String salt = deltager.getPassord().getPwd_salt();
			String passordhash = deltager.getPassord().getPwd_hash();
			boolean valid = Passord.validerMedSalt(passord, salt, passordhash);

			if (valid) {
				adminLogginn(request); //Logger bruker i denne sesjonen inn som admin
				response.sendRedirect("deltagerliste");
			} else {
				response.sendRedirect("adminlogginn?feilmelding");
			}
		} else {
			response.sendRedirect("adminlogginn?feilmelding");
		}

	}

}
