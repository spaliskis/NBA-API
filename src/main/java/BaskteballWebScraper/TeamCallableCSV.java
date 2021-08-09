package BaskteballWebScraper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
	* Class that implements the Callable interface and uses a scraped element to create a String array from it
	* @author Sarunas
*/
public class TeamCallableCSV implements Callable{
    private Element tr;
    String[] eRow = new String[500];

    public TeamCallableCSV(Element tr){
        this.tr = tr;
    }

    /**
        * Method overrided from Callable interface. 
        * @return Object
        * object that stores scraped team's data
    */
    @Override
    public Object call() {
            Elements fullRow = tr.select("td");
            eRow[0] = tr.select("th a").first().html();
            eRow[1] = fullRow.get(0).html();
            eRow[2] = fullRow.get(1).html();
            eRow[3] = fullRow.get(4).html();
            String url = tr.select("th a").attr("href");
            try{
                Document doc = Jsoup.connect("https://www.basketball-reference.com" + url).get();
                Elements roster = doc.select("#roster tbody tr");
                int i = 4;
                for (Element rEntry : roster) {
                    Elements player = rEntry.select("td");
                    eRow[i++] = player.get(0).select("a").first().html();
                    eRow[i++] = player.get(1).html();
                    eRow[i++] = player.get(2).html();
                    eRow[i++] = player.get(3).html();
                    String date = player.get(4).html().replace(",", "");
                    eRow[i++] = date;
                    String plUrl = player.get(0).select("a").attr("href");
                    try{
                        Document plDoc = Jsoup.connect("https://www.basketball-reference.com" + plUrl).get();
                        Elements plElements = plDoc.select("tr[id=per_game.2021][class=full_table]");
                        for (Element column : plElements) {
                            
                            Elements data = column.select("td");
                            eRow[i++] = data.get(6).html();
                            eRow[i++] = data.get(9).html();
                            eRow[i++] = data.get(12).html();
                            eRow[i++] = data.get(15).html();
                            eRow[i++] = data.get(19).html();
                            eRow[i++] = data.get(22).html();
                            eRow[i++] = data.get(23).html();
                            eRow[i++] = data.get(24).html();
                            eRow[i++] = data.get(25).html();
                            eRow[i++] = data.get(26).html();
                            eRow[i++] = data.get(27).html();
                            eRow[i++] = data.get(28).html();
                        }
                    }
                    catch(Exception plEx){
                        System.out.println("Something went wrong while trying to scrape player's data: " + plEx);
                    }
                }
            }
            catch(Exception ex){
                System.out.println("Something went wrong: " + ex);
            }

            return eRow;
        
    }

    
}

