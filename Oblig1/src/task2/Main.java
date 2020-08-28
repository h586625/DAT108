package task2;

public class Main {

	public static void main(String[] args) {

		Rutsjebane hamburgerBane = new Rutsjebane();

		Thread kokk1 = new Thread(
				new KokkRunnable(hamburgerBane, new Kokk("Geralt"))
				);
		Thread kokk2 = new Thread(
				new KokkRunnable(hamburgerBane, new Kokk("Lars"))
				);
		Thread kokk3 = new Thread(
				new KokkRunnable(hamburgerBane, new Kokk("Bob"))
				);
		Thread servitoor1 = new Thread(
				new ServitoorRunnable(hamburgerBane, new Servitoor("Triss"))
				);
		Thread servitoor2 = new Thread(
				new ServitoorRunnable(hamburgerBane, new Servitoor("Cathrine"))
				);

		System.out.println("Hamburger køen begynner!");

		kokk1.start();
		servitoor2.start();
		servitoor1.start();
		kokk2.start();
		kokk3.start();
	}
}
