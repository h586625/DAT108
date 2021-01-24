package Eksamen2018;

public class Oppgg5WebApplikasjoner {
//I denne oppgaven skal dere lage deler av en liten webapplikasjon der studenter kan svare på quizer som en lærer gir i et klasserom. 
//Alle quizer har alternativene A, B, C og D. 
//Hva de ulike alternativene betyr, skrives f.eks. på tavlen. 
//Data for aktiv quiz (alltid kun én aktiv quiz) lagres i minnet på webtjeneren, dvs. det er ingen database involvert.
	
//a. 	I dette brukstilfellet ønsker læreren og klassen å se resultatet for enquiz. 
//		Resultat-siden (/quiz) viser svarresultatet (så langt):
	
	// 10% . Skriv JSP-en (viewet) for Skjermbilde1, dvs. QuizResultat.jsp. Bruk en "løkke" for å
	// skrive ut alternativene, dvs. ikke hardkode A, B, C, D. Det er ikke nødvendig å ta med JSPdirektiv-setninger
	
	
<html>
	...
	<body>
	 <h1>${gjeldendeQuiz.omTekst}</h1>
	 <table border="1">
	 <tr><th>Alternativ</th><th>Antall svar</th></tr>
	 <c:forEach var="alternativ" items="${gjeldendeQuiz.svarAlternativer}">
	 <tr><td>${alternativ.key}</td><td>${alternativ.value}</td></tr>
	 </c:forEach>
	 </table>
	 <form method="post">
	 <p><input type="submit" value="Start ny quiz med tittelen" />
	 <input type="text" name="quizTittel"/>
	 <b>NB! Nullstiller alle svar.</b></p>
	 </form>
	</body>
	</html> 
	
//b.	20% . Skriv controller-klassen for /quiz, dvs. QuizController.java. Husk å få med både
// 		brukstilfelle 1 og brukstilfelle 2, og både normal- og alternativscenarier. Det er ikke
//		nødvendig å ta med package- og import-setninger.
	
	@WebServlet("/quiz")
	
	public class QuizController extends HttpServlet {
	 @Override
	 protected void doGet(
	 HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {
	 if (!LoginHjelper.isInnlogget(request)) {
	 response.sendRedirect("logginn?ikkeInnlogget");

	 } else {
	 Quiz gjeldendeQuiz
	 = (Quiz) getServletContext().getAttribute("gjeldendeQuiz");

	 if (gjeldendeQuiz == null) {
	 request.getRequestDispatcher("WEB-INF/IngenAktivQuiz.html")
	 .forward(request, response);
	 } else {
	 request.getRequestDispatcher("WEB-INF/QuizResultat.jsp")
	 .forward(request, response);
	 }
	 }
	 }
	 @Override
	 protected void doPost(
	 HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {
	 if (!LoginHjelper.isInnlogget(request)) {
	 response.sendRedirect("logginn?ikkeInnlogget");

	 } else {
	 String quizTittel = request.getParameter("quizTittel");
	 Quiz nyQuiz = new Quiz(quizTittel);
	 getServletContext().setAttribute("gjeldendeQuiz", nyQuiz);
	 response.sendRedirect("quiz");
	 }
	 }
	}
	
//c.	10% . Skriv controller-klassen for /logginn, dvs. LogginnController.java. Korrekt pinkode
//		er hardkodet i web.xml som en init-parameter kalt korrektPin. Det er ikke nødvendig å ta
//		med package- og import-setninger.
	
	@WebServlet(name = "LogginnController", urlPatterns = "/logginn")
	public class LogginnController extends HttpServlet {
		 private static final long serialVersionUID = 1L;
		 private String korrektPin;

		 @Override
		 public void init() throws ServletException {
		 korrektPin = getServletConfig().getInitParameter("korrektPin");
		 }
		 @Override
		 protected void doGet(HttpServletRequest request,
		 HttpServletResponse response) throws ServletException, IOException {

		 if (request.getParameter("ugyldigPin") != null) {
		 request.setAttribute("feilmelding", "Ugyldig pin");

		 } else if (request.getParameter("ikkeInnlogget") != null) {
		 request.setAttribute("feilmelding", "Du er ikke innlogget");
		 }
		 request.getRequestDispatcher("WEB-INF/Logginn.jsp").forward(
		 request, response);
		 }
		 @Override
		 protected void doPost(HttpServletRequest request,
		 HttpServletResponse response) throws ServletException, IOException {

		 String pin = request.getParameter("pin");

		 if (pin == null || !pin.equals(korrektPin)) {
		 response.sendRedirect("logginn?ugyldigPin");

		 } else {
		 LoginHjelper.loggInn(request);
		 response.sendRedirect("quiz");
		 }
		 }
		}
}
