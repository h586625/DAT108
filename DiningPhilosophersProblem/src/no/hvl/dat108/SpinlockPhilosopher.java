package no.hvl.dat108;

import java.util.concurrent.ThreadLocalRandom;

public class SpinlockPhilosopher extends Thread
{
	private String name;
	private boolean[] chopsticks;
	private  int leftStick;
	private  int rightStick;

	public SpinlockPhilosopher(String name, int leftStick, int rightStick,	 boolean[] chopsticks)
	{
		this.name = name;
		this.leftStick = leftStick;
		this.rightStick = rightStick;
		this.chopsticks = chopsticks;
	}

	@Override
	public void run()
	{
		while (true)
		{

			if (isFree(leftStick) && isFree(rightStick))
			{
				grab(leftStick);
				System.out.println(name + " grabs left stick.");
				grab(rightStick);
				System.out.println(name + " grabs right stick.");
				eat();
				release(leftStick);
				System.out.println(name + " releases left stick.");
				release(rightStick);
				System.out.println(name + " releases right stick.");
				think();
			}
		}
	}

	void eat()
	{
		try
		{
			int sleepTime = ThreadLocalRandom.current().nextInt(1000, 10000);
			System.out.println(name + " eats for " + sleepTime);
			Thread.sleep(sleepTime);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	void think()
	{
		try
		{
			int thinkTime = ThreadLocalRandom.current().nextInt(1000, 10000);
			System.out.println(name + " thinks for " + thinkTime);
			Thread.sleep(thinkTime);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	void grab(int actual)
	{
		while (!chopsticks[actual])
		{
			//Waiting...
		}
		chopsticks[actual] = false;
	}

	void release(int actual)
	{
		chopsticks[actual] = true;
	}

	boolean isFree(int actual)
	{
		return chopsticks[actual];
	}
}
