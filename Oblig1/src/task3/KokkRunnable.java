package task3;

import java.util.Random;

public class KokkRunnable implements Runnable {

	private Kokk kokk;
	private Rutsjebane hamburgerKoe;
	Random rand = new Random();

	public KokkRunnable(Rutsjebane hamburgerKoe, Kokk kokk) {
		this.kokk = kokk;
		this.hamburgerKoe = hamburgerKoe;
	}

	@Override
	public void run() {
		while (true) {
			int randSeconds = rand.nextInt(4000);
			boolean lagtTil = false;
			try {
				Thread.sleep(randSeconds+2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(hamburgerKoe) {
				while (!lagtTil) {
					if (!hamburgerKoe.erFull()) {
						lagtTil = hamburgerKoe.leggTil();
						System.out.print(kokk.getNavn() + " har laget burger:\t\t(" + hamburgerKoe.getElementNr() + ") => ");
						hamburgerKoe.printElementer();
					} else {
						System.out.println("### Køen er nå full! Kokken " + kokk.getNavn() + " venter ###");
					}
				}
			} // synchronized()
		}
	} // run()

}
