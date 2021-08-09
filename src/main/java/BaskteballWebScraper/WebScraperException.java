package BaskteballWebScraper;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import Util.WebScraperUtil;

/** Custom exception to be thrown when the specific basketball websites can't be successfully scraped.
 * Main usage is to delete the website's address which failed from the working_domains.txt file
 * @author Simon
 */

public class WebScraperException extends Exception {

    /**
     *
     * @param message
     *      Error message
     * @param cause
     *      Cause
     */
    public WebScraperException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message
     *      Error message
     */

    public WebScraperException(String message) {
        super(message);
    }

    /**
     *
     * @param cause
     *      Cause
     */
    public WebScraperException(Throwable cause) {
        super(cause);
    }

    /** The main constructor used to delete the failed domain from currently working domains' file and log the error
     *
     * @param message
     *      Error message
     * @param domainName
     *      The domain name of the website that failed to be scraped
     * @param workingDomainsPath
     *      The path to the file which holds all the currently working domains
     * @param cause
     *      Cause
     * @param filePath
     *      The path to the file where the exception is logged
     * @param level
     *      Logging level
     */
    // String filePath parameter is used to define the path where to log the exception
    public WebScraperException(String message, String domainName, String workingDomainsPath,Throwable cause, String filePath, Level level ) {
        super(message, cause);
        logException(filePath, level, message, cause);
        try{
            WebScraperUtil.deleteCSVRowsByFields(workingDomainsPath, 0, domainName);
        } catch (IOException e) {
            System.out.println("Error at WebScraperException.java while removing broken domain from the working_domains file");
        }
    }

    /** Method used to log the error
     *
     * @param filePath
     *      The path to the file where the exception is logged
     * @param level
     *      Logging level
     * @param message
     *      Error mesage
     * @param cause
     *      Cause
     */

    private void logException(String filePath, Level level, String message, Throwable cause){

        // Sets language to English, otherwise the logging would be done in host's default language
        Locale.setDefault(Locale.ENGLISH);

        Logger logger = Logger.getLogger("WebScraperExceptionLog");
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler(filePath, true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log the message
            logger.log(level, message, cause);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
