package no.hvl.dat108;

public class DiningPhilosophers {

	public static void main(String[] args) throws Exception {

		final Philosopher[] philosophers = new Philosopher[5];
		Chopstick[] forks = new Chopstick[philosophers.length];

		for (int i = 0; i < forks.length; i++) {
			forks[i] = new Chopstick(i);
		}

		for (int i = 0; i < philosophers.length; i++) {
			Chopstick leftFork = forks[i];
			Chopstick rightFork = forks[(i + 1) % forks.length];

			if (i == philosophers.length-1) {
				// The last philosopher picks up the right fork first
				philosophers[i] = new Philosopher(rightFork, leftFork);
			} else {
				philosophers[i] = new Philosopher(leftFork, rightFork);
			}

			Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
			t.start();
		}
	}
}
