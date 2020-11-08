package no.hvl.dat108;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.ejb.EJB;

import org.junit.jupiter.api.Test;

public class ValideringSkjema {
	
	@EJB
	private DeltagerDAOMemory deltagerDAO;
	
	@Test
	public void validFirstname() {

		assertTrue(Skjema.isFornavnGyldig("�gir"));
		assertTrue(Skjema.isFornavnGyldig("Heidi-Marie"));
		assertTrue(Skjema.isFornavnGyldig("Herbj�rn"));
		assertTrue(Skjema.isFornavnGyldig("Silje Malene"));
		assertFalse(Skjema.isFornavnGyldig("H"));
		assertFalse(Skjema.isFornavnGyldig("�"));
		assertFalse(Skjema.isFornavnGyldig("Navn som er over 20 tegn langt"));
		assertFalse(Skjema.isFornavnGyldig(""));
		
    }
	
	@Test
	public void validLastname() {
		
		assertTrue(Skjema.isEtternavnGyldig("�gir"));
		assertTrue(Skjema.isEtternavnGyldig("Hansen-Torp"));
		assertTrue(Skjema.isEtternavnGyldig("St�len"));
		assertTrue(Skjema.isEtternavnGyldig("Mjelde Hammersland"));
		assertFalse(Skjema.isEtternavnGyldig("H"));
		assertFalse(Skjema.isEtternavnGyldig("�"));
		assertFalse(Skjema.isEtternavnGyldig("Navn som er over 20 tegn langt"));
		assertFalse(Skjema.isEtternavnGyldig(""));
		
	}
	
	@Test
	public void validMobilnr() {
		
		assertTrue(Skjema.isMobilGyldig("97084676"));
		assertTrue(Skjema.isMobilGyldig("45904590"));
		assertFalse(Skjema.isMobilGyldig("970 84 676"));
		assertFalse(Skjema.isMobilGyldig("470"));
		assertFalse(Skjema.isMobilGyldig("nittisyv"));
		assertFalse(Skjema.isMobilGyldig(""));
		assertFalse(Skjema.isMobilGyldig("970846766"));
		assertFalse(Skjema.isMobilGyldig("9708467"));
	
	}
	
	@Test
	public void validPassord() {
		
		assertTrue(Skjema.isPassordGyldig("heihei"));
		assertTrue(Skjema.isPassordGyldig("passord med ���"));
		assertFalse(Skjema.isPassordGyldig("kort"));
		assertFalse(Skjema.isPassordGyldig(""));
		assertFalse(Skjema.isPassordGyldig("   "));
		
	}
	
	@Test
	public void validPassordRep() {
		
		assertTrue(Skjema.isPassordGyldig("heihei"));
		assertTrue(Skjema.isPassordGyldig("passord med ���"));
		assertFalse(Skjema.isPassordGyldig("kort"));
		assertFalse(Skjema.isPassordGyldig(""));
		assertFalse(Skjema.isPassordGyldig("   "));
		
	}
	
	@Test
	public void validKjonn() {
		
		assertTrue(Skjema.isKjonnOppgitt("kvinne"));
		assertTrue(Skjema.isKjonnOppgitt("mann"));
		assertFalse(Skjema.isKjonnOppgitt(null));
		
	}
	
	
	
	
}
