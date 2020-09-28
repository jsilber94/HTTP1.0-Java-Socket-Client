package concordia.comp445;

import java.io.*;
import java.net.*;
import java.util.Map;

public class HttpClient {

    private final static int httpPort = 9891;
    private static final StringBuilder responseString = new StringBuilder();
    private static Socket socket = null;
    private static URI uri = null;
    private static String verb = null;

    private static OutputStream output = null;
    private static PrintWriter writer = null;
    private static BufferedReader reader = null;

    private static String request(Map<String, String> headers) throws IOException {
        HttpClient.sendRequest(headers);
        return HttpClient.receiveResponse();
    }

    private static void sendRequest(Map<String, String> headers) throws IOException {
        // send data to server: use OutputStream
        output = socket.getOutputStream();
        // wrap OutputSteam in PrinterWriter so we send data in text format + autoFlush
        writer = new PrintWriter(output, true);

        if (verb.equals("GET"))
            writer.println("GET " + uri.getPath() + " HTTP/1.0");
        else writer.println("POST " + uri.getPath() + " HTTP/1.0");

        writer.println("Host: " + uri.getHost());
        writer.println("User-Agent: HTTP/1.0 Client");

        for (String key : headers.keySet()) {
            writer.println(key + ": " + headers.get(key));
        }
        writer.println("Connection: close");
        writer.println();
    }

    private static String receiveResponse() throws IOException {

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            responseString.append(line).append("\n");
        }
        return responseString.toString();
    }


    public static String get(String url, Map<String, String> headers) throws URISyntaxException {
        try {
            verb = "GET";
            if (url == null || url.equals(""))
                throw new MalformedURLException("Bad url");
            else
                uri = new URI(url);

            // connect to server
            socket = new Socket(uri.getHost(), HttpClient.httpPort);
            // make the request
            String response = request(headers);

            //close connections
            writer.close();
            output.close();
            reader.close();

            socket.close();
            return response;
        } catch (MalformedURLException ex) {
            System.out.println("URL is invalid: " + ex.getMessage());
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
        return null;
    }


//    public static String doPost(String url, Map<String, String> headers) {
//        return "";
//    }
//
//    public static String doPost(String url, Map<String, String> headers, String data) {
//        return "";
//    }
//
//    public static String doPost(String url, Map<String, String> headers, File file) {
//        return "";
//    }
}
