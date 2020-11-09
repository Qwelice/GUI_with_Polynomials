package gui.panels.upperpanel;

import gui.eventlisteners.upperpanelevents.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpperPanel extends JPanel {
    private ArrayList<WithPointsEventHandler> pointEventHandlers = new ArrayList<>();
    private ArrayList<WithGraphicsEventHandler> graphicsEventHandlers = new ArrayList<>();
    private ArrayList<AddNewPointHandler> newPointEventHandlers = new ArrayList<>();
    private ArrayList<DeletePointHandler> deletePointHandlers = new ArrayList<>();
    private ArrayList<WithFullGraphHandler> withFullGraphHandlers = new ArrayList<>();

    private static final Dimension MIN_TEXT_SIZE = new Dimension(40, 20);

    private final JTextField xField;
    private final JTextField yField;
    private final JLabel x;
    private final JLabel y;
    private final SelectPanel selectPanel;
    private final JButton addPoint;
    private final JButton deletePoint;
    private final JLabel xD;
    private final JLabel yD;
    private final JTextField xDField;
    private final JTextField yDField;

    public UpperPanel(){
        x = new JLabel("X: ");
        y = new JLabel("Y: ");
        selectPanel = new SelectPanel();
        selectPanel.withPoints.setBackground(Color.WHITE);
        selectPanel.withPoints.setBorder(new EtchedBorder());
        selectPanel.withGraph.setBackground(Color.WHITE);
        selectPanel.withGraph.setBorder(new EtchedBorder());
        selectPanel.withFullGraph.setBackground(Color.WHITE);
        selectPanel.withFullGraph.setBorder(new EtchedBorder());
        selectPanel.withPoints.setSelected(true);
        selectPanel.withGraph.setSelected(true);
        selectPanel.withFullGraph.setSelected(true);
        xField = new JTextField();
        xField.setText("");
        yField = new JTextField();
        yField.setText("");
        xD = new JLabel("X for del: ");
        yD = new JLabel("Y for del: ");
        xDField = new JTextField();
        xDField.setText("");
        yDField = new JTextField();
        yDField.setText("");
        addPoint = new JButton("ADD point");
        deletePoint = new JButton("DEL point");

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(x, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(xD, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(xField, MIN_TEXT_SIZE.width, MIN_TEXT_SIZE.width, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(xDField, MIN_TEXT_SIZE.width, MIN_TEXT_SIZE.width, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(10)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(y, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(yD, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                    .addGap(5)
                    .addComponent(yField, MIN_TEXT_SIZE.width, MIN_TEXT_SIZE.width, GroupLayout.DEFAULT_SIZE)
                    .addGap(5)
                    .addComponent(yDField, MIN_TEXT_SIZE.width, MIN_TEXT_SIZE.width, GroupLayout.DEFAULT_SIZE)
                    .addGap(5)
                )
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(addPoint, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(deletePoint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                    .addGap(5)
                    .addComponent(selectPanel.withPoints, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addGap(5)
                    .addComponent(selectPanel.withGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addGap(5)
                )
                .addGap(5)
                .addComponent(selectPanel.withFullGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
        );
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(x, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(xField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(10)
                        .addComponent(y, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(yField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(addPoint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(selectPanel.withPoints, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(selectPanel.withFullGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(xD, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(xDField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(10)
                        .addComponent(yD, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(yDField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(deletePoint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(selectPanel.withGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
        );
        selectPanel.withPoints.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                notifyWithPointListeners(selectPanel.withPoints.isSelected());
            }
        });
        selectPanel.withGraph.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                notifyWithGraphicsListeners(selectPanel.withGraph.isSelected());
            }
        });
        selectPanel.withFullGraph.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                notifyWithFullGraphHandlers(selectPanel.withFullGraph.isSelected());
            }
        });
        addPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!xField.getText().isEmpty() && !yField.getText().isEmpty()){
                    var x = Double.parseDouble(xField.getText().strip());
                    var y = Double.parseDouble(yField.getText().strip());
                    xField.setText("");
                    yField.setText("");
                    notifyNewPointHandlers(x, y);
                }
            }
        });
        deletePoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!xDField.getText().isEmpty() && !yDField.getText().isEmpty()){
                    var x = Double.parseDouble(xDField.getText().strip());
                    var y = Double.parseDouble(yDField.getText().strip());
                    xDField.setText("");
                    yDField.setText("");
                    notifyDelPointHandlers(x, y);
                }
            }
        });
    }

    public void addWithPointListener(WithPointsEventHandler handler){
        pointEventHandlers.add(handler);
    }
    public void removeWithPointListener(WithPointsEventHandler handler){
        pointEventHandlers.remove(handler);
    }
    public void notifyWithPointListeners(boolean state){
        for(var l : pointEventHandlers)
            l.timeToChangeState(state);
    }
    public void addWithGraphicsListener(WithGraphicsEventHandler handler){
        graphicsEventHandlers.add(handler);
    }
    public void removeWithGraphicsListener(WithGraphicsEventHandler handler){
        graphicsEventHandlers.remove(handler);
    }
    public void notifyWithGraphicsListeners(boolean state){
        for(var l : graphicsEventHandlers)
            l.timeToChangeState(state);
    }
    public void addNewPointListener(AddNewPointHandler handler){
        newPointEventHandlers.add(handler);
    }
    public void removeNewPointListener(AddNewPointHandler handler){
        newPointEventHandlers.remove(handler);
    }
    public void notifyNewPointHandlers(double x, double y){
        for(var l : newPointEventHandlers)
            l.timeToAddPoint(x, y);
    }
    public void addDelPointListener(DeletePointHandler handler){
        deletePointHandlers.add(handler);
    }
    public void removeDelPointListener(DeletePointHandler handler){
        deletePointHandlers.remove(handler);
    }
    public void notifyDelPointHandlers(double x, double y){
        for(var h : deletePointHandlers)
            h.timeToDeletePoint(x, y);
    }
    public void addWithFullGraphListener(WithFullGraphHandler handler){
        withFullGraphHandlers.add(handler);
    }
    public void removeWithFullGraphListener(WithFullGraphHandler handler){
        withFullGraphHandlers.remove(handler);
    }
    public void notifyWithFullGraphHandlers(boolean x){
        for(var h : withFullGraphHandlers)
            h.timeToChangeState(x);
    }
}
