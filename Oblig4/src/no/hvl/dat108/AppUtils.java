package no.hvl.dat108;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class AppUtils {

	public static final void invalidateSession(HttpServletRequest request) {

		HttpSession sesjon = request.getSession(false);

		if (sesjon != null) {
			sesjon.invalidate();
		}
	}

	public static final HttpSession newSession(HttpServletRequest request, int maxInactiveInterval) {

		invalidateSession(request);

		HttpSession sesjon = request.getSession(true);
		sesjon.setMaxInactiveInterval(maxInactiveInterval);

		return sesjon;
	}
}
