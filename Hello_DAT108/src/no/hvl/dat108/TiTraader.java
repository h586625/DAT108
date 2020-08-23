package no.hvl.dat108;

public class TiTraader {
	public static void main(String[] args) {
		String navn = Thread.currentThread().getName();
		System.out.println("Main tråden heter " + navn);

		for (int i=1; i<=10; i++) {
			Thread t = new Thread(new MinRunnable(), "Tråd nr. " + i);
			t.start();
		}
	}
}
