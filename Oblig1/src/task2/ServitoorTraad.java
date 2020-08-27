package task2;

import java.util.Random;

public class ServitoorTraad extends Thread {
	private Servitoor servitoor;
	private Rutsjebane hamburgerKoe;
	Random rand = new Random();

	public ServitoorTraad(Rutsjebane hamburgerKoe, Servitoor servitoor) {
		this.servitoor = servitoor;
		this.hamburgerKoe = hamburgerKoe;
	}

	public String getNavn() {
		return servitoor.getNavn();
	}

	@Override
	public synchronized void run() {
		while (true) {
			int randSeconds = rand.nextInt(4000);
			boolean hentet = false;
			try {
				sleep(randSeconds+2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(hamburgerKoe) {
				while (!hentet) {
					if (!hamburgerKoe.erTom()) {
						int burger = hamburgerKoe.fjern();
						if(burger > -1) {
							System.out.print(getNavn() + " har hentet burger: [" + burger + "] ");
							hamburgerKoe.printElementer();
							hamburgerKoe.notifyAll();
							hentet = true;
						}
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
