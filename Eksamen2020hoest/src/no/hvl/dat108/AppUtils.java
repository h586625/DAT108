package no.hvl.dat108;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public final class AppUtils {

	public static final void invalidateSession(HttpServletRequest request)
	{
		HttpSession sesjon = request.getSession(false);

		if (sesjon != null) {
			sesjon.invalidate();
		}
	}

	public static final HttpSession newSession(HttpServletRequest request, int maxInactiveInterval)
	{
		invalidateSession(request);

		HttpSession sesjon = request.getSession(true);
		sesjon.setMaxInactiveInterval(maxInactiveInterval);

		return sesjon;
	}

	public static final Optional<String> getCookie(HttpServletRequest request, String name)
	{
		return Arrays.stream(request.getCookies())
				.filter(c -> c.getName().equals(name))
				.map(Cookie::getValue)
				.findAny();
	}

	public static final void addCookie(HttpServletResponse response, String name, String value)
	{
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(60*60*24*365); //sekunder
		response.addCookie(cookie);
	}

	public static final boolean isHttpParameterEmpty(String param)
	{
		if (param == null || param.trim().isEmpty()) {
			return true;
		}

		return false;
	}
}
