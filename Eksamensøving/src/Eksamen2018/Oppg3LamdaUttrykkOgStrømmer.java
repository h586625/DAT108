package Eksamen2018;

public class Oppg3LamdaUttrykkOgStrømmer {
	//Vi tenker oss at vi skal lage en applikasjon som blant annet skriver ut diverse lister av studenter.
	//Listene kan være sortert på ulike ting, utvalgetav studenter kan være ulikt, 
	//og hvilken informasjon som vises om den enkelt student kan være ulik.
	//Klassen StudentinneholderString fornavn, String etternavn, String studentnrog String klasseid.
	
	
	//a) 
	//Til sortering tenker vi å brukeferdige metoder, som f.eks.
	//Collections.sort(List<T>, Comparator<T>)ellerStream.sorted(Comparator<T>). 
	//En comparator er en såkalt funksjonell kontrakt(functional interface), 
	//og har metoden int compare(T o1, T o2)som gir positivt svar om o1 > o2, 0 om o1 = o2, 
	//og negativt svar om o1<o2.i.
	
	//i.Skriv et Lambda-uttrykk for en comparator som kan brukes til å sortere studentene på etternavn
	//ii. Tilordne lambda-uttrykket til en konstant paaEtternavn i Student-klassen 
	//slik at det kan gjenbrukes i applikasjonen.(Det holder å vise setningensom deklarerer konstanten. 
	//Husk å få med type.)
	
	public final static Comparator<Student> paaEtternavn
	 = (s1, s2) -> s1.etternavn.compareTo(s2.etternavn);



//Alternativt (men ikke gjennomgått/vist i kurset):
	 public final static Comparator<Student> paaEtternavn
	 = Comparator.comparing(s -> s.etternavn);
	 //eller = Comparator.comparing(Student::getEtternavn);
	 
	 
	 
	 //b)
	 //Vi tenker å bruke Stream-APIettil å genererede listene vi ønskerå skrive ut. 
	 //Anta at vi i utgangspunktet har alle studentene i en liste studenter. 
	 //Skriv kode som genererer følgende lister:
	 
	 //i.En liste over alle studenter, 
	 //sortert stigende på etternavn. 
	 //Bruk konstanten fra a.iii løsningen. 
	 //Lagre den genererte listen i variabelen sortertPaaEtternavn.
	 
	 //ii.En liste over navnene (fornavn + " " + etternavn) til studentenei klasse"17HINF". 
	 //Lagre den genererte listen i en variabel studentnavnI17HINF.
	 
	 List<Student> sortertPaaEtternavn = studenter.stream()		 
			 .sorted(Student.paaEtternavn)
			 .collect(Collectors.toList());
	 List<String> studentnavnI17HINF = studenter.stream()
			 .filter(s -> s.getKlasseid().equals("17HINF"))
			 .map(s -> s.getFornavn() + " " + s.getEtternavn())
			 .collect(Collectors.toList());
	 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 