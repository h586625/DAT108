package no.hvl.dat108.sender_mottaker;

public class Main {

	// ... er variabelt antall elementer (tabell)
	public static void main(String... blablabla) {

		Melding m = new Melding();

		Sender sender = new Sender(m);
		Mottaker mottaker = new Mottaker(m);

		sender.start();
		mottaker.start();
	}
}

