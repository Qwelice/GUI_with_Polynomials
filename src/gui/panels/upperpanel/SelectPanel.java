package gui.panels.upperpanel;

import javax.swing.*;

public class SelectPanel extends JPanel {
    public final JCheckBox withPoints;
    public final JCheckBox withGraph;
    public final JCheckBox withFullGraph;

    public SelectPanel(){
        withGraph = new JCheckBox("graphics");
        withPoints = new JCheckBox("points");
        withFullGraph = new JCheckBox("full graph");
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(withPoints, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(withGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(withFullGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
        );
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGap(5)
                .addComponent(withPoints, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(withGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(withFullGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
        );
    }
}
