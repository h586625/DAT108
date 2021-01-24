package no.hvl.dat108;

import java.util.concurrent.Semaphore;

public class Chopstick
{

	public Semaphore mutex = new Semaphore(1);

	private int id;

	Chopstick(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	void grab()
	{
		try
		{
			mutex.acquire();
		}
		catch (Exception e)
		{
			e.printStackTrace(System.out);
		}
	}

	void release()
	{
		mutex.release();
	}

	boolean isFree()
	{
		return mutex.availablePermits() > 0;
	}
}


