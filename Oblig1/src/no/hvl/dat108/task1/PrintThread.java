package no.hvl.dat108.task1;

import static javax.swing.JOptionPane.showMessageDialog;

public class PrintThread extends Thread {

	private Message msg;
	private int interval;
	private boolean running = true;

	public PrintThread(Message msg, int interval) {
		this.msg = msg;
		this.interval = interval;
	}

	@Override
	public void run() {
		while (running) {
			if (msg.getContent().equals("quit")) {
				showMessageDialog(null, "Programmet er nå avsluttet");
				System.exit(0);
				running = false;
				break;
			}

			System.out.println(msg.getContent());

			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} // run()
}
