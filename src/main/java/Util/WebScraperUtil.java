package Util;

import BaskteballWebScraper.TeamCallable;
import BaskteballWebScraper.TeamCallableCSV;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

/**
 * Class that has methods to be used in the web scraper
 * @author Simon
 * @author Sarunas
 */
public class WebScraperUtil {
    
    /**
     * Uses multithreading to scrape every object and uses the objects to create a JSONObject that has all of the conference's data
     * @param e all elements that need to be scraped
     * @param name name of the conference that is used in creating the JSONObject
     * @return JSONObject JSONObject that has all of the conference's data
     */
    public static JSONObject fillList(Elements e, String name) {
        JSONObject mainObj = new JSONObject();
        List<JSONObject> objList = new ArrayList<>();
        ArrayList<FutureTask> future = new ArrayList<FutureTask>();

        for (Element tr : e) {
            TeamCallable callable = new TeamCallable(tr);
            future.add(new FutureTask(callable));
            Thread t = new Thread(future.get(future.size()-1));
            t.start();
        }

        for (FutureTask futureTask : future) {
            Object obj = null;
            try {
                obj = futureTask.get();
            } catch (InterruptedException | ExecutionException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            objList.add((JSONObject) obj);
        }
        mainObj.put(name, new JSONArray(objList));

        System.out.println(name + " written to the file");

        return mainObj;
    }


    public static List<String[]> fillListCSV(Elements e) {
            List<String[]> columnData = new ArrayList<>();
            ArrayList<FutureTask> future = new ArrayList<FutureTask>();
    
            for (Element tr : e) {
                TeamCallableCSV callable = new TeamCallableCSV(tr);
                future.add(new FutureTask(callable));
                Thread t = new Thread(future.get(future.size()-1));
                t.start();
            }

            for (FutureTask futureTask : future) {
                Object row = null;
                try {
                    row = futureTask.get();
                } catch (InterruptedException | ExecutionException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                columnData.add((String[])row);
            }
        return columnData;

    }

    /** Scrapes the specified URL to a specified .txt file
     *
     * @param url
     *      URL to be scraped
     * @param filePath
     *      File path with file name where the scraped data should be saved
     * @throws Exception
     *      If there is an error in scraping the website or wrong path was specified
     */

    // Scrapes a URL and writes it into a txt file
    static public void scrapeUrlToTxtFile(String url, String filePath) throws Exception{
        try{
            Document doc = Jsoup.connect(url).get();
            String htmlString = doc.html();
            PrintWriter out = new PrintWriter(filePath);
            out.println(htmlString);
         }catch (Exception e){
            throw e;
        }
    }

    /** Deletes rows which contains a specified value in specified column from a CSV file
     *
     * @param filePath
     *      Path to the CSV file
     * @param column
     *      Column in which the specified value should be searched for
     * @param value
     *      String value to be found and deleted
     * @throws IOException
     *      If specified path is wrong or there was an error in re-creating the file with deleted rows
     */

    // Deletes all the rows which have the specified value in the specified column
    public static void deleteCSVRowsByFields(String filePath, int column, String value) throws IOException {
        List<String[]> allRows = null;
        if(column<0) throw new IOException("Column can't be negative");
        try {
            allRows = readCSVFile(filePath);

            List<String[]> filtered = allRows.stream()
                    .filter(row -> !row[column].equals(value))
                    .collect(Collectors.toList());

            // Trying to delete the previous file so it could be replaced by a new one with filtered values
            if (new File(filePath).delete()) {
                CSVWriter w = new CSVWriter(new FileWriter(filePath), CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.RFC4180_LINE_END);
                filtered.forEach(line -> w.writeNext(line));
                w.close();
            } else {
                throw new FileNotFoundException("Something went wrong in WebScraperUtil.deleteCSVField method");
            }
        }catch(IOException e){
            throw e;
        }

    }

    /** A method that reads a CSV file and returns the data it contains
     *
     * @param filePath
     *     Path to the CSV file
     * @return
     *     List<String[]> where every the String array contains the data of a single row
     * @throws IOException
     *     If specified path is wrong
     */

    public static List<String[]> readCSVFile(String filePath) throws IOException {
        List<String[]> allRows;
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            allRows = reader.readAll();
        } catch (IOException e) {
            throw e;
        }
        return allRows;
    }
}
