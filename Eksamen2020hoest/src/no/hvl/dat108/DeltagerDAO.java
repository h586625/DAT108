package no.hvl.dat108;

import java.util.List;

public class DeltagerDAO {
	List<Deltager finnAlle() {
		return em.createQuery("SELECT d FROM Deltager d", Deltager.class).getResultList();
	}

	void registrerBetalingFor(String mobil) {
		Deltager d = finnDeltager(String mobil);
		if (d != null) {
			d.setBetalt(true);
			em.merge(d);
		}
	}
}
