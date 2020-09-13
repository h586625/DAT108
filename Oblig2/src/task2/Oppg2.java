package task2;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class Oppg2 {

	public static BiFunction<Integer, Ansatt, Integer> fastKroneTillegg(int tillegg) {
		return (lonn, a) -> lonn + tillegg;
	}

	public static BiFunction<Integer, Ansatt, Integer> fastProsentTillegg(int tillegg) {
		return (lonn, a) -> ((lonn * (100 + tillegg)) / 100);
	}

	private static void lonnsoppgjor(List<Ansatt> ansatte, BiFunction<Integer, Ansatt, Integer> beregning) {
		ansatte.forEach((a) -> {
			int aarslonn = a.getAarslonn();
			a.setAarslonn(beregning.apply(aarslonn, a));
		});
	}

	public static BiFunction<Integer, Ansatt, Integer> fastKroneTilleggHvisLavLonn(int tillegg, int lavlonn) {
		return (lonn, a) -> {
			return (lonn <= lavlonn) ? fastKroneTillegg(tillegg).apply(lonn, a) : lonn;
		};
	}

	private static BiFunction<Integer, Ansatt, Integer> fastProsentTilleggHvisKvinne(int tillegg) {
		return (lonn, a) -> {
			return (a.getKjonn() == Kjonn.KVINNE) ? fastProsentTillegg(tillegg).apply(lonn, a) : lonn;
		};
	}

	private static void skrivUtAlle(List<Ansatt> ansatte) {
		ansatte.forEach(System.out::println);
	}

	public static void main(String[] args) {

		List<Ansatt> ansatte = Arrays.asList(
				new Ansatt("Trond", "Hauge", Kjonn.MANN, "Spillutvikler", 800000),
				new Ansatt("Ida", "Mjelde", Kjonn.KVINNE, "Jetpack-hero", 10000000),
				new Ansatt("Isabella", "Nesheim", Kjonn.KVINNE, "Biologiprogrammerer", 950000),
				new Ansatt("Thomas", "Blom", Kjonn.MANN, "Kinogud", 3000000),
				new Ansatt("Cathrine", "Bjerke Monsen", Kjonn.KVINNE, "Webutvikler", 600000)
				);

		System.out.println("Alle ansatte før lønnsoppgjør:");
		System.out.println("==============================");
		skrivUtAlle(ansatte);
		System.out.println("\nAlle ansatte etter fast kronetillegg:");
		System.out.println("==============================");
		lonnsoppgjor(ansatte, fastKroneTillegg(20000));
		skrivUtAlle(ansatte);
		System.out.println("\nAlle ansatte etter fast prosenttillegg:");
		System.out.println("==============================");
		lonnsoppgjor(ansatte, fastProsentTillegg(5));
		skrivUtAlle(ansatte);
		System.out.println("\nAlle ansatte etter fast kronetillegg basert på lav lønn:");
		System.out.println("==============================");
		lonnsoppgjor(ansatte, fastKroneTilleggHvisLavLonn(10000, 900000));
		skrivUtAlle(ansatte);
		System.out.println("\nAlle ansatte etter fast prosenttillegg basert på kjønn:");
		System.out.println("==============================");
		lonnsoppgjor(ansatte, fastProsentTilleggHvisKvinne(15));
		skrivUtAlle(ansatte);
	}
}
