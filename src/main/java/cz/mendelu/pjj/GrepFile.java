package cz.mendelu.pjj;

import java.io.*;

public class GrepFile extends Grep {

    public GrepFile(ITransmissionSystemHandler transmissionSystemHandler) {
        super(transmissionSystemHandler);
    }

    public void readInputFromFile(ITransmissionSystem transmissionSystem, String path) {
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            grepFile(file, transmissionSystem, "[A-Z]", "vertex");
            grepFile(file, transmissionSystem, "[A-Z],[A-Z]", "path");
        } else {
            throw new IllegalArgumentException("File path " + path + " is not valid");
        }
    }

    private void grepFile(File file, ITransmissionSystem transmissionSystem, String regex, String controller) {
        try (Reader r = new FileReader(file)) {
            grep(r, transmissionSystem, regex, controller);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
