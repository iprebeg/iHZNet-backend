package com.prebeg.ihznet.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class PeriodService {

	@SuppressWarnings("unused")
	private transient final Log log = LogFactory.getLog(getClass());
	
	public String calulatePeriodFromAToB(String a, String b) {
		
		String delta = null;
		
		DateFormat df = new SimpleDateFormat("HH:mm");
		DateFormat dfm = new SimpleDateFormat("mm");
		DateFormat dfh = new SimpleDateFormat("HH");
		
		try {
			/* testing */
			Date da = df.parse(a);
			Date db = df.parse(b);
			
			
			//log.info("formating: a:" + df.format(da) + " b:" + df.format(db));
			
			Long deltaT = db.getTime() - da.getTime() - (60 * 60 * 1000);
			
			Date deltaD = new Date(deltaT);
			
			//Date deltaBAT = new Date(deltaT);
			//log.info("1: for " + deltaT + " seconds, deltaBAT:" + df.format(deltaD));
			delta = df.format(deltaD);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		try {
			Date da = df.parse(a);
			Date db = df.parse(b);
			
			Calendar cb = Calendar.getInstance();
			
			String deltaT;
			
			cb.set(Calendar.MINUTE, Integer.parseInt(dfm.format(db)));
			cb.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dfh.format(db)));

			cb.add(Calendar.HOUR_OF_DAY, -(Integer.parseInt(dfh.format(da))));
			cb.add(Calendar.MINUTE, -(Integer.parseInt(dfm.format(da))));
			
			Date deltad = cb.getTime();
			deltaT = df.format(deltad);
			
			log.info("2:deltaCAL:" + deltaT);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		return delta;
	}
}
