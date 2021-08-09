package BaskteballWebScraper;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.logging.Level;
import Util.WebScraperUtil;

import com.opencsv.CSVWriter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

    /**
     * Used for scraping data from different websites, creating objects from it and writing them to a file
     * @author Simon
     * @author Sarunas
    */
public class WebScraper {
    /**
     * This method scrapes data from basketballreference.com and writes it into a json file
     * @throws WebScraperException
     *  if a website can't be scraped
    */
    static public void basketballreference() throws WebScraperException{
        try{
            // Load the teams' document from a URL
            Document doc = Jsoup.connect("https://www.basketball-reference.com/leagues/NBA_2021.html").get();
            Elements eTable = doc.select("#confs_standings_E tbody tr");
            Elements wTable = doc.select("#confs_standings_W tbody tr");

            // First create file object for file placed at location specified by filepath
            File file = new File("src/main/resources/webscraper/data/teamsData.json");
            FileWriter writer = new FileWriter(file);

            JSONObject eastTeams = WebScraperUtil.fillList(eTable, "eastTeams");
            JSONObject westTeams =  WebScraperUtil.fillList(wTable, "westTeams");

            JSONArray teams = new JSONArray();
            teams.put(eastTeams);
            teams.put(westTeams);
            
            writer.write(teams.toString());

            // closing writer connection
            writer.close();
        }catch(Exception e){
            // Arguments: error message, domain name, path of the file with working domains list (so that this domain could be temporarily removed from the list,
            // cause exception, path of the file where domain errors are being logged, logging level
            throw new WebScraperException("Error at basketballreference method", "basketballreference", "src/main/resources/webscraper/websites_status/working_domains.csv", e, "src/main/resources/webscraper/websites_status/DomainErrors.log", Level.WARNING);
        }
        System.out.println("Data scrapping process finished successfully");
    }


    /**
     * This method scrapes data from basketballreference.com and writes it into a csv file
     * @throws WebScraperException
     *  if a website can't be scraped
    */
    static public void basketballreferenceCSV() throws WebScraperException{
        try{
            // Load the teams' document from a URL
            Document doc = Jsoup.connect("https://www.basketball-reference.com/leagues/NBA_2021.html").get();
            Elements eTable = doc.select("#confs_standings_E tbody tr");
            Elements wTable = doc.select("#confs_standings_W tbody tr");

            // First create file object for file placed at location specified by filepath
            File file = new File("src/main/resources/webscraper/data/teamsDataCSV.csv");
            // create CSVWriter object
            CSVWriter writer = new CSVWriter(new FileWriter(file, true), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);

            // Iterate through all the divs representing player's summary: Games, Points, Total Rebounds, Assists
            List<String[]> eastTeams =  WebScraperUtil.fillListCSV(eTable);
            List<String[]> westTeams =  WebScraperUtil.fillListCSV(wTable);

            eastTeams.forEach(strings -> writer.writeNext(strings));
            westTeams.forEach(strings -> writer.writeNext(strings));

            // closing writer connection
            writer.close();

        }catch(Exception e){
            // Arguments: error message, domain name, path of the file with working domains list (so that this domain could be temporarily removed from the list,
            // cause exception, path of the file where domain errors are being logged, logging level
            throw new WebScraperException("Error at basketballreference method", "basketballreference", "src/main/resources/webscraper/websites_status/working_domains.csv", e, "src/main/resources/webscraper/websites_status/DomainErrors.log", Level.WARNING);
        }
        System.out.println("Data scrapping process finished successfully");
    } 

    /**
     * This method scrapes data from nba.com
     * @throws WebScraperException
     *  if a website can't be scraped
    */
    static public void nba() throws WebScraperException{
        try{
            Document doc = Jsoup.connect("https://www.nba.com/teams/none-existant-route").get();
        }catch(Exception e){
            throw new WebScraperException("Error at nba method", "nba", "src\\main\\resources\\webscraper\\websites_status\\working_domains.csv", e, "src\\main\\resources\\webscraper\\websites_status\\DomainErrors.log", Level.WARNING);
        }

    }

    /**
     * This method scrapes data from espn.com
     * @throws WebScraperException
     *  if a website can't be scraped
    */
    static public void espn() throws WebScraperException{
        try{
            Document doc = Jsoup.connect("https://www.espn.com/teams/none-existant-route").get();
        }catch(Exception e){
            throw new WebScraperException("Error at espn method", "espn", "src/main/resources/webscraper/websites_status/working_domains.csv", e, "src/main/resources/webscraper/websites_status/DomainErrors.log", Level.WARNING);
        }
    }

    public static void main(String[] args) throws WebScraperException {
        basketballreference();
    }

}