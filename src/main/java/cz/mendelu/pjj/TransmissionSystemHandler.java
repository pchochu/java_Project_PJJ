package cz.mendelu.pjj;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Set;

public class TransmissionSystemHandler implements ITransmissionSystemHandler  {

    @Override
    public TransmissionSystem getMasterTransmissionSystem() {
        return MasterTransmissionSystem;
    }

    @Override
    public void createTransmissionSystem(String vertex) {
        TransmissionSystem transmissionSystem = new TransmissionSystem();
        transmissionSystem.addVertex(vertex);
        listOfTransmissionSystems.add(transmissionSystem);
    }

    @Override
    public void mergeTransmissionSystems(String src, String dest) {
        TransmissionSystem sourceSystem = findTransmissionSystemByContainingVertex(src);
        TransmissionSystem destSystem = findTransmissionSystemByContainingVertex(dest);
        if(sourceSystem.equals(destSystem)){
            sourceSystem.addPath(src,dest);
        } else {
            merge(sourceSystem, destSystem);
            listOfTransmissionSystems.remove(destSystem);
        }
    }

    private TransmissionSystem findTransmissionSystemByContainingVertex(String vertex) {
        for(TransmissionSystem transmissionSystem: listOfTransmissionSystems){
            if(transmissionSystem.getAllVertices().contains(vertex)){
                return transmissionSystem;
            }
        }
        throw new IllegalArgumentException();
    }

    private void merge(TransmissionSystem sourceSystem, TransmissionSystem destSystem) {
        addNewVerticesFromDestSystemToSourceSystem(sourceSystem, destSystem);
        addNewPathsFromDestSystemToSourceSystem(sourceSystem,destSystem);
    }

    private void addNewPathsFromDestSystemToSourceSystem(TransmissionSystem sourceSystem, TransmissionSystem destSystem) {
        for(String vertex: destSystem.getAllVertices()){
            LinkedList<String> dest = destSystem.getTransmissionSystem().get(vertex);
            for(String path: dest){
                sourceSystem.addPath(vertex, path);
            }
        }
    }

    private void addNewVerticesFromDestSystemToSourceSystem(TransmissionSystem sourceSystem, TransmissionSystem destSystem) {
        for(String newVertexToSourceSystem: destSystem.getAllVertices()) {
            sourceSystem.addVertex(newVertexToSourceSystem);
        }
    }

    @Override
    public void printAllTransmissionSystems() {
        for(TransmissionSystem transmissionSystem: listOfTransmissionSystems){
            System.out.println("************");
            System.out.println(transmissionSystem.getAllVertices());
        }
    }
}
