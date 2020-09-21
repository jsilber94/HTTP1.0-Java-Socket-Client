package concordia.comp445;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This program demonstrates a client socket application that connects
 * to a Whois server to get information about a domain name.
 *
 * @author www.codejava.net
 * https://www.codejava.net/java-se/networking/java-socket-client-examples-tcp-ip
 */

public class Main {
    public static void main(String[] args) {
        String hostname = "time.nist.gov";
        int port = 13;

        try (Socket socket = new Socket(hostname, port)) {

            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);

            int character;
            StringBuilder data = new StringBuilder();

            while ((character = reader.read()) != -1) {
                data.append((char) character);
            }

            System.out.println(data);

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
