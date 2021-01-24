package no.hvl.dat108;

public class LambdaOppgaveKlasse {
	private String something;
	private String something2;
	private int someInt;

	public LambdaOppgaveKlasse(String something, String something2, int someInt) {
		this.something = something;
		this.something2 = something2;
		this.someInt = someInt;
	}

	// Høyreklikk -> Source -> Generate Getters and Setters...
	// når alle klassevariablene er definerte


	@Override
	public String toString() {
		return something + ", " + something2 + ", " + someInt;
	}

	public String getSomething() {
		return something;
	}

	public void setSomething(String something) {
		this.something = something;
	}

	public String getSomething2() {
		return something2;
	}

	public void setSomething2(String something2) {
		this.something2 = something2;
	}

	public int getSomeInt() {
		return someInt;
	}

	public void setSomeInt(int someInt) {
		this.someInt = someInt;
	}
}
