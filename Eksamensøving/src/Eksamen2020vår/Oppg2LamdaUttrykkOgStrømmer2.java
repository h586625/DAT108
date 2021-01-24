package Eksamen2020vår;

public class Oppg2LamdaUttrykkOgStrømmer2 {
	//Koden nedenfor hører til a), b) og c).
	
	public class MainABC {
		public static void main(String[] args) {
			int[] tab = new int[] {1,2,3,4,5,6,7,8,9,10};
			int antallPartall = antallOk(tab, ???);
			System.out.println(antallPartall);
			int antallPrimtall = antallOk(tab, ???);
			System.out.println(antallPrimtall);
		}
	...
	}
/*a) Metoden antallOk() teller opp hvor mange tall i en tabell av heltall som oppfyller en gitt
	betingelse. Hvilken Java-type tror du den andre parameteren i antallOk() må være (der det står
	???)?*/
	
//Løsning 2a):
		/*Den andre parameteren i antallOk() må være en betingelse (et predikat) av heltall,
		f.eks. den innebyggede typen (java.util.function.)Predicate<Integer>.*/
	
/*b) Skriv et lambdauttrykk som erstatter parameteren ??? i setningen som finner antallPartall.*/
	
//Løsning 2b):
		//x -> x % 2 == 0

/*c) Vi har også en hjelpemetode boolean isPrime(int n) som returnerer om n er et primtall eller
	ikke. Skriv et uttrykk som erstatter parameteren ??? i setningen som finner antallPrimtall.*/
	
//Løsning 2c):
		/*x -> isPrime(x)
		evt. MainABC::isPrime*/
	
	
	//Koden nedenfor tilhører d).
	
	/*public class MainD {
	public static void main(String[] args) {
	Button knappen = new Button("Trykk her!");
	MinActionListener knappTrykket = new MinActionListener();
	knappen.addActionListener(knappTrykket);
	...
	}
	}
	class MinActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	System.out.println("Noen trykket på knappen!");
	}
	}*/
/*d) Forenkle koden over så mye du kan ved å bruke lambdauttrykk.*/
	
//Løsning 2d):
		public class MainD {
		public static void main(String[] args) {
		Button knappen = new Button("Trykk her!");
		knappen.addActionListener(
		 e -> System.out.println("Noen trykket på knappen!"));
		...
		}
		}

		//Koden nedenfor tilhører e).
		
		/*public class MainE {
		public static void main(String[] args) {
		List<Student> studenter = Arrays.asList(
		new Student(10001, "Atle", "Patle"),
		...);
		//Start
		List<Integer> studentnumre = new ArrayList<>();
		for (Student s : studenter) {
		if (s.getEtternavn().startsWith("A")) {
		studentnumre.add(s.getStudentnr());
		}
		}
		//Slutt
		...
		}
		}*/
		
//e) Skriv om løsningen mellom //Start og //Slutt i koden over ved å bruke streams-APIet.
		
	//Løsning 2e:
		List<Integer> studentnumre = studenter.stream()
		.filter(s -> s.getEtternavn().startsWith("A"))
		.map(Student::getStudentnr)
		.collect(Collectors.toList());
}


