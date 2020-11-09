package gui.painters;

import convertation.ConvertPlane;
import convertation.Converter;
import gui.eventlisteners.derivativepainterevents.PolynomialSetHandler;
import gui.eventlisteners.functionpainterevents.PolynomialEventHandler;
import gui.graphics.components.Painter;
import gui.visualobjects.VPoint;
import polynomials.Newton;

import java.awt.*;
import java.util.*;

public class FunctionPainter extends Painter implements Comparator<VPoint<Double>>{

    private ArrayList<PolynomialEventHandler> polynomialEventHandlers = new ArrayList<>();
    private ArrayList<PolynomialSetHandler> polynomialSetHandlers = new ArrayList<>();

    private boolean withGraph = true;
    private boolean withFullGraph = true;

    private ConvertPlane plane = null;
    private LinkedList<VPoint<Double>> dots = new LinkedList<>();
    private int parts = 100; //рассчитать расстояние между узлами в пикселях
    private Newton newton = null;
    private Color color;

    public FunctionPainter(ConvertPlane plane){
        this.plane = plane;
    }

    public ConvertPlane getPlane(){
        return plane;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void addDot(double x, double y){
        dots.add(new VPoint<>(x, y));
        dots.sort(this);
        if(newton == null){
            LinkedHashMap<Double, Double> values = new LinkedHashMap<>();
            for(var d : dots)
                values.put(d.getX(), d.getY());
            newton = new Newton(values);
            notifyPolynomialSetHandlers(newton);
            notifyPolynomialHandlers(newton.toString());
        } else{
            newton.add(x, y);
            notifyPolynomialSetHandlers(newton);
            notifyPolynomialHandlers(newton.toString());
        }
    }

    public void removeDot(int x, int y){
        var dx = Converter.xScr2Crt(x, plane);
        var dy = Converter.yScr2Crt(y, plane);
        removeDot(dx, dy);
    }

    public void removeDot(double x, double y){
        for(int i = 0; i < dots.size(); i++){
            if(dots.get(i).contains(x, y)){
                LinkedHashMap<Double, Double> values = new LinkedHashMap<>();
                dots.remove(dots.get(i));
                dots.sort(this);
                for(var d : dots)
                    values.put(d.getX(), d.getY());
                newton = new Newton(values);
                notifyPolynomialSetHandlers(newton);
                if(dots.size() > 0)
                    notifyPolynomialHandlers(newton.toString());
                else
                    notifyPolynomialHandlers("None");
                break;
            }
        }
    }

    public void removeAllDots(){
        dots.clear();
        LinkedHashMap<Double, Double> p = new LinkedHashMap<>();
        newton = new Newton(p);
        notifyPolynomialSetHandlers(newton);
    }

    public void addDot(int x, int y){
        var dx = Converter.xScr2Crt(x, plane);
        var dy = Converter.yScr2Crt(y, plane);
        addDot(dx, dy);
    }

    public void setParts(int x){
        parts = x;
    }

    public void drawLines(Graphics graphics){
        if(graphics != null){
            graphics.setColor(Color.RED);
            Graphics2D g = (Graphics2D) graphics;
            g.setStroke(new BasicStroke(1));
            if(dots.size() == 2){
                var x1_ = plane.getXMax();
                var x2_ = plane.getXMin();
                var x1 = Converter.xCrt2Scr(x1_, plane);
                var y1 = Converter.yCrt2Scr(newton.substitute(x1_), plane);
                var x2 = Converter.xCrt2Scr(x2_, plane);
                var y2 = Converter.yCrt2Scr(newton.substitute(x2_), plane);
                graphics.drawLine(x2, y2, x1, y1);
            }
            else if(dots.size() == 1){
                var x1 = Converter.xCrt2Scr(plane.getXMin(), plane);
                var x2 = Converter.xCrt2Scr(plane.getXMax(), plane);
                var y = Converter.yCrt2Scr(dots.get(0).getY(), plane);
                g.drawLine(x1, y, x2, y);
            }
            else if(dots.size() >= 2){
                for(int i = 1; i < dots.size(); i++){
                    var dx = Math.abs(dots.get(i).getX() - dots.get(i-1).getX()) / parts;
                    var temp1 = dots.get(i - 1).getX();
                    var temp2 = dots.get(i - 1).getX() + dx;
                    for(int j = 0; j < parts; j++){
                        var x = Converter.xCrt2Scr(temp1, plane);
                        var y = Converter.yCrt2Scr(newton.substitute(temp1), plane);
                        var xx = Converter.xCrt2Scr(temp2, plane);
                        var yy = Converter.yCrt2Scr(newton.substitute(temp2), plane);
                        g.drawLine(x, y, xx, yy);
                        temp1 = temp2;
                        temp2 += dx;
                    }
                }
            }
        }
    }

    public void drawLinesFull(Graphics graphics){
        if(graphics != null){
            if(color != null)
                graphics.setColor(color);
            else
                graphics.setColor(Color.RED);
            Graphics2D g = (Graphics2D) graphics;
            g.setStroke(new BasicStroke(1));
            if(dots.size()==1){
                var x1 = Converter.xCrt2Scr(plane.getXMin(), plane);
                var x2 = Converter.xCrt2Scr(plane.getXMax(), plane);
                var y = Converter.yCrt2Scr(dots.get(0).getY(), plane);
                g.drawLine(x1, y, x2, y);
            }
            if(dots.size() >= 2){
                var x1 = plane.getXMin();
                var x2 = plane.getXMax();
                for(int i = (int)(x1 - 1); i < (int)(x2+1); i++){
                    var dx = 1. / parts;
                    double t1 = i;
                    double t2 = t1 + dx;
                    for(int j = 0; j < parts; j++){
                        var ty1 = newton.substitute(t1);
                        var ty2 = newton.substitute(t2);
                        graphics.drawLine(Converter.xCrt2Scr(t1, plane), Converter.yCrt2Scr(ty1, plane),
                                Converter.xCrt2Scr(t2, plane), Converter.yCrt2Scr(ty2, plane));
                        t1 = t2;
                        t2 += dx;
                    }
                }
            }
        }
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D g = (Graphics2D)graphics;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        if(withFullGraph)
            drawLinesFull(graphics);
        else if(withGraph)
            drawLines(graphics);
    }

    @Override
    public void update(int width, int height) {
        plane.setRealWidth(width);
        plane.setRealHeight(height);
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

    public void addPolynomialListener(PolynomialEventHandler handler){
        polynomialEventHandlers.add(handler);
    }
    public void removePolynomialListener(PolynomialEventHandler handler){
        polynomialEventHandlers.remove(handler);
    }
    private void notifyPolynomialHandlers(String polynomial){
        for(var l : polynomialEventHandlers)
            l.timeToChangePolynomial(polynomial);
    }

    public void isWithGraphics(boolean nonFull){
        withGraph = nonFull;
    }
    public void isWithFullGraphics(boolean full){
        withFullGraph = full;
    }
    public void addPolynomialSetListener(PolynomialSetHandler handler){
        polynomialSetHandlers.add(handler);
    }
    public void removePolynomialSetListener(PolynomialSetHandler handler){
        polynomialSetHandlers.remove(handler);
    }
    private void notifyPolynomialSetHandlers(Newton n){
        if(n!=null){
            for(var h : polynomialSetHandlers)
                h.timeToSetPolynomial(n);
        }
    }
}
