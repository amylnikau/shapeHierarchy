package shape.polygon;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rhombus extends Parallelogram {

    public Rhombus() {

    }

    public Rhombus(Point theCenter, Point cornerPoint, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, cornerPoint, frameWidth, frameColor, fillColor);
    }

    @Override
    public List<Point> getParallelogramPoints(Point cornerPoint) {
        Point theCenter = getLocation();
        List<Point> points = new ArrayList<>(4);
        points.add(new Point(cornerPoint.x, theCenter.y));
        points.add(new Point(theCenter.x, 2*theCenter.y-cornerPoint.y));
        points.add(new Point(2*theCenter.x-cornerPoint.x, theCenter.y));
        points.add(new Point(theCenter.x, cornerPoint.y));
        return points;
    }
}