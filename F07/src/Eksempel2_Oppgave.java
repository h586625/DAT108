/* Kopiert fra javabrains.io sitt Java 8 Lambda Basics kurs */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@FunctionalInterface
interface Betingelse2 {
	boolean erSann(Person p);
}

public class Eksempel2_Oppgave {

	static void printAll(List<Person> liste) {
		for (Person p : liste) {
			System.out.println(p);
		}
	}

	static void printLastNameStartsWithC(List<Person> liste) {
		for (Person p : liste) {
			if (p.getLastName().startsWith("C")) {
				System.out.println(p);
			}
		}
	}

	static void printConditionally(List<Person> liste, Betingelse2 b) {
		for (Person p : liste) {
			if (b.erSann(p)) {
				System.out.println(p);
			}
		}
	}

	static void printConditionally2(List<Person> liste, Predicate<Person> b) {
		for (Person p : liste) {
			if (b.test(p)) {
				System.out.println(p);
			}
		}
	}

	static void doConditionally(List<Person> liste, Predicate<Person> b,
			Consumer<Person> consumer) {
		for (Person p : liste) {
			if (b.test(p)) {
				consumer.accept(p);
			}
		}
	}

	public static void main(String[] args) {

		List<Person> people = Arrays.asList(
				new Person("Charles", "Dickens", 60),
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Matthew", "Arnold", 39)
				);

		// 1: Sorter personene på etternavn.
		Collections.sort(people, (a, b) -> a.getLastName().compareTo(b.getLastName()));

		// 2: Skriv ut alle personene, en linje per person.
		// 		Lag en egen hjelpemetode printAll(...) som gjør dette.
		// printAll(people);
		// doConditionally(people, p -> true, p -> System.out.println(p));
		// Benytter en metodereferanse istedet for println:
		doConditionally(people, p -> true, System.out::println);
		System.out.println();

		// 3: Skriv ut alle personene som har etternavn som begynner på C.
		//		Lag en egen hjelpemetode printLastNameStartsWithC(...) som gjør dette.
		//printLastNameStartsWithC(people);
		doConditionally(people, p -> p.getLastName().startsWith("C"), p -> System.out.println(p));
		System.out.println();

		// 4: Skriv ut alle personene som har fornavn som begynner på C.
		//		Vi ser nå at det er tungvint å lage én utskriftsmetode per
		//      spesialtilfelle. Lag en generell hjelpemetode printConditionally(...)
		//		gjør jobben i stedet. Denne tar inn listen + et lambdauttrykk.
		printConditionally(people, p -> p.getFirstName().startsWith("C"));

		// 5: Skriv ut ALLE personene ved å bruke printConditionally(...)
		// printConditionally(people, p -> true);
		// printConditionally2(people, p -> true);
	}
}
