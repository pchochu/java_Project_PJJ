package cz.mendelu.pjj;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public interface ITransmissionSystem {

    Map<String, LinkedList<String>> getTransmissionSystem();

    void addPath(String src, String dest) throws IllegalArgumentException;

    void addVertex(String src) throws IllegalArgumentException;

    Set getAllVertices();

    Map<String, String> getAllPaths();



}
