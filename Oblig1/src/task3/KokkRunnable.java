package task3;

import java.util.Random;

public class KokkRunnable implements Runnable {

	private Kokk kokk;
	private Rutsjebane hamburgerKoe;
	Random rand = new Random();

	public KokkRunnable(Rutsjebane hamburgerKoe, Kokk kokk) {
		this.kokk = kokk;
		this.hamburgerKoe = hamburgerKoe;
	}

	@Override
	public void run() {
		while (true) {
			int randSeconds = rand.nextInt(4000);
			try {
				Thread.sleep(randSeconds+2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(
					kokk.getNavn() + " har laget burger:\t\t(" + hamburgerKoe.leggTil() + ") => " +
							hamburgerKoe.elementerToString()
					);
		}
	} // run()

}
