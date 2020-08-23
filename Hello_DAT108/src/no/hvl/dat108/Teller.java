package no.hvl.dat108;

public class Teller {
	// this will cause RC unless we use synchronized
	// where it's changed
	// public int verdi = 0;

	// we rather define synchronized methods here
	// as an alternative
	private int verdi = 0;

	//	public void tellOpp() {
	//		synchronized (this) {
	//			verdi++;
	//		}
	//	}
	//
	//	public void tellNed() {
	//		synchronized (this) {
	//			verdi--;
	//		}
	//	}

	// we can also synchronize a whole method
	// rather than just parts of it, like shown
	// above
	public synchronized void tellOpp() {
		verdi++;
	}

	public synchronized void tellNed() {
		verdi++;
	}

	public int getVerdi() {
		return verdi;
	}
}
