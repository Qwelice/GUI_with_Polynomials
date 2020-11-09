package gui.graphics.components;

import java.awt.*;

public abstract class Painter {
    public abstract void draw(Graphics graphics);
    public abstract void update(int width, int height);
}
