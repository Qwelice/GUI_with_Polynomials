package gui.panels.functionpanel;

import convertation.ConvertPlane;
import convertation.Converter;
import gui.eventlisteners.mainpanelevents.functionpanelevents.SmoothEventHandler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class FunctionPanel extends JPanel {
    private ArrayList<SmoothEventHandler> smoothEventHandlers = new ArrayList<>();
    private static final Dimension MIN_L_SIZE = new Dimension(40, 20);
    private static final Dimension MIN_FIELD_SIZE = new Dimension(100, 20);

    private final JSpinner spinner;
    private final SpinnerNumberModel snm = new SpinnerNumberModel(100, 1, 10000, 1);
    private final JLabel coordinates;
    private final JTextField functionField;
    private final JLabel polynomial = new JLabel("Polynomial: ");

    public FunctionPanel(){
        spinner = new JSpinner(snm);
        coordinates = new JLabel("X: 0.0, Y: 0.0");
        functionField = new JTextField("None");
        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(coordinates, MIN_L_SIZE.width, MIN_L_SIZE.width, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(spinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(20)
                .addGroup(groupLayout.createParallelGroup()
                    .addGap(5)
                    .addComponent(polynomial, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addGap(5)
                    .addComponent(functionField, MIN_FIELD_SIZE.width, MIN_FIELD_SIZE.width, GroupLayout.DEFAULT_SIZE)
                    .addGap(5)
                )

        );
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(coordinates, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(20)
                        .addComponent(polynomial, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(spinner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(functionField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
        );
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                notifySmoothHandlers(snm.getNumber().intValue());
            }
        });
    }

    public void updateCoordinates(int x, int y, ConvertPlane plane){
        var dx = Converter.xScr2Crt(x, plane);
        var dy = Converter.yScr2Crt(y, plane);
        coordinates.setText("X: " + Math.round(100*dx)/100. + " Y: " + Math.round(100*dy)/100.);
    }

    public void updatePolynomial(String polynomial){
        functionField.setText(polynomial);
    }

    public void addSmoothListener(SmoothEventHandler handler){
        smoothEventHandlers.add(handler);
    }
    public void removeSmoothListener(SmoothEventHandler handler){
        smoothEventHandlers.remove(handler);
    }

    public void notifySmoothHandlers(int x){
        for(var h : smoothEventHandlers)
            h.timeToChangeSmoothness(x);
    }
}
