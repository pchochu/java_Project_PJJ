package cz.mendelu.pjj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep{

    protected Pattern pattern;
    protected String message;
    private ITransmissionSystemHandler transmissionSystemHandler;

    public Grep(ITransmissionSystemHandler transmissionSystemHandler) {
        this.transmissionSystemHandler = transmissionSystemHandler;
    }

    public String getMessage() {
        return message;
    }

    protected void grep(Reader reader, ITransmissionSystem transmissionSystem, String regex, String controller) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        while ((line != null) && (line.isEmpty() == false)) {
            try {

                if (addIfMatches(line, transmissionSystem, controller, regex)) {
                    System.out.println(message);
                } else {
                    System.err.println(message);
                }
            } catch (Exception e) {
                System.err.println(e);
            } finally {
                line = br.readLine();
            }
        }
    }

    protected boolean addIfMatches(String line, ITransmissionSystem transmissionSystem, String controller, String regex) {
        setPattern(regex);
        Matcher m = pattern.matcher(line);
        if (m.matches()) {
            addVertexOrPath(transmissionSystem, line, controller);
            message = String.format("%s sucessfully added", line);
            return true;
        } else {
            message = String.format("You entered %s but you have to enter %s", line, pattern);
            return false;
        }
    }


    protected void addVertexOrPath(ITransmissionSystem transmissionSystem, String line, String controller){
        if (controller.equals("vertex")) {
            transmissionSystem.addVertex(line);
            transmissionSystemHandler.createTransmissionSystemAndAddToListOfSystems(line);
        }
        if (controller.equals("path")) {
            String[] fromTo = line.split(",");
            String src = fromTo[0];
            String dest = fromTo[1];
            transmissionSystem.addPath(src,dest);
            transmissionSystemHandler.mergeTransmissionSystems(src,dest);
        }
    }

    protected void setPattern(String regex){
        this.pattern = Pattern.compile(regex);
    }
}
