package no.hvl.dat108;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		// Metode 1
		MinTraad t = new MinTraad("test");
		t.start();
		t.stopp();

		// Metode 2
		Thread anonymT = new Thread() {
			@Override
			public void run() {
				System.out.println("Utskrift fra tråden til den anonyme klassen");
			}
		};
		anonymT.start();

		// Metode 3
		Thread runnableT = new Thread(new MinRunnable());
		// Metode 4
		//		Thread runnableT = new Thread(new MinRunnable() {
		//			kunne overskrevet run() her også, som en anonym
		//			vanlig med event listeners osv. slik som i JavaScript
		//			vi kan også erstatte dette med et enda enklere lambda uttrykk:
		// Metode 5
		//    		() -> System.out.println(); istedenfor new MinRunnable() {}
		//		});
		runnableT.start();

		Thread.sleep(1000);

		System.out.println("Utskrift fra main");
	}
}
