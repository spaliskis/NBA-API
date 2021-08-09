import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.*;

import Util.WebScraperUtil;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class WebScraperUtilTest {
    

    @Test
    @DisplayName("Testing if the fillList method returns expected object")
    public void testFillList(){
        Elements e = new Elements();
        e.add(new Element("tr").append("<th scope=\"row\" class=\"left \" data-stat=\"team_name\"><a href=\"/teams/PHI/2021.html\">Philadelphia 76ers</a>*&nbsp;<span class=\"seed\">(1)&nbsp;</span></th><td class=\"right \" data-stat=\"wins\">49</td><td class=\"right \" data-stat=\"losses\">23</td><td class=\"right \" data-stat=\"win_loss_pct\">.681</td><td class=\"right \" data-stat=\"gb\">—</td><td class=\"right \" data-stat=\"pts_per_g\">113.6</td><td class=\"right \" data-stat=\"opp_pts_per_g\">108.1</td><td class=\"right \" data-stat=\"srs\">5.28</td>"));
        JSONObject obj1 = null;
        try {
            obj1 = new JSONObject("{\"wins\":\"49\",\"ppg\":\"113.6\",\"players\":[{\"stats\":{\"blk\":\"0.8\",\"ast\":\"1.7\",\"mpg\":\"28.0\",\"threePerc\":\".405\",\"trb\":\"3.8\",\"pf\":\"1.8\",\"twoPerc\":\".438\",\"fgPerc\":\".412\",\"stl\":\"1.3\",\"tov\":\"1.0\",\"freePerc\":\".775\",\"pts\":\"9.5\"},\"name\":\"Danny Green\",\"weight\":\"215\",\"position\":\"SF\",\"birthDate\":\"June 22, 1987\",\"height\":\"6-6\"},{\"stats\":{\"blk\":\"0.9\",\"ast\":\"0.9\",\"mpg\":\"17.3\",\"threePerc\":\".250\",\"trb\":\"8.4\",\"pf\":\"2.9\",\"twoPerc\":\".611\",\"fgPerc\":\".587\",\"stl\":\"0.4\",\"tov\":\"1.6\",\"freePerc\":\".576\",\"pts\":\"7.0\"},\"name\":\"Dwight Howard\",\"weight\":\"265\",\"position\":\"C\",\"birthDate\":\"December 8, 1985\",\"height\":\"6-10\"},{\"stats\":{\"blk\":\"1.1\",\"ast\":\"1.0\",\"mpg\":\"20.0\",\"threePerc\":\".301\",\"trb\":\"1.9\",\"pf\":\"2.0\",\"twoPerc\":\".590\",\"fgPerc\":\".420\",\"stl\":\"1.6\",\"tov\":\"0.5\",\"freePerc\":\".444\",\"pts\":\"3.9\"},\"name\":\"Matisse Thybulle\",\"weight\":\"201\",\"position\":\"SG\",\"birthDate\":\"March 4, 1997\",\"height\":\"6-5\"},{\"stats\":{\"blk\":\"0.3\",\"ast\":\"3.1\",\"mpg\":\"23.2\",\"threePerc\":\".350\",\"trb\":\"2.3\",\"pf\":\"2.1\",\"twoPerc\":\".494\",\"fgPerc\":\".450\",\"stl\":\"0.6\",\"tov\":\"1.6\",\"freePerc\":\".830\",\"pts\":\"13.0\"},\"name\":\"Shake Milton\",\"weight\":\"205\",\"position\":\"SG\",\"birthDate\":\"September 26, 1996\",\"height\":\"6-5\"},{\"stats\":{\"blk\":\"0.8\",\"ast\":\"3.5\",\"mpg\":\"32.5\",\"threePerc\":\".394\",\"trb\":\"6.8\",\"pf\":\"1.9\",\"twoPerc\":\".546\",\"fgPerc\":\".512\",\"stl\":\"0.9\",\"tov\":\"1.7\",\"freePerc\":\".892\",\"pts\":\"19.5\"},\"name\":\"Tobias Harris\",\"weight\":\"226\",\"position\":\"PF\",\"birthDate\":\"July 15, 1992\",\"height\":\"6-8\"},{\"stats\":{\"blk\":\"0.2\",\"ast\":\"2.0\",\"mpg\":\"15.3\",\"threePerc\":\".301\",\"trb\":\"1.7\",\"pf\":\"1.3\",\"twoPerc\":\".512\",\"fgPerc\":\".462\",\"stl\":\"0.4\",\"tov\":\"0.7\",\"freePerc\":\".871\",\"pts\":\"8.0\"},\"name\":\"Tyrese Maxey\",\"weight\":\"200\",\"position\":\"SG\",\"birthDate\":\"November 4, 2000\",\"height\":\"6-2\"},{\"stats\":{\"blk\":\"0.6\",\"ast\":\"6.9\",\"mpg\":\"32.4\",\"threePerc\":\".300\",\"trb\":\"7.2\",\"pf\":\"2.9\",\"twoPerc\":\".562\",\"fgPerc\":\".557\",\"stl\":\"1.6\",\"tov\":\"3.0\",\"freePerc\":\".613\",\"pts\":\"14.3\"},\"name\":\"Ben Simmons\",\"weight\":\"240\",\"position\":\"PG\",\"birthDate\":\"July 20, 1996\",\"height\":\"6-11\"},{\"stats\":{\"blk\":\"0.1\",\"ast\":\"2.7\",\"mpg\":\"28.7\",\"threePerc\":\".450\",\"trb\":\"2.4\",\"pf\":\"1.7\",\"twoPerc\":\".485\",\"fgPerc\":\".467\",\"stl\":\"0.8\",\"tov\":\"1.1\",\"freePerc\":\".896\",\"pts\":\"12.5\"},\"name\":\"Seth Curry\",\"weight\":\"185\",\"position\":\"SG\",\"birthDate\":\"August 23, 1990\",\"height\":\"6-2\"},{\"stats\":{\"blk\":\"0.2\",\"ast\":\"1.5\",\"mpg\":\"19.3\",\"threePerc\":\".375\",\"trb\":\"2.1\",\"pf\":\"1.2\",\"twoPerc\":\".445\",\"fgPerc\":\".401\",\"stl\":\"0.9\",\"tov\":\"0.8\",\"freePerc\":\".732\",\"pts\":\"9.1\"},\"name\":\"Furkan Korkmaz\",\"weight\":\"202\",\"position\":\"SG\",\"birthDate\":\"July 24, 1997\",\"height\":\"6-7\"},{\"stats\":{\"blk\":\"1.4\",\"ast\":\"2.8\",\"mpg\":\"31.1\",\"threePerc\":\".377\",\"trb\":\"10.6\",\"pf\":\"2.4\",\"twoPerc\":\".541\",\"fgPerc\":\".513\",\"stl\":\"1.0\",\"tov\":\"3.1\",\"freePerc\":\".859\",\"pts\":\"28.5\"},\"name\":\"Joel Embiid\",\"weight\":\"280\",\"position\":\"C\",\"birthDate\":\"March 16, 1994\",\"height\":\"7-0\"},{\"stats\":{\"blk\":\"0.3\",\"ast\":\"0.8\",\"mpg\":\"16.7\",\"threePerc\":\".342\",\"trb\":\"2.4\",\"pf\":\"1.4\",\"twoPerc\":\".411\",\"fgPerc\":\".360\",\"stl\":\"0.5\",\"tov\":\"0.4\",\"freePerc\":\".667\",\"pts\":\"4.2\"},\"name\":\"Mike Scott\",\"weight\":\"237\",\"position\":\"PF\",\"birthDate\":\"July 16, 1988\",\"height\":\"6-7\"},{\"stats\":{\"blk\":\"0.1\",\"ast\":\"0.5\",\"mpg\":\"9.3\",\"threePerc\":\".368\",\"trb\":\"0.9\",\"pf\":\"0.8\",\"twoPerc\":\".333\",\"fgPerc\":\".361\",\"stl\":\"0.3\",\"tov\":\"0.3\",\"freePerc\":\".750\",\"pts\":\"3.7\"},\"name\":\"Isaiah Joe\",\"weight\":\"165\",\"position\":\"SG\",\"birthDate\":\"July 2, 1999\",\"height\":\"6-4\"},{\"stats\":{\"blk\":\"0.5\",\"ast\":\"0.5\",\"mpg\":\"6.8\",\"threePerc\":\".000\",\"trb\":\"2.3\",\"pf\":\"1.1\",\"twoPerc\":\".583\",\"fgPerc\":\".538\",\"stl\":\"0.4\",\"tov\":\"0.5\",\"freePerc\":\".500\",\"pts\":\"3.4\"},\"name\":\"Paul Reed\",\"weight\":\"210\",\"position\":\"PF\",\"birthDate\":\"June 14, 1999\",\"height\":\"6-9\"},{\"stats\":{\"blk\":\"0.2\",\"ast\":\"2.4\",\"mpg\":\"22.4\",\"threePerc\":\".388\",\"trb\":\"2.0\",\"pf\":\"1.0\",\"twoPerc\":\".547\",\"fgPerc\":\".482\",\"stl\":\"0.8\",\"tov\":\"1.0\",\"freePerc\":\".800\",\"pts\":\"8.7\"},\"name\":\"George Hill\",\"weight\":\"188\",\"position\":\"PG\",\"birthDate\":\"May 4, 1986\",\"height\":\"6-4\"},{\"stats\":{\"blk\":\"0.0\",\"ast\":\"0.4\",\"mpg\":\"4.9\",\"threePerc\":\".286\",\"trb\":\"0.8\",\"pf\":\"0.6\",\"twoPerc\":\".636\",\"fgPerc\":\".500\",\"stl\":\"0.1\",\"tov\":\"0.3\",\"freePerc\":\".737\",\"pts\":\"2.4\"},\"name\":\"Rayjon Tucker\",\"weight\":\"209\",\"position\":\"PG\",\"birthDate\":\"September 24, 1997\",\"height\":\"6-3\"},{\"stats\":{\"blk\":\"0.2\",\"ast\":\"0.2\",\"mpg\":\"9.0\",\"threePerc\":\".286\",\"trb\":\"0.9\",\"pf\":\"0.6\",\"twoPerc\":\".000\",\"fgPerc\":\".235\",\"stl\":\"0.3\",\"tov\":\"0.3\",\"freePerc\":\".833\",\"pts\":\"1.5\"},\"name\":\"Anthony Tolliver\",\"weight\":\"240\",\"position\":\"PF\",\"birthDate\":\"June 1, 1985\",\"height\":\"6-8\"},{\"stats\":{\"blk\":\"0.2\",\"ast\":\"0.8\",\"mpg\":\"16.8\",\"threePerc\":\".287\",\"trb\":\"2.9\",\"pf\":\"0.9\",\"twoPerc\":\".375\",\"fgPerc\":\".303\",\"stl\":\"0.3\",\"tov\":\"0.4\",\"freePerc\":\".800\",\"pts\":\"3.1\"},\"name\":\"Gary Clark\",\"weight\":\"225\",\"position\":\"PF\",\"birthDate\":\"November 16, 1994\",\"height\":\"6-6\"}],\"name\":\"Philadelphia 76ers\",\"losses\":\"23\"}]}");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }

        JSONArray arr = new JSONArray();
        arr.put(obj1);

        JSONObject main = new JSONObject();
        try {
            main.put("eastTeams", arr);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        assertEquals(main.toString(), WebScraperUtil.fillList(e, "eastTeams").toString());

        
    }

    @Test
    @DisplayName("Testing if fillList returns something")
    public void testFillListNotNull(){
        Elements e = new Elements();
        e.add(new Element("tr").append("<th scope=\"row\" class=\"left \" data-stat=\"team_name\"><a href=\"/teams/BRK/2021.html\">Brooklyn Nets</a>*&nbsp;<span class=\"seed\">(2)&nbsp;</span></th><td class=\"right \" data-stat=\"wins\">48</td><td class=\"right \" data-stat=\"losses\">24</td><td class=\"right \" data-stat=\"win_loss_pct\">.667</td><td class=\"right \" data-stat=\"gb\">1.0</td><td class=\"right \" data-stat=\"pts_per_g\">118.6</td><td class=\"right \" data-stat=\"opp_pts_per_g\">114.1</td><td class=\"right \" data-stat=\"srs\">4.24</td>"));
        e.add(new Element("tr").append("<th scope=\"row\" class=\"left \" data-stat=\"team_name\"><a href=\"/teams/PHI/2021.html\">Philadelphia 76ers</a>*&nbsp;<span class=\"seed\">(1)&nbsp;</span></th><td class=\"right \" data-stat=\"wins\">49</td><td class=\"right \" data-stat=\"losses\">23</td><td class=\"right \" data-stat=\"win_loss_pct\">.681</td><td class=\"right \" data-stat=\"gb\">—</td><td class=\"right \" data-stat=\"pts_per_g\">113.6</td><td class=\"right \" data-stat=\"opp_pts_per_g\">108.1</td><td class=\"right \" data-stat=\"srs\">5.28</td>"));

        assertNotNull(WebScraperUtil.fillList(e, "eastTeams"));
    }

    @Test
    @DisplayName("Testing if providing a non existent URL is handled by throwing an exception")
    public void testScrapeUrlToTxtFileWrongURL(){
        assertThrows(Exception.class, () -> {
            WebScraperUtil.scrapeUrlToTxtFile("https://stackoverflow.com/non-existent-url", "./src/main/resources/webscraper/output.txt");
        });
    }

    @Test
    @DisplayName("Testing if providing a non existent file path is handled by throwing an exception")
    public void testScrapeUrlToTxtFileWrongFilePath(){
        assertThrows(Exception.class, () -> {
            WebScraperUtil.scrapeUrlToTxtFile("https://stackoverflow.com", "./src/main/resourcesss/webscraper/output.txt");
        });
    }

    @Test
    @DisplayName("Testing if providing a working URL and a correct file path creates the .txt file")
    public void testScrapeUrlToTxtFileCheckIfCreatesFile(){
        try{
            WebScraperUtil.scrapeUrlToTxtFile("https://stackoverflow.com", "./src/main/resources/webscraper/output.txt");
        }catch(Exception e){
            System.out.println("Something happened in testScrapeUrlToTxtFileCheckIfCreatesFile test");
        }
        File file = new File("./src/main/resources/webscraper/output.txt");
        assertTrue(file.exists());
    }

    @Test
    @DisplayName("Testing if providing a wrong file path is handled by throwing an exception")
    public void testDeleteCSVRowsByFieldsWrongFilePath(){
        assertThrows(IOException.class, () -> {
            WebScraperUtil.deleteCSVRowsByFields("src\\main\\resources\\webscraper\\websites_status\\noSuchFile.csv", 1, "value");
        });
    }

    @Test
    @DisplayName("Testing if providing a negative column is handled by throwing an exception")
    public void testDeleteCSVRowsByFieldsNegativeColumn(){
        assertThrows(IOException.class, () -> {
            WebScraperUtil.deleteCSVRowsByFields("src\\main\\resources\\webscraper\\websites_status\\working_domains.csv", -1, "value");
        });
    }

    @Test
    @DisplayName("Testing if providing a wrong file path is handled by throwing an exception")
    public void testReadsCSVFileWrongFilePath(){
        assertThrows(IOException.class, () -> {
            WebScraperUtil.readCSVFile("src\\main\\resources\\webscraper\\no_such_folder\\working_domains.csv");
        });
    }

    @Test
    @DisplayName("Testing if providing a file with wrong format (not CSV) is handled by throwing an exception")
    public void testReadsCSVFileWrongFileFormat(){
        assertThrows(IOException.class, () -> {
            WebScraperUtil.readCSVFile("src\\main\\resources\\webscraper\\websites_status\\working_domains.txt");
        });
    }

}