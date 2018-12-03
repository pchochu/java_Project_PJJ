package cz.mendelu.pjj;

import java.util.regex.Pattern;

public class Grep{

    protected Pattern pattern;
    private ITransmissionSystemHandler transmissionSystemHandler;

    public Grep(ITransmissionSystemHandler transmissionSystemHandler) {
        this.transmissionSystemHandler = transmissionSystemHandler;
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
