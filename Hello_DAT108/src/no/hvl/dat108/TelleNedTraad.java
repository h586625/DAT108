package no.hvl.dat108;

public class TelleNedTraad extends Thread {

	private Teller teller;
	private int antall;

	public TelleNedTraad(Teller teller, int antall) {
		this.teller = teller;
		this.antall = antall;
	}

	@Override
	public void run() {
		for (int i=0; i<antall; i++) {

			// without this, it would cause an RC
			synchronized (teller) {
				// Kritisk seksjon
				// teller.verdi--;
				teller.tellNed();
			}
		}
	}
}
