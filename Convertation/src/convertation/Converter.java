package convertation;

public class Converter {
    public static int xCrt2Scr(double x, ConvertPlane plane) {
        var px = plane.getXDen();
        return (int)(px * (x - plane.getXMin()));
    }

    public static double xScr2Crt(double x, ConvertPlane plane) {
        var px = plane.getXDen();
        return x * (1.0 / px) + plane.getXMin();
    }

    public static int yCrt2Scr(double y, ConvertPlane plane) {
        var py = plane.getYDen();
        return (int)(py * (plane.getYMax() - y));
    }

    public static double yScr2Crt(double y, ConvertPlane plane) {
        var py = plane.getYDen();
        return (-y) * (1.0 / py) + plane.getYMax();
    }
}
