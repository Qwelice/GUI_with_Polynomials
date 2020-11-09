package gui.panels.controlpanel;

import gui.eventlisteners.controlpanelevents.SizeEventHandler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class ControlPanel extends JPanel {
    public ArrayList<SizeEventHandler> listeners = new ArrayList<>();

    private static final Dimension MIN_COMPONENT_SIZE = new Dimension(40, 20);

    SpinnerNumberModel snm1 = new SpinnerNumberModel(-5.1, -100., 4.9, 0.1);
    SpinnerNumberModel snm2 = new SpinnerNumberModel(5.1, -4.9, 100., 0.1);
    SpinnerNumberModel snm3 = new SpinnerNumberModel(-5.1, -100., 4.9, 0.1);
    SpinnerNumberModel snm4 = new SpinnerNumberModel(5.1, -4.9, 100., 0.1);

    JSpinner spinner_1 = new JSpinner(snm1);
    JSpinner spinner_2 = new JSpinner(snm2);
    JSpinner spinner_3 = new JSpinner(snm3);
    JSpinner spinner_4 = new JSpinner(snm4);

    JLabel LABEL_1 = new JLabel();
    JLabel LABEL_2 = new JLabel();
    JLabel LABEL_3 = new JLabel();
    JLabel LABEL_4 = new JLabel();

    public ControlPanel() {
        LABEL_1.setText("Xmin: ");
        LABEL_2.setText("Xmax: ");
        LABEL_3.setText("Ymin: ");
        LABEL_4.setText("Ymax: ");
        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);
        setBackground(Color.GRAY.brighter());

        spinner_1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                snm1.setMinimum((double) spinner_1.getValue() - 100.);
                snm2.setMinimum((double) spinner_1.getValue() + 0.1);
                notifyListeners();
            }
        });

        spinner_2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                snm1.setMaximum((double) spinner_2.getValue() - 0.1);
                snm2.setMaximum((double) spinner_2.getValue() + 100);
                notifyListeners();
            }
        });

        spinner_3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                snm3.setMinimum((double) spinner_3.getValue() - 100.);
                snm4.setMinimum((double) spinner_3.getValue() + 0.1);
                notifyListeners();
            }
        });

        spinner_4.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                snm3.setMaximum((double) spinner_4.getValue() - 0.1);
                snm4.setMaximum((double) spinner_4.getValue() + 100.);
                notifyListeners();
            }
        });

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(LABEL_1, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(LABEL_3, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(spinner_1, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(spinner_3, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(20)
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(LABEL_2, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(LABEL_4, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(spinner_2, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(spinner_4, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
        );
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(LABEL_1, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(spinner_1, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(20)
                        .addComponent(LABEL_2, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(spinner_2, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(LABEL_3, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(spinner_3, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(20)
                        .addComponent(LABEL_4, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(spinner_4, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
        );
    }

    public double getXMin() {
        return snm1.getNumber().doubleValue();
    }

    public double getXMax() {
        return snm2.getNumber().doubleValue();
    }

    public double getYMin() {
        return snm3.getNumber().doubleValue(); }

    public double getYMax() {
        return snm4.getNumber().doubleValue();
    }

    public void setXMin(double value){
        spinner_1.setValue(value);
    }
    public void setXMax(double value){
        spinner_2.setValue(value);
    }
    public void setYMin(double value){
        spinner_3.setValue(value);
    }
    public void setYMax(double value){
        spinner_4.setValue(value);
    }

    public void addListener(SizeEventHandler listener){
        listeners.add(listener);
    }
    public void removeListener(SizeEventHandler listener){
        listeners.remove(listener);
    }
    public void notifyListeners(){
        for(var l : listeners)
            l.timeToChangeSize();
    }
}
