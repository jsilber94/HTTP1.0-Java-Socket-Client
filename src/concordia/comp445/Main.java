package concordia.comp445;

import java.io.File;
import java.util.Map;

import static java.util.Map.entry;


public class Main {
    public static void main(String[] args) {
//        getParams();
//        delete();
//        getParamsVerbose();
//        postJson();
//        postFile();
//        getRedirect();
    }

    public static void getParams() {
        String url = "http://httpbin.org/get?course=networking&assignment=1";
        Map<String, String> headers = Map.ofEntries(entry("Accept ", "text/html"));
        System.out.println(HttpClient.get(url, headers, false));
    }

    public static void delete() {
        String url = "http://dummy.restapiexample.com/delete/";
        System.out.println(HttpClient.get(url, null, false));
    }

    public static void getParamsVerbose() {
        String url = "http://httpbin.org/get?course=networking&assignment=1";
        Map<String, String> headers = Map.ofEntries(entry("Accept ", "text/html"));
        System.out.println(HttpClient.get(url, headers, true));
    }

    public static void postJson() {
        String url = "http://httpbin.org/post";
        String json = "{\"Assignment\":1}";
        Map<String, String> headers = Map.ofEntries(entry("Content-Type:", "application/json"));
        System.out.println(HttpClient.post(url, headers, json, false));
    }

    public static void postFile() {
        String url = "http://httpbin.org/post";
        String userDirectory = System.getProperty("user.dir");
        File file = new File(userDirectory + "/test.txt");
        Map<String, String> headers = Map.ofEntries();//entry("Content-Type:", "text/html"));
        System.out.println(HttpClient.post(url, headers, file, false));
    }

    public static void getRedirect() {
        String url = "https://httpbingo.org/absolute-redirect/6";
        Map<String, String> headers = Map.ofEntries(entry("Accept ", "text/html"));
        System.out.println(HttpClient.get(url, headers, false));
    }
}
