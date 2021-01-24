package no.hvl.dat108;

public class DiningPhilosophersSemaphore
{
	static int n = 5;
	static SemaphorePhilosopher[] philosophers = new SemaphorePhilosopher[n];
	static Chopstick[] chopsticks = new Chopstick[n];
	static String[] names = {"Locke", "Plato", "Aristoteles", "Descartes", "Kant"};

	public static void main(String[] args)
	{
		for (int i = 0; i < n; i++)
		{
			chopsticks[i] = new Chopstick(i);
		}

		for (int i = 0; i < n; i++)
		{
			Thread philosopher = new Thread(
					new SemaphorePhilosopher(names[i], chopsticks[i], chopsticks[(i + 1) % n])
					);
			philosopher.start();
		}
	}
}
