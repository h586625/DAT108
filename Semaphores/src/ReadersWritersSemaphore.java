import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class ReadersWritersSemaphore
{
	//Problem 1 - No reader should be kept waiting unless a writer
	//has already obtained permission to use the shared object

	//Problem 2 - Once a writer is ready, that writer should perform
	//its write as soon as possible.

	static final int READERS_NUM = 4;
	static final int WRITERS_NUM = 2;

	static Thread[] readers = new Thread[READERS_NUM];
	static Thread[] writers = new Thread[WRITERS_NUM];

	static int readCount, writeCount = 0;

	static int readTime, writeTime, sleepTime;

	static Semaphore readLock = new Semaphore(1);
	static Semaphore writeLock = new Semaphore(1);
	static Semaphore readAttempt = new Semaphore(1);
	static Semaphore dataBase = new Semaphore(1);

	static class Read implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					//ENTRY SECTION
					readAttempt.acquire();
					readLock.acquire();
					readCount++;
					if (readCount == 1)
						dataBase.acquire();
					readLock.release();
					readAttempt.release();

					//READING SECTION
					System.out.println(Thread.currentThread().getName() + " is performing reading.");
					readTime = ThreadLocalRandom.current().nextInt(0, 1000);
					Thread.sleep(readTime);
					System.out.println(Thread.currentThread().getName() + " has finished reading.");

					//EXIT SECTION
					readLock.acquire();
					readCount--;
					if (readCount == 0)
						dataBase.release();
					readLock.release();

					//REST SECTION TO HINDER STARVATION
					sleepTime = ThreadLocalRandom.current().nextInt(0, 1000);
					Thread.sleep(sleepTime);
				}
				catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	static class Write implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					//ENTRY SECTION
					writeLock.acquire();
					writeCount++;
					if (writeCount == 1)
						readAttempt.acquire();
					writeLock.release();

					//WRITING SECTION
					dataBase.acquire();
					System.out.println(Thread.currentThread().getName() + " is performing writing.");
					writeTime = ThreadLocalRandom.current().nextInt(0, 1000);
					Thread.sleep(writeTime);
					System.out.println(Thread.currentThread().getName() + " has finished writing.");
					dataBase.release();

					//EXIT SECTION
					writeLock.acquire();
					writeCount--;
					if (writeCount == 0)
						readAttempt.release();
					writeLock.release();

					//REST SECTION TO HINDER STARVATION
					sleepTime = ThreadLocalRandom.current().nextInt(0, 1000);
					Thread.sleep(sleepTime);
				}
				catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Read read = new Read();
		Write write = new Write();

		for (int i = 0; i < READERS_NUM; i++) {
			readers[i] = new Thread(read, "Reader " + (i + 1));
			readers[i].start();
		}

		for (int i = 0; i < WRITERS_NUM; i++) {
			writers[i] = new Thread(write, "Writer " + (i + 1));
			writers[i].start();
		}
	}
}