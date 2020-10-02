package concordia.comp445;
import com.beust.jcommander.*;

import java.util.Map;

@Parameters(commandDescription = "GET request")
public class HttpcGet {

    @Parameter(names = {"-v"})
    private Boolean verbose = false;

    @Parameter(names = {"-o"})
    private String outputFile;

    @Parameter(names = {"-h"})
    private String header;

    @Parameter(description = "Host name")
    private String url;

    public Boolean getVerbose() {
        return verbose;
    }

    public String getHeader() {
        return header;
    }

    public String getUrl() {
        return url;
    }

    public String getOutputFile() {
        return outputFile;
    }
}

