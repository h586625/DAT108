package Eksamen2019vår;

public class Oppg5WebApplikasjoner2 {
	//a) Skriv JSP-en (viewet) som genererer Skjermbilde1 og Skjermbilde2, altså Stemmeskjema.jsp. Det
		//er ikke nødvendig å ta med JSP-direktiv-setninger. JSTL-tips: Du kan iterere i et map som i en
		//liste, der du får ${e.key} og ${e.value}
	
	//Løsning:
		<!DOCTYPE html>
		<html>
		<body>
		<h1>${gjeldendeQuiz.tittel}</h1>
		<p>Velg et av alternativene i listen</p>
		<form method="post">
		 <fieldset>
		 <legend>Ditt svar</legend>
		 <p>
		 <c:forEach var="alternativ"
		 items="${gjeldendeQuiz.svarAlternativer}">
		 <input type="radio" name="svaralternativ"
		 value="${alternativ.key}" />${alternativ.key}<br/>
		 </c:forEach>
		 </p>
		 <p><font color="red">${feilmelding}</font></p>
		 <p><input type="submit" value="Send svar" /></p>
		 </fieldset>
		</form>
		</body>
		</html>
		/*PS! Man burde kanskje også sendt med ${quiz.id} som en hidden-parameter
		slik at doPost vet hvilken quiz svaret er ment for, f.eks. hvis læreren
		starter en ny quiz uten at studenten har refreshet skjemaet. Det sto
		ikke noe om dette i oppgaveteksten, så det kan regnes som utenfor
		oppgaven.*/
		
		
	//b) Skriv doPost i controller-klassen for /stemme, dvs. StemmeController.java. Det er ikke
		//nødvendig å ta med package- og import-setninger. Husk å få med både normal- og
		//alternativscenarier.
		
	//Løsning:
		 @Override protected void doPost(
			 HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
			 Quiz quiz = (Quiz)
			 getServletContext().getAttribute("gjeldendeQuiz");

			 // Sjekker aller først om det er en aktiv quiz. Hvis ikke: redirect!
			 if (quiz == null) {
			 response.sendRedirect("stemme?ingenAktivQuiz");
			 return;
			 }
			 // PS! Ikke med i oppgavetekst (som i a)) => Ikke trekk.
			 // Sjekker så om studenten svarer på aktiv quiz (og ikke en gammel en).
			 String stemtPaaquizId = request.getParameter("quizId");
			 if (!quiz.getId().equals(stemtPaaquizId)) {
			 response.sendRedirect("stemme?stemtPaaGammelQuiz");
			 return;
			 }
			 // Sjekker så om studenten prøver å svare på nytt. Det er jo ikke lov.
			 // Her må vi holde orden på dette i session (evt. i cookie).
			 String stemtTidligereId = (String) request.getSession()
			 .getAttribute("sisteQuizSvartPaa");
			 if (quiz.getId().equals(stemtTidligereId)) {
			 response.sendRedirect("stemme&alleredeStemtPaaQuiz");
			 return;
			 }
			 String valgtAlternativ = request.getParameter("svaralternativ");
			 // Nå vet vi at det finnes en aktiv quiz, og at svaret gjelder
			 // denne aktive quizen, og at studenten ikke allerede har stemt.
			 // Hvis studenten har glemt å krysse av for et alternativ går vi
			 // til en side som gir feilmelding om det.
			 if (valgtAlternativ == null) {
			 response.sendRedirect("stemme?ugyldigValg");
			 return;
			 }
			 // Nå vet vi at alt er OK.
			 //Trådsikker oppdatering av antall stemmer i quiz-objektet.
			 synchronized(quiz) {
			 quiz.giStemmeTil(valgtAlternativ);
			 }
			 // Ta vare på data for kvittering og for å hindre omstemming.
			 request.getSession().setAttribute("sisteQuizSvartPaa", stemtPaaquizId);
			 request.getSession().setAttribute("svaralternativ", valgtAlternativ);
			 response.sendRedirect("stemme?kvittering");
			 }
		 
	//c) Skriv doGet i controller-klassen for /stemme, dvs. StemmeController.java. Det er ikke nødvendig
		 //å ta med package- og import-setninger. Husk å få med både normal- og alternativscenarier.
		
	//Løsning:
		 @Override
		 protected void doGet(
		 HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
		 // Også i doGet() er det mange ulike tilfeller å tenke på:
		 // 1 - vanlig GET til /stemme, men ingen aktiv quiz
		 // 2 - redirect fra doPost() pga. ingen aktiv quiz ved posting
		 // 3 - vanlig GET til /stemme, men bruker har allerede stemt på quiz
		 // 4 - redirect fra doPost() pga. forsøk, men allerede stemt på quiz
		 // 5 - redirect fra doPost() etter OK avgitt stemme (= stemt)
		 // 6 - redirect fra doPost() pga. manglende valg (radioknapp)
		 // 7 (- redirect fra doPost() pga. stemmeforsøk på feil quiz)
		 // 8 - vanlig GET til /stemme, alt OK.
		 Quiz gjeldendeQuiz = (Quiz)
		 getServletContext().getAttribute("gjeldendeQuiz");
		 // 1 og 2 => forward til IngenAktivQuiz-side
		 if (gjeldendeQuiz == null
		 || request.getParameter("ingenAktivQuiz") != null) {
		 request.getRequestDispatcher("WEB-INF/IngenAktivQuiz.html")
		 .forward(request, response);
		 return;
		 }
		 // 3, 4 og 5 => forward til Kvittering.jsp
		 String stemtTidligereId = (String) request.getSession()
		 .getAttribute("sisteQuizSvartPaa");
		 if (gjeldendeQuiz.getId().equals(stemtTidligereId)) {
		 request.getRequestDispatcher("WEB-INF/Kvittering.jsp")
		 .forward(request, response);
		 return;
		 }
		 // 6, 7 og 8 er nå forward til stemmeskjema med litt ulike meldinger
		 String feilmelding = "";
		 if (request.getParameter("ugyldigValg") != null) {
		 feilmelding = "Du må velge ett av alternativene. Prøv igjen!";
		 } else if request.getParameter("stemtPaaGammelQuiz") != null) {
		 // Denne er ikke nevnt i oppgaveteksten, så ...
		 feilmelding = "Du stemte på en gammel quiz. Prøv igjen!";
		 }
		 request.setAttribute("feilmelding", feilmelding);
		 request.getRequestDispatcher("WEB-INF/Stemmeskjema.jsp")
		 .forward(request, response);
		 }
}
