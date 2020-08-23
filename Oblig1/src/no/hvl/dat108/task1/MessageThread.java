package no.hvl.dat108.task1;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class MessageThread implements Runnable {
	private Message msg;
	private boolean running = true;

	public MessageThread(Message msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		while (running) {
			msg.setContent(showInputDialog("Skriv inn din melding, quit for å slutte"));
		}
		if (msg.getContent().equals("quit")) {
			showMessageDialog(null, "Du har nå avsluttet programmet.");
			System.exit(0);
			running = false;
		}
	}
}