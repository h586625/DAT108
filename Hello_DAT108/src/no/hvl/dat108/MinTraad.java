package no.hvl.dat108;

// Cons: we can't extend other classes now.

public class MinTraad extends Thread {
	private boolean fortsette = true;

	public MinTraad(String navn) {
		super(navn);
	}

	public void stopp() {
		fortsette = false;
	}

	@Override
	public void run() {
		System.out.println("Utskrift fra tråd");
		while (fortsette) {
			System.out.println(getName());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
