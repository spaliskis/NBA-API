package BaskteballWebScraper;

import java.util.concurrent.Callable;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
	* Class that implements the Callable interface and uses a scraped element to create a JSONObject from it
	* @author Sarunas
*/
public class TeamCallable implements Callable{
    private Element tr;
    private String urlPath;

    /**
        * Initializes the element that will be used in the class
    */
    public TeamCallable(Element tr){
        this.tr = tr;
    }

    /**
        * Method overrided from Callable interface. 
        * @return Object
        * object that stores scraped team's data
        * @throws WebScraperException
        * if an element cannot be scraped
    */
    @Override
    public Object call() throws WebScraperException{
        JSONObject object = scrapeTeam();
        return object;
    }

    /**
        * Uses class's element to scrape it's data
        * @return JSONObject
        * object that stores scraped team's data
        * @throws WebScraperException
        * if an element cannot be scraped
    */
    public JSONObject scrapeTeam() throws WebScraperException{
        String url;
        JSONObject obj;
        try {           
            Elements fullRow = tr.select("td");
            obj = new JSONObject();

            String teamName = tr.select("th a").first().html();
            int pos = teamName.indexOf(" ") + 1;
            urlPath = teamName.substring(pos).toLowerCase();
            obj.put("name", teamName);

            String wins = fullRow.get(0).html();
            obj.put("wins", wins);

            String losses = fullRow.get(1).html();
            obj.put("losses", losses);

            String ppg = fullRow.get(4).html(); 
            obj.put("ppg", ppg);

            obj.put("players", new JSONObject());

            url = tr.select("th a").attr("href");

            scrapePlayerData(url, obj);
        } catch (Exception e) {
            throw new WebScraperException("Error: cannot scrape this element");
        }
        return obj;

    }

    /**
        * Scrapes player's data from a given url and stores it in a JSONObject
        * @param url
        * the url from which the player's data is scraped
        * @param obj
        * the JSONObject where the player's data is stored
        * @throws WebScraperException
        * if an element cannot be scraped
    */
    public void scrapePlayerData(String url, JSONObject obj) throws WebScraperException{
        try {
            Document doc = null;
            try {
                doc = Jsoup.connect("https://www.basketball-reference.com" + url).get();
            } catch (Exception e) {
                System.out.println("The given URL is invalid");
            }

            Elements roster = doc.select("#roster tbody tr");
            JSONArray plArray = new JSONArray();
            
            for (Element rEntry : roster) {
                Elements player = rEntry.select("td");
                JSONObject playerObj = new JSONObject();
                String name = player.get(0).select("a").first().html();
                playerObj.put("name", name);
                String position = player.get(1).html();
                playerObj.put("position", position);
                String height = player.get(2).html();
                playerObj.put("height", height);
                String weight = player.get(3).html();
                playerObj.put("weight", weight);
                String birthDate = player.get(4).html();
                playerObj.put("birthDate", birthDate);
    
    
                String plUrl = player.get(0).select("a").attr("href");
                scrapePlayerStats(plUrl, playerObj, plArray);
                
            }
            obj.put("players", plArray);
        }   catch(Exception e){
                System.out.println("Something went wrong while trying to scrape player's data: " + e);
                throw new WebScraperException("Error at TeamCallable.call() method", e);
        }

    }

    /**
        * Scrapes player's stats from a given url and stores it in a JSONArray
        * @param plUrl
        * the url from which the player's stats is scraped
        * @param playerObj
        * the JSONObject where the player's stats is stored
        * @param plArray
        * the JSONObject where the playerObj is stored
        * @throws WebScraperException
        * if an element cannot be scraped
    */
    public void scrapePlayerStats(String plUrl, JSONObject playerObj, JSONArray plArray) throws WebScraperException{
        try {
            Document plDoc = Jsoup.connect("https://www.basketball-reference.com" + plUrl).get();
            Elements plElements = plDoc.select("tr[id=per_game.2021][class=full_table]");
            for (Element column : plElements) {
                JSONObject stats = new JSONObject();
                Elements data = column.select("td");
                String mpg = data.get(6).html();
                stats.put("mpg", mpg);
                String fg = data.get(9).html();
                stats.put("fgPerc", fg);
                String fg3 = data.get(12).html();
                stats.put("threePerc", fg3);
                String fg2 = data.get(15).html();
                stats.put("twoPerc", fg2);
                String frFg = data.get(19).html();
                stats.put("freePerc", frFg);
                String trb = data.get(22).html();
                stats.put("trb", trb);
                String ast = data.get(23).html();
                stats.put("ast", ast);
                String stl = data.get(24).html();
                stats.put("stl", stl);
                String blk = data.get(25).html();
                stats.put("blk", blk);
                String tov = data.get(26).html();
                stats.put("tov", tov);
                String pf = data.get(27).html();
                stats.put("pf", pf);
                String pts = data.get(28).html();
                stats.put("pts", pts);
    
                playerObj.put("stats", stats);
    
                plArray.put(playerObj);
            }
        } catch(Exception e){
            System.out.println("Something went wrong while trying to scrape player's stats: " + e);
            throw new WebScraperException("Error at TeamCallable.call() method", e);
        }

    }

    /**
     * Returns object's url
     * @return urlPath
     *  object's url
     */
    public String getUrlPath(){
        return urlPath;
    }

    
}
