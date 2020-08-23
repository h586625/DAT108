package no.hvl.dat108.task1;

public class Main {

	public static void main(String[] args) {
		Message msg = new Message("Hallo verden!");

		Thread messageThread = new Thread(new MessageThread(msg));
		Thread printThread = new Thread(new PrintThread(msg, 3000));

		messageThread.start();
		printThread.start();
	}

}
