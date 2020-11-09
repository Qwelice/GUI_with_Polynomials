package gui.panels.mainpanel;
import gui.graphics.components.GraphicsPanel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class MainPanel extends JPanel {
    private static final Dimension MIN_PANEL_SIZE = new Dimension(400, 400);

    public GraphicsPanel graphicsPanel;
    public RightPanel rightPanel;

    public MainPanel(){
        graphicsPanel = new GraphicsPanel();
        rightPanel = new RightPanel();

        graphicsPanel.setBackground(Color.WHITE);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(new EtchedBorder());

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addComponent(graphicsPanel, MIN_PANEL_SIZE.width, MIN_PANEL_SIZE.width, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(graphicsPanel, MIN_PANEL_SIZE.height, MIN_PANEL_SIZE.height, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
        );
    }
}
