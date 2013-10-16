package com.prebeg.ihznet.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="listaStajalista")
public class ListaStajalista {
	
	@XmlElement(name="stajaliste")
	private List<Stajaliste> stajalista = new LinkedList<Stajaliste>();
	
	
	public void add(Stajaliste stajaliste) {
		stajalista.add(stajaliste);
	}

	public List<Stajaliste> getStajalista() {
		return stajalista;
	}

	public void setStajalista(List<Stajaliste> stajalista) {
		this.stajalista = stajalista;
	}
}

