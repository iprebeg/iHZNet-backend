package com.prebeg.ihznet;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.StringReader;

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

import com.prebeg.ihznet.model.ListaKolodvora;
import com.prebeg.ihznet.model.LiveInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@ContextConfiguration( locations = {"file:src/main/resources/META-INF/spring/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring/webmvc-config.xml"})
public class LiveInfoTest extends TestCase {

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
    public void test_01_getLiveInfo() throws Exception 
    {
    	MvcResult res = this.mockMvc.perform(get("/liveInfo?VL=2611").session(session))
    		.andExpect(status().is(200))
    		.andReturn();
    	
    	try {
	    	int status = res.getResponse().getStatus();
	    	String body = res.getResponse().getContentAsString();
	    	
	    	System.out.println("STATUS:" + status);
	    	System.out.println("BODY:" + body);
	    	
	    	JAXBContext ctx = JAXBContext.newInstance(LiveInfo.class);
	    	Unmarshaller unm = ctx.createUnmarshaller();
	    	LiveInfo lista = (LiveInfo) unm.unmarshal(new StringReader(body));
	    	
	    	assertNotNull(lista);
	    	assertNotNull(lista.getProlazista());
	    	assertTrue(!lista.getProlazista().isEmpty());
	    	assertTrue(lista.getProlazista().size() > 10);
	    	
	    	System.out.println("SIZE:" + lista.getProlazista().size());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
