package gui.painters;

import convertation.ConvertPlane;
import convertation.Converter;
import gui.eventlisteners.derivativepainterevents.PolynomialHandler;
import gui.graphics.components.Painter;
import polynomials.Newton;
import polynomials.Polynomial;

import java.awt.*;
import java.util.ArrayList;

public class DerivativePainter extends Painter {
    private ArrayList<PolynomialHandler> polynomialHandlers = new ArrayList<>();

    private Newton newton = null;
    private Polynomial derivative = null;
    private ConvertPlane plane = null;
    private int parts = 100;
    private Color color;
    private boolean isDotsEmpty = false;

    public DerivativePainter(ConvertPlane plane){
        this.plane = plane;
    }

    public ConvertPlane getPlane(){
        return plane;
    }

    public void setColor(Color color){
        this.color = color;
    }
    public void setPolynomial(Newton p){
        newton = p;
        CalculateDer();
    }
    private void CalculateDer(){
        if(newton != null){
            ArrayList<Double> toDer = new ArrayList<>();
            if(newton.getSize() > 0){
                for(int i = 1; i < newton.getDeg()+1; i++) {
                    var t = newton.get(i) * i;
                    toDer.add(t);
                }
            }
            derivative = new Polynomial(toDer);
            if(derivative.getSize() > 0)
                notifyPolynomialHandlers(derivative.toString());
            else
                notifyPolynomialHandlers("None");
        }
    }
    public void setParts(int x){
        parts = x;
    }
    public void drawLines(Graphics graphics){
        if(graphics != null){
            if(color != null)
                graphics.setColor(color);
            else
                graphics.setColor(Color.RED);
            if(derivative != null && !isDotsEmpty){
                var x1 = plane.getXMin();
                var x2 = plane.getXMax();
                for(int i = (int)(x1 - 1); i < (int)(x2+1); i++){
                    var dx = 1. / parts;
                    double t1 = i;
                    double t2 = t1 + dx;
                    for(int j = 0; j < parts; j++){
                        var ty1 = derivative.substitute(t1);
                        var ty2 = derivative.substitute(t2);
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
        drawLines(graphics);
    }

    @Override
    public void update(int width, int height) {
        plane.setRealWidth(width);
        plane.setRealHeight(height);
    }

    public void addPolynomialListener(PolynomialHandler handler){
        polynomialHandlers.add(handler);
    }
    public void removePolynomialListener(PolynomialHandler handler){
        polynomialHandlers.remove(handler);
    }
    public void notifyPolynomialHandlers(String polynomial){
        for(var h : polynomialHandlers)
            h.timeToChangePolynomial(polynomial);
    }
    public void setEmpty(boolean empty){
        isDotsEmpty = empty;
    }
}
