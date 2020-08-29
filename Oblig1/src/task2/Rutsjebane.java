package task2;

import java.util.LinkedList;
import java.util.Queue;

public class Rutsjebane {

	private int maksPlass;
	private int elementNr = 0;
	private static Queue<Integer> elementer = new LinkedList<Integer>();

	public Rutsjebane() {
		maksPlass = 5;
	}

	public Rutsjebane(int maksPlass) {
		this.maksPlass = maksPlass;
	}

	public void printElementer() {
		System.out.print("[");
		int i = 0;

		for (Integer el : elementer) {
			i++;
			if (i > 1) System.out.print(", ");
			System.out.print("(" + el + ")");
		}

		System.out.println("]");
	}

	public int getAntall() {
		return elementer.size();
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
			return true;
		} else {
			return false;
		}
	}

	public synchronized int fjern() {
		if (!erTom()) {
			return elementer.remove();
		} else {
			return -1;
		}
	}

	public boolean erTom() {
		return getAntall() == 0;
	}

	public boolean erFull() {
		return maksPlass == getAntall();
	}
}
