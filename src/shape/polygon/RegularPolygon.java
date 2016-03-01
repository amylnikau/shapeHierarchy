package shape.polygon;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RegularPolygon extends Polygon {

    private int sideNum;
    private boolean isRotating = false;

    public RegularPolygon() {

    }

    public RegularPolygon(Point theCenter, Point pointOnCircle, int sideNum) {
        setLocation(theCenter);
        setPoints(getPolygonPoints(theCenter, pointOnCircle, sideNum));
    }

    public RegularPolygon(Point theCenter, Point pointOnCircle, int sideNum, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
        this.sideNum = sideNum;
        setPoints(getPolygonPoints(theCenter, pointOnCircle, sideNum));
    }

    private List<Point> getPolygonPoints(Point theCenter, Point pointOnCircle, int sideNum) {
        List<Point> points = new ArrayList<>(sideNum + 1);
        double radius = Math.sqrt(Math.pow((pointOnCircle.x) - theCenter.x, 2) + Math.pow(pointOnCircle.y - theCenter.y, 2));
        double z;
        double angle = 360.0 / sideNum;
        if (isRotating) {
            z = Math.asin((theCenter.y - pointOnCircle.y) / radius) * 180 / Math.PI;
            if (pointOnCircle.x < theCenter.x)
                z = 180.0 - z;
        } else {
            if (sideNum % 2 != 0)
                z = 90;
            else
                z = 90 - angle / 2;
        }
        for (int i = 0; i < sideNum; i++) {
            points.add(new Point(theCenter.x + (int) (Math.cos(z / 180 * Math.PI) * radius),
                    theCenter.y - (int) (Math.sin(z / 180 * Math.PI) * radius)));
            z = z + angle;
        }
        return points;
    }

    public void setPointOnCircle(Point p) {
        setPoints(getPolygonPoints(getLocation(), p, sideNum));
    }

    public int getSideNum() {
        return sideNum;
    }

    public void setSideNum(int sideNum) {
        this.sideNum = sideNum;
    }

    public boolean isRotating() {
        return isRotating;
    }

    public void setRotating(boolean rotating) {
        isRotating = rotating;
    }
}