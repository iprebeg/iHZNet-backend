package com.prebeg.ihznet.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="listaKolodvora")
public class ListaKolodvora {
	
	@XmlElement(name="kolodvor")
	List<Kolodvor> kolodvori = new LinkedList<Kolodvor>();

	public void addKolodvor(Kolodvor kolodvor) {
		kolodvori.add(kolodvor);
	}

	public List<Kolodvor> getKolodvori() {
		return kolodvori;
	}
	
	public Kolodvor kolodvorWithId(Integer id) {
		for (Kolodvor k : kolodvori) {
			if (k.getId().equals(id))
				return k;
		}
		return null;
	}
	
}
