package com.prebeg.ihznet.service;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.prebeg.ihznet.data.scraper.RasporedScraper;
import com.prebeg.ihznet.model.Raspored;

@Component
public class RasporedService {
	
	private transient final Log log = LogFactory.getLog(getClass());
	
	@Resource
	RasporedScraper rasporedScraper;
	
	public Raspored getRaspored(Integer odlazniKolodvorId, Integer dolazniKolodvorId, String datum, String dv) {
		
		Raspored raspored = null;
		try {
			raspored = rasporedScraper.getRaspored(odlazniKolodvorId, dolazniKolodvorId, datum, dv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return raspored;
	}
}
