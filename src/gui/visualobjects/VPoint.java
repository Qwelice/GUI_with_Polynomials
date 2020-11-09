package gui.visualobjects;

import java.awt.*;

public class VPoint<T1>{

    private T1 x;
    private T1 y;
    private Color pointColor;
    private Color borderColor;
    private int radius = 0;
    private double epsilon;
    private static final double EPSILON = 0.087;


    public VPoint(T1 x, T1 y) {
        this.x = x;
        this.y = y;
        setEpsilon();
    }

    public double distant(double x, double y){
        var dx = (double)this.x - x;
        var dy = (double)this.y - y;
        return Math.sqrt(Math.pow(dx, 2));
    }

    public double distant(VPoint other){
        var dx = (double)this.x - (double)other.x;
        var dy = (double)this.y - (double)this.y;
        return Math.sqrt(Math.pow(dx, 2));
    }

    private void setEpsilon(){
        epsilon = EPSILON;
    }

    public void setX(T1 x){
        this.x = x;
    }

    public void setY(T1 y){
        this.y = y;
    }

    public boolean contains(VPoint other){
        return distant(other) < epsilon;
    }

    public boolean contains(double x, double y){
        return distant(x, y) < epsilon;
    }

    public VPoint setRadius(int x) {
        radius = x;
        setEpsilon();
        return this;
    }

    public VPoint setPointColor(Color color) {
        pointColor = color;
        return this;
    }

    public VPoint setBorderColor(Color color){
        borderColor = color;
        return this;
    }

    public Color getPointColor(){
        return pointColor;
    }

    public Color getBorderColor(){
        return borderColor;
    }

    public int getRadius(){
        return radius;
    }

    public T1 getX() {
        return x;
    }

    public T1 getY() {
        return y;
    }

    @Override
    public boolean equals(Object other) {
        if (other.getClass() != this.getClass())
            return false;
        return ((VPoint) other).x == this.x && ((VPoint) other).y == y;
    }

    @Override
    public int hashCode() {
        return x.hashCode() + y.hashCode();
    }


}
