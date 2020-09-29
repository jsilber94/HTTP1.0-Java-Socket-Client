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
        post();
//        get();
    }

    public static void get() {
        String url = "http://localhost:9891/";
        Map<String, String> headers = Map.ofEntries(entry("Accept ", "text/html"));
        System.out.println(HttpClient.get(url, headers));
    }

    public static void post() {

        String url = "http://httpbin.org/post";
//        String url = "http://localhost:9891/login";
//        String json = "{\"email\":1,\"password\":\"John\"}";
        String data = "hello";
//        Map<String,String> headers = Map.ofEntries(entry("a","b"));
        Map<String, String> headers = Map.ofEntries(entry("Content-Length", data.length() + ""));//, entry("Content-Type", "text/plain"));
        System.out.println(HttpClient.post(url, headers, data));

    }

    public static void post1() {
        String url = "http://localhost:9891/";
        Map<String, String> headers = Map.ofEntries(entry("Accept: ", "text/html"));
        System.out.println(HttpClient.post(url, headers));
    }
}
