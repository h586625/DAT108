package no.hvl.dat108;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "dat108oblig4")
public class Deltager {

	@Id
	private String mobil;
	private String fornavn;
	private String etternavn;
	private String kjonn;
	@Embedded
	private Passord passord;

	public Deltager(String mobil, Passord passord, String fornavn, String etternavn, String kjonn) {
		this.mobil = mobil;
		this.passord = passord;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.kjonn = kjonn;
	}

	public Deltager() {}

	public String getMobil() {
		return mobil;
	}
	public Passord getPassord() {
		return passord;
	}
	public String getFornavn() {
		return fornavn;
	}
	public String getEtternavn() {
		return etternavn;
	}
	public String getKjonn() {
		return kjonn;
	}

	@Override
	public String toString() {
		return "Deltager [mobil=" + mobil + ", fornavn=" + fornavn + ", etternavn=" + etternavn + ", kjonn=" + kjonn
				+ "]";
	}
}
