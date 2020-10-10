package concordia.comp445;

import com.beust.jcommander.JCommander;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class httpc {

    private static String url;
    private static Boolean isVerbose = false;
    private static Map<String, String> headers;
    private static Map<String, String> options;

    public static void main(String[] args) {
        String requestType = "";
        String response = "";

        // parse
        Parameters parameters = new Parameters();
        HttpcGet get = new HttpcGet();
        HttpcPost post = new HttpcPost();
        JCommander parser = JCommander.newBuilder().addObject((parameters)).addCommand("get", get).addCommand("post", post).build();
        parser.parse(args);

        requestType = parser.getParsedCommand(); // GET | POST | help

        // printing out the text for the command httpc help
        if(parameters.getHelp()) {
            HttpcHelp help = new HttpcHelp(requestType);
            System.out.println(help.displayHelpText());
        } else {

            // for the command httpc get
            if(requestType.equals("get")) {
                headers = new HashMap<String, String>();
                url = get.getUrl();
                isVerbose = get.getVerbose();
                parseParameters(get.getHeader()); // headers
                response = HttpClient.get(url, headers, isVerbose);
                if(get.getOutputFile() != null){
                    try {
                        FileWriter myWriter = new FileWriter(get.getOutputFile());
                        myWriter.write(response);
                        myWriter.close();
                    }catch(IOException ioe){
                        System.out.println("Unable to find output file.");
                        System.out.println("Printing response.");
                        System.out.println(response);
                    }
                }else System.out.println(response);

            // for the command httpc post
            } else if(requestType.equals("post")) {
                url = post.getUrl();
                isVerbose = post.getVerbose();
                headers = new HashMap<String, String>();
                options = new HashMap<String, String>();
                parseParameters(post.getHeader());
                if(post.getOption() != null) {
                    options.put("inline_data", post.getOption());
                }
                if(post.getInputFile() != null) {
                    options.put("input_file", post.getInputFile());
                }
                try{
                    if (options.get("inline_data") != null)
                        response = HttpClient.post(url, headers,options.get("inline_data"), isVerbose);
                    else if (options.get("input_file") != null){
                        if(options.get("input_file").substring(0).equals("/")){
                            File file = new File(System.getProperty("user.dir") + options.get("input_file"));
                            response = HttpClient.post(url,headers, file, isVerbose);
                        }else {
                            File file = new File(System.getProperty("user.dir") +"/"+ options.get("input_file"));
                            response = HttpClient.post(url,headers, file, isVerbose);
                        }
                    }
                    else
                        response = HttpClient.post(url, headers, isVerbose);
                    if(post.getOutputFile() != null) {
                        FileWriter myWriter = new FileWriter(post.getOutputFile());
                        myWriter.write(response);
                        myWriter.close();
                    }else System.out.println(response);
                } catch(IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch(IOException ioe){
                    System.out.println("Unable to find output file.");
                    System.out.println("Printing response.");
                    System.out.println(response);
                }
            }
        }
    }

    private static void parseParameters(String parameters) {
        if(parameters == null || parameters.length() == 0) return;
        String[] header = parameters.split(":");
        headers.put(header[0], header[1]);
    }

    private static String readInputFile(String inputFileName) {
        String output = null;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            StringBuilder inputBuilder = new StringBuilder();
            String line = reader.readLine();

            while(line != null) {
                inputBuilder.append(line);
                line = reader.readLine();
            }
            output = inputBuilder.toString();
            reader.close();
        } catch(Exception e) {
            System.out.println("Could not read file");
        }
        return output;
    }
}
