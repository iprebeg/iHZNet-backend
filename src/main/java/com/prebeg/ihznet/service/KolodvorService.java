package com.prebeg.ihznet.service;

import java.io.IOException;
import java.net.MalformedURLException;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.prebeg.ihznet.data.scraper.KolodvorScraper;
import com.prebeg.ihznet.model.ListaKolodvora;

@Component
public class KolodvorService {
	
	@SuppressWarnings("unused")
	private transient final Log log = LogFactory.getLog(getClass());

	@Resource
	KolodvorScraper kolodvorScraper;
	
  Date ts = null;
  ListaKolodvora kolodvori = null;
  private static int secondsStale = 3600;

	public ListaKolodvora getKolodvori() {
	
    if (kolodvori == null || ts == null || ((new Date()).getTime()-ts.getTime())/1000 > secondsStale)
    {
		  try {
			  kolodvori = kolodvorScraper.getKolodvori();
        ts = new Date();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
		
		return kolodvori;
	}
}
