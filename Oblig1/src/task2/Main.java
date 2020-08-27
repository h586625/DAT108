package task2;

public class Main {

	public static void main(String[] args) {

		Rutsjebane hamburgerBane = new Rutsjebane();
		Thread kokk1 = new Kokk(hamburgerBane, "Geralt");
		Thread kokk2 = new Kokk(hamburgerBane, "Lars");
		Thread kokk3 = new Kokk(hamburgerBane, "Bob");
		Thread serv1 = new Servitoor(hamburgerBane, "Triss");
		Thread serv2 = new Servitoor(hamburgerBane, "Cathrine");

		System.out.println("Hamburger køen begynner!");

		kokk1.start();
		serv1.start();
		serv2.start();
		kokk2.start();
		kokk3.start();
	}
}
