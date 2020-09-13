package task1;

import java.util.Arrays;
import java.util.List;

public class Oppg1a {
	public static void main(String[] args) {
		List<String> listen = Arrays.asList("10", "1", "20", "110", "21", "12");
		System.out.println(listen);
		listen.stream()
		.mapToInt(num -> Integer.parseInt(num))
		.sorted()
		.forEach(num -> System.out.print(num + " "));
		// but the task requires a representation of a Comparator
		System.out.println();
		listen.stream()
		.sorted((a, b) -> Integer.valueOf(a).compareTo(Integer.valueOf(b)))
		.forEach(num -> System.out.print(num + " "));
		// notice that this solution sorts the list is a mutable way
		System.out.println();
		listen.sort((a, b) -> Integer.valueOf(a).compareTo(Integer.valueOf(b)));
		System.out.println(listen);
	}
}
