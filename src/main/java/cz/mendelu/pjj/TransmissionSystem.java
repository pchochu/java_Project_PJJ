package cz.mendelu.pjj;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class TransmissionSystem implements ITransmissionSystem {

    private Map<String, LinkedList<String>> transmissionSystem;

    public TransmissionSystem() {
        this.transmissionSystem = new HashMap<>();
    }

    @Override
    public Map<String, LinkedList<String>> getTransmissionSystem() {
        return transmissionSystem;
    }

    /*
        if vertex src or vertex dest doesn't exist in the system, throws exception.
        if the path from src to dest already exists, throws exception
        otherwise, create path between src and dest
     */
    @Override
    public void addPath(String src, String dest) throws IllegalArgumentException {
        if(!transmissionSystem.containsKey(src)){
            throw new IllegalArgumentException(String.format("No vertex %s found", src));
        } else if(!transmissionSystem.containsKey(dest)){
            throw new IllegalArgumentException(String.format("No vertex %s found", dest));
        } else if(transmissionSystem.containsKey(src)){
            // read actual paths from vertex src
            LinkedList<String> ll = transmissionSystem.get(src);
            if(ll.contains(dest)) {
                throw new IllegalArgumentException(String.format("The path from %s to %s already exists", src, dest));
            }
        }
        transmissionSystem.get(src).add(dest);
        transmissionSystem.get(dest).add(src);
    }

    @Override
    public void addVertex(String src) throws IllegalArgumentException {
        if(!transmissionSystem.containsKey(src)){
            transmissionSystem.put(src, new LinkedList<>());
        } else{
            throw new IllegalArgumentException(String.format("Vertex %s already exists", src));
        }
    }

    @Override
    public Set<String> getAllVertices() {
        return transmissionSystem.keySet();
    }

    @Override
    public Map getAllPaths() {
        Map<String, String> paths = new HashMap<>();
        for (String vertex: transmissionSystem.keySet()) {
            LinkedList<String> ll = transmissionSystem.get(vertex);
            for(String pathTo: ll){
                paths.put(vertex, pathTo);
            }
        }
        return paths;
    }

}
