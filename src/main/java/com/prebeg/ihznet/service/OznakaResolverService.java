package com.prebeg.ihznet.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.prebeg.ihznet.model.ListaOznaka;
import com.prebeg.ihznet.model.Oznaka;

@Component
public class OznakaResolverService {

	private transient final Log log = LogFactory.getLog(getClass());

	public ListaOznaka resolve(HtmlTableCell tableCell)
	{
		//log.info("resolving images");
		
		List<Oznaka> oznake = new LinkedList<Oznaka>();

			String str = tableCell.asXml();
				
			if (str.contains("sl51"))
				oznake.add(Oznaka.RAZRED1);
			
			if (str.contains("sl52"))
				oznake.add(Oznaka.RAZRED2);
			
			if (str.contains("sl53") || str.contains("sl54"))
				oznake.add(Oznaka.REZERVACIJA);
			
			if (str.contains("sl55"))
				oznake.add(Oznaka.RESTORAN);
	
			if (str.contains("sl56"))
				oznake.add(Oznaka.BUFFET);
			
			if (str.contains("sl57") || str.contains("sl58"))
				oznake.add(Oznaka.KREVET);
			
			if (str.contains("sl59"))
				oznake.add(Oznaka.BICIKL);
			
			if (str.contains("sl60"))
				oznake.add(Oznaka.AUTOMOBIL);	
	
		if (oznake.size() == 0) {
			return null;
		}
		
		ListaOznaka lOznaka = new ListaOznaka();
		lOznaka.setOznake(oznake);
		return lOznaka;
	}
}
