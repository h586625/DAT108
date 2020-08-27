package task2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Rutsjebane extends Thread {

	private int plass;
	private int antall = 0;
	private static Queue<Integer> hamburgere;

	public Rutsjebane() {
		this.plass = 5;
		hamburgere = new LinkedList<Integer>();
	}

	public Rutsjebane(int plass) {
		this.plass = plass;
		hamburgere = new LinkedList<Integer>();
	}

	public synchronized void printHamburgere() {
		for (Integer item: hamburgere) {
			System.out.println(item);
		}
	}

	public int getAntallBurgere(int antall) {
		return antall;
	}

	public void setAntallBurgere(int antall) {
		this.antallBurgere = antall;
	}

	public synchronized Queue<Integer>() getHamburgere() {
		return hamburgere;
	}



	@Override
	public void run() {
		printHamburgere();
	}

	public synchronized void leggTilHamburger(Integer hamburger) throws InterruptedException {
		Random rand = new Random();
		int betweenTwoAndSixSeconds = rand.nextInt((2000) + 1) + 4000;
		if (!erFull()) {
			System.out.println("Kokk1 legger på hamburger (" + antall + ") => [(1)]");
			sleep(betweenTwoAndSixSeconds);
			hamburgere.add(hamburger);
			antall++;
			notifyAll();
		} else {
			System.out.println("### Kokk2 er klar med en hamburger, men rutsjebanen er full. Venter! ###");
		}
	}

	public synchronized void fjernHamburger(Integer hamburger) throws InterruptedException {
		Random rand = new Random();
		int betweenTwoAndSixSeconds = rand.nextInt((2000) + 1) + 4000;
		if (!erTom()) {
			System.out.println("Servitør2 tar av hamburger (1) => [");
			sleep(betweenTwoAndSixSeconds);
			hamburgere.remove(hamburger);
			antall--;
			notifyAll();
		} else {
			System.out.println("### Servitør2 vil ta en hamburger, men rutsjebanen er tom. Venter! ###");
		}
	}

	public boolean erTom() {
		return plass == 0;
	}

	public boolean erFull() {
		return plass == hamburgere.size();
	}
}
