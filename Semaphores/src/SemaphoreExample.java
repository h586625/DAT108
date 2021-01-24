import java.util.concurrent.Semaphore;

public class SemaphoreExample {

	static int count = 0;
	static int step = 1;
	static int limit = 100;
	static Semaphore sem = new Semaphore(1);

	public static void main(String[] args) {

		//Tries to increase count to 100
		Thread increaser = new Thread(new Runnable() {
			@Override
			public void run() {
				while (count < limit) {
					try {
						sem.acquire(); 			//wait()
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					changeCount(step);			//Critical section
					sem.release();				//signal()
				}
				System.out.println("I've reached my limit!");
			}
		});

		//Tries decreasing count to -100
		Thread decreaser = new Thread(new Runnable() {
			@Override
			public void run() {
				while (count > -limit) {
					try {
						sem.acquire();			//wait()
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					changeCount(-step);			//Critical section
					sem.release();				//signal()
				}
				System.out.println("I've reached my limit!");
			}
		});

		increaser.start();
		decreaser.start();

	}

	static void changeCount(int num) {
		count += num;
		System.out.println("The count is now " + count);
	}
}
