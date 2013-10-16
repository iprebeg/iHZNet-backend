package com.prebeg.ihznet.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="stajaliste")
public class Stajaliste {
	
	@XmlElement(name="nazivStajalista")
	private String stajaliste;
	
	@XmlElement(name="vrijemeDolaskaStajaliste")
	private String vrijemeDolaskaStajaliste;
	
	@XmlElement(name="vrijemeOdlaskaStajaliste")
	private String vrijemeOdlaskaStajaliste;
	
	public String getStajaliste() {
		return stajaliste;
	}

	public void setStajaliste(String stajaliste) {
		this.stajaliste = stajaliste;
	}

	public String getVrijemeDolaskaStajaliste() {
		return vrijemeDolaskaStajaliste;
	}

	public void setVrijemeDolaskaStajaliste(String vrijemeDolaskaStajaliste) {
		this.vrijemeDolaskaStajaliste = vrijemeDolaskaStajaliste;
	}

	public String getVrijemeOdlaskaStajaliste() {
		return vrijemeOdlaskaStajaliste;
	}

	public void setVrijemeOdlaskaStajaliste(String vrijemeOdlaskaStajaliste) {
		this.vrijemeOdlaskaStajaliste = vrijemeOdlaskaStajaliste;
	}
	
	
	

}
