import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import BaskteballWebScraper.TeamCallable;
import BaskteballWebScraper.WebScraperException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TeamCallableTest {

    @Test
    @DisplayName("Testing if scrapeTeam throws an exception with incorrect element")
    public void testScrapeTeamThrowsWebScraperException(){
        assertThrows(WebScraperException.class, () -> {
            TeamCallable callable = new TeamCallable(new Element("<h1>Hello, World</h1>"));
            callable.scrapeTeam();
        });

    }


    @Test
    @DisplayName("Testing if scrapePlayerData throws an exception when given an invalid url")
    public void testScrapePlayerDataThrowsExceptionOnInvalidURL() throws Exception{
        Element e = new Element("tr").append("<th scope=\"row\" class=\"left \" data-stat=\"team_name\"><a href=\"/teams/BRK/2021.html\">Brooklyn Nets</a>*&nbsp;<span class=\"seed\">(2)&nbsp;</span></th><td class=\"right \" data-stat=\"wins\">48</td><td class=\"right \" data-stat=\"losses\">24</td><td class=\"right \" data-stat=\"win_loss_pct\">.667</td><td class=\"right \" data-stat=\"gb\">1.0</td><td class=\"right \" data-stat=\"pts_per_g\">118.6</td><td class=\"right \" data-stat=\"opp_pts_per_g\">114.1</td><td class=\"right \" data-stat=\"srs\">4.24</td>");
        assertThrows(Exception.class, () -> {
            TeamCallable callable = new TeamCallable(e);
            callable.scrapePlayerData("wrongurl", new JSONObject());
        });
    }

    @Test
    @DisplayName("Testing if scrapePlayerStats throws an exception when given an invalid url")
    public void testScrapePlayerStatsThrowsExceptionOnInvalidURL() throws Exception{
        Element e = new Element("tr").append("<th scope=\"row\" class=\"left \" data-stat=\"team_name\"><a href=\"/teams/BRK/2021.html\">Brooklyn Nets</a>*&nbsp;<span class=\"seed\">(2)&nbsp;</span></th><td class=\"right \" data-stat=\"wins\">48</td><td class=\"right \" data-stat=\"losses\">24</td><td class=\"right \" data-stat=\"win_loss_pct\">.667</td><td class=\"right \" data-stat=\"gb\">1.0</td><td class=\"right \" data-stat=\"pts_per_g\">118.6</td><td class=\"right \" data-stat=\"opp_pts_per_g\">114.1</td><td class=\"right \" data-stat=\"srs\">4.24</td>");
        assertThrows(Exception.class, () -> {
            TeamCallable callable = new TeamCallable(e);
            callable.scrapePlayerStats("wrongurl", new JSONObject(), new JSONArray());
        });
    }
}
