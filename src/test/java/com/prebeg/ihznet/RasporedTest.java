package com.prebeg.ihznet;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.prebeg.ihznet.model.Linija;
import com.prebeg.ihznet.model.Putovanje;
import com.prebeg.ihznet.model.Raspored;
import com.prebeg.ihznet.model.Stajaliste;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@ContextConfiguration( locations = {"file:src/main/resources/META-INF/spring/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring/webmvc-config.xml"})
public class RasporedTest extends TestCase {

	@Autowired WebApplicationContext wac; 
    @Autowired MockHttpSession session;
    @Autowired MockHttpServletRequest request;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        //System.out.println("build web app context " + wac);
    }
    
    @Test
    public void test_01_getDjakovoZagreb() throws Exception 
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
    	Date today = new Date();
    	
    	String dt = sdf.format(today);
    	String dv = "S";
    	int odk = 145;
    	int dok = 649;
    	
    	String url = String.format("/raspored?NKOD1=%d&NKDO1=%d&DT=%s&DV=%s", odk, dok, dt, dv);
    	
    	System.out.println(url);
    	
    	MvcResult res = this.mockMvc.perform(get(url).session(session))
    		.andExpect(status().is(200))
    		.andReturn();
    
    	int status = res.getResponse().getStatus();
    	String body = res.getResponse().getContentAsString();
    	
    	System.out.println("STATUS:" + status);
    	System.out.println("BODY:" + body);
    	
    	
    	JAXBContext ctx = JAXBContext.newInstance(Raspored.class);
    	Unmarshaller unm = ctx.createUnmarshaller();
    	Raspored lista = (Raspored) unm.unmarshal(new StringReader(body));
    	
    	assertNotNull(lista);
    	assertNotNull(lista.getPutovanja());
    	assertTrue(!lista.getPutovanja().isEmpty());
    	assertTrue(lista.getPutovanja().size() >= 1);
    	
    	System.out.println("SIZE:" + lista.getPutovanja().size());
    	
    	for (Putovanje p : lista.getPutovanja())
    	{
    		assertNotNull(p.getListaLinija());
    		assertNotNull(p.getListaLinija().getLinije());
    		assertNotNull(!p.getListaLinija().getLinije().isEmpty());
    		
    		for (Linija l : p.getListaLinija().getLinije())
    		{
    			assertNotNull(l);
    			assertNotNull(l.getListaStajalista());
    			assertNotNull(l.getListaStajalista().getStajalista());
    			assertNotNull(!l.getListaStajalista().getStajalista().isEmpty());
    			
    			for (Stajaliste s : l.getListaStajalista().getStajalista())
    			{
    				assertNotNull(s);
    			}
    		}
    	}
    }
}
