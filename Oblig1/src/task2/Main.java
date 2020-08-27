package task2;

public class Main {

	public static void main(String[] args) {

		Rutsjebane hamburgerBane = new Rutsjebane();
		Thread kokk1 = new KokkTraad(hamburgerBane, new Kokk("Geralt"));
		Thread kokk2 = new KokkTraad(hamburgerBane, new Kokk("Lars"));
		Thread kokk3 = new KokkTraad(hamburgerBane, new Kokk("Bob"));
		Thread serv1 = new ServitoorTraad(hamburgerBane, new Servitoor("Triss"));
		Thread serv2 = new ServitoorTraad(hamburgerBane, new Servitoor("Cathrine"));

		System.out.println("Hamburger køen begynner!");

		kokk1.start();
		serv1.start();
		serv2.start();
		kokk2.start();
		kokk3.start();
	}
}
