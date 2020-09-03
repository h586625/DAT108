package task3;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Rutsjebane {

	private int maksPlass;
	private int elementNr = 0;
	private static LinkedBlockingQueue<Integer> elementer;

	public Rutsjebane() {
		elementer = new LinkedBlockingQueue<Integer>(5);
	}

	public Rutsjebane(int maksPlass) {
		elementer = new LinkedBlockingQueue<Integer>(maksPlass);
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

	public boolean leggTil() {
		if (!erFull()) {
			return elementer.offer(++elementNr);
		} else {
			return false;
		}
	}

	public int fjern() {
		if (!erTom()) {
			try {
				return elementer.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public boolean erTom() {
		return getAntall() == 0;
	}

	public boolean erFull() {
		return maksPlass == getAntall();
	}
}
