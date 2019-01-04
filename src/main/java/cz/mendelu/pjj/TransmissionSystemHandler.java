package cz.mendelu.pjj;

import java.util.LinkedList;
public class TransmissionSystemHandler implements ITransmissionSystemHandler  {

    @Override
    public TransmissionSystem getMasterTransmissionSystem() {
        return MasterTransmissionSystem;
    }

    public LinkedList<TransmissionSystem> getListOfTransmissionSystems() {
        return listOfTransmissionSystems;
    }

    @Override
    public void createTransmissionSystemAndAddToListOfSystems(String vertex) {
        TransmissionSystem transmissionSystem = new TransmissionSystem();
        transmissionSystem.addVertex(vertex);
        listOfTransmissionSystems.add(transmissionSystem);
    }

    @Override
    public void mergeTransmissionSystems(String src, String dest) {
        try {
            TransmissionSystem sourceSystem = findTransmissionSystemByContainingVertex(src);
            TransmissionSystem destSystem = findTransmissionSystemByContainingVertex(dest);

            if (sourceSystem.equals(destSystem)) {
                sourceSystem.addPath(src, dest);
            } else {
                merge(sourceSystem, destSystem);
                listOfTransmissionSystems.remove(destSystem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        returns transmissionSystem from listOfTransmissionSystems containing String vertex
     */
    private TransmissionSystem findTransmissionSystemByContainingVertex(String vertex) {
        for(TransmissionSystem transmissionSystem: listOfTransmissionSystems){
            if(transmissionSystem.getAllVertices().contains(vertex)){
                return transmissionSystem;
            }
        }
        throw new IllegalArgumentException(String.format("There is no graph containing %s", vertex));
    }

    private void merge(TransmissionSystem sourceSystem, TransmissionSystem destSystem) {
        addNewVerticesFromDestSystemToSourceSystem(sourceSystem, destSystem);
        addNewPathsFromDestSystemToSourceSystem(sourceSystem,destSystem);
    }


    private void addNewPathsFromDestSystemToSourceSystem(TransmissionSystem sourceSystem, TransmissionSystem destSystem) {
        for(String vertex: destSystem.getAllVertices()){
            LinkedList<String> dest = destSystem.getTransmissionSystem().get(vertex);
            for(String path: dest){
                /*
                    Creating path creates two-way path. Therefore it is needed to remove
                    one one-way path from destSystem
                    Example: If there is path from A -> B, then there is path from B -> A
                    When going trough the system, there is the same path added twice
                    to system which causes error. One one-way path has to be removed
                  */
                destSystem.getTransmissionSystem().get(path).remove(vertex);
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
        for (TransmissionSystem transmissionSystem : getListOfTransmissionSystems()) {
            System.out.println("************");
            System.out.println(transmissionSystem.getAllVertices());
        }
    }
}
