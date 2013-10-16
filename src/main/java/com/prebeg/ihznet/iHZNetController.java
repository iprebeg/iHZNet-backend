package com.prebeg.ihznet;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;
import org.w3c.css.sac.InputSource;

import com.prebeg.ihznet.model.Linija;
import com.prebeg.ihznet.model.ListaKolodvora;
import com.prebeg.ihznet.model.LiveInfo;
import com.prebeg.ihznet.model.Putovanje;
import com.prebeg.ihznet.model.Raspored;
import com.prebeg.ihznet.service.KolodvorService;
import com.prebeg.ihznet.service.LiveInfoService;
import com.prebeg.ihznet.service.RasporedService;



/**
 * Handles requests for the application home page.
 */

@Controller
public class iHZNetController {
	
	private transient final Log log = LogFactory.getLog(getClass());

	@Resource
	RasporedService rasporedService;
	
	@Resource
	LiveInfoService liveInfoService;

	@Resource
	KolodvorService kolodvorService;
	
	
	@RequestMapping(value="/raspored", method=RequestMethod.GET)
	@ResponseBody
	public Raspored raspored(@RequestParam("NKOD1") Integer odlazniKolodvorId, @RequestParam("NKDO1") Integer dolazniKolodvorId, 
			@RequestParam("DT") String datum, @RequestParam("DV") String dv) {
		
		log.info("raspored:" + odlazniKolodvorId + "," + dolazniKolodvorId + "," + datum + "," + dv);
			
		Raspored raspored = rasporedService.getRaspored(odlazniKolodvorId, dolazniKolodvorId, datum, dv);
		return raspored;
	}
	
	@RequestMapping(value="/listaKolodvora", method=RequestMethod.GET)
	@ResponseBody
	public ListaKolodvora listaKolodvora() {
		
		log.info("listaKolodvora");
		ListaKolodvora kolodvori = kolodvorService.getKolodvori();
		return kolodvori;
	}
	
	@RequestMapping(value="/liveInfo", method=RequestMethod.GET)
	@ResponseBody
	public LiveInfo liveInfo(@RequestParam("VL") String linija) {
		
		log.debug("liveInfo");
		LiveInfo liveInfo = liveInfoService.getLiveInfo(linija);
		
		return liveInfo;
	}
}

