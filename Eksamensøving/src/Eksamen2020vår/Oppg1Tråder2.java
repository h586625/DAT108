package Eksamen2020vår;

public class Oppg1Tråder2 {
	/*a) I Java kan vi lage tråder på to ulike måter, enten ved å arve fra klassen Thread eller ved å
	implementere Runnable. Den siste er benyttet i eksempelet som er gitt i oppgaven. Vis
	hvordan eksempelet vil se ut hvis vi arver fra klassen Thread.*/
	
	
	//Løsning:
	
	package no.hvl.dat108;
	/**
	* Kode for eksamensoppgave 1 i DAT108 våren 2020.
	*
	* @author Atle Geitung
	*/
	public class Oppgave1 {
	 /**
	 * @param args ikke i bruk
	 */
	 public static void main(String[] args) {
		 Thread t1 = new Thread(new Thread1(), "Thread1");
		 Thread t2 = new Thread(new Thread2(), "Thread2");
		 t1.start();
		 t2.start();
		 try {
		 t2.join();
		 t1.join();
		 } catch (InterruptedException e) {
		 // ignorer
		 }
	 }
	}
	
	package no.hvl.dat108.answera;
	/**
	* Kode for eksamensoppgave 1 i DAT109 våren 2020.
	*
	* @author Atle Geitung
	*/
	public class Thread1 extends Thread {
	 public Thread1() {
	 super("Thread1");
	 }
	@Override
	 public void run() {
	 while (true) {
	 synchronized (Integer.class) {
	 System.out.println(Thread.currentThread().getName() +
	 " - låst på Integer.class");
	 synchronized (String.class) {
	 System.out.println(Thread.currentThread().getName() +
	 " - låst på String.class");
	 }
	 }
	 }
	 }
	}
	package no.hvl.dat108.answera;
	/**
	* Kode for eksamensoppgave 1 i DAT109 våren 2020.
	*
	* @author Atle Geitung
	*/
	public class Thread2 extends Thread {
	 public Thread2() {
	 super("Thread2");
	 }
	 @Override
	 public void run() {
	 while (true) {
	 synchronized (String.class) {
	 System.out.println(Thread.currentThread().getName() +
	 " - låst på String.class");
	 synchronized (Integer.class) {
	 System.out.println(Thread.currentThread().getName() +
	 " - låst på Integer.class");
	 }
	 }
	 }
	 }
	}
	
/*b) Forklar hva som skjer når vi kjører main i eksempelet som er gitt i oppgaven.*/
	
	//Løsning:
		/*Det som tilsynelatende skjer er at vi lager de to trådene t1 og t1, deretter startes de og til slutt
		ventes det på at begge skal bli ferdig før main avsluttes. Problemet er at t1 og t2 hver definerer
		to kritiske regioner med nøkkelordet synchronized. Begge trådene er synkronisert på
		Integer.class og String.class, men i omvendt rekkefølge. Dette betyr at de vil vente på hverandre
		når begge trådene er startet fordi den ene tråden ikke frigjør ressursen som den andre venter på.
		Altså vi har en vranglås og programmet vil aldri avsluttes. */
	
/*c) Det er et synkroniseringsproblem i eksempelet som er gitt i oppgaven. Hvis du ikke forklarte
	det i oppgave 1b), må du forklare det nå. Forklar hvordan vi kan fikse dette problemet. Du
	behøver ikke skrive kode.*/
	
	//Løsning: 
	
	/*Synkroniseringsproblemet ble forklart i forrige deloppgave. Den enkle løsningen er å
	låse ressursene i samme rekkefølge i de to trådene.*/
	
/*d) I oppgave 1c) forklarte du hvordan synkroniseringsproblemet i eksempelet kan løses. Har du
	flere idéer til hvordan dette kan løses?*/
	
	//Løsning: 
	
	/*Hvis rekkefølgen av de kritiske regionene hadde vært nødvendig for logikken, kunne vi
	ikke bare byttet om på rekkefølgen og vi måtte lagd en bedre løsning. Vi ønsker å låse minst
	mulig av koden og det å låse på tråd-nivå (synchronized på run-metode eller tråd-objekt) er ikke
	ønskelig da det er det samme som om vi ikke bruker tråder. Vi må lage kritiske regioner med
	andre verktøy som for eksempel Lock og Semaphore som Java tilbyr.*/
}
