package concordia.comp445;

import com.beust.jcommander.Parameter;

@Parameters(commandDescription = "POST request")
public class HttpcPost {

    @Parameter(names = {"-v"})
    private Boolean verbose = false;

    @Parameter(names = {"-o"})
    private String outputFile;

    @Parameter(names = {"-h"})
    private String header;

    @Parameter(names = {"-d"}, forbids={"-f"})
    private String option;

    @Parameter(names = {"-f"},forbids={"-d"})
    private String inputFile;

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

    public String getOption() {
        return option;
    }

    public String getInputFile() {
        return inputFile;
    }

}
