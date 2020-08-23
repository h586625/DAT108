package no.hvl.dat108;

// Without RC, it should have been 0

public class RaceCondition {

	public static void main(String[] args) throws InterruptedException {

		Teller teller = new Teller();

		Thread telleOppTraad = new TelleOppTraad(teller, 10000);
		Thread telleNedTraad = new TelleNedTraad(teller, 10000);

		telleOppTraad.start();
		telleNedTraad.start();

		telleOppTraad.join();
		telleNedTraad.join();

		// Because of join(), we are sure that
		// the threads are finished working
		System.out.println(teller.getVerdi());
	}
}
