package scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScraperLauncher {

	public static void main(String[] args) {
		 String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
		    try {
				Document pageToScrape = Jsoup.connect(url).get();
				
				Elements links = pageToScrape.select("div[id=productLister]").select("h3");
				for (Element link : links) {
				    System.out.print("\nLink: " + link);
				}
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 

	}

}
