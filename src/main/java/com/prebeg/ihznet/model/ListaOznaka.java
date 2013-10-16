package com.prebeg.ihznet.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="oznake")
public class ListaOznaka {

	@XmlElement(name="oznaka")
	private List<Oznaka> oznake;

	public List<Oznaka> getOznake() {
		return oznake;
	}

	public void setOznake(List<Oznaka> oznake) {
		this.oznake = oznake;
	}	
	
	
}
