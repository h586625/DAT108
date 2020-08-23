package no.hvl.dat108;

public class TelleOppTraad extends Thread {

	private Teller teller;
	private int antall;

	public TelleOppTraad(Teller teller, int antall) {
		this.teller = teller;
		this.antall = antall;
	}

	@Override
	public void run() {
		for (int i=0; i<antall; i++) {

			// without this, it would cause an RC
			synchronized(teller) {
				// Kritisk seksjon
				// leser først verdien, inkrementerer og til slutt assignment
				// de to siste operasjonene skaper konflikt
				// teller.verdi++;
				teller.tellOpp();
			}
		}
	}
}
