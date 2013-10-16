package com.prebeg.ihznet.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="oznaka")
public enum Oznaka {
	RAZRED1, RAZRED2, REZERVACIJA, RESTORAN, BUFFET, KREVET, BICIKL, AUTOMOBIL
}
