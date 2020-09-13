
public class Oppgave {

	private static void betingetUtskrift(String s, Betingelse b) {
		if (b.erSann(s)) {
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		String str = "Hallo på do!";

		betingetUtskrift(str, s -> s.startsWith("X"));
		betingetUtskrift(str, s -> s.startsWith("H"));
		betingetUtskrift(str, s -> s.length() > 3);
		betingetUtskrift(str, s -> s.length() < 3);
	}
}

@FunctionalInterface
interface Betingelse {
	boolean erSann(String s);
}
