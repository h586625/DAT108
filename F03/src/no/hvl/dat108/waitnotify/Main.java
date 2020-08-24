package no.hvl.dat108.waitnotify;

public class Main {

	private static String melding;

	public static void main(String[] args) {

		// læreren vår administrerer hvem som
		// skal få ballen
		Object lock = new Object();

		Thread printlnTraad = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized(lock) {
					// while() fordi denne tråden
					// kan risikere å våkne uten en notify (spurious wakeup)
					// den kan også få en interrupt
					// og hvis dette skjer før melding er satt,
					// vil den skrive ut melding
					// if (melding == null) {
					// dette er en "spin lock"
					while (melding == null) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				System.out.println(melding);
			}
		});

		Thread giVerdiTraad = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					melding = "Dette er en melding";
					lock.notifyAll();
					// fordi notify();
					// kan velge tilfeldig
					// hvem som får beskjed
				}
			}
		});

		printlnTraad.start();
		giVerdiTraad.start();
	}
}

