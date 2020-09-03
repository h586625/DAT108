package task3;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Rutsjebane {

	private int elementNr = 0;
	private static LinkedBlockingQueue<Integer> elementer;

	public Rutsjebane() {
		elementer = new LinkedBlockingQueue<Integer>(5);
	}

	public Rutsjebane(int maksPlass) {
		elementer = new LinkedBlockingQueue<Integer>(maksPlass);
	}

	public String elementerToString() {
		String str = "[";
		int i = 0;
		for (Integer el : elementer) {
			i++;
			if (i > 1) str += (", ");
			str += "(" + el + ")";
		}
		str += "]";
		return str;
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

	public int leggTil() {
		try {
			elementer.put(++elementNr);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return elementNr;
	}

	public int fjern() {
		try {
			return elementer.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
