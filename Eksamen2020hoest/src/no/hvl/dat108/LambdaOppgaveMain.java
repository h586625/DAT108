package no.hvl.dat108;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaOppgaveMain
{
	public static Predicate<Integer> deleligMedTre(int x) {
		return tall -> tall % x == 0;
	}

	public static void main(String[] args)
	{
		List<String> strenger = Arrays.asList(
				new String("Hei på deg"),
				new String("hllo på dO"),
				new String("tingeling")
				);
		List<Integer> heltall = Arrays.asList(
				Integer.valueOf(2),
				Integer.valueOf(5),
				Integer.valueOf(9)
				);
		List<String> passordlisten = Arrays.asList(
				"qwerty","123","passord", "peace&love", "abc", "12345678", "admin",
				"tomee", "fotball", "hei på deg");
		List<String> tiMestVanligePassord = Arrays.asList(
				"123456","123456789", "qwerty", "password", "1234567", "12345678",
				"12345", "iloveyou", "111111", "123123");

		// a)
		Function<String, String> toLowerCase = s -> s.toLowerCase();
		strenger.stream().map(toLowerCase).forEach(System.out::println);

		// b)
		heltall.stream().filter(deleligMedTre(3)).forEach(System.out::println);

		// c)
		List<String> nyListe = passordlisten.stream()
				.filter(p -> tiMestVanligePassord.contains(p))
				.collect(Collectors.toList());
		nyListe.forEach(System.out::println);

		// d)
		OptionalDouble gjennomsnittligPassordlengde = passordlisten
				.stream()
				.map(i -> i.length())
				.mapToInt(x -> x)
				.average();
		gjennomsnittligPassordlengde.ifPresent(
				avg -> System.out.println("Gjennomsnittlig passordlengde er " + avg + "\n")
				);

	}
}
