package convertation;

public class ConvertPlane {
    private int realWidth;

    public int getWidth() {
        return realWidth - 1;
    }

    private int realHeight;

    public int getHeight() {
        return realHeight - 1;
    }

    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    public void setXMin(double xMin){
        this.xMin = xMin;
    }
    public void setXMax(double xMax){
        this.xMax = xMax;
    }
    public void setYMin(double yMin){
        this.yMin = yMin;
    }
    public void setYMax(double yMax){
        this.yMax = yMax;
    }
    public void setRealWidth(int RealWidth){
        this.realWidth = Math.abs(RealWidth);
    }
    public void setRealHeight(int RealHeight){
        this.realHeight = Math.abs(RealHeight);
    }

    public double getXMin() {
        return xMin;
    }

    public double getXMax() {
        return xMax;
    }

    public double getYMin() {
        return yMin;
    }

    public double getYMax() {
        return yMax;
    }

    public double getXDen() {
        return getWidth() / (xMax - xMin);
    }

    public double getYDen() {
        return getHeight() / (yMax - yMin);
    }

    public ConvertPlane(int realWidth, int realHeight, double xMin, double xMax,
                                double yMin, double yMax) {
        this.realWidth = realWidth;
        this.realHeight = realHeight;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }
}
