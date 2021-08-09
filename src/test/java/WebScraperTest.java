import BaskteballWebScraper.WebScraper;
import BaskteballWebScraper.WebScraperException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class WebScraperTest {
    

    @Test
    @DisplayName("Testing if the basketballreference method writes something to a file")
    public void testBasketballreferenceWrites(){
        try {
            WebScraper.basketballreference();
        } catch (WebScraperException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            String contents = Files.readString(Path.of("src" + File.separator + "main" + File.separator + "resources" + File.separator + "webscraper" + File.separator + "data" + File.separator + "teamsData.json"));
            assertNotEquals("", contents);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

    }


}
