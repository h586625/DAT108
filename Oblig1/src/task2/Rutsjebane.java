package task2;

import java.util.LinkedList;
import java.util.Queue;

public class Rutsjebane {

	private int plass;
	private int antall = 0;
	private int elementNr = 0;
	private static Queue<Integer> elementer;

	public Rutsjebane() {
		plass = 5;
		elementer = new LinkedList<Integer>();
	}

	public Rutsjebane(int plass) {
		this.plass = plass;
		elementer = new LinkedList<Integer>();
	}

	public void printElementer() {
		System.out.print("[");

		for (Integer el : elementer) {
			System.out.print("(" + el + ")");
		}

		System.out.println("]");
	}

	public int getAntall() {
		return antall;
	}

	public void setAntall(int antall) {
		this.antall = antall;
	}

	public int getElementNr() {
		return elementNr;
	}

	public Queue<Integer> getElementer() {
		return elementer;
	}

	public synchronized boolean leggTil() {
		if (!erFull()) {
			elementer.add(++elementNr);
			antall++;
			return true;
		} else {
			return false;
		}
	}

	public synchronized int fjern() {
		if (!erTom()) {
			antall--;
			return elementer.remove();
		} else {
			return -1;
		}
	}

	public boolean erTom() {
		return plass == 0;
	}

	public boolean erFull() {
		return plass == elementer.size();
	}
}
