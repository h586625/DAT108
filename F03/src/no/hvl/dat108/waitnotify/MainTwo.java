package no.hvl.dat108.waitnotify;

// Her bruker vi Melding istedenfor String melding
// For å flytte logikken fra Main til Melding objektet
public class MainTwo {

	public static void main(String[] args) {

		Melding melding = new Melding();

		Thread printlnTraad = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(melding.getInnhold());
			}
		});

		Thread giVerdiTraad = new Thread(new Runnable() {
			@Override
			public void run() {
				melding.setInnhold("Dette er en melding");
			}
		});

		printlnTraad.start();
		giVerdiTraad.start();
	}
}

