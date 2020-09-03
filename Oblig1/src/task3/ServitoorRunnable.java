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
			try {
				Thread.sleep(randSeconds+2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (hamburgerKoe.getAntall() > 0) {
				System.out.println(
						servitoor.getNavn() + " har hentet burger:\t\t(" + hamburgerKoe.fjern() + ") => " +
								hamburgerKoe.elementerToString()
						);
			}
		}
	} // run()
}
