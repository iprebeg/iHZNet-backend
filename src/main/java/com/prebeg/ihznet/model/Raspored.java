package com.prebeg.ihznet.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="raspored")
public class Raspored {
	
	@XmlElement(name="putovanje")
	private List<Putovanje> putovanja = new LinkedList<Putovanje> ();
	
	public void addPutovanje(Putovanje putovanje)  {
		putovanja.add(putovanje);
	}
	
	public void addPutovanja(List<Putovanje> putovanja) {
		this.putovanja.addAll(putovanja);
	}
	
	public List<Putovanje> getPutovanja () {
		return putovanja;
	}
}
