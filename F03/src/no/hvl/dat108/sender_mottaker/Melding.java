package no.hvl.dat108.sender_mottaker;

public class Melding {

	private String tekst;
	private boolean mottatt = true;

	public boolean erMottatt() {
		return mottatt;
	}

	public void send(String nyMelding) {
		tekst = nyMelding;
		mottatt = false;
	}

	public String motta() {
		mottatt = true;
		return tekst;
	}

}
