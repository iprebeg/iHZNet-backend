package com.prebeg.ihznet.data.scraper;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.prebeg.ihznet.model.LiveInfo;
import com.prebeg.ihznet.model.Prolaziste;
import com.prebeg.ihznet.service.KolodvorService;
import com.prebeg.ihznet.service.OznakaResolverService;
import com.prebeg.ihznet.service.PeriodService;

@Component
public class LiveInfoScraper {
	
	@SuppressWarnings("unused")
	private transient final Log log = LogFactory.getLog(getClass());
	
	@Resource
	KolodvorService kolodvorService;
	
	@Resource
	OznakaResolverService oznakaResolverService;
	
	@Resource
	PeriodService periodService;
	
	//private String baseurl = "http://vred.hznet.hr/hzinfo/Default.asp?category=hzinfo&service=PANO&SCREEN=1";
	//private String baseurl = "http://vred.hznet.hr/hzinfo/Default.asp?VL=&Category=hzinfo&Service=tpvl&SCREEN=2";
	private String baseurl = "http://vred.hzinfra.hr/hzinfo/Default.asp?VL=&Category=hzinfo&Service=tpvl&SCREEN=2";
	
	private WebClient getWebClient() {
		WebClient wc = new WebClient(BrowserVersion.CHROME);
		wc.getOptions().setCssEnabled(true);
		wc.getOptions().setJavaScriptEnabled(true);		
		wc.setAjaxController(new NicelyResynchronizingAjaxController());
		wc.setRefreshHandler(new ThreadedRefreshHandler());

		
		return wc;
	}
	
	public LiveInfo getLiveInfo(String linija) throws FailingHttpStatusCodeException, MalformedURLException, IOException 
	{
		WebClient wc = getWebClient();
		
		final HtmlPage page = wc.getPage(baseurl);
		
		HtmlForm form = page.getForms().get(0);
		
		final HtmlTextInput vlakInput = (HtmlTextInput) form.getInputByName("VL"); 
				
		vlakInput.setText(linija);
		
		final HtmlInput okButton = (HtmlInput) form.getInputsByValue(" OK ").get(0);

		final HtmlPage vlakPage = okButton.click();
		
		//log.info(vlakPage.asXml());
		
		LiveInfo liveInfo = getVlakInfo(vlakPage);
		
		wc.closeAllWindows();
		return liveInfo;
	
	}
	
	public LiveInfo getVlakInfo(HtmlPage vlakPage) 
	{
		
		for (HtmlAnchor a : vlakPage.getAnchors()) {
			//log.info(a.asText());
		}
		
		HtmlAnchor kretanjeAnchor = vlakPage.getAnchors().get(2);
		
		//log.info("url:" + kretanjeAnchor.asXml());
		
		HtmlPage kretanjePage = null;
		
		try {
			kretanjePage = kretanjeAnchor.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//log.info("url:" + kretanjePage.asXml());
		
		if (kretanjePage.getAnchors().size() == 0)
			return new LiveInfo();

		
		HtmlAnchor kretanjeVlakAnchor = kretanjePage.getAnchors().get(0);
		//log.info("url:" + kretanjeVlakAnchor.asXml());
		
		HtmlPage kretanjeVlakPage = null;
		
		try {
			kretanjeVlakPage = kretanjeVlakAnchor.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//log.info(kretanjeVlakPage.asXml());
		
		LiveInfo liveInfo = parseKretanjePage(kretanjeVlakPage);
		
		return liveInfo;
	}
	
	
	public LiveInfo parseKretanjePage(HtmlPage kretanjeVlakPage)
	{
		HtmlTable kretanjeTable = null;
		try {
			kretanjeTable = (HtmlTable)kretanjeVlakPage.getElementsByTagName("table").item(1);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
		
		// Kolodvor	DolazakOdlazak	Datum	Sat	Kasni
		
		LiveInfo liveInfo = new LiveInfo();
		
		Prolaziste currentProlaziste = null;
		
		for (HtmlTableRow row : kretanjeTable.getRows()) {
			
			if (row == kretanjeTable.getRow(0) || row == kretanjeTable.getRow(kretanjeTable.getRowCount() - 1))
				continue;
		
			HtmlTableCell kolodvor           = row.getCells().get(0);
			HtmlTableCell dolazakOdlazak     = row.getCells().get(1);	
			HtmlTableCell datum              = row.getCells().get(2);
			HtmlTableCell sat                = row.getCells().get(3);
			HtmlTableCell kasni              = row.getCells().get(4);
			
			//log.info(kolodvor.asText() +" " + dolazakOdlazak.asText()+" " + datum.asText()+" " +sat.asText()+" " +kasni.asText().trim());
			
			
			
			if (dolazakOdlazak.asText().equalsIgnoreCase("dolazak")) 
			{
				currentProlaziste = new Prolaziste();
				currentProlaziste.setProlaziste(kolodvor.asText());
				currentProlaziste.setKasnjenjeDolaska(kasni.asText());
				currentProlaziste.setVrijemeDolaskaProlaziste(sat.asText());
				liveInfo.addProlaziste(currentProlaziste);
			} 
			else if (dolazakOdlazak.asText().equalsIgnoreCase("odlazak") && liveInfo.getProlazista().size() == 0)
			{
				currentProlaziste = new Prolaziste();
				currentProlaziste.setProlaziste(kolodvor.asText());
				currentProlaziste.setVrijemeOdlaskaProlaziste(sat.asText());
				currentProlaziste.setKasnjenjeOdlaska(kasni.asText());
				liveInfo.addProlaziste(currentProlaziste);
			} 
			else if (dolazakOdlazak.asText().equalsIgnoreCase("odlazak") && currentProlaziste.getProlaziste().equalsIgnoreCase(kolodvor.asText())) 
			{
				currentProlaziste.setVrijemeOdlaskaProlaziste(sat.asText());
				currentProlaziste.setKasnjenjeOdlaska(kasni.asText());
			}
			else if (dolazakOdlazak.asText().equalsIgnoreCase("odlazak")) 
			{
				currentProlaziste = new Prolaziste();
				currentProlaziste.setProlaziste(kolodvor.asText());
				currentProlaziste.setVrijemeOdlaskaProlaziste(sat.asText());
				currentProlaziste.setKasnjenjeOdlaska(kasni.asText());
				liveInfo.addProlaziste(currentProlaziste);
			}	
		}
		
		return liveInfo;
	}
	
}
