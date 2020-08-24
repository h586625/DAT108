package no.hvl.dat108.sender_mottaker;

import javax.swing.JOptionPane;

public class Sender extends Thread {

	private Melding melding;

	public Sender(Melding melding) {
		this.melding = melding;
	}

	@Override
	public void run() {
		while (true) {
			String nyMelding = JOptionPane.showInputDialog("Skriv inn melding:");

			synchronized(melding) {
				// spin lock
				while (!melding.erMottatt()) {
					try {
						melding.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				melding.send(nyMelding);
				melding.notifyAll();
				System.out.println("Sender har sendt melding: " + nyMelding);
			}
		}
	}
}
