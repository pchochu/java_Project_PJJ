package cz.mendelu.pjj;

import java.io.*;
import java.util.regex.Matcher;

public class GrepFile extends Grep {

    public GrepFile(ITransmissionSystemHandler transmissionSystemHandler) {
        super(transmissionSystemHandler);
    }

    public void readInputFromFile(ITransmissionSystem transmissionSystem, String path){
        File file = new File(path);
        if(file.exists() && file.isFile()){
            grepFile(file, transmissionSystem, "[A-Z]", "vertex");
            grepFile(file, transmissionSystem, "[A-Z]:[A-Z]", "path");
        } else {
            throw new IllegalArgumentException("File path " + path + " is not valid");
        }
    }

    public void grepFile(File file, ITransmissionSystem transmissionSystem, String regex, String controller){
        try (Reader r = new FileReader(file)){
            grep(r, transmissionSystem, regex, controller);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void grep(Reader reader, ITransmissionSystem transmissionSystem, String regex, String controller) {
        setPattern(regex);
        BufferedReader br = new BufferedReader(reader);
        try {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher m = pattern.matcher(line);
                if (m.matches()) {
                    addVertexOrPath(transmissionSystem, line, controller);
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
