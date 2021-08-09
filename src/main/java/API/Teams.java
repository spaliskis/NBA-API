package API;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import BaskteballWebScraper.WebScraper;
import Util.WebScraperUtil;

/** Used to store basketball teams' data in a Map object and to update the data by webscraping it
 * @author Simon
 */

public class Teams {

    private Map<String, ArrayList<Team>> teams;

    /** Returns the basketball teams' data
     *
     * @return
     *      Returns basketball teams' Map object
     */
    public Map<String, ArrayList<Team>> getTeams(){
        return teams;
    }

    /** Updates the data by webscraping it
     *
     */
    public void update(){
        System.out.println("Starting the automatic data scraping...");

        // Getting all the working basketball websites to scrap the data from
        List<String[]> allRows = null;
        try {
            allRows = WebScraperUtil.readCSVFile("src" + File.separator + "main" + File.separator + "resources" + File.separator + "webscraper" + File.separator + "websites_status" + File.separator + "working_domains.csv");
        }
        catch(IOException e){
            e.printStackTrace();
        }

        if(allRows != null){
            for(String[] row : allRows){
                System.out.println("Trying to scrape the " + row[0] + " website...");
                try {
                    Method scrapingMethod
                            = WebScraper.class.getDeclaredMethod(
                            row[0]);
                    scrapingMethod.invoke(null);

                    System.out.println("Automatic data scrapping finished successfully");

                    // Loading the teams' data into a Map object
                    System.out.println("Loading the teams' data into a Map object...");
                    teams = LoadData.loadJSON();

                    System.out.println("Finished loading the teams' data into a Map object");
                    break;
                } catch (Exception e) {
                    System.out.println(row[0] + " website scraping failed, check the DomainErrors.log file");
                }
            }
        }
        else {
            System.out.println("Data scrapping couldn't be performed. No working domains, check the DomainErrors.log file");
        }
    }
}
