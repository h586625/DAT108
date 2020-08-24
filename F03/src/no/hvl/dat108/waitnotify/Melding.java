package no.hvl.dat108.waitnotify;

public class Melding {
	private String innhold;

	public synchronized String getInnhold() {
		while (!harInnhold()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return innhold;
	}

	public synchronized void setInnhold(String innhold) {
		this.innhold = innhold;
		notifyAll();
	}

	public boolean harInnhold() {
		return innhold != null;
	}
}
