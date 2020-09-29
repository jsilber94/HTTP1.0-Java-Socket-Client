package concordia.comp445;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpClient {

    private final static int httpPort = 80;
    private static final StringBuilder responseString = new StringBuilder();
    private static Socket socket = null;
    private static URI uri = null;
    private static String verb = null;

    private static OutputStream output = null;
    private static PrintWriter writer = null;
    private static BufferedReader reader = null;

    private static String data = null;
    private static File file = null;


    private static String request(Map<String, String> headers) throws IOException {
        HttpClient.sendRequest(headers);
        return HttpClient.receiveResponse();
    }

    private static void sendRequest(Map<String, String> headers) throws IOException {
        // send data to server: use OutputStream
        output = socket.getOutputStream();
        // wrap OutputSteam in PrinterWriter so we send data in text format + autoFlush
        writer = new PrintWriter(output, true);
        OutputStreamWriter out = new OutputStreamWriter(output);//, StandardCharsets.UTF_8);


        if (verb.equals("POST")) {
            writer.println("POST " + uri.getPath() + " HTTP/1.0");
        } else writer.println("GET " + uri.getPath() + " HTTP/1.0");

        writer.println("Accept:" + "*/*");
        writer.println("Host: " + uri.getHost());
        writer.println("User-Agent: Concordia-HTTP/1.0");

        boolean isContentTypePresent = false;
        boolean isContentLengthPresent = false;

        for (String key : headers.keySet()) {
            writer.println(key + ": " + headers.get(key));
//            if (verb.equals("POST")) {
////                if (key.trim().equalsIgnoreCase("content-type")) {
////                    isContentTypePresent = true;
////                }
//                if (key.trim().equalsIgnoreCase("content-length")) {
//                    isContentLengthPresent = true;
//                }
//            }
        }
//        if (verb.equals("POST") && !isContentTypePresent) {
//            writer.println("Content-Type: text/plain");
//        }
//        if (verb.equals("POST") && !isContentLengthPresent) {
//            writer.println("Content-Length: " + data.length());
//        }
//        if (verb.equals("POST")) {
            ObjectOutputStream o = new ObjectOutputStream(output);
            o.writeObject(data);
//        }
//        writer.println(data);

        writer.println("Connection: close");
        writer.println();
    }

    private static String receiveResponse() throws IOException {

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        return "gg";
        String line;
        while ((line = reader.readLine()) != null) {
            responseString.append(line).append("\n");
        }
        return responseString.toString();
    }


    public static String get(String url, Map<String, String> headers) {
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
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String url, Map<String, String> headers) {
        try {
            verb = "POST";
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
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String url, Map<String, String> headers, String body) {
        try {
            verb = "POST";
            if (url == null || url.equals(""))
                throw new MalformedURLException("Bad url");
            else
                uri = new URI(url);

            // connect to server
            socket = new Socket(uri.getHost(), HttpClient.httpPort);
            data = body;
            // make the request
            String response = request(headers);

            //close connections
            writer.close();
            output.close();
//            reader.close();

            socket.close();
            data = null;
            file = null;
            return response;
        } catch (MalformedURLException ex) {
            System.out.println("URL is invalid: " + ex.getMessage());
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static String post(String url, Map<String, String> headers, File file) {
//        return "";
//    }
}
