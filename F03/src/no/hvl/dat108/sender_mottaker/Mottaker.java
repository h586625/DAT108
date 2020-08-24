package no.hvl.dat108.sender_mottaker;

public class Mottaker extends Thread {

	private Melding melding;

	public Mottaker(Melding melding) {
		this.melding = melding;
	}

	@Override
	public void run() {
		while (true) {
			synchronized(melding) {
				// spin lock
				while (melding.erMottatt()) {
					try {
						melding.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				String mottattMelding = melding.motta();
				melding.notifyAll();
				System.out.println("Mottaker har mottatt melding: " + mottattMelding);

				try {
					System.out.println("Prosesserer melding...");
					sleep(5_000);
					System.out.println("Melding klar!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
