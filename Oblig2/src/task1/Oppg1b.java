package task1;

import java.util.function.BiFunction;

public class Oppg1b {

	public static int beregn(int a, int b, BiFunction<Integer, Integer, Integer> operasjon) {
		return operasjon.apply(a, b);
	}

	public static BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;

	public static BiFunction<Integer, Integer, Integer> storst = (a, b) -> Integer.max(a, b);

	public static BiFunction<Integer, Integer, Integer> avstand = (a, b) -> Math.abs(a-b);

	public static void main(String[] args) {

		System.out.println(beregn(12, 13, sum));
		System.out.println(beregn(-5, 3, storst));
		System.out.println(beregn(54, 45, avstand));
	}
}
