package task3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Oppg3 {

	public static void main(String[] args) {

		List<Ansatt> ansatte = Arrays.asList(
				new Ansatt("Trond", "Hauge", Kjonn.MANN, "Spillutvikler sjef", 800_000),
				new Ansatt("Ida", "Mjelde", Kjonn.KVINNE, "Jetpack-hero", 10_000_000),
				new Ansatt("Isabella", "Nesheim", Kjonn.KVINNE, "Biologiprogrammerer sjef", 950_000),
				new Ansatt("Thomas", "Blom", Kjonn.MANN, "Kinogud", 300_0000),
				new Ansatt("Cathrine", "Bjerke Monsen", Kjonn.KVINNE, "Webutvikler", 600_000)
				);

		// a)
		System.out.println("Alle ansattes etternavn:");
		List<String> ansattesEtternavn = ansatte
				.stream()
				.map(a -> a.getEtternavn())
				.collect(Collectors.toList());
		System.out.println(ansattesEtternavn + "\n");

		// b)
		System.out.println("Antall ansatte som er kvinner:");
		long antallKvinner = ansatte
				.stream()
				.filter(a -> a.getKjonn() == Kjonn.KVINNE)
				.count();
		System.out.println(antallKvinner + "\n");

		// c)
		List<Integer> kvinnersAarslonn = ansatte
				.stream()
				.filter(a -> a.getKjonn() == Kjonn.KVINNE)
				.map(a -> a.getAarslonn())
				.collect(Collectors.toList());

		OptionalDouble gjennomsnittsLonnForKvinner = kvinnersAarslonn
				.stream()
				.mapToInt(x -> x)
				.average();
		gjennomsnittsLonnForKvinner.ifPresent(
				avg -> System.out.println("Kvinnenes gjennomsnittslønn er " + avg + "kr\n")
				);

		// d)
		System.out.println("Ansatte før sjefstillegg:");
		System.out.println(ansatte);
		List<Ansatt> ansatteEtterSjefsTillegg = ansatte
				.stream()
				.filter(a -> a.getStilling().contains("sjef"))
				.collect(Collectors.toList());
		ansatteEtterSjefsTillegg.forEach(
				a -> a.setAarslonn((a.getAarslonn() * 107)/100)
				);
		System.out.println("Ansatte etter sjefstillegg:");
		System.out.println(ansatteEtterSjefsTillegg + "\n");

		// e)
		boolean finnesAnsatteSomTjenerMerEnn800k = ansatte
				.stream().anyMatch(a -> a.getAarslonn() > 800_000);
		System.out.println("Finnes det ansatte som tjener mer enn 800 000 kroner?");
		System.out.println(finnesAnsatteSomTjenerMerEnn800k + "\n");

		// f)
		String ansatteSomEnStreng = ansatte.stream()
				.map(a -> a.toString() + "\n").reduce("", String::concat);
		System.out.println("Ansatte som en streng, uten løkke:");
		System.out.println(ansatteSomEnStreng + "\n");

		// g)
		Optional<Ansatt> ansattMedLavestlonn = ansatte.
				stream()
				.min(Comparator.comparing(Ansatt::getAarslonn));
		System.out.println("Ansatt med lavest lønn:");
		System.out.println(ansattMedLavestlonn + "\n");

		// h)
		List<Integer> heltall = IntStream.rangeClosed(1, 999)
				.boxed().collect(Collectors.toList());

		Integer heltallSum = heltall
				.stream()
				.mapToInt(Integer::intValue)
				.filter(a -> a%3 == 0 || a%5 == 0)
				.sum();
		System.out.println(heltallSum);
	}
}
