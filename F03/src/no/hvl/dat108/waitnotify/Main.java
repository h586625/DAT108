package no.hvl.dat108.waitnotify;

public class Main {

	private static String melding;

	public static void main(String[] args) {

		// l�reren v�r administrerer hvem som
		// skal f� ballen
		Object lock = new Object();

		Thread printlnTraad = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized(lock) {
					// while() fordi denne tr�den
					// kan risikere � v�kne uten en notify (spurious wakeup)
					// den kan ogs� f� en interrupt
					// og hvis dette skjer f�r melding er satt,
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
					// hvem som f�r beskjed
				}
			}
		});

		printlnTraad.start();
		giVerdiTraad.start();
	}
}

