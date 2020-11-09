package gui.panels.auxiliarypanel;

import gui.eventlisteners.footerevents.ColorDerListener;
import gui.eventlisteners.footerevents.ColorFuncListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AuxiliaryPanel extends JPanel {
    private ArrayList<ColorDerListener> derHandlers = new ArrayList<>();
    private ArrayList<ColorFuncListener> funcHandlers = new ArrayList<>();
    private static final Dimension MIN_COMPONENT_SIZE = new Dimension(100, 20);

    private final JTextField derField;
    private final JButton forDer;
    private final JButton forFunction;
    private final JLabel derivative;

    private static final Dimension MIN_B_SIZE = new Dimension(150, 20);

    public AuxiliaryPanel(){
        derField = new JTextField();
        derField.setText("None");
        forDer = new JButton("DerColor");
        forFunction = new JButton("FuncColor");
        derivative = new JLabel("Derivative");
        forDer.setToolTipText("Derivative");
        forFunction.setToolTipText("Function");
        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(forDer, MIN_B_SIZE.width, MIN_B_SIZE.width, MIN_B_SIZE.width)
                        .addGap(5)
                        .addComponent(forFunction, MIN_B_SIZE.width, MIN_B_SIZE.width, MIN_B_SIZE.width)
                        .addGap(5)
                )
                .addGap(20)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(derivative, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(derField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
        );
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(forDer,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(20)
                        .addComponent(derivative, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(forFunction, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(20)
                        .addComponent(derField, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height, MIN_COMPONENT_SIZE.height)
                        .addGap(5)
                )
        );
        forDer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(AuxiliaryPanel.this, "Color", null);
                notifyDerColorHandlers(newColor);
            }
        });
        forFunction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(AuxiliaryPanel.this, "Color", null);
                notifyFuncColorHandlers(newColor);
            }
        });
    }
    public void setPolynomial(String polynomial){
        derField.setText(polynomial);
    }
    public void addDerColorListener(ColorDerListener handler){
        derHandlers.add(handler);
    }
    public void addFuncColorListener(ColorFuncListener handler){
        funcHandlers.add(handler);
    }
    public void removeDerColorListener(ColorDerListener handler){
        derHandlers.remove(handler);
    }
    public void removeFuncColorListener(ColorFuncListener handler){
        funcHandlers.remove(handler);
    }
    public void notifyDerColorHandlers(Color color){
        for(var h : derHandlers)
            h.timeToChangeColor(color);
    }
    public void notifyFuncColorHandlers(Color color){
        for(var h : funcHandlers)
            h.timeToChangeColor(color);
    }
}
