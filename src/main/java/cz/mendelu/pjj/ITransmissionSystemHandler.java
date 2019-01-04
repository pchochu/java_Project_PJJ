package cz.mendelu.pjj;

import java.util.LinkedList;

public interface ITransmissionSystemHandler {
    /*
        MasterTransmissionSystem is TransmissionSystem that saves all the vertices and their paths.

        ************************************************************************************************
        2nd part of the project (not implemented, just a caption of thoughts):
        MasterTransmissionSystem is created to handle second part of the project (galvanic isolation of vertices).
        When the system contains galvanic connection and the connection is disconnected, two subsystems are
        created. On the other hand, when the system is created backwards from two subsystems, it is checked
        whether there is path in MasterTransmissionSystem. If there is path from src to dest and src and dest
        vertices are marked as galvanic, two subsystems connect together.
        ***************************************************************************************************
     */
    TransmissionSystem MasterTransmissionSystem = new TransmissionSystem();

    /*
        List of all actual systems (subsystems). Not containing MasterSystem
     */
    LinkedList<TransmissionSystem> listOfTransmissionSystems = new LinkedList<>();

    /*
        returns MasterTransmissionSystem
     */
    TransmissionSystem getMasterTransmissionSystem();

    /*
        Creates a new subsystem containing just one vertex and adds this.subsystem to list of subsystems
     */
    void createTransmissionSystemAndAddToListOfSystems(String vertex);

    /*
        When new path between two vertices is created, merge systems src and dest a.k.a systems that
         contain these vertices, together. If src and dest are in the same system, create new path
         in the system. When two distinct systems are merged, the dest system is removed from list of systems.
     */
    void mergeTransmissionSystems(String src, String dest);

    /*
        Print all actual system stored in listOfTransmissionSystems
     */
    void printAllTransmissionSystems();

    LinkedList<TransmissionSystem> getListOfTransmissionSystems();
}
