package com.prebeg.ihznet.data.scraper;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.prebeg.ihznet.model.Kolodvor;
import com.prebeg.ihznet.model.ListaKolodvora;

@Component
public class KolodvorScraper {

	private WebClient getWebClient() {
		WebClient wc = new WebClient(BrowserVersion.FIREFOX_3_6);
		wc.setCssEnabled(true);
		wc.setJavaScriptEnabled(true);		
		wc.setAjaxController(new NicelyResynchronizingAjaxController());
		
		return wc;
	}
	
	//private String baseurl = "http://vred.hznet.hr/hzinfo/?category=hzinfo&service=vred3&nkod1=&nkdo1=&lang=hr&screen=4";
	private String baseurl = "http://vred.hzinfra.hr/hzinfo/?category=hzinfo&service=vred3&nkod1=&nkdo1=&lang=hr&screen=4";
	
	public ListaKolodvora getKolodvori_no_js() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		
		WebClient wc = getWebClient();
		
		final HtmlPage page = wc.getPage(baseurl);
		
		HtmlForm searchForm = page.getForms().get(0);
		
		ListaKolodvora kolodvori = new ListaKolodvora();
		
		final HtmlSelect NKOD1Select = (HtmlSelect) searchForm.getSelectByName("NKOD1");
		
		for (HtmlOption option : NKOD1Select.getOptions()) {  
			if (option.asText().trim().length() > 0) {
				Kolodvor kolodvor = new Kolodvor();
				kolodvor.setNaziv(option.asText().trim());
				kolodvor.setId(NKOD1Select.getOptions().indexOf(option));
				kolodvori.addKolodvor(kolodvor);
			}
		}
		
		return kolodvori;
	}
	
	public ListaKolodvora getKolodvori() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		
		WebClient wc = getWebClient();
		
		final HtmlPage page = wc.getPage(baseurl);
		
		HtmlForm searchForm = page.getForms().get(0);
		
		ListaKolodvora kolodvori = new ListaKolodvora();
		
		final HtmlSelect NKOD1Select = (HtmlSelect) searchForm.getSelectByName("ddList1");
		
		for (HtmlOption option : NKOD1Select.getOptions()) {  
			if (option.asText().trim().length() > 0 && !option.asText().contains("Izaberi")) {
				
				Kolodvor kolodvor = new Kolodvor();
				
				/*FIXME *temp HACKS
				
				String naziv = null;
				if (option.asText().equals("SPLIT"))
					naziv = "Split";
				else if (option.asText().equals("ZAGREB GL. KOL."))
					naziv = "Zagreb Gl. kol.";
				else
					naziv = option.asText().trim();
				 */
				String naziv = option.asText().trim();
					
				kolodvor.setNaziv(naziv);
				kolodvor.setId(NKOD1Select.getOptions().indexOf(option)); 
				kolodvori.addKolodvor(kolodvor);
			}
		}
		
		return kolodvori;
	}
	
}
