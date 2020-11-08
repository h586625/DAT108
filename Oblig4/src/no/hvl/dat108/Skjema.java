package no.hvl.dat108;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

public class Skjema {

	@EJB
	private DeltagerDAOMemory deltagerDAO;

	private String fornavn;
	private String fornavnFeil;
	private String etternavn;
	private String etternavnFeil;
	private String mobil;
	private String mobilFeil;
	private String passord;
	private String passordFeil;
	private String passordRep;
	private String passordRepFeil;
	private String kjonn;
	private String kjonnFeil;

	public Skjema(HttpServletRequest request) {
		fornavn = request.getParameter("fornavn");
		etternavn = request.getParameter("etternavn");
		mobil = request.getParameter("mobil");
		passord = request.getParameter("passord");
		passordRep = request.getParameter("passordRepetert");
		kjonn = request.getParameter("kjonn");
	}



	// =====ERROR MESSAGES=====

	public boolean isAllInputGyldig() {
		return isFornavnGyldig(fornavn)
				&& isEtternavnGyldig(etternavn)
				&& isMobilGyldig(mobil)
				&& isPassordGyldig(passord)
				&& isPassordLike(passord, passordRep)
				&& isKjonnOppgitt(kjonn);
	}

	public static boolean isFornavnGyldig(String fornavn) {
		return fornavn.length() > 1 && fornavn.length() < 20 && fornavn.matches("^[A-ZÆØÅ].*");
	}

	public static boolean isEtternavnGyldig(String etternavn) {
		return etternavn.length() > 1 && etternavn.length() < 20 && etternavn.matches("^[A-ZÆØÅ].*");
	}

	public static boolean isMobilGyldig(String mobil) {
		return mobil.matches("\\d{8}");
	}

	public static boolean isPassordGyldig(String passord) {
		return passord.length() > 5;
	}

	public static boolean isPassordLike(String passord, String passordRep) {
		return passord.equals(passordRep);
	}

	public static boolean isKjonnOppgitt(String kjonn) {
		return kjonn != null;
	}

	public void settOppFeilmeldinger() {

		if (!isFornavnGyldig(fornavn)) {
			fornavn = "";
			fornavnFeil = "Ugyldig fornavn!";
		}
		if (!isEtternavnGyldig(etternavn)) {
			etternavn = "";
			etternavnFeil = "Ugyldig etternavn!";
		}
		if (!isMobilGyldig(mobil)) {
			mobil = "";
			mobilFeil = "Ugyldig mobilnr!";
		}
		if (!isPassordGyldig(passord)) {
			passord = "";
			passordFeil = "Ugyldig passord";
		}
		if (!isPassordLike(passord, passordRep)) {
			passordRep = "";
			passordRepFeil = "Passordene må være like";
		}
		if (!isKjonnOppgitt(kjonn)) {
			kjonn = "";
			kjonnFeil = "Du må oppgi kjønn";
		}
	}



	// =====CREATE FORM OBJECT=====

	public Deltager lagDeltager() {
		Passord passwordHashedAndSalted = Passord.lagPassord(passord);
		Deltager d = new Deltager(mobil, passwordHashedAndSalted, fornavn, etternavn, kjonn);
		return d;
	}



	// =====GETTERS=====

	public String getFornavn() {
		return fornavn;
	}

	public String getFornavnFeil() {
		return fornavnFeil;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public String getEtternavnFeil() {
		return etternavnFeil;
	}

	public String getMobil() {
		return mobil;
	}

	public String getMobilFeil() {
		return mobilFeil;
	}

	public String getPassord() {
		return passord;
	}

	public String getPassordFeil() {
		return passordFeil;
	}

	public String getPassordRepetert() {
		return passordRep;
	}

	public String getPassordRepetertFeil() {
		return passordRepFeil;
	}

	public String getKjonn() {
		return kjonn;
	}

	public String getKjonnFeil() {
		return kjonnFeil;
	}
}
