package gui.panels.mainpanel;

import gui.eventlisteners.mainpanelevents.rightpanelevents.DeleteNodeHandler;
import gui.eventlisteners.mainpanelevents.rightpanelevents.RemoveAllHandler;
import gui.eventlisteners.mainpanelevents.rightpanelevents.SelectEventHandler;
import gui.visualobjects.VPoint;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class RightPanel extends JPanel implements Comparator<VPoint<Double>> {

    private ArrayList<VPoint<Double>> nodesList = new ArrayList<>();

    private ArrayList<DeleteNodeHandler> deleteNodeHandlers = new ArrayList<>();
    private ArrayList<RemoveAllHandler> removeAllHandlers = new ArrayList<>();
    private ArrayList<SelectEventHandler> selectEventHandlers = new ArrayList<>();

    private static final Dimension MIN_COMPONENT_SIZE = new Dimension(70, 100);

    private final JList nodes;
    private final JLabel nodesLabel;
    private final JScrollPane nodeScroll;
    private DefaultListModel<String> dlm;
    private final JButton clear;
    private final JButton deleteNode;

    public RightPanel(){
        nodesLabel = new JLabel("Nodes: ");
        dlm = new DefaultListModel<>();
        dlm.add(0, "None");
        nodes = new JList(dlm);
        nodeScroll = new JScrollPane(nodes);
        clear = new JButton("Clear");
        deleteNode = new JButton("Del");
        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(nodesLabel, MIN_COMPONENT_SIZE.width, MIN_COMPONENT_SIZE.width, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(nodeScroll, MIN_COMPONENT_SIZE.width, MIN_COMPONENT_SIZE.width, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(deleteNode, MIN_COMPONENT_SIZE.width, MIN_COMPONENT_SIZE.width, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(clear, MIN_COMPONENT_SIZE.width, MIN_COMPONENT_SIZE.width, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
        );
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGap(5)
                .addComponent(nodesLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(nodeScroll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(deleteNode, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(clear, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
        );
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyRemoveAllHandlers();
                removeAllPoints();
            }
        });
        nodes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(nodes.getSelectedIndex() != -1)
                    notifySelectHandlers(nodesList.get(nodes.getSelectedIndex()));
            }
        });
        deleteNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nodes.getSelectedIndex() != -1){
                    var index = nodes.getSelectedIndex();
                    notifyDeleteNodeHandlers(nodesList.get(index));
                    removePoint(nodesList.get(index).getX(), nodesList.get(index).getY());
                }
            }
        });
    }

    public void addPoint(double x, double y){
        nodesList.add(new VPoint<>(x, y));
        nodesList.sort(this);
        dlm.clear();
        for(var n : nodesList)
            dlm.add(dlm.size(), "X: " + Math.round(10000*n.getX())/10000. + " Y: "
                    + Math.round(10000*n.getY())/10000.);
    }

    public void removePoint(double x, double y){
        for(int i = 0; i < nodesList.size(); i++){
            if(nodesList.get(i).contains(x, y)){
                nodesList.remove(i);
            }
        }
        nodesList.sort(this);
        dlm.clear();
        for(var n : nodesList)
            dlm.add(dlm.size(), "X: " + Math.round(10000*n.getX())/10000. + " Y: "
                    + Math.round(10000*n.getY())/10000.);
        if(dlm.size() == 0)
            dlm.add(0, "None");
    }

    public void removeAllPoints(){
        dlm.clear();
        nodesList.clear();
        dlm.add(0, "None");
    }

    public void addRemoveAllListener(RemoveAllHandler handler){
        removeAllHandlers.add(handler);
    }
    public void removeRemoveAllListener(RemoveAllHandler handler){
        removeAllHandlers.remove(handler);
    }
    public void notifyRemoveAllHandlers(){
        for(var l : removeAllHandlers)
            l.timeToRemoveAll();
    }
    public void addSelectListener(SelectEventHandler handler){
        selectEventHandlers.add(handler);
    }
    public void removeSelectListener(SelectEventHandler handler){
        selectEventHandlers.remove(handler);
    }
    private void notifySelectHandlers(VPoint<Double> node){
        for(var l : selectEventHandlers)
            l.timeToSelect(node);
    }
    public void addDeleteNodeListener(DeleteNodeHandler handler){
        deleteNodeHandlers.add(handler);
    }
    public void removeDeleteNodeListener(DeleteNodeHandler handler){
        deleteNodeHandlers.remove(handler);
    }
    private void notifyDeleteNodeHandlers(VPoint<Double> node){
        for(var l : deleteNodeHandlers)
            l.timeToDeleteNode(node);
    }

    @Override
    public int compare(VPoint<Double> o1, VPoint<Double> o2) {
        if(o1.getX() > o2.getX())
            return 1;
        else if(o1.getX() < o2.getX())
            return -1;
        else
            return 0;
    }
}
