package task2;

import java.util.Random;

public class Servitoor extends Thread {
	private String navn;
	private Rutsjebane hamburgerKoe;
	Random rand = new Random();

	public Servitoor(Rutsjebane hamburgerKoe, String navn) {
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
			boolean hentet = false;
			try {
				sleep(randSeconds+2000);
			} catch (InterruptedException e) {}
			synchronized(hamburgerKoe) {
				while (!hentet) {
					if (!hamburgerKoe.erTom()) {
						int burger = hamburgerKoe.fjern();
						if(burger > -1) {
							System.out.print(navn + " har hentet burger: [" + burger + "] ");
							hamburgerKoe.printElementer();
							hamburgerKoe.notifyAll();
							hentet = true;
						}
					} else {
						try {
							System.out.println("Koe full!!! kokk: " + navn + " venter");
							hamburgerKoe.wait();
						} catch (InterruptedException e) {}
					}
				}
			} // synchronized()
		}
	} // run()
}
