package gui.painters;

import convertation.ConvertPlane;
import convertation.Converter;
import gui.graphics.components.Painter;
import gui.visualobjects.VPoint;

import java.awt.*;
import java.util.LinkedList;

public class PointPainter extends Painter {

    private LinkedList<VPoint<Double>> points = new LinkedList<>();
    private VPoint<Integer> abstractPoint = new VPoint<>(0, 0);
    ConvertPlane plane = null;
    public ConvertPlane getPlane(){
        return plane;
    }

    private boolean withPoint = true;

    public PointPainter(ConvertPlane plane){
        this.plane = plane;
    }



    @Override
    public void draw(Graphics graphics) {
        Graphics2D g = (Graphics2D)graphics;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        if(withPoint)
            drawPoints(graphics);
    }

    public void addPoint(int x, int y){
        var dx = Converter.xScr2Crt(x, plane);
        var dy = Converter.yScr2Crt(y, plane);
        addPoint(dx, dy);
    }

    public void removePoint(double x, double y){
        for(int i = 0; i < points.size(); i++){
            if(points.get(i).contains(x, y)){
                points.remove(points.get(i));
                break;
            }
        }
    }

    public void removePoint(int x, int y){
        var dx = Converter.xScr2Crt(x, plane);
        var dy = Converter.yScr2Crt(y, plane);
        removePoint(dx, dy);
    }

    public void removeAllPoints(){
        points.clear();
    }

    public void addPoint(double x, double y){
        points.add(new VPoint<>(x, y).setRadius(4).setBorderColor(Color.GRAY).setPointColor(Color.RED));
    }

    public void drawPoints(Graphics graphics){
        if(graphics != null){
            Graphics2D g = (Graphics2D)graphics;
            for(var p : points){
                var x = Converter.xCrt2Scr(p.getX(), plane);
                var y = Converter.yCrt2Scr(p.getY(), plane);
                graphics.setColor(p.getPointColor());
                graphics.fillOval(x - p.getRadius(), y - p.getRadius(),
                        2 * p.getRadius(), 2 * p.getRadius());
                if(p.contains(Converter.xScr2Crt(abstractPoint.getX(), plane), Converter.yScr2Crt(abstractPoint.getY(), plane)))
                    p.setBorderColor(Color.CYAN);
                else
                    p.setBorderColor(Color.GRAY);
                g.setColor(p.getBorderColor());
                g.setStroke(new BasicStroke(2));
                g.drawOval(x - p.getRadius(), y - p.getRadius(),
                        2 * p.getRadius(), 2 * p.getRadius());
            }
        }
    }

    public boolean isPointExist(double x, double y){
        for(var p : points){
            if(p.contains(x, y))
                return true;
        }
        return false;
    }

    public boolean isPointExist(int x, int y){
        var dx = Converter.xScr2Crt(x, plane);
        var dy = Converter.yScr2Crt(y, plane);
        return isPointExist(dx, dy);
    }

    public void resetPoint(int x, int y){
        abstractPoint.setX(x);
        abstractPoint.setY(y);
    }

    public void resetPoint(double x, double y){
        var dx = Converter.xCrt2Scr(x, plane);
        var dy = Converter.yCrt2Scr(y, plane);
        resetPoint(dx, dy);
    }

    public void isWithPoint(boolean result){
        withPoint = result;
    }

    public int getPointsSize(){
        return points.size();
    }

    @Override
    public void update(int width, int height) {
        plane.setRealWidth(width);
        plane.setRealHeight(height);
    }
}
