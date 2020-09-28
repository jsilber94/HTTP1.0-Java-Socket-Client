package concordia.comp445;

import java.util.Map;
import static java.util.Map.entry;

/**
 * This program demonstrates a client socket application that connects
 * to a Whois server to get information about a domain name.
 *
 * @author www.codejava.net
 * https://www.codejava.net/java-se/networking/java-socket-client-examples-tcp-ip
 */

public class Main {
    public static void main(String[] args) throws Exception {

//        String url = "https://www.google.ca/?q=hello+world";

        String url = "http://localhost:9891/";
        Map<String,String> headers = Map.ofEntries(entry("Accept: ","text/html"));
        System.out.println(HttpClient.get(url, headers));

    }
}
