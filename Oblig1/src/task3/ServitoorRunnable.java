package task3;

import java.util.Random;

public class ServitoorRunnable implements Runnable{
	private Servitoor servitoor;
	private Rutsjebane hamburgerKoe;
	Random rand = new Random();

	public ServitoorRunnable(Rutsjebane hamburgerKoe, Servitoor servitoor) {
		this.servitoor = servitoor;
		this.hamburgerKoe = hamburgerKoe;
	}

	@Override
	public void run() {
		while (true) {
			int randSeconds = rand.nextInt(4000);
			boolean hentet = false;
			try {
				Thread.sleep(randSeconds+2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(hamburgerKoe) {
				while (!hentet) {
					if (!hamburgerKoe.erTom()) {
						int burger = hamburgerKoe.fjern();
						if(burger > -1) {
							System.out.print(servitoor.getNavn() + " har hentet burger:\t\t(" + burger + ") => ");
							hamburgerKoe.printElementer();
							hentet = true;
						}
					} else {
						System.out.println("### K�en er n� tom! Servit�ren " + servitoor.getNavn() + " venter ###");
					}
				}
			} // synchronized()
		}
	} // run()
}
