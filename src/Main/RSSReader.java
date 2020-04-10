package Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class RSSReader {

    public static String readRSS(String urlAddress) throws Exception {
        URL rssURL = new URL(urlAddress);

        BufferedReader in = new BufferedReader(new InputStreamReader(rssURL.openStream()));
        String sourceCode = "";
        String line;

        while ((line = in.readLine()) != null) {
            if (line.contains("title")) {
                sourceCode += line + "\n";
            }
        }
        in.close();
        return sourceCode;








    }



}
