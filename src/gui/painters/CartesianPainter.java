package gui.painters;

import convertation.ConvertPlane;
import convertation.Converter;
import gui.graphics.components.*;

import java.awt.*;

public class CartesianPainter extends Painter {
    private ConvertPlane plane = null;

    public ConvertPlane getPlane(){
        return plane;
    }

    public CartesianPainter(ConvertPlane plane){
        this.plane = plane;
    }

    @Override
    public void draw(Graphics graphics) {
        drawAxes(graphics);
        drawTicks(graphics);
        drawNumbers(graphics);
    }

    public void drawAxes(Graphics graphics){
        if (graphics != null) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(Converter.xCrt2Scr(plane.getXMin(), plane), Converter.yCrt2Scr(0, plane),
                    Converter.xCrt2Scr(plane.getXMax(), plane), Converter.yCrt2Scr(0, plane));
            graphics.drawLine(Converter.xCrt2Scr(0, plane), Converter.yCrt2Scr(plane.getYMax(), plane),
                    Converter.xCrt2Scr(0, plane), Converter.yCrt2Scr(plane.getYMin(), plane));
        }
    }

    public void drawTicks(Graphics graphics){
        var x0 = Converter.xCrt2Scr(0., plane);
        var y0 = Converter.yCrt2Scr(0., plane);
        int df = 2;
        if (graphics != null) {
            var cond = (Math.abs(0 - plane.getXMin()) >= Math.abs(0 - plane.getXMax())) ? Math.abs((int) plane.getXMin()) :
                    Math.abs((int) plane.getXMax());
            if (((int) plane.getXMin() <= -cond || (int) plane.getXMax() >= cond) && cond >= 10) {
                graphics.setColor(Color.RED);
                var h = 2;
                for (long i = (int) (plane.getXMin() * 10); i < (int) (plane.getXMax() * 10); i++) {
                    if (i % cond == 0) {
                        graphics.drawLine(Converter.xCrt2Scr(i / 10., plane), y0 + df + h,
                                Converter.xCrt2Scr(i / 10., plane), y0 - df - h);
                    }
                }
            } else {
                for (long i = (int) (plane.getXMin() * 10); i < (int) (plane.getXMax() * 10); i++) {
                    var h = 2;
                    if (i % 10 == 0) {
                        graphics.setColor(Color.RED);
                    } else if (i % 5 == 0) {
                        h = 1;
                        graphics.setColor(Color.BLUE);
                    } else {
                        graphics.setColor(Color.DARK_GRAY);
                        h = 0;
                    }
                    graphics.drawLine(Converter.xCrt2Scr(i / 10., plane), y0 + df + h,
                            Converter.xCrt2Scr(i / 10., plane), y0 - df - h);
                }
            }
            cond = (Math.abs(0 - plane.getYMin()) >= Math.abs(0 - plane.getYMax())) ? Math.abs((int) plane.getYMin()) :
                    Math.abs((int) plane.getYMax());
            if (((int) plane.getYMin() <= -cond || (int) plane.getYMax() >= cond) && cond >= 10) {
                graphics.setColor(Color.RED);
                var h = 2;
                for (long i = (int) (plane.getYMin() * 10); i < (int) (plane.getYMax() * 10); i++) {
                    if (i % cond == 0) {
                        graphics.drawLine(x0 + df + h, Converter.yCrt2Scr(i / 10., plane),
                                x0 - df - h, Converter.yCrt2Scr(i / 10., plane));
                    }
                }
            } else {
                for (long i = (int) (plane.getYMin() * 10); i < (int) (plane.getYMax() * 10); i++) {
                    var h = 2;
                    if (i % 10 == 0) {
                        graphics.setColor(Color.RED);
                    } else if (i % 5 == 0) {
                        h = 1;
                        graphics.setColor(Color.BLUE);
                    } else {
                        graphics.setColor(Color.DARK_GRAY);
                        h = 0;
                    }
                    graphics.drawLine(x0 + df + h, Converter.yCrt2Scr(i / 10., plane),
                            x0 - df - h, Converter.yCrt2Scr(i / 10., plane));
                }
            }
        }
    }

    public void drawNumbers(Graphics graphics){
        if (graphics != null) {
            var x0 = Converter.xCrt2Scr(0., plane);
            var y0 = Converter.yCrt2Scr(0., plane);
            graphics.setColor(Color.MAGENTA);
            var cond = (Math.abs(0 - plane.getXMin()) >= Math.abs(0 - plane.getXMax())) ? Math.abs((int) plane.getXMin()) :
                    Math.abs((int) plane.getXMax());
            if (((int) plane.getXMin() <= -cond || (int) plane.getXMax() >= cond) && cond >= 10) {
                for (long i = (int) (plane.getXMin() * 10); i < (int) (plane.getXMax() * 10); i++) {
                    if (i % cond == 0 && i != 0) {
                        var h = -10;
                        if (i > 0)
                            h = 20;
                        var p = i > 0 ? -6 : -10;
                        graphics.drawString(String.valueOf(i / 10.), Converter.xCrt2Scr(i / 10., plane) + p, y0 + h);
                    }
                }
            } else {
                for (long i = (int) (plane.getXMin() * 10); i < (int) (plane.getXMax() * 10); i++) {
                    if (i == 0 || i % 5 != 0)
                        continue;
                    var h = -10;
                    if (i > 0)
                        h = 20;
                    var p = i > 0 ? -6 : -10;
                    graphics.drawString(String.valueOf(i / 10.), Converter.xCrt2Scr(i / 10., plane) + p, y0 + h);
                }
            }
            cond = (Math.abs(0 - plane.getYMin()) >= Math.abs(0 - plane.getYMax())) ? Math.abs((int) plane.getYMin()) :
                    Math.abs((int) plane.getYMax());
            if (((int) plane.getYMin() <= -cond || (int) plane.getYMax() >= cond) && cond >= 10) {
                for (long i = (int) (plane.getYMin() * 10); i < (int) (plane.getYMax() * 10); i++) {
                    if (i % cond == 0 && i != 0) {
                        var h = -30;
                        if (i > 0)
                            h = 10;
                        graphics.drawString(String.valueOf(i / 10.), x0 + h, Converter.yCrt2Scr(i / 10., plane) + 4);
                    }
                }
            } else {
                for (long i = (int) (plane.getYMin() * 10); i < (int) (plane.getYMax() * 10); i++) {
                    if (i == 0 || i % 5 != 0)
                        continue;
                    var h = -30;
                    if (i > 0)
                        h = 10;
                    graphics.drawString(String.valueOf(i / 10.), x0 + h, Converter.yCrt2Scr(i / 10., plane) + 4);
                }
            }
        }
    }

    @Override
    public void update(int width, int height) {
        plane.setRealWidth(width);
        plane.setRealHeight(height);
    }
}
