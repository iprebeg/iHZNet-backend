package com.prebeg.ihznet.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import org.joda.time.Period;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormat;
import org.joda.time.format.PeriodFormatterBuilder;

@Component
public class PeriodService {

	@SuppressWarnings("unused")
	private transient final Log log = LogFactory.getLog(getClass());
	
	public String calulatePeriodFromAToB(String a, String b) {
		
    //System.out.println("a:" + a);
    //System.out.println("b:" + b);

	if (a.equals("24:00"))
		a = "00:00";
	
	if (b.equals("24:00"))
		b = "00:00";	
		
    if (a.compareTo(b) <= 0)
    {
      a = "01.01.1970" + " " + a;
      b = "01.01.1970" + " " + b;
    }
    else
    {
      a = "01.01.1970" + " " + a;
      b = "02.01.1970" + " " + b;
    }

		String delta = "";
		
		try {
		  DateTimeFormatter df = DateTimeFormat.forPattern("dd.MM.YYYY HH:mm");
		
			DateTime da = df.parseDateTime(a);
			DateTime db = df.parseDateTime(b);
			
      Period period = new Period(da, db);

      PeriodFormatter pf = new PeriodFormatterBuilder()
        .printZeroAlways()
        .minimumPrintedDigits(2)
        .appendHours()
        .appendSeparator(":")
        .appendMinutes()
        .toFormatter();

			delta = pf.print(period);

		} catch (Exception e) {
			e.printStackTrace();
		}

    //System.out.println("c:" + delta);
		
		return delta;
	}
}
