package cz.mendelu.pjj;

import java.io.InputStreamReader;
import java.io.Reader;

public class GrepTerminal extends Grep {

    public GrepTerminal(ITransmissionSystemHandler transmissionSystemHandler) {
        super(transmissionSystemHandler);
    }

    public void readInputFromTerminal(ITransmissionSystem transmissionSystem){
        try (Reader r = new InputStreamReader(System.in)) {
            System.out.println("You are reding vertices. To read vertice type [A-Z]");
            grep(r, transmissionSystem, "[A-Z]", "vertex");
            System.out.println("You are reding paths between vertice. To read path type [A-Z],[A-Z]");
            grep(r, transmissionSystem, "[A-Z],[A-Z]", "path");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
