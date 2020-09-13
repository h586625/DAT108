
import java.util.Random;

public class Eksempel1_Oppgave {

	public static void main(String[] args) {
		// new Thread(() -> doSomeWildStuff()).start();
		// new Thread(() -> doSomeWildStuff()).start();
		// Enda enklere å lagre lambda-uttrykket som en variabel:
		Runnable r = () -> doSomeWildStuff();
		new Thread(r).start();
		new Thread(r).start();
	}

	public static void doSomeWildStuff() {
		Random r = new Random();
		for (int i=1; i<=10; i++) {
			System.out.println(i);
			try {
				Thread.sleep(r.nextInt(100));
			} catch (InterruptedException e) {
			}
		}
	}
}
