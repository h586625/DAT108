
public class Eksempel4 {

	public static void main(String[] args) {

		//Lage en runnable som sier Hallo
		//Lage og kj�re 2 tr�der med denne runnable-en
		//Skulle �nske at de to tr�dene ogs� skriver ut tr�d-id-en.

		Runnable r = () -> {
			String navn = Thread.currentThread().getName();
			System.out.println("Hallo fra " + navn);
		};

		new Thread(r, "T1").start();
		new Thread(r, "T2").start();
	}

}

