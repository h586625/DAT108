package Eksamen2019høst;

public class Oppg1Tråder {
	
	//a) I Java kan vi lage tråder på to ulike måter, enten ved å overlaste Thread eller ved å
	//implementere Runnable. Vis hvordan dette gjøres på de to måtene når run-metoden ser slik
	//ut:
	
	/*public void run() {
	 System.out.println("Tråden kjører");
	} */
	
	//Løsning:
		/*Det er to ulike metoder den ene er å arve fra Thread og den andre er å implementere
		Runnable. Eksempler er gitt under. Kilde: The Java Tutorials, Concurrency, Defining
		and Starting a Thread.*/
	
		//Metode 1: En klasse som arver fra Thread og implementerer run()
		public class MyThread extends Thread {
		 @Override
		 public void run() {
		 System.out.println("Tråden kjører");
		 }
		}
		// bruk
		Thread thread = new MyThread();
		thread.start();
		
		//Metode 2: En klasse som implementerer Runnable og implementerer run()
		public class MyRunnableClass implements Runnable {
		 @Override
		 public void run() {
		 System.out.println("Tråden kjører");
		 }
		}
		// bruk
		Thread thread = new Thread(new MyRunnableClass());
		thread.start();
		
		//Metode 2a: En anonym klasse som implementerer Runnable og implementerer run()
		Runnable runnable = new Runnable {
		 @Override
		 public void run() {
		 System.out.println("Tråden kjører");
		 }
		}
		// bruk
		Thread thread = new Thread(runnable);
		thread.start();
		
		/*Metode 2b: Et lambda-uttrykk med implementasjon av run(). Er mulig fordi Runnable
		er et funksjonelt grensesnitt.*/
		Runnable runnable = () -> {
		 System.out.println("Tråden kjører");
		}
		// bruk
		Thread thread = new Thread(runnable);
		thread.start();

}
