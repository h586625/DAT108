package Eksamen2020vår;

public class Oppg4Webapplikasjoner2 {
	/*I denne oppgaven skal du jobbe med og lage deler av en liten webapplikasjon for filmanmeldelser. De
	aktuelle URL-er / sider vi skal se på er:
	
	• filmanmeldelser viser alle anmeldelser for en film (gitt ved parameteren film_id), se fig 4.1
	• feilmelding viser en feilmeldingsside knyttet til en gitt aarsak. Et eksempel på en slik årsak
	kan være om man prøver å nå en side med manglende eller ugyldig film_id, se fig 4.2.*/
	
/*a) (10% ~ 24 min) Skriv doGet() i servleten for filmanmeldelser. Som du ser av URL-en i fig. 4.1 er
	det en request-parameter "film_id" som angir hvilken film det gjelder. Ved manglende eller
	ugyldig film_id skal det omdirigeres til feilmelding. Ellers skal siden vises som i fig. 4.1.*/
	
	/*Merknad: Løsningen her er store trekk identisk med løsningen på 4a) på ordinær
	eksamen (som de har tilgjengelig). Men ...
	 # Det er ikke oppgitt i oppgaveteksten at vi har en Validator. Kan vi anta
	at en slik finnes?
	 # Det er behov for å regne ut snittrating basert på anmeldelsene, både som
	heltall (antall stjerner) og med en desimal. Kanskje det mest naturlige
	er å legge dette i Film. Hvilken løsning som er valgt / antatt må forklares.
	 # Forward skal gjøres til .../filmanmeldelser.jsp*/
	
	//Løsning 4a):
	
	 /*# Vi antar at vi har en filmEAO som vi kan bruke til å hente en film fra
	databasen.
	 # Vi antar også at vi har fetchType.EAGER på listen av anmeldelser, slik at
	anmeldelser blir hentet sammen med film-objektet.
	 # Vi antar at film-objektet har metoder for å hente ut gjennomsnittsrating.
	Disse kan da hentes ut i JSP-en direkte.*/
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {
		//1p
		String filmIdAsString = request.getParameter("film_id");
		//1p
		if (!filmIdAsString.matches("\\d+")) { // evt. løsning uten regex
	 response.sendRedirect("feilmelding?aarsak=ugyldigRequest");
	 } else {
		 //1p 
		 int filmId = Integer.parseInt(filmIdAsString);
		 //2p 
		 Film film = filmEAO.finnFilm(filmId);
		 //1p 
		 if (film == null) {
			 response.sendRedirect("feilmelding?aarsak=ugyldigRequest");
		 } else {
			 //2p 
			 request.setAttribute("film", film);
			 //2p 
			 request.getRequestDispatcher("WEB-INF/filmanmeldelser.jsp")
			 .forward(request, response);
	 }
	 }
	}
/*b) (10% ~ 24 min) Skriv jsp-en for filmanmeldelser -siden (fig. 4.1). Du trenger ikke tenke på JSPdirektiver. Du kan anta at bilder ligger lagret i mappen "bilder" i applikasjonen. Bildene som angir
	stjerner for rating heter "ratingX.png", f.eks. <img src="bilder/rating3.png"> for bildet
	(3 stjerner).*/
	
//Løsning 4b):
	
	/* # Vi antar at film-objektet har metoder for å hente ut gjennomsnittsrating:
	int getAvrundetSnittrating()
	double (evt. String) getSnittratingMedEnDesimal()
	Disse kan da hentes ut i JSP-en direkte.*/
	
	<!DOCTYPE html>
	<html>
	<head>
	 ...
	 	<title>Filmanmeldelser</title>
	</head>
	<body>
	 	<p><a href="filmliste">Tilbake til filmlisten</a>
	 	<table><tr>
	 		//1p 
	 		<td><img src="bilder/${film.bildefil}" height="100"></td>
	 		<td style=""><b>${film.tittel}</b><br/><br/>
	 			//1p 
				<img src="bilder/rating${film.avrundetSnittrating}.png" />
			//1p 
				(${film.snittratingMedEnDesimal})</td>
			</tr></table>
			//1p 
			<p><a href="anmelde?film_id=${film.id}">Anmeld filmen</a></p>
			<p><b>Anmeldelser (nyeste øverst):</b></p>
			//2p 
			<c:forEach var="anmeldelse" items="${film.anmeldelser}">
				//1p 
				<p<img src="bilder/rating${anmeldelse.rating}.png" />
					//1p 
					av <c:out value="${anmeldelse.anmelder}" /></p>
				//2p 
				<blockquote><i>"<c:out value="${anmeldelse.tekst}" />"</i></blockquote>
	</c:forEach>
	</body>
	</html>
}
