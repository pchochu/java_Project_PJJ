package cz.mendelu.pjj;

public class AppHandler {

    private Grep grep;
    private ITransmissionSystemHandler transmissionSystemHandler;
    private String[] args;

    public AppHandler(String[] args) {
        this.transmissionSystemHandler = new TransmissionSystemHandler();
        this.args = args;
    }

    public void start() {
        TransmissionSystem masterTransmissionSystem = transmissionSystemHandler.getMasterTransmissionSystem();
        if(args.length < 1){
            grep = new GrepTerminal(transmissionSystemHandler);
            readVerticesAndPathsFromTerminalAndSaveToSystem(masterTransmissionSystem);
        } else if(args.length < 2){
            grep = new GrepFile(transmissionSystemHandler);
            try {
                readVerticesAndPathsFromFileAndSaveToSystem(masterTransmissionSystem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        transmissionSystemHandler.printAllTransmissionSystems();
    }

    private void readVerticesAndPathsFromTerminalAndSaveToSystem(ITransmissionSystem transmissionSystem){
        ((GrepTerminal)grep).readInputFromTerminal(transmissionSystem);
    }

    private void readVerticesAndPathsFromFileAndSaveToSystem(ITransmissionSystem transmissionSystem){
        ((GrepFile)grep).readInputFromFile(transmissionSystem, args[0]);
    }
}
