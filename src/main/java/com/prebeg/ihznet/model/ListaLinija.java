package com.prebeg.ihznet.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="linije")
public class ListaLinija {

	@XmlElement(name="linija")
	private List<Linija> linije = new LinkedList<Linija>();
	
	public void add(Linija linija) {
		linije.add(linija);
	}

	public List<Linija> getLinije() {
		return linije;
	}

	public void setLinije(List<Linija> linije) {
		this.linije = linije;
	}
}
