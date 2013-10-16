package com.prebeg.ihznet.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="kolodvor")
public class Kolodvor {
	
	@XmlElement(name="idKolodvora")
	Integer id;
	
	@XmlElement(name="nazivKolodvora")
	String naziv;
	GeoLocation lokacija;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public GeoLocation getLokacija() {
		return lokacija;
	}
	public void setLokacija(GeoLocation lokacija) {
		this.lokacija = lokacija;
	}
	
	
}
