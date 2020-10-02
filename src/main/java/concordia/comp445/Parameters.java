package concordia.comp445;
import com.beust.jcommander.Parameter;

public class Parameters {

    @Parameter(names = {"help"}, help = true)
    private Boolean help = false;

    public Boolean getHelp() {
        return help;
    }
}
