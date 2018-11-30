package cz.mendelu.pjj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;

public class GrepTerminal extends Grep{

    public GrepTerminal(ITransmissionSystemHandler transmissionSystemHandler) {
        super(transmissionSystemHandler);
    }

    public void readInputFromTerminal(ITransmissionSystem transmissionSystem){
        Reader r = new InputStreamReader(System.in);
        grep(r, transmissionSystem, "[A-Z]", "vertex");
        grep(r, transmissionSystem, "[A-Z]:[A-Z]", "path");
    }

    private void grep(Reader reader, ITransmissionSystem transmissionSystem, String regex, String controller) {
        setPattern(regex);
        BufferedReader br = new BufferedReader(reader);
        try {
            String line = br.readLine();
            while (!line.isEmpty()) {
                Matcher m = pattern.matcher(line);
                if (m.matches()) {
                    addVertexOrPath(transmissionSystem, line, controller);
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
