package task2;

import java.util.Random;

public class KokkTraad extends Thread {

	private Kokk kokk;
	private Rutsjebane hamburgerKoe;
	Random rand = new Random();

	public KokkTraad(Rutsjebane hamburgerKoe, Kokk kokk) {
		this.kokk = kokk;
		this.hamburgerKoe = hamburgerKoe;
	}

	public String getNavn() {
		return kokk.getNavn();
	}

	@Override
	public synchronized void run() {
		while (true) {
			int randSeconds = rand.nextInt(4000);
			boolean lagtTil = false;
			try {
				sleep(randSeconds+2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(hamburgerKoe) {
				while (!lagtTil) {
					if (!hamburgerKoe.erFull()) {
						lagtTil = hamburgerKoe.leggTil();
						System.out.print(getNavn() + " har laget burger: [" + hamburgerKoe.getAntall() + "] ");
						hamburgerKoe.printElementer();
						hamburgerKoe.notifyAll();
					} else {
						try {
							System.out.println("K�en er n� full! Kokken " + getNavn() + " venter");
							hamburgerKoe.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			} // synchronized()
		}
	} // run()

}
