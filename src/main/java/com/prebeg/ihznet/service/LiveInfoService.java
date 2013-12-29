package com.prebeg.ihznet.service;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.prebeg.ihznet.data.scraper.LiveInfoScraper;
import com.prebeg.ihznet.model.LiveInfo;

@Component
public class LiveInfoService {

	@SuppressWarnings("unused")
	private transient final Log log = LogFactory.getLog(getClass());
	
	@Resource
	LiveInfoScraper liveInfoScraper;
	
	public LiveInfo getLiveInfo(String linija) {
		
		LiveInfo liveInfo = null;
		
		try {
			liveInfo = liveInfoScraper.getLiveInfo(linija);
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return liveInfo;
	}
}
