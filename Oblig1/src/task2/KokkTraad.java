package task2;

public class KokkTraad implements Runnable {

	private Kokk kokk;

	public KokkTraad(Kokk kokk) {
		this.kokk = kokk;
	}

	@Override
	public void run() {

		if (msg.getContent().equals("quit")) {
			showMessageDialog(null, "Du har nå avsluttet programmet.");
			System.exit(0);
			running = false;
		}
	}
}