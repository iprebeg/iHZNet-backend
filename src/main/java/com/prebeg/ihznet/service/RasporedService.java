package com.prebeg.ihznet.service;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.prebeg.ihznet.data.scraper.ParallelRasporedScraper;
import com.prebeg.ihznet.data.scraper.RasporedScraper;
import com.prebeg.ihznet.model.Raspored;

import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.lang.Runtime;

@Component
public class RasporedService {
	
	@SuppressWarnings("unused")
	private transient final Log log = LogFactory.getLog(getClass());
	
	@Resource
	ParallelRasporedScraper prasporedScraper;
	
  @Resource
  CachedRasporedService cachedRasporedService;

  final Set<String> currentScraping = new HashSet<String>();

  private String key(Integer odlazniKolodvorId, Integer dolazniKolodvorId, String datum, String dv) 
  {
    String key = odlazniKolodvorId.toString() + "##" + dolazniKolodvorId.toString() + "##" + datum + "##" + dv;
    return key;
  }

  private Thread asyncFetch(final Integer odlazniKolodvorId, final Integer dolazniKolodvorId, final String datum, final String dv) 
  {
    // preemptive S
    if (dv.equals("D")) {
      //asyncFetch(odlazniKolodvorId, dolazniKolodvorId, datum, "S");
    }

    final String key = key(odlazniKolodvorId,dolazniKolodvorId,datum,dv);
    if (cachedRasporedService.getRaspored(key) == null && !currentScraping.contains(key))
    {
      Thread t = new Thread() {
        @Override
        public void run() {
          currentScraping.add(key);
			    Raspored raspored = prasporedScraper.getRaspored(odlazniKolodvorId, dolazniKolodvorId, datum, dv);
          if (raspored != null) cachedRasporedService.putRaspored(key, raspored);
          currentScraping.remove(key);
        }
      };
      
      t.start();
      return t;
    }
    
    return null;
  }

	public Raspored getCachedRaspored(Integer odlazniKolodvorId, Integer dolazniKolodvorId, String datum, String dv) {
		
    String key = key(odlazniKolodvorId,dolazniKolodvorId,datum,dv);
		Raspored raspored = cachedRasporedService.getRaspored(key);

    if (raspored == null)
    {
      Thread t = asyncFetch(odlazniKolodvorId,dolazniKolodvorId,datum,dv);
      if (t != null && t.isAlive())
      {  
      
      	try {
      		synchronized (t) {
      			t.wait(7000L);
			}
  		} catch (InterruptedException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  		}
      
      	raspored = cachedRasporedService.getRaspored(key);
      	
      }
    }
    
    if (raspored == null)
  	{
  		raspored = new Raspored();
  		raspored.setWaitpoll(true);
 	}
    
		return raspored;
	}

  public Raspored getRaspored(Integer odlazniKolodvorId, Integer dolazniKolodvorId, String datum, String dv) {
    
    String key = key(odlazniKolodvorId,dolazniKolodvorId,datum,dv);
		Raspored raspored = cachedRasporedService.getRaspored(key);
   
    if (raspored == null)
    {
      currentScraping.add(key);
      asyncFetch(odlazniKolodvorId,dolazniKolodvorId,datum,dv);
      raspored = prasporedScraper.getRaspored(odlazniKolodvorId, dolazniKolodvorId, datum, dv);
      if (raspored != null) cachedRasporedService.putRaspored(key, raspored);
      currentScraping.remove(key);
    }

    return raspored;
  }
}
