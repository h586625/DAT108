package no.hvl.dat108;

public class PauseOgStopp {

	public static void main(String[] args) throws InterruptedException {

		MinTraad t = new MinTraad("Dingdong-tr�d");
		t.start();

		Thread.sleep(5000);

		t.stopp();
	}
}
