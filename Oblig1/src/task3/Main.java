package task3;

public class Main {

	public static void main(String[] args) {

		Rutsjebane hamburgerBane = new Rutsjebane();

		Thread kokk1 = new Thread(
				new KokkRunnable(hamburgerBane, new Kokk("Blom"))
				);
		Thread kokk2 = new Thread(
				new KokkRunnable(hamburgerBane, new Kokk("Isabella"))
				);
		Thread kokk3 = new Thread(
				new KokkRunnable(hamburgerBane, new Kokk("Trond"))
				);
		Thread servitoor1 = new Thread(
				new ServitoorRunnable(hamburgerBane, new Servitoor("Cathrine"))
				);
		Thread servitoor2 = new Thread(
				new ServitoorRunnable(hamburgerBane, new Servitoor("Ida"))
				);

		System.out.println("Hamburger køen begynner!");

		kokk1.start();
		servitoor2.start();
		servitoor1.start();
		kokk2.start();
		kokk3.start();
	}
}
