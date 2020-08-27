package task2;

import java.util.Random;

public class Kokk extends Thread {

	private String navn;
	private Rutsjebane hamburgerKoe;
	Random rand = new Random();

	public Kokk(Rutsjebane hamburgerKoe, String navn) {
		this.navn = navn;
		this.hamburgerKoe = hamburgerKoe;
	}

	public String getNavn() {
		return navn;
	}

	@Override
	public synchronized void run() {
		while (true) {
			int randSeconds = rand.nextInt(4000);
			boolean lagtTil = false;
			try {
				sleep(randSeconds+2000);
			} catch (InterruptedException e) {}
			synchronized(hamburgerKoe) {
				while (!lagtTil) {
					if (!hamburgerKoe.erFull()) {
						lagtTil = hamburgerKoe.leggTil();
						System.out.print(navn + " har laget burger: [" + hamburgerKoe.getAntall() + "] ");
						hamburgerKoe.printElementer();
						hamburgerKoe.notifyAll();
					} else {
						try {
							System.out.println("Kø full!!! kokk: " + navn + " venter");
							hamburgerKoe.wait();
						} catch (InterruptedException e) {}
					}
				}
			} // synchronized()
		}
	} // run()

}
