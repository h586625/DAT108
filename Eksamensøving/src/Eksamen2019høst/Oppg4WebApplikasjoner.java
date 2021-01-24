package Eksamen2019høst;

public class Oppg4WebApplikasjoner {
	/*I denne oppgaven skal du jobbe med og lage deler av en liten webapplikasjon for filmanmeldelser. Et
	par aktuelle URL-er / sider:
	
	 filmliste ... (ikke relevant for oppgavene)
	 filmanmeldelser viser alle anmeldelser for en film (gitt ved parameteren film_id), se fig 4.1
	 anmelde viser skjema for anmeldelse av en film (gitt ved parameteren film_id), se fig 4.2.
	Ved trykk på knappen "Send anmeldelse" blir anmeldelsen postet til den samme URL-en og
	lagret i databasen. Deretter omdirigeres man igjen til filmanmeldelser for filmen.
	 feilmelding viser en feilmeldingsside knyttet til en gitt aarsak. Et eksempel på en slik årsak
	kan være om man prøver seg på en av de andre URL-ene / sidene med manglende eller
	ugyldig film_id, se fig 4.3.*/
}

/*a) 	(10% ~ 24 min) Skriv doGet() i servleten for anmelde. Som du ser av URL-en i fig. 4.2 er det en
		request-parameter "film_id" som angir hvilken film det gjelder. Ved manglende eller ugyldig
		film_id skal det omdirigeres til feilmelding. Ellers skal siden vises som i fig. 4.2. Du kan anta at du
		har en hjelpemetode isGyldigFilmId(String s) som sjekker om en streng er en gyldig film_id (dvs.
		et heltall).*/

//Løsning: (maks 10 poeng)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	//1p 
	String filmIdAsString = request.getParameter("film_id");
	
	//1p 
	if (!Validator.isGyldigFilmId(filmIdAsString)) {
	response.sendRedirect("feilmelding?aarsak=ugyldigRequest");
	return;
	}
	/*Litt av poenget med MVC er at Controlleren (dvs. denne metoden) henter
	frem og gjør klar data for visning i Viewet (JSP-en). I vårt tilfelle
	er det filmens bilde og tittel som er relevant, men generelt kan jo hele
	film-objektet legges klart til visning. Koden i klammen er derfor sentral!*/
	//1p
	int filmId = Integer.parseInt(filmIdAsString);
	//2p
	Film film = filmEao.finnFilm(filmId);
	
	//1p 
	if (film == null) { //Film finnes ikke i db: Ugyldig request ...
		response.sendRedirect("feilmelding?aarsak=ugyldigRequest");
		return;
	}
	//2p
	request.setAttribute("film", film);
	
	//2p
	request.getRequestDispatcher(
			"WEB-INF/anmelde.jsp").forward(request, response);
	}
	
	
	/*b) 	(10% ~ 24 min) Skriv jsp-en for anmelde-siden (fig. 4.2). Du trenger ikke tenke på JSP-direktiver.
			Du kan anta at bilder ligger lagret i mappen "bilder" i applikasjonen. Bildene som angir stjerner
			for rating heter "ratingX.png", f.eks. <img src="bilder/rating3.png"> for bildet (3
			stjerner). For full score skal det brukes en løkke for å sette opp de 5 rating-alternativene. Rating
			"3 stjerner" skal være checked.*/

//Løsning: (maks 10 poeng)
	<!DOCTYPE html>
	<html>
	...
	<body>
	 	<p><a href="filmanmeldelser?film_id=${film.id}">Tilbake til anmeldelser</a></p>
	 	//1p
	 	<p><img src="bilder/${film.bildefil}" height="100">${film.tittel}</p>
	 //..., men kun hvis "film" er lagret som attributt i doGet()!
	 	<p>Din anmeldelse:</p>
	 	//2p 
	 	<form action="anmelde" method="post">
			//2p
			<input type="hidden" name="film_id" value="${film.id}" />
		//NB! Vi MÅ sende med film.id! Ellers vet vi ikke hvilken film som anmeldes!
			Rating<br/>
	 		//1p
	 		<c:forEach var="i" begin="1" end="5">
	 			//1p
	 			<input type="radio" name="rating"
	 					value="${6-i}" ${i eq 3 ? "checked" : ""}>
	 			<img src="bilder/rating${6-i}.png"><br/>
	 		</c:forEach>
	 		<p>Tekst<br/>
	 		//1p
	 		<textarea name="tekst" rows="5" cols="40"></textarea></p>
	 		//1p
	 		<p>Hilsen <input type="text" name="anmelder" /></p>
	 		//<input type="hidden" name="film_id" value="${film.film_id}">
	 		//1p
	 		<p><input type="submit" value="Send anmeldelse"></p>
	 	</form>
	</body>
	</html>
	
