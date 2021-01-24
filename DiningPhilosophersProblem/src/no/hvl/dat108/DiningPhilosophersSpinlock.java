package no.hvl.dat108;

import java.util.Arrays;

public class DiningPhilosophersSpinlock {
	static final int PHILOSOPHERS_NUM = 5;
	static boolean[] chopsticks = new boolean[PHILOSOPHERS_NUM];
	static SpinlockPhilosopher[] philosophers = new SpinlockPhilosopher[PHILOSOPHERS_NUM];
	static String[] philosopherNames = {"Locke", "Plato", "Aristoteles", "Descartes", "Kant"};

	public static void main(String[] args)
	{
		Arrays.fill(chopsticks, Boolean.TRUE);

		for (int i = 0; i < PHILOSOPHERS_NUM; i++)
		{
			philosophers[i] = new SpinlockPhilosopher(philosopherNames[i], i, (i + 1) % 5, chopsticks);
			philosophers[i].start();
		}
	}
}
