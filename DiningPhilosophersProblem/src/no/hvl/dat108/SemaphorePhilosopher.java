package no.hvl.dat108;

import java.util.concurrent.ThreadLocalRandom;

public class SemaphorePhilosopher implements Runnable
{
	private String name;
	private Chopstick leftStick;
	private Chopstick rightStick;

	public SemaphorePhilosopher(String name, Chopstick leftStick, Chopstick rightStick)
	{
		this.name = name;
		this.leftStick = leftStick;
		this.rightStick = rightStick;
	}

	@Override
	public void run()
	{
		while (true)
		{
			if (leftStick.isFree() && rightStick.isFree())
			{
				leftStick.grab();
				System.out.println(name + " grabs left stick " + (leftStick.getId()+1) + ".");
				rightStick.grab();
				System.out.println(name + " grabs right stick " + (rightStick.getId()+1) + ".");

				eat();

				leftStick.release();
				System.out.println(name + " releases left stick "  + (leftStick.getId()+1) + ".");
				rightStick.release();
				System.out.println(name + " releases right stick " + (rightStick.getId()+1) + ".");

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
}

