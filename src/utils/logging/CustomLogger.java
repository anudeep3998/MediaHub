package utils.logging;

import java.io.IOException;
import java.util.logging.*;

/**
 * User: Anudeep Bulla
 * Date: 10/25/13
 * Time: 1:03 PM
 */
public class CustomLogger {
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
    static private FileHandler fileHTML;
    static private Formatter formatterHTML;

    static public void setup() throws IOException {

        // Get the global logger to configure it
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        logger.setLevel(Level.INFO);
        fileTxt = new FileHandler("Logging.txt");
        fileHTML = new FileHandler("Logging.html");

        // create txt Formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

        // create HTML Formatter
        formatterHTML = new HTMLFormatter();
        fileHTML.setFormatter(formatterHTML);
        logger.addHandler(fileHTML);
    }

}
