package cz.mendelu.pjj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;

public class GrepTerminal extends Grep {

    public GrepTerminal(ITransmissionSystemHandler transmissionSystemHandler) {
        super(transmissionSystemHandler);
    }

    public void readInputFromTerminal(ITransmissionSystem transmissionSystem){
        try (Reader r = new InputStreamReader(System.in)) {
            grep(r, transmissionSystem, "[A-Z]", "vertex");
            grep(r, transmissionSystem, "[A-Z],[A-Z]", "path");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void grep(Reader reader, ITransmissionSystem transmissionSystem, String regex, String controller) throws IOException {
        setPattern(regex);
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        while (!line.isEmpty()) {
            try {
                Matcher m = pattern.matcher(line);
                if (m.matches()) {
                    addVertexOrPath(transmissionSystem, line, controller);
                }
            } catch (Exception e) {
                System.err.println(e);
            }
            line = br.readLine();
        }
    }
}
