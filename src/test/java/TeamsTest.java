import static org.junit.Assert.assertNull;

import API.Teams;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


public class TeamsTest {

    @Test
    @DisplayName("If Teams class object is created and the update() method is not ran getTeams() should return null")
    public void testScrapeTeamThrowsWebScraperException(){
        Teams test = new Teams();
        assertNull(test.getTeams());
    }

}