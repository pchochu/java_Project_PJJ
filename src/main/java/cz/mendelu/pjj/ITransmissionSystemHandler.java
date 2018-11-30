package cz.mendelu.pjj;

import java.util.LinkedList;

public interface ITransmissionSystemHandler {
    TransmissionSystem MasterTransmissionSystem = new TransmissionSystem();

    LinkedList<TransmissionSystem> listOfTransmissionSystems = new LinkedList<>();

    TransmissionSystem getMasterTransmissionSystem();

    void createTransmissionSystem(String vertex);

    void mergeTransmissionSystems(String src, String dest);

    void printAllTransmissionSystems();
}
