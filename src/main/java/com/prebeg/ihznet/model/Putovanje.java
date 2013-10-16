package com.prebeg.ihznet.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="putovanje")
public class Putovanje {
	
	@XmlElement(name="izravno")
	private Boolean izravno;
	
	@XmlElement(name="linije")
	private ListaLinija listaLinija;
	
	@XmlElement(name="relacija")
	private String relacija;
	
	@XmlElement(name="datumPolaska")
	private String datumPolaska;
	
	@XmlElement(name="brojPresjedanja")
	private int brojPresjedanja;
	
	@XmlElement(name="ukupnoTrajanjeVoznje")
	private String ukupnoTrajanjeVoznje;
	
	@XmlElement(name="ukupnoTrajanjeCekanja")
	private String ukupnoTrajanjeCekanja;
	
	@XmlElement(name="ukupnoTrajanjePutovanja")
	private String ukupnoTrajanjePutovanja;
	
	public Boolean getIzravno() {
		return izravno;
	}

	public String getRelacija() {
		return relacija;
	}

	public String getDatumPolaska() {
		return datumPolaska;
	}

	public int getBrojPresjedanja() {
		return brojPresjedanja;
	}

	
	
	public void setIzravno(Boolean izravno) {
		this.izravno = izravno;
	}


	public void setRelacija(String relacija) {
		this.relacija = relacija;
	}

	public void setDatumPolaska(String datumPolaska) {
		this.datumPolaska = datumPolaska;
	}

	public void setBrojPresjedanja(int brojPresjedanja) {
		this.brojPresjedanja = brojPresjedanja;
	}

	public String getUkupnoTrajanjeVoznje() {
		return ukupnoTrajanjeVoznje;
	}

	public void setUkupnoTrajanjeVoznje(String ukupnoTrajanjeVoznje) {
		this.ukupnoTrajanjeVoznje = ukupnoTrajanjeVoznje;
	}

	public String getUkupnoTrajanjeCekanja() {
		return ukupnoTrajanjeCekanja;
	}

	public void setUkupnoTrajanjeCekanja(String ukupnoTrajanjeCekanja) {
		this.ukupnoTrajanjeCekanja = ukupnoTrajanjeCekanja;
	}

	public String getUkupnoTrajanjePutovanja() {
		return ukupnoTrajanjePutovanja;
	}

	public void setUkupnoTrajanjePutovanja(String ukupnoTrajanjePutovanja) {
		this.ukupnoTrajanjePutovanja = ukupnoTrajanjePutovanja;
	}

	public ListaLinija getListaLinija() {
		return listaLinija;
	}

	public void setListaLinija(ListaLinija listaLinija) {
		this.listaLinija = listaLinija;
	}

	

	
}
