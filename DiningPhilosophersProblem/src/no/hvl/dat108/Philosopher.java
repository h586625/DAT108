package no.hvl.dat108;

import java.util.concurrent.ThreadLocalRandom;

public class Philosopher implements Runnable {
	// The forks on either side of this Philosopher
	private Chopstick leftFork;
	private Chopstick rightFork;

	public Philosopher(Chopstick leftFork, Chopstick rightFork) {
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}

	private synchronized void doAction(String action) throws InterruptedException {
		System.out.println(
				Thread.currentThread().getName() + " " + action
				);
		Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
	}

	@Override
	public void run() {
		try {
			while (true) {
				// thinking
				doAction("Thinking");
				synchronized (leftFork) {
					doAction("Picked up left fork " + (leftFork.getId()+1));
				}
				// eating
				synchronized (rightFork) {
					doAction("Picked up right fork " + (rightFork.getId()+1) + " - eating");
					doAction("Put down right fork " + (rightFork.getId()+1));
				}
				// Back to thinking
				doAction("Put down left fork " + (leftFork.getId()+1) + ". Back to thinking");
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return;
		}
	}
}
