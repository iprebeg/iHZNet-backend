package com.prebeg.ihznet;


import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prebeg.ihznet.data.scraper.ParallelRasporedScraper;
import com.prebeg.ihznet.data.scraper.RasporedScraper;
import com.prebeg.ihznet.model.Raspored;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration( locations = {"file:src/main/resources/META-INF/spring/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring/webmvc-config.xml"})
public class ParallelScrapeTest extends TestCase {

	@Autowired RasporedScraper scraper;
	@Autowired ParallelRasporedScraper pscraper;
	
	static String dt;
	
	int osk = 397;
	int zgb = 657;
	int gnc = 166;
	
	JAXBContext xmlCtx;
	Marshaller marshaller;
		
	@Before
	public void setUpThis() {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
    	Date today = new Date();
    	dt = sdf.format(today);
    	
    	try {
			xmlCtx = JAXBContext.newInstance(Raspored.class);
			marshaller = xmlCtx.createMarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
    public void test_00_dumbo() throws Exception 
    {
		Raspored r = scraper.getRaspored(osk, zgb, dt, "S");
		assertEquals(1,1+1-1);
    }

    @Test
    public void test_01A_getOsijekZagrebPresjednjaSingle() throws Exception 
    {
    	Raspored r = scraper.getRaspored(osk, zgb, dt, "S");
    	assertNotNull(r);
    	assertFalse(r.getPutovanja().isEmpty());
    }
    
    @Test
    public void test_01B_getOsijekZagrebPresjedanjaParallel() throws Exception 
    {
    	Raspored r = pscraper.getRaspored(osk, zgb, dt, "S");
    	assertNotNull(r);
    	assertFalse(r.getPutovanja().isEmpty());
    }
    
     @Test
    public void test_02A_getOsijekZagrebIzravniSingle() throws Exception 
    {
    	 Raspored r = scraper.getRaspored(osk, zgb, dt, "D");
    	 assertNotNull(r);
    	 assertFalse(r.getPutovanja().isEmpty());
    }
    
    @Test
    public void test_02B_getOsijekZagrebIzravniParallel() throws Exception 
    {
    	Raspored r = pscraper.getRaspored(osk, zgb, dt, "D");
    	assertNotNull(r);
    	assertFalse(r.getPutovanja().isEmpty());
    }
     
    @Test
    public void test_03A_getGajniceZagrebPresjednjaSingle() throws Exception 
    {
    	Raspored r = scraper.getRaspored(gnc, zgb, dt, "S");
    	assertNotNull(r);
    	assertFalse(r.getPutovanja().isEmpty());
    }
    
    @Test
    public void test_03B_getGajniceZagrebPresjedanjaParallel() throws Exception 
    {
    	Raspored r = pscraper.getRaspored(gnc, zgb, dt, "S");
    	assertNotNull(r);
    	assertFalse(r.getPutovanja().isEmpty());
    }

    @Test
    public void test_04A_getGajniceZagrebIzravniSingle() throws Exception 
    {
    	Raspored r = scraper.getRaspored(gnc, zgb, dt, "D");
    	assertNotNull(r);
    	assertFalse(r.getPutovanja().isEmpty());
    }
    
    @Test
    public void test_04B_getGajniceZagrebIzravniParallel() throws Exception 
    {
    	Raspored r = pscraper.getRaspored(gnc, zgb, dt, "D");
    	assertNotNull(r);
    	assertFalse(r.getPutovanja().isEmpty());
    }
    
    @Test
    public void test_91_getGajniceZagrebSAssert() throws Exception 
    {
    	Raspored rp = pscraper.getRaspored(gnc, zgb, dt, "D");
    	Raspored rs = scraper.getRaspored(gnc, zgb, dt, "D");
    	
    	String sa = rasporedToString(rp);
    	String sb = rasporedToString(rs);
    	
    	assertNotNull(sa);
    	assertNotNull(sb);
    	assertFalse(sa.isEmpty());
    	assertFalse(sb.isEmpty());
    	assertEquals(sa, sb);
    }
    
    @Test
    public void test_92_getOsijekZagrebSAssert() throws Exception 
    {
    	Raspored rp = pscraper.getRaspored(osk, zgb, dt, "S");
    	Raspored rs = scraper.getRaspored(osk, zgb, dt, "S");
    	
    	String sa = rasporedToString(rp);
    	String sb = rasporedToString(rs);
    	
    	assertNotNull(sa);
    	assertNotNull(sb);
    	assertFalse(sa.isEmpty());
    	assertFalse(sb.isEmpty());
    	assertEquals(sa, sb);
    }
     
    @Test
    public void test_93_getOsijekZagrebSAssert() throws Exception 
    {
    	Raspored rp = pscraper.getRaspored(osk, zgb, dt, "D");
    	Raspored rs = scraper.getRaspored(osk, zgb, dt, "D");
    	
    	String sa = rasporedToString(rp);
    	String sb = rasporedToString(rs);
    	
    	assertNotNull(sa);
    	assertNotNull(sb);
    	assertFalse(sa.isEmpty());
    	assertFalse(sb.isEmpty());
    	assertEquals(sa, sb);
    }
    
    @Test
    public void test_94_getGajniceZagrebSAssert() throws Exception 
    {
    	Raspored rp = pscraper.getRaspored(gnc, zgb, dt, "S");
    	Raspored rs = scraper.getRaspored(gnc, zgb, dt, "S");
    	
    	String sa = rasporedToString(rp);
    	String sb = rasporedToString(rs);
    	
    	assertNotNull(sa);
    	assertNotNull(sb);
    	assertFalse(sa.isEmpty());
    	assertFalse(sb.isEmpty());
    	assertEquals(sa, sb);
    }
    
    private String rasporedToString(Raspored r)
    {
    	StringWriter sw = new StringWriter();
    	try {
			marshaller.marshal(r, sw);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return sw.toString();
    }
}
