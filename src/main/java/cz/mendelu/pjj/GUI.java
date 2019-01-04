package cz.mendelu.pjj;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Set;

public class GUI extends JFrame {
    ITransmissionSystem transmissionSystem;
    private JButton pridaj;
    private JTextArea subGrafy;
    private JTextField textPridaj;
    private JTextArea vrcholy;
    private JLabel text;
    private JPanel root;
    private JButton ukonci;
    private JLabel msg;
    private String vertexOrPath = "vertex";
    private ITransmissionSystemHandler transmissionSystemHandler;
    private Grep grep;

    public GUI(ITransmissionSystemHandler transmissionSystemHandler, ITransmissionSystem transmissionSystem) {
        this.transmissionSystemHandler = transmissionSystemHandler;
        grep = new Grep(transmissionSystemHandler);
        this.transmissionSystem = transmissionSystem;
        add(root);
        setTitle("This is my title");
        setSize(500, 300);


        pridaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    grep.addIfMatches(textPridaj.getText(), transmissionSystem, getVertexOrPath(), getRegex());
                    showMessage(grep.getMessage());
                    vrcholy.setText(vypisVrcholy());
                    subGrafy.setText(vypisSubGrafy());
                    textPridaj.setText(null);
                } catch (Exception ex) {
                    showMessage(ex.toString());
                }
            }
        });

        ukonci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vertexOrPath = "path";
                text.setText("Pridavanie ciest");
            }
        });
    }

    public String getVertexOrPath() {
        return vertexOrPath;
    }

    private String vypisSubGrafy() {
        LinkedList<TransmissionSystem> list = transmissionSystemHandler.getListOfTransmissionSystems();
        String output = "";
        for (TransmissionSystem transmissionSystem : list) {
            output = output + "\n";
            output = output + "**********";
            output = output + "\n";
            output = output + transmissionSystem.getAllVertices();
        }
        return output;
    }

    private String vypisVrcholy() {
        LinkedList<TransmissionSystem> list = transmissionSystemHandler.getListOfTransmissionSystems();
        String output = "";
        for (TransmissionSystem transmissionSystem : list) {
            Set<String> vertices = transmissionSystem.getAllVertices();
            for (String s : vertices) {
                output = output + s + ";";
            }
        }
        return output;
    }


    public void showMessage(String message) {
        msg.setText(message);
    }

    private String getRegex() {
        if (getVertexOrPath().equals("vertex")) {
            return String.format("[A-Z]");
        } else {
            return String.format("[A-Z],[A-Z]");
        }
    }
}
