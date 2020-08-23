package no.hvl.dat108;

public class MinRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("Utskrift fra tråden til runnable: " + Thread.currentThread().getName());
	}
}