/*c) (10% ~ 24 min) Skriv doPost() i servleten for anmelde. Du kan anta at forespørselen kommer fra
	skjemaet, dvs. du trenger ikke å gjøre håndteringen robust ut over det. Hvis anmelderfeltet er
	tomt, skal anmeldelsen lagres med anmelder "Anonym". Etter anmeldelsen er lagret i databasen
	skal man omdirigeres til filmanmeldelsene for aktuell film.
	
	
	Løsning: (maks 10 poeng)*/
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {

	 //1p
	 String filmIdAsString = request.getParameter("film_id");
	 //1p
	 int filmId = Integer.parseInt(filmIdAsString);
	 //1p
	 String ratingAsString = request.getParameter("rating");
	 int rating = Integer.parseInt(ratingAsString);
	 //1p
	 String tekst = request.getParameter("tekst");

	 //1p
	 String anmelder = request.getParameter("anmelder");
	 /*Merknad her: Mange studenter tester på (anmelder == null). null er ikke
	 mulig hvis forespørselen kommer fra skjemaet. Et tomt anmelder-felt vil
	 medføre at anmelder er "". Man kan (bør) seff teste på null først for
	 å gjøre programmet mer robust, men ...*/
	 //1p
	 if (anmelder.trim().isEmpty()) {
	  anmelder = "Anonym";
	 }

	 //2p
	 filmEao.anmeldFilm(filmId, rating, tekst, anmelder);

	 /*Merknad her: En del studenter gjør i tillegg:
	 Film film = filmEao.finnFilm(filmId);
	 Anmeldelse anmeldelse = new Anmeldelse(rating, tekst, anmelder, film);
	 film.leggTilAnmeldelse(anmeldelse);
	 Tanken var at dette gjøres av filmEao.anmeldFilm(..), men jeg forstår
	 misforståelsen. Og: Anmeldelsen må f.eks. lagres i databasen for å få
	 primærnøkkel etc., så det kan uansett ikke gjøres slik som vist i
	 merknaden!*/
	 //2p
	 response.sendRedirect("filmanmeldelser?film_id=" + filmId);
	 }
	 
	 
/*d) (10% ~ 24 min) Vi ønsker å ha mulighet til forhåndsutfylling av anmelder-feltet hvis noen har
	 anmeldt en film fra samme nettleser tidligere. Til dette kan man bruke informasjonskapsler
	 (cookies). Til hjelp i løsningen skal du bruke hjelpeklassen CookieHjelper. Vis med frittstående
	 kodesnutter (dvs. ikke bland sammen med tidligere løsning) hvilken kode som må legges til i
	 AnmeldeServlet.doGet(), AnmeldeServlet.doPost() og anmelde.jsp for å få denne
	 tilleggsfunksjonaliteten.
	 Løsning: (maks 10 poeng)*/
	 
	 
	  //i doPost(...):
	  ...
	  String anmelder = ...
	  if (anmelder.trim().isEmpty()) {
	  ...
	  } else {
	  CookieHjelper cookieHjelper = new CookieHjelper();
	  //3p
	  cookieHjelper.addCookieToResponse(response, "anmelder", anmelder);
	  }
	  ...
	  i doGet(...):
	  ...
	  CookieHjelper cookieHjelper = new CookieHjelper();
	  //2p
	  String anmelder = cookieHjelper.getCookieFromRequest(request, "anmelder");
	  //2p
	  request.setAttribute("anmelder", anmelder);
	  ...
	  /*Merknad: Man kan få tak i cookie direkte i JSP-en med ${cookie.anmelder}.
	  Da slipper man å gjøre disse tingene i doGet().
	  Men: Da vil ikke cookien bli URL-dekodet. Siden cookie-verdien
	  er noe brukeren har tastet inn (kan f.eks. inneholde æøå), må
	  cookien URL-enkodes ...!*/
	  
	  //i JSP:
	  <p>Hilsen <input type="text" name="anmelder"
	  //3p
	  value="<c:out value='${anmelder}' />" />
	  /*Merknad: ${anmelder} refererer til attributten satt i doGet(). Vi må
	  også escape denne med <c:out> fordi det er en verdi gitt av
	  bruker.*/
	
	  
}
