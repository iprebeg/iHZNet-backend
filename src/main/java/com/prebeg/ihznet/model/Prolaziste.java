package com.prebeg.ihznet.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="prolaziste")
public class Prolaziste {
	
	@XmlElement(name="nazivProlazista")
	private String prolaziste;
	
	@XmlElement(name="vrijemeDolaskaProlaziste")
	private String vrijemeDolaskaProlaziste;
	
	@XmlElement(name="vrijemeOdlaskaProlaziste")
	private String vrijemeOdlaskaProlaziste;
	
	@XmlElement(name="kasnjenjeDolaska")
	private String kasnjenjeDolaska;
	
	@XmlElement(name="kasnjenjeOdlaska")
	private String kasnjenjeOdlaska;
	
	public String getKasnjenjeDolaska() {
		return kasnjenjeDolaska;
	}

	public void setKasnjenjeDolaska(String kasnjenjeDolaska) {
		this.kasnjenjeDolaska = kasnjenjeDolaska;
	}

	public String getKasnjenjeOdlaska() {
		return kasnjenjeOdlaska;
	}

	public void setKasnjenjeOdlaska(String kasnjenjeOdlaska) {
		this.kasnjenjeOdlaska = kasnjenjeOdlaska;
	}

	public String getProlaziste() {
		return prolaziste;
	}

	public void setProlaziste(String prolaziste) {
		this.prolaziste = prolaziste;
	}

	public String getVrijemeDolaskaProlaziste() {
		return vrijemeDolaskaProlaziste;
	}

	public void setVrijemeDolaskaProlaziste(String vrijemeDolaskaProlaziste) {
		this.vrijemeDolaskaProlaziste = vrijemeDolaskaProlaziste;
	}

	public String getVrijemeOdlaskaProlaziste() {
		return vrijemeOdlaskaProlaziste;
	}

	public void setVrijemeOdlaskaProlaziste(String vrijemeOdlaskaProlaziste) {
		this.vrijemeOdlaskaProlaziste = vrijemeOdlaskaProlaziste;
	}
}
