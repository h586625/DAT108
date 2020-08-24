package no.hvl.dat108.a.busywait;

public class Main {

	private static String melding;

	public static void main(String[] args) {

		Thread printlnTraad = new Thread(new Runnable() {
			@Override
			public void run() {
				//				 Den mest naive løsningen, men
				//				 fortsatt busy-waiting, som
				//				 ikke er særlig bra mtp. CPU-bruk
				while (melding == null) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(melding);
			}
		});

		Thread giVerdiTraad = new Thread(new Runnable() {
			@Override
			public void run() {
				melding = "Dette er en melding";
			}
		});

		printlnTraad.start();
		giVerdiTraad.start();
	}
}
