package gui.graphics.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel {
    public ArrayList<Painter> painters = new ArrayList<>();

    public void addPainter(Painter p){
        painters.add(p);
    }
    public void removePainter(Painter p){
        painters.remove(p);
    }

    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        painters.forEach(painter -> {painter.draw(graphics);});
    }
}
