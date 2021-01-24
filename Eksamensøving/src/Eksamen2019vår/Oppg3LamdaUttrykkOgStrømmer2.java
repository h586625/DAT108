package Eksamen2019vår;

public class Oppg3LamdaUttrykkOgStrømmer2 {
	//a) Stream-APIet har en metode filter( betingelse ) som filtrerer en stream av elementer,
	//dvs. plukker ut de som oppfyller en gitt betingelse. En slik filtreringsmetode hadde vært nyttig
	//selv om vi ikke hadde hatt streams.
	//Skriv en metode List<T> filter(List<T> liste, Predicate<T> betingelse) som
	//tar inn en liste og en betingelse, og returner en ny liste med elementene som oppfyller
	//betingelsen.
	
	//Løsning:
		private static <T>
		List<T> filter(List<T> liste, Predicate<T> betingelse) {
		 List<T> resultat = new ArrayList<>();
		 for (T t : liste) {
		 if (betingelse.test(t)) {
		 resultat.add(t);
		 }
		 }
		 return resultat;
		}
		//Alternativ kreativ løsning gitt av én student:
		private static <T>
		List<T> filter(List<T> liste, Predicate<T> betingelse) {
		 return liste.stream()
		 .filter(betingelse)
		 .collect(Collectors.toList());
		 
		 
	//b) Anta at du har en liste av String-objekter kalt listen. Skriv til sammen to kodelinjer som
	//1. Bruker metoden i a) til å lage en ny liste med alle strengene som starter med en "#".
	//2. Skriver ut elementene i den filtrerte listen, ett element per linje.
		 
		// Løsning:
			//1
			List<String> filtrert = filter(listen, s -> s.startsWith("#"));
			//2
			filtrert.forEach(s -> System.out.println(s));
			//evt.
			filtrert.forEach(System.out::println);
			
			
	//c) Anta at du har en liste av Bok-objekter kalt boker. En bok har tittel, forfatter, osv... Bruk StreamAPIet til å:
	//1. Finne antall bøker i listen som inneholder ordet "Java".
	//2. Lage en ny sortert liste av alle forfatterne (uten duplikater)
			
		//Løsning:
			//1
			long antallJavaBoker = boker.stream()
			 .filter(bok -> bok.getTittel().contains("Java"))
			 .count();
			//2
			List<String> forfattere = boker.stream()
			 .map(bok -> bok.getForfatter()) //el. map(Bok::getForfatter)
			 .distinct()
			 .sorted()
			 .collect(Collectors.toList());
		}
}
