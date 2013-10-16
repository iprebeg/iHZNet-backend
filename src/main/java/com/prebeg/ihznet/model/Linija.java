package com.prebeg.ihznet.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="linija")
public class Linija {
	
	@XmlElement(name="oznake")
	private ListaOznaka oznake;
	
	@XmlElement(name="nazivLinije")
	private String nazivLinije;
	
	@XmlElement(name="odlazniKolodvor")
	private String odlazniKolodvor;
	
	@XmlElement(name="dolazniKolodvor")
	private String dolazniKolodvor;
	
	@XmlElement(name="vrijemeOdlaska")
	private String vrijemeOdlaska;
	
	@XmlElement(name="vrijemeDolaska")
	private String vrijemeDolaska;
	
	@XmlElement(name="trajanjeVoznje")
	private String trajanjeVoznje;
	
	@XmlElement(name="listaStajalista")
	private ListaStajalista listaStajalista;
	
	public String getTrajanjeVoznje() {
		return trajanjeVoznje;
	}

	public void setTrajanjeVoznje(String vrijemeVoznje) {
		this.trajanjeVoznje = vrijemeVoznje;
	}
	
	public ListaOznaka getOznake() {
		return oznake;
	}

	public void setOznake(ListaOznaka oznake) {
		this.oznake = oznake;
	}

	public String getNazivLinije() {
		return nazivLinije;
	}
	public void setNazivLinije(String nazivLinije) {
		this.nazivLinije = nazivLinije;
	}
	
	public String getOdlazniKolodvor() {
		return this.odlazniKolodvor;
	}
	
	public void setOdlazniKolodvor(String odlazniKolodvor) {
		this.odlazniKolodvor = odlazniKolodvor;
	}
	
	public String getDolazniKolodvor() {
		return this.dolazniKolodvor;
	}
	public void setDolazniKolodvor(String dolazniKolodvor) {
		this.dolazniKolodvor = dolazniKolodvor;
	}
	public String getVrijemeOdlaska() {
		return this.vrijemeOdlaska;
	}
	public void setVrijemeOdlaska(String vrijemeOdlaska) {
		this.vrijemeOdlaska = vrijemeOdlaska;
	}
	public String getVrijemeDolaska() {
		return this.vrijemeDolaska;
	}
	public void setVrijemeDolaska(String vrijemeDolaska) {
		this.vrijemeDolaska = vrijemeDolaska;
	}

	public ListaStajalista getListaStajalista() {
		return listaStajalista;
	}

	public void setListaStajalista(ListaStajalista listaStajalista) {
		this.listaStajalista = listaStajalista;
	}
	

}
