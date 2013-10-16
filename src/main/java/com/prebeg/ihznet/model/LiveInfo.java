package com.prebeg.ihznet.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="liveInfo")
public class LiveInfo {
	
	@XmlElement(name="prolaziste")
	private List<Prolaziste> prolazista = new LinkedList<Prolaziste> ();
	
	public void addProlaziste(Prolaziste prolaziste)  {
		prolazista.add(prolaziste);
	}
	
	public void addProlazista(List<Prolaziste> arolazista) {
		this.prolazista.addAll(prolazista);
	}
	
	public List<Prolaziste> getProlazista () {
		return prolazista;
	}

}
