package com.prebeg.ihznet.service;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.prebeg.ihznet.data.scraper.RasporedScraper;
import com.prebeg.ihznet.model.Raspored;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.lang.Runtime;

@Component
public class RasporedService {
	
	@SuppressWarnings("unused")
	private transient final Log log = LogFactory.getLog(getClass());
	
	@Resource
	RasporedScraper rasporedScraper;
	
  @Resource
  CachedRasporedService cachedRasporedService;

  private String key(Integer odlazniKolodvorId, Integer dolazniKolodvorId, String datum, String dv) 
  {
    String key = odlazniKolodvorId.toString() + "##" + dolazniKolodvorId.toString() + "##" + datum + "##" + dv;
    return key;
  }

  private void inOtherThread(final Integer odlazniKolodvorId, final Integer dolazniKolodvorId, final String datum, final String dv) 
  {
    new Thread()
    {
      @Override
      public void run() {
        
        String key = key(odlazniKolodvorId,dolazniKolodvorId,datum,dv);

        if (cachedRasporedService.getRaspored(key) == null)
        {
			    Raspored raspored = rasporedScraper.getRaspored(odlazniKolodvorId, dolazniKolodvorId, datum, dv);
          if (raspored != null)
          {
            cachedRasporedService.putRaspored(key, raspored);
          }
        }
      }
    }.start();

    if (dv.equals("D"))
    {
      new Thread()
      {
        @Override
        public void run() {
          
          String key = key(odlazniKolodvorId,dolazniKolodvorId,datum,"S");
          
          if (cachedRasporedService.getRaspored(key) == null)
          {
            System.out.println("doin veze too");
			      Raspored raspored = rasporedScraper.getRaspored(odlazniKolodvorId, dolazniKolodvorId, datum, "S");
            System.out.println("done veze");
            if (raspored != null)
            {
              cachedRasporedService.putRaspored(key, raspored);
            }
          }
        }
      }.start();
    }
  }

	public Raspored getCachedRaspored(Integer odlazniKolodvorId, Integer dolazniKolodvorId, String datum, String dv) {
		
    String key = key(odlazniKolodvorId,dolazniKolodvorId,datum,dv);
		Raspored raspored = cachedRasporedService.getRaspored(key);

    if (raspored == null)
    {
      raspored = new Raspored();
      raspored.setWaitpoll(true);
      try 
      {
        inOtherThread(odlazniKolodvorId,dolazniKolodvorId,datum,dv);
		  } 
      catch (Exception e) 
      {
			  e.printStackTrace();
	  	}
    }
		
		return raspored;
	}

  public Raspored getRaspored(Integer odlazniKolodvorId, Integer dolazniKolodvorId, String datum, String dv) {
    
    String key = key(odlazniKolodvorId,dolazniKolodvorId,datum,dv);
		Raspored raspored = cachedRasporedService.getRaspored(key);
   
    try {
      if (raspored == null)
      {
        raspored = rasporedScraper.getRaspored(odlazniKolodvorId, dolazniKolodvorId, datum, dv);
        if (raspored != null) cachedRasporedService.putRaspored(key, raspored);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return raspored;
  }
}
