package task2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Rutsjebane extends Thread {

	private int plass = 5;
	private static Queue<Hamburger> hamburgere;

	public Rutsjebane(int plass) {
		this.plass = plass;
		hamburgere = new LinkedList<Hamburger>();
	}

	public synchronized Queue<Hamburger> getHamburgere() {
		while (!erTom()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return hamburgere;
	}

	public static synchronized void leggTilHamburger(Hamburger hamburger) {
		Random rand = new Random();
		int betweenThreeAndSixSeconds = rand.nextInt((6000 - 3000) + 1) + 3000;
		sleep(betweenThreeAndSixSeconds);
		hamburgere.add(hamburger);
		notifyAll();
	}

	public static synchronized void fjernHamburger(Hamburger hamburger) {
		Random rand = new Random();
		int betweenThreeAndSixSeconds = rand.nextInt((6000 - 3000) + 1) + 3000;
		sleep(betweenThreeAndSixSeconds);
		hamburgere.remove(hamburger);
		notifyAll();
	}

	public boolean erTom() {
		return plass == 0;
	}

	public boolean erFull() {
		return plass == hamburgere.size();
	}
}
